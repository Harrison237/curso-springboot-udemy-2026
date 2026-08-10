20-07-2026 (reemplazo de clase 19-07-2026)

Aspect @Around
Ordenación de aspectos mediante @Order
Un aspecto de orden superior envuelve a un aspecto de orden inferior. Se ejecuta de la siguiente forma:
    - Aspecto 1: Before
    - Aspecto 2: Before
    - Aspecto 2: After
    - Aspecto 1: After
Separación de Pointcuts en clase aparte
    - Si el Pointcut hace parte del mismo paquete del aspecto se llama con <clase>.<metodo>. Si es parte de otro paquete se debe llamar con <ruta.al.paquete>.<clase>.<metodo>

Cierre de sección 8.

20-07-2026

Inicio de sección 9 - Hibernate ORM y JPA

Creación de proyecto para sección de jpa
Creación y configuración de base de datos para sección
Creación de CrudRepository con acciones básicas para tabla person
Configuración de properties básicas en application.properties y habilitación de creación de estructura automática mediante hibernate
Población de datos en tabla person con datos de la clase 123

Veo varias similitudes con TypeORM, por lo que no he encontrado mayor dificultad hasta el momento.

21-07-2026

Verificación de población en base de datos y cambio en parámetro de properties para solo aplicar updates y que no se cree la bd desde cero cada que se inicia la aplicación
Creación de consultas personalizadas con @Query y con los nombres reservados dentro del PersonRepository

- Hay que estudiar los nombres reservados para las consultas mediante los nombres de los métodos en la interfaz, esto en Hibernate que es el ORM.

22-07-2026

Recuperación de un único objeto mediante consultas por Id
Consultas para recuperación de un único objeto
Uso de Optional para verificar que el objeto que llega desde el Repository existe
Uso de referencia de nombre dentro del repositorio para consultas personalizadas
Recuperación parcial de un objeto mediante seleccioń en @Query

Sigo pensando que es bastante similar a TypeORM, las querys por nombre de método son bastante potentes y un buen reemplazo al QueryBuilder de TypeORM.
Igual hay que ver cómo se consiguen objetos mediante joins

Leer la siguiente entrada de spring data jpa para referencia consultas por nombre de método
https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

23-07-2026

Uso de anotación @Transactional y con Readonly = true

25-07-2026

Métodos para actualizar y eliminar registros
Se pueden eliminar registros mediante Id o mediante el objeto completo. Al eliminar mediante el Id, JPA se encarga de verificar que exista previamente.
Recuperación de datos específicos e instanciación mediante constructores que tengan campos concretos con "new" directo en la consulta
Se puede instanciar de igual forma objetos DTO, pero es importante referenciar específicamente dónde está el paquete que contiene al DTO, ya que al no ser parte
    del contexto de persistencia de Hibernate no se pueden encontrar de otra manera.
Se puede concatenar en JPQL mediante el uso de ||, además de la función CONCAT de SQL.
Uso de distintos operadores de SQL como CONCAT, BETWEEN, ORDER BY, LOWER, UPPER, DISTINCT, tanto mediante consulta personalizada como por nombre de método.

- Temas interesantes, todo en orden.

26-07-2026

Uso de operadores MIN, MAX, LENGTH, AVG, SUM, COUNT e IN
Uso de subconsultas
Uso de clases embebidas como "subEntities" dentro de las entidades principales
Ciclos de vida de una entity, en específico @PrePersist y @PreUpdate

- Creo que lo más interesante de estas clases fueron las clases embebidas, esto es algo que no había visto antes y me parece bastante útil, aunque
  no estoy seguro de qué tanto servirá en un proyecto a gran escala, donde la cantidad de clases y paquetes puede ser muchísmimo mayor.
- Hay que averiguar más acerca de los ciclos de vida de las clases Entity, aunque igualmente se ven muy parecidos a los de TypeORM.
- Me encontré un problema al utilizar la inmutabilidad en el curso, lo que en el vídeo fue usar un simple "set", en mi caso se convirtió en la creación
  de un par de constructores extra y la instanciación de una clase Audit completamente nueva, por lo que no estoy seguro si este, siendo el proceso correcto
  tenga que ser tan extenso.

Buscar sobre
Lazy Loading.
N+1.
Fetch Join.
Entity Graph.
Dirty Checking.
Cascading.
Persistence Context.
Flush.
Locking.
Optimistic Lock.
Second Level Cache.

Cierre de seccioń 10.

27-07-2026

Explicación de relaciones dentro de SpringData, OneToMany y ManyToOne
Explicación de nombramiento de columnas automáticas para relaciones.
Cómo nombrar explícitamente una columna de relación en caso de que el default no se ajuste a lo requerido.
Explicación breve sobre el uso de Cascade en relaciones.

- Todas las anotaciones y su uso siguen siendo muy parecidas a TypeORM.

