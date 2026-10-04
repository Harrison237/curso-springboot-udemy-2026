export DB_PASSWORD=$(aws secretsmanager get-secret-value \
	--secret-id springboot-course/db-password \
	--query SecretString \
	--output text | jq -r .password)

SAVE_TEMPLATES="$1"
DONT_DEPLOY="$2"
ALLOWED_PUBLIC_IP="192.168.1.3/32"
PRIVATE_CIDR_IP_A="172.31.200.0/24"
PRIVATE_CIDR_IP_B="172.31.201.0/24"
DEPLOY_PARAMETERS="--parameters BaseResourcesStack:DevelopmentClientCidr='${ALLOWED_PUBLIC_IP}' --parameters BaseResourcesStack:PrivateSubnetACidr='${PRIVATE_CIDR_IP_A}' --parameters BaseResourcesStack:PrivateSubnetBCidr='${PRIVATE_CIDR_IP_B}'"
NAME_PATTERN="springboot-course-bastion-instance"

if [[ -z "${SAVE_TEMPLATES}" || "${SAVE_TEMPLATES}" != "true" ]]; then
	SAVE_TEMPLATES="false"
fi

mvn clean package
PACKAGE_STATUS=$?

if [ "$PACKAGE_STATUS" -gt 0 ]; then
	echo "Error al realizar el mvn package"
	exit $PACKAGE_STATUS
fi

exec_cdk_synth() {
	CDK_STACK="$1"
	STATUS=0

	if [[ "$SAVE_TEMPLATES" == "true" ]]; then
		mkdir -p cdk-templates
		cdk synth "$CDK_STACK" ${DEPLOY_PARAMETERS} \
			>"cdk-templates/template-${CDK_STACK}.yaml"
		STATUS=$?
	else
		cdk synth "$CDK_STACK" ${DEPLOY_PARAMETERS}
		STATUS=$?
	fi

	if [ $STATUS -gt 0 ]; then
		echo "CDK Synth falló"
		exit 1
	fi
}

exec_cdk_deploy() {
	CDK_STACK="$1"
	EXEC_COMMAND="cdk deploy ${CDK_STACK} --no-rollback ${DEPLOY_PARAMETERS}"

	if ! $EXEC_COMMAND; then
		echo "CDK Deploy falló"
		exit 1
	fi
}
#  "SecretsStack" 
CDK_DEPLOY_STACKS=("BaseResourcesStack" "ECSServiceStack" "IntegrationTestStack")

for STACK in "${CDK_DEPLOY_STACKS[@]}"; do
	echo "Desplegando $STACK"
	exec_cdk_synth $STACK

	if [[ ! "$DONT_DEPLOY" == "true" ]]; then
		if [ $STACK = "BaseResourcesStack" ]; then
			INSTANCE_IDS=$(aws ec2 describe-instances \
				--filters \
				"Name=tag:Name,Values=$NAME_PATTERN" \
				"Name=instance-state-name,Values=running" \
				--query 'Reservations[].Instances[].InstanceId' \
				--output text)

			if [[ -z "$INSTANCE_IDS" ]]; then
				echo "No hay instancias running con Name=$NAME_PATTERN"
			else
				echo "Deteniendo instancias: $INSTANCE_IDS"

				aws ec2 stop-instances \
					--instance-ids $INSTANCE_IDS
			fi
		fi

		exec_cdk_deploy $STACK
	fi
done