30-07-2026

Al utilizar la relación OneToMany sin especificar una @JoinColumn en la tabla padre, se crea automáticamente una tabla intermedia
  que guarda la relación (sí, tabla intermedia aunque no sea una relación ManyToMany).
La tabla que se crea puede ser personalizada con nombre de la tabla y nombres de las columnas de relacioń. En una tabla intermedia de
  OneToMany, el nombre de la columna que referencia a la tabla hija tiene que ser un constraint (se agrega mediante @UniqueConstraints).
  En caso de ser ManyToMany, ambas columnas de relación deben ser constraints.
Gracias a CascadeType.ALL, al guardar un objeto de tabla padre, si tiene objetos de tabla hija, entonces los objetos de la tabla hija
  también se persisten en la base de datos.
Para borrar un objeto de la tabla hija mediante referencia a la tabla padre, es importante tener el @Override al método equals en el módelo
  de la tabla hija.
Se debe tener cuidado con el lazy load al momento de intentar eliminar un objeto de tabla hija mediante referencia a la
  instancia de la tabla padre.

01-08-2026

Relaciones SQL bidireccionales representadas en Entities de JPA.
Para evitar el uso del parámetro en el application.properties de carga lazy, se pueden utilizar consultas personalizadas en el repository
  en cuestión, de forma que se hagan joins para obtener las colecciones necesarias.
Es importante que, si se va a solicitar más de una colección en una sola consulta, las instancias en los entities sean de tipo Set y no List,
  ya que el Set se encuentra mejor optimizado y el uso de List da error.
Se debe prestar atención a las referencias circulares en los toString de los entities.
Los toString de los entities llaman a la base de datos y puede haber problemas con llamados múltiples si la sesión se cierra.

- Hay que averiguar más sobre el uso de los tipos de CASCADE y las sesiones de JPA.

02-08-2026

Relaciones OneToOne y ManyToMany
Configuración de tabla intermedia en ManyToMany

Las relaciones bidireccionales siguen siendo un problema en inmutabilidad al requerir el uso de Setters posterior a la creacioń de los objetos,
  nada que no se pueda solucionar con lógica y sin referenciar mutuamente a las clases (que tampoco lo veo muy necesario).

03-08-2026

Diferentes métodos en relación ManyToMany, buscar por base de datos, eliminar y relación bidireccional entre tablas.
Inicio de proyecto para sección 12.

- Cierre de sección 11

05-08-2026

Métodos básicos para el crud en el ProductController
Validación de productos no encontrados en los métodos de update y delete en el service
Uso de programación funcional para los retornos tanto del service como del controller
Uso de validaciones en clases para asegurar que los campos del objeto recibido cumplan con ciertas condiciones

- Preferí utilizar programación funcional en lugar de seguir el curso ya que es más compacta y se puede entender con facilidad
  (*Hay que tener cuidado con complicar demasiado el código)
- La validación de objetos es muy parecida al class-validator que usaba en NestJS
- Me surgió la duda, si la validación de objetos puede realizarse con distintas herramientas, esta no debería estar tan acoplada a la
  clase/objeto en cuestión, hay alguna forma de desacoplarla? O al escoger validador hay que utilizar esa herramienta de inicio a fin?

06-08-2026

Creación y uso de validaciones personalizadas
Creación y uso de validaciones personalizadas mediante clases que implementan la interfaz "Validator" de springboot
Creación y uso de validaciones personalizadas mediante anotaciones
Validaciones personalizadas buscando en la base de datos
Mensajes de error personalizados utilizando archivos .properties

- Cierre sección 12

07-08-2026

Inicio de sección, creación y mapeo de nuevas tablas Role y User con tabla intermedia para relación ManyToMany
Revisión básica de protección de rutas mediante SecurityFilterChain, aún sin entrar al tema de JWT (protección de rutas públicas)
Especificidad entre métodos POST, GET, etc para rutas públicas
TroubleShotting de problemas presentados por inmutabilidad en campos booleanos, tanto en entidad como en controlador
Declaración de ruta pública que hace que el valor de "admin" se fuerce a "false" para la creación de un usuario
Declaración de Bean para PasswordEncoder (Uso de Bcrypt, clásico)
Exclusión de campos sensibles en la respuesta JSON de la lista de usuarios mediante anotación JsonProperty

- Tuve varios problemas al seguir el patrón de inmutabilidad, sobre todo con los campos boolean, ya que lo que es un Setter se vuelve
  un constructor entero.
- Hay que tener cuidado con los booleanos al enviar los valores a la base de datos, ya que por defecto no pueden ser null.
- La parte de excluir campos en el JSON de respuesta es bastante interesante.

08-08-2026 (Parte 1)

Agregada dependencia JWT al proyecto para validación
Creación de clase TokenJwtConfig para configuración de valores estáticos
Implementación de interfaz UserDetailsService para validación de usuario que intenta iniciar sesión mediante
  programación funcional
Traducción de roles del usuario hacia instancia de SimpleGrantedAuthority para posterior seteo de claims en token
La interfaz UserDetailsService en el método loadUserByUsername espera que se retorne una instancia de User
  del paquete de springFramework, sin embargo, como en el proyecto local ya se tiene una clase llamada User, entonces
  hay que referenciar a la clase directo del paquete para poder interactuar correctamente
Se retiran @Autowired de constructores al no ser necesarios, la DI ya se maneja directo por spring al ser componentes o Beans
Se agregan filtros a la clase de SpringSecurity para autenticación mediante Jwt y validación del Jwt
Para el filtro de autenticación, se implementa la interfaz UsernamePasswordAuthenticationFilter
Para el uso del user en el filtro de autenticación en el método successfulAuthentication, el user que llega desde el authResult.getPrincipal
  también es una instancia de User de springFramework (no la clase local)
Se realiza la creación del Jwt para validación de sesiones
En este caso, el SECRET_KEY utilizado para generar el Jwt es dinámico, por lo que se requiere uno nuevo cada vez que se inicia la aplicación

- Tuve un problema con la validación de IsExistsDbValidation al momento de instanciar el service y el repository, la mejor solución
  fue mover la lógica al service, a lo mejor algo acoplado a la implementación, pero sigue siendo lógica de negocio después de todo
- La implementación de Jwt fue extensa, aunque ya la conocía de NestJs, Springboot cuenta con más restricciones aunque esto da una
  implementación más robusta de las validaciones
- Me interesaría aprender sobre manejo de tokens vencidos a nivel de base de datos, aunque esto es un anti-patron ya que la validación
  de los Jwt debería estar desacoplada de la persistencia (a lo mejor se puede hacer en caché también)
- Qué pasa cuando se trabaja en un sistema distribuido? Acaso cada microservicio debe tener su propia implementación de Jwt?
- Investigar sobre RBAC vs permissions vs ABAC
- Investigar JWT + symmetric vs asymmetric signing

08-08-2026 (Parte 2)

Validación de uso de rutas y métodos según roles
Uso de anotación PreAuthorize directamente en métodos de controllers para validar el rol al intentar utilizar las rutas
Uso de requestMatchers en filterChain para validación de rol en uso de rutas
Configuración de cors para acceso a recursos desde frontend

- La validación de roles en rutas me pareció interesante, pero parece algo engorroso al momento de tener que declararlo a nivel de ruta método.
  Además qué pasa cuando se tienen permisos además de roles? Se puede hacer de forma masiva en lugar de tener que configurar ruta por ruta?
- Misma pregunta que sobre Jwt, cómo se configura esta restricción sobre rutas en un sistema distribuido por microservicios?

- Cierre de sección 13

09-08-2026 (Parte 1)

La sección 15 (ya que la sección 14 fue acerca de descarga de recursos) trató sobre el despliegue de la aplicación en una infraestructura serverless en AWS.
Uno de los recursos a utilizar fue una instancia de RDS, como ya tengo experiencia en el trabajo de que estas instancias son costosas, aún cuando no se están
utilizando (xd) decidí utilizar una herramienta llamada "floci" (mejor que localstack) para simular la infraestructura necesaria de AWS. A continuación describo lo más
importante del despliegue.

- Setup de entorno "Serverless": Como me gusta complicarme la vida, decidí que montaría floci en un computador aparte, mi laptop personal con Ubuntu 26.04, de forma que se pueda emular correctamente el acceso a recursos externos en lugar de poder ingresar a todo mediante "localhost".
  Toqué un poco de docker compose, nunca lo había utilizado pero no va más allá de montar distintos contenedores con un solo archivo .yml
  Dato importante, hay que compartir la red por el host y puerto "0.0.0.0:4566" hacia el puerto del contenedor 4566, ya que se requiere exponer todo el tráfico en la red local para poder hacer las configuraciones desde mi máquina. Esto no es una práctica recomendada en un entorno de producción, pero bajo mi red todo está protegido.
  Monté también una instancia de floci-ui, dato interesante, para configurar la url hacia el servicio de floci se tiene que referenciar al nombre del servicio dentro del docker-compose.yml en lugar de una dirección IP directa.
  Lo demás fueron configuraciones generales, como el nivel de log o el volumen para que no se borre la información al hacer docker compose down

- Instancia RDS: La creación de esta instancia fue relativamente sencilla. Lo interesante de esta herramienta es que obliga a utilizar comandos de la aws cli al no tener acceso a la consola de AWS, por lo que fuerza de cierta manera a aprender los comandos más importantes relacionados a los servicios. Una de las cosas más importantes sobre este servicio es que al invocarlo en la AWS CLI se tiene que utilizar siempre el --endpoint-url, ya que la herramienta lo sobreescribe e intenta irse a un endpoint real de AWS, cosa que causó varios problemas.
  Lo segundo y más importante, es que el contenedor que monta floci para la RDS no expone el puerto para la conexión a la base de datos directamente, sino que lo deja encerrado en un puerto (en este caso fue el 33060) de la red interna de docker que crea floci por defecto (red floci_default). Para poder exponer el puerto se tuvo que crear un servicio extra en el docker compose, un contenedor con la imagen alpine/socat que se encargara de tomar el tráfico del contenedor creado por floci en el puerto 3306 interno y exponerlo hacia un puerto personalizado del host, en este caso se escogió el 13306 (para referencia completa, revisar el docker-compose.yml)
  Importante** En caso de tener más instancias de RDS, cada una requeriría de su propio servicio alpine/socat para redirigir el tráfico de la red interna de docker hacia algún puerto del host

- Instancia EC2: El curso montaba los recursos hacia una instancia de EC2, por lo que quise hacer lo mismo de manera local. En este caso, la aws cli no requiere especificar el --endpoint-url, ya que no lo sobreescribe cómo sí lo hacían los comandos relacionados a rds. Las AMIS que provee floci son simples imágenes del repositorio público de AWS, lo que trae algunas diferencias respecto al aprovisionamiento que hace AWS.
  Problema principal: La instancia EC2 de floci no aprovisiona ssh por defecto
    Al tomar una simple imagen del repositorio público de AWS, la imagen no viene realmente configurada para temas que AWS da por defecto como la conexión por SSH, por lo que esto se tuvo que configurar manualmente. Me di cuenta ya que el comando aws ec2 create-key-pair no crea un archivo .pem válido, sino que crea uno fake que no permite una correcta conexión mediante ssh.
    Sumado a esto, en el contenedor que crea floci para simular la instancia tampoco viene instalado openssh-server por defecto, de manera que no había ninguna forma de conectarse a ssh. Esto es algo esperable de un emulador de servicios de AWS, y son la clase de detalles que se deben gestionar manualmente. En este caso, instalé openssh-server en el contendor manualmente, y agregué la llave creada manualmente en mi equipo hacia el archivo "/root/.ssh/authorized_keys" para poder simular correctamente el comportamiento de ssh hacia una instancia de EC2. Por defecto, floci expone el puerto 2200 para conectarse hacia la instancia, hay que ver qué otros puertos expone a medida que se crean más instancias.
    Para conectar utilizaba el siguiente comando 'ssh -i floci-ec2-key -p 2200 root@192.168.1.10', para próximas pruebas sería bueno configurar el usuario ec2-user en el contenedor, ya que este es el usuario por defecto que crea AWS en sus instancias para conexiones ssh, en lugar del root que finalmente quedó.
    Por lo demás, instalación de java jdk 17, copiar el archivo .jar generado mediante scp y levantar mediante java -jar
    Importante** El contenedor que simula la instancia se crea en la misma red de docker (floci_default) que la instancia de RDS, por lo que se puede conectar directamente mediante la ip interna del contenedor tal como se haría en una red privada de VPC.
    Al momento de crear reglas de entrada en el security-group de la instancia, lo que hace floci es generar un contenedor con la imagen alpine/socat para exponer el puerto declarado hacia un puerto del host (en mi caso, configuré el tráfico para que pudiera entrar por el puerto 8080 a la instancia, y floci lo expuso hacia el puerto 30000 del host, aplicando la misma lógica manual que se utilizó para exponer el tráfico de la instancia de RDS). Por eso el application.properties quedó apuntando hacia una base de datos en 172.18.0.5:3306, por esta configuración se tuvieron que saltar los tests de maven (desde el equipo local no hay conexión hacia esa dirección IP)

- Quiero hacer este mismo ejercicio mediante un aprovisionamiento con AWS CDK, montando un apigateway que redirija hacia una lambda y de ahí se vaya a un task montado en ECS (tal como se hace en el trabajo actualmente)

- Cierre de sección 15

09-08-2026 (Parte 2)

Despliegue de aplicativo en servidor tomcat 11 con compilación war (para volver a compilación jar hay que revertir este commit)
La ruta base de la api se vuelve "crud-jpa/**" al ser tomcat un servidor para web

- Cierre de sección 16

10-08-2026 (Parte 1)

Creación y configuracioń de proyecto de React

- Toda la seccioń 17 es sobre la creación de una aplicación react para comunicarse con un backend de spring boot, luego me di cuenta que el nombre del curso es "Construye aplicaciones web con SpringBoot, Thymeleaf, React, Angular". No le estoy poniendo mucha atención, lo tengo al 1.25x y solo voy replicando el código, ya que sinceramente no me interesa aprender react, pero lo necesito para avanzar, así que X.
