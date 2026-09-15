# 📚 Notas del Curso de Spring Boot (Udemy 2026)

> Notas personales tomadas a lo largo del curso.
> Cada sección incluye los **temas vistos**, mis **observaciones** y las **preguntas que dejé para investigar**.
>
> _Nota: el curso resultó siendo más básico de lo esperado, por eso muchas entradas cruzan conceptos con TypeORM / NestJS (que ya conocía) y dejan preguntas abiertas para abordar más adelante._

---

## 🗂️ Índice

- [Sección 8 — AOP (Programación Orientada a Aspectos)](#sección-8--aop-programación-orientada-a-aspectos)
- [Sección 9 — Hibernate ORM y JPA (introducción)](#sección-9--hibernate-orm-y-jpa-introducción)
- [Sección 10 — Consultas con Spring Data JPA](#sección-10--consultas-con-spring-data-jpa)
- [Sección 11 — Relaciones entre entidades](#sección-11--relaciones-entre-entidades)
- [Sección 12 — CRUD y validaciones](#sección-12--crud-y-validaciones)
- [Sección 13 — Spring Security y JWT](#sección-13--spring-security-y-jwt)
- [Sección 14 — Descarga de recursos](#sección-14--descarga-de-recursos)
- [Sección 15 — Despliegue serverless en AWS (con floci)](#sección-15--despliegue-serverless-en-aws-con-floci)
- [Sección 16 — Despliegue en Tomcat (WAR)](#sección-16--despliegue-en-tomcat-war)
- [Secciones 17–21 — Frontend (React y Angular)](#secciones-1721--frontend-react-y-angular)
- [🎯 Cuadro de prioridades — temas y preguntas a abordar](#-cuadro-de-prioridades--temas-y-preguntas-a-abordar)

---

## Sección 8 — AOP (Programación Orientada a Aspectos)

### 📅 20-07-2026 (reemplazo de clase 19-07-2026)

**Temas vistos**

- `@Around`
- Ordenación de aspectos mediante `@Order`.
  - Un aspecto de orden superior envuelve a un aspecto de orden inferior. Se ejecuta así:
    1. Aspecto 1: `Before`
    2. Aspecto 2: `Before`
    3. Aspecto 2: `After`
    4. Aspecto 1: `After`
- Separación de _Pointcuts_ en una clase aparte.
  - Si el _Pointcut_ pertenece al mismo paquete del aspecto, se llama con `<clase>.<metodo>`.
  - Si pertenece a otro paquete, se debe llamar con `<ruta.al.paquete>.<clase>.<metodo>`.

> ✅ **Cierre de sección 8.**

---

## Sección 9 — Hibernate ORM y JPA (introducción)

### 📅 20-07-2026

**Temas vistos**

- Inicio de sección 9 — Hibernate ORM y JPA.
- Creación del proyecto para la sección de JPA.
- Creación y configuración de la base de datos para la sección.
- Creación de un `CrudRepository` con acciones básicas para la tabla `person`.
- Configuración de propiedades básicas en `application.properties` y habilitación de la creación automática de la estructura mediante Hibernate.
- Población de datos en la tabla `person` con los datos de la clase 123.

**Observaciones**

- Veo varias similitudes con TypeORM, por lo que no he encontrado mayor dificultad hasta el momento.

---

## Sección 10 — Consultas con Spring Data JPA

### 📅 21-07-2026

**Temas vistos**

- Verificación de la población en la base de datos y cambio en el parámetro de `properties` para solo aplicar _updates_, de forma que no se cree la BD desde cero cada vez que se inicia la aplicación.
- Creación de consultas personalizadas con `@Query` y con los nombres reservados dentro de `PersonRepository`.

**🔍 Para investigar**

- Hay que estudiar los nombres reservados para las consultas mediante los nombres de los métodos en la interfaz (esto en Hibernate, que es el ORM).

---

### 📅 22-07-2026

**Temas vistos**

- Recuperación de un único objeto mediante consultas por `Id`.
- Consultas para recuperación de un único objeto.
- Uso de `Optional` para verificar que el objeto que llega desde el `Repository` existe.
- Uso de referencia de nombre dentro del repositorio para consultas personalizadas.
- Recuperación parcial de un objeto mediante selección en `@Query`.

**Observaciones**

- Sigo pensando que es bastante similar a TypeORM; las _querys_ por nombre de método son bastante potentes y un buen reemplazo al `QueryBuilder` de TypeORM.
- Igual hay que ver cómo se consiguen objetos mediante _joins_.

**Referencia**

- Leer la siguiente entrada de Spring Data JPA sobre consultas por nombre de método:
  https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

---

### 📅 23-07-2026

**Temas vistos**

- Uso de la anotación `@Transactional` con `readOnly = true`.

---

### 📅 25-07-2026

**Temas vistos**

- Métodos para actualizar y eliminar registros.
  - Se pueden eliminar registros mediante `Id` o mediante el objeto completo. Al eliminar mediante el `Id`, JPA se encarga de verificar que exista previamente.
- Recuperación de datos específicos e instanciación mediante constructores que tengan campos concretos, con `new` directo en la consulta.
  - De igual forma se pueden instanciar objetos DTO, pero es importante referenciar específicamente dónde está el paquete que contiene al DTO, ya que al no ser parte del contexto de persistencia de Hibernate no se pueden encontrar de otra manera.
- Se puede concatenar en JPQL mediante el uso de `||`, además de la función `CONCAT` de SQL.
- Uso de distintos operadores de SQL como `CONCAT`, `BETWEEN`, `ORDER BY`, `LOWER`, `UPPER`, `DISTINCT`, tanto mediante consulta personalizada como por nombre de método.

**Observaciones**

- Temas interesantes, todo en orden.

---

### 📅 26-07-2026

**Temas vistos**

- Uso de operadores `MIN`, `MAX`, `LENGTH`, `AVG`, `SUM`, `COUNT` e `IN`.
- Uso de subconsultas.
- Uso de clases embebidas como _subEntities_ dentro de las entidades principales.
- Ciclos de vida de una _entity_, en específico `@PrePersist` y `@PreUpdate`.

**Observaciones**

- Lo más interesante de estas clases fueron las **clases embebidas**; es algo que no había visto antes y me parece bastante útil, aunque no estoy seguro de qué tan útil será en un proyecto a gran escala, donde la cantidad de clases y paquetes puede ser muchísimo mayor.
- Hay que averiguar más acerca de los **ciclos de vida de las _entities_**, aunque se ven muy parecidos a los de TypeORM.
- ⚠️ Me encontré un problema al utilizar la **inmutabilidad** en el curso: lo que en el vídeo era usar un simple `set`, en mi caso se convirtió en la creación de un par de constructores extra y la instanciación de una clase `Audit` completamente nueva. No estoy seguro de si, siendo este el proceso correcto, tenga que ser tan extenso.

**🔍 Para investigar**

- Lazy Loading.
- N+1.
- Fetch Join.
- Entity Graph.
- Dirty Checking.
- Cascading.
- Persistence Context.
- Flush.
- Locking.
- Optimistic Lock.
- Second Level Cache.

> ✅ **Cierre de sección 10.**

---

## Sección 11 — Relaciones entre entidades

### 📅 27-07-2026

**Temas vistos**

- Explicación de relaciones dentro de Spring Data: `OneToMany` y `ManyToOne`.
- Nombramiento automático de columnas para relaciones.
- Cómo nombrar explícitamente una columna de relación en caso de que el _default_ no se ajuste a lo requerido.
- Explicación breve sobre el uso de `Cascade` en relaciones.

**Observaciones**

- Todas las anotaciones y su uso siguen siendo muy parecidas a TypeORM.

---

### 📅 30-07-2026

**Temas vistos**

- Al utilizar la relación `OneToMany` sin especificar un `@JoinColumn` en la tabla padre, se crea automáticamente una **tabla intermedia** que guarda la relación (sí, tabla intermedia aunque no sea una relación `ManyToMany`).
  - La tabla creada puede personalizarse con nombre de la tabla y nombres de las columnas de relación.
  - En una tabla intermedia de `OneToMany`, el nombre de la columna que referencia a la tabla hija tiene que ser un _constraint_ (se agrega mediante `@UniqueConstraints`).
  - En caso de ser `ManyToMany`, ambas columnas de relación deben ser _constraints_.
- Gracias a `CascadeType.ALL`, al guardar un objeto de la tabla padre, si tiene objetos de tabla hija, entonces los objetos de la tabla hija también se persisten en la base de datos.
- Para borrar un objeto de la tabla hija mediante referencia a la tabla padre, es importante tener el `@Override` del método `equals` en el modelo de la tabla hija.
- ⚠️ Se debe tener cuidado con el _lazy load_ al momento de intentar eliminar un objeto de tabla hija mediante referencia a la instancia de la tabla padre.

---

### 📅 01-08-2026

**Temas vistos**

- Relaciones SQL bidireccionales representadas en _entities_ de JPA.
- Para evitar el uso del parámetro de carga _lazy_ en `application.properties`, se pueden utilizar consultas personalizadas en el repositorio correspondiente, de forma que se hagan _joins_ para obtener las colecciones necesarias.
- ⚠️ Si se va a solicitar más de una colección en una sola consulta, las instancias en los _entities_ deben ser de tipo `Set` y no `List`, ya que el `Set` se encuentra mejor optimizado y el uso de `List` da error.
- ⚠️ Se debe prestar atención a las **referencias circulares** en los `toString` de los _entities_.
  - Los `toString` de los _entities_ llaman a la base de datos y puede haber problemas con llamados múltiples si la sesión se cierra.

**🔍 Para investigar**

- Hay que averiguar más sobre el uso de los tipos de `CASCADE` y las sesiones de JPA.

---

### 📅 02-08-2026

**Temas vistos**

- Relaciones `OneToOne` y `ManyToMany`.
- Configuración de tabla intermedia en `ManyToMany`.

**Observaciones**

- Las relaciones bidireccionales siguen siendo un problema para la inmutabilidad al requerir el uso de _setters_ posterior a la creación de los objetos; nada que no se pueda solucionar con lógica y sin referenciar mutuamente a las clases (que tampoco lo veo muy necesario).

---

### 📅 03-08-2026

**Temas vistos**

- Diferentes métodos en la relación `ManyToMany`: buscar por base de datos, eliminar y relación bidireccional entre tablas.
- Inicio del proyecto para la sección 12.

> ✅ **Cierre de sección 11.**

---

## Sección 12 — CRUD y validaciones

### 📅 05-08-2026

**Temas vistos**

- Métodos básicos para el CRUD en el `ProductController`.
- Validación de productos no encontrados en los métodos de _update_ y _delete_ en el _service_.
- Uso de **programación funcional** para los retornos tanto del _service_ como del _controller_.
- Uso de validaciones en clases para asegurar que los campos del objeto recibido cumplan con ciertas condiciones.

**Observaciones**

- Preferí utilizar programación funcional en lugar de seguir el curso, ya que es más compacta y se entiende con facilidad.
  - ⚠️ _Hay que tener cuidado con complicar demasiado el código._
- La validación de objetos es muy parecida al `class-validator` que usaba en NestJS.

**🔍 Para investigar**

- Si la validación de objetos puede realizarse con distintas herramientas, esta no debería estar tan acoplada a la clase/objeto en cuestión. ¿Hay alguna forma de **desacoplarla**? ¿O al escoger un validador hay que utilizar esa herramienta de inicio a fin?

---

### 📅 06-08-2026

**Temas vistos**

- Creación y uso de validaciones personalizadas.
- Creación y uso de validaciones personalizadas mediante clases que implementan la interfaz `Validator` de Spring Boot.
- Creación y uso de validaciones personalizadas mediante anotaciones.
- Validaciones personalizadas buscando en la base de datos.
- Mensajes de error personalizados utilizando archivos `.properties`.

> ✅ **Cierre de sección 12.**

---

## Sección 13 — Spring Security y JWT

### 📅 07-08-2026

**Temas vistos**

- Inicio de sección: creación y mapeo de nuevas tablas `Role` y `User` con tabla intermedia para la relación `ManyToMany`.
- Revisión básica de protección de rutas mediante `SecurityFilterChain`, aún sin entrar al tema de JWT (protección de rutas públicas).
- Especificidad entre métodos `POST`, `GET`, etc. para rutas públicas.
- _Troubleshooting_ de problemas presentados por inmutabilidad en campos booleanos, tanto en la entidad como en el controlador.
- Declaración de ruta pública que hace que el valor de `"admin"` se fuerce a `false` para la creación de un usuario.
- Declaración de _Bean_ para `PasswordEncoder` (uso de **bcrypt**, clásico).
- Exclusión de campos sensibles en la respuesta JSON de la lista de usuarios mediante la anotación `@JsonProperty`.

**Observaciones**

- Tuve varios problemas al seguir el patrón de inmutabilidad, sobre todo con los campos _boolean_, ya que lo que es un _setter_ se vuelve un constructor entero.
- ⚠️ Hay que tener cuidado con los booleanos al enviar los valores a la base de datos, ya que por defecto no pueden ser `null`.
- La parte de excluir campos en el JSON de respuesta es bastante interesante.

---

### 📅 08-08-2026 (Parte 1)

**Temas vistos**

- Agregada la dependencia JWT al proyecto para validación.
- Creación de la clase `TokenJwtConfig` para configuración de valores estáticos.
- Implementación de la interfaz `UserDetailsService` para validar al usuario que intenta iniciar sesión, mediante programación funcional.
- Traducción de roles del usuario hacia instancias de `SimpleGrantedAuthority` para posterior _seteo_ de _claims_ en el token.
- La interfaz `UserDetailsService`, en el método `loadUserByUsername`, espera que se retorne una instancia de `User` del paquete de Spring Framework. Sin embargo, como en el proyecto local ya existe una clase llamada `User`, hay que referenciar la clase directo del paquete para poder interactuar correctamente.
- Se retiran los `@Autowired` de los constructores al no ser necesarios; la DI ya se maneja directo por Spring al ser componentes o _Beans_.
- Se agregan filtros a la clase de Spring Security para autenticación mediante JWT y validación del JWT.
  - Para el filtro de autenticación, se implementa la interfaz `UsernamePasswordAuthenticationFilter`.
  - En el uso del _user_ en el filtro de autenticación, dentro del método `successfulAuthentication`, el _user_ que llega desde `authResult.getPrincipal` también es una instancia de `User` de Spring Framework (no la clase local).
- Creación del JWT para validación de sesiones.
  - En este caso, el `SECRET_KEY` utilizado para generar el JWT es dinámico, por lo que se requiere uno nuevo cada vez que se inicia la aplicación.

**Observaciones**

- Tuve un problema con la validación `IsExistsDbValidation` al instanciar el _service_ y el _repository_; la mejor solución fue mover la lógica al _service_. Quizá quede algo acoplado a la implementación, pero sigue siendo lógica de negocio después de todo.
- La implementación de JWT fue extensa, aunque ya la conocía de NestJS. Spring Boot cuenta con más restricciones, aunque esto da una implementación más robusta de las validaciones.

**🔍 Para investigar**

- Me interesaría aprender sobre el **manejo de tokens vencidos a nivel de base de datos**, aunque esto es un anti-patrón, ya que la validación de los JWT debería estar desacoplada de la persistencia (a lo mejor se puede hacer en caché también).
- ¿Qué pasa cuando se trabaja en un **sistema distribuido**? ¿Acaso cada microservicio debe tener su propia implementación de JWT?
- Investigar sobre **RBAC vs permissions vs ABAC**.
- Investigar **JWT + symmetric vs asymmetric signing**.

---

### 📅 08-08-2026 (Parte 2)

**Temas vistos**

- Validación de uso de rutas y métodos según roles.
- Uso de la anotación `@PreAuthorize` directamente en métodos de los _controllers_ para validar el rol al intentar utilizar las rutas.
- Uso de `requestMatchers` en el `filterChain` para validación de rol en el uso de rutas.
- Configuración de CORS para acceso a recursos desde el _frontend_.

**Observaciones**

- La validación de roles en rutas me pareció interesante, pero parece engorrosa al tener que declararla a nivel de ruta/método.
  - ¿Qué pasa cuando se tienen **permisos además de roles**? ¿Se puede hacer de forma masiva en lugar de tener que configurar ruta por ruta?
- Misma pregunta que sobre JWT: ¿cómo se configura esta restricción sobre rutas en un **sistema distribuido por microservicios**?

> ✅ **Cierre de sección 13.**

---

## Sección 14 — Descarga de recursos

> Sección dedicada a la descarga de recursos. (No tomé notas destacadas de esta sección.)

---

## Sección 15 — Despliegue serverless en AWS (con floci)

### 📅 09-08-2026 (Parte 1)

La sección 15 (la sección 14 fue sobre descarga de recursos) trató sobre el despliegue de la aplicación en una infraestructura _serverless_ en AWS.

Uno de los recursos a utilizar fue una instancia de RDS. Como ya tengo experiencia en el trabajo de que estas instancias son costosas, aún cuando no se están utilizando (xd), decidí utilizar una herramienta llamada **"floci"** (mejor que _localstack_) para simular la infraestructura necesaria de AWS. A continuación, lo más importante del despliegue.

---

#### Setup de entorno "Serverless"

- Como me gusta complicarme la vida, decidí montar floci en un computador aparte — mi laptop personal con Ubuntu 26.04 —, de forma que se pueda emular correctamente el acceso a recursos externos en lugar de poder ingresar a todo mediante `localhost`.
- Toqué un poco de _docker compose_; nunca lo había utilizado, pero no va más allá de montar distintos contenedores con un solo archivo `.yml`.
  - ⚠️ **Dato importante:** hay que compartir la red por el _host_ y puerto `0.0.0.0:4566` hacia el puerto del contenedor `4566`, ya que se requiere exponer todo el tráfico en la red local para poder hacer las configuraciones desde mi máquina. Esto **no es una práctica recomendada** en un entorno de producción, pero bajo mi red todo está protegido.
- Monté también una instancia de _floci-ui_. Dato interesante: para configurar la URL hacia el servicio de floci se tiene que referenciar al **nombre del servicio** dentro del `docker-compose.yml` en lugar de una dirección IP directa.
- Lo demás fueron configuraciones generales, como el nivel de log o el volumen para que no se borre la información al hacer `docker compose down`.

---

#### Instancia RDS

- La creación de esta instancia fue relativamente sencilla. Lo interesante de esta herramienta es que **obliga a utilizar comandos de la AWS CLI** al no tener acceso a la consola de AWS, por lo que fuerza de cierta manera a aprender los comandos más importantes relacionados a los servicios.
  - Una de las cosas más importantes sobre este servicio es que, al invocarlo en la AWS CLI, siempre se tiene que utilizar el `--endpoint-url`, ya que la herramienta lo sobreescribe e intenta irse a un _endpoint_ real de AWS, cosa que causó varios problemas.
- ⚠️ Lo segundo y más importante: el contenedor que monta floci para la RDS **no expone el puerto** para la conexión a la base de datos directamente, sino que lo deja encerrado en un puerto (en este caso fue el `33060`) de la red interna de Docker que crea floci por defecto (red `floci_default`).
  - Para poder exponer el puerto se tuvo que crear un servicio extra en el _docker compose_: un contenedor con la imagen `alpine/socat` que se encargara de tomar el tráfico del contenedor creado por floci en el puerto `3306` interno y exponerlo hacia un puerto personalizado del _host_, en este caso el `13306` (para referencia completa, revisar el `docker-compose.yml`).
  - **Importante:** en caso de tener más instancias de RDS, cada una requeriría de su propio servicio `alpine/socat` para redirigir el tráfico de la red interna de Docker hacia algún puerto del _host_.

---

#### Instancia EC2

- El curso montaba los recursos hacia una instancia de EC2, por lo que quise hacer lo mismo de manera local.
  - En este caso, la AWS CLI **no requiere** especificar el `--endpoint-url`, ya que no lo sobreescribe como sí lo hacían los comandos relacionados a RDS.
  - Las AMIs que provee floci son simples imágenes del repositorio público de AWS, lo que trae algunas diferencias respecto al aprovisionamiento que hace AWS.

- ⚠️ **Problema principal: la instancia EC2 de floci no aprovisiona SSH por defecto.**
  - Al tomar una simple imagen del repositorio público de AWS, la imagen no viene realmente configurada para temas que AWS da por defecto como la conexión por SSH, por lo que esto se tuvo que configurar manualmente. Me di cuenta ya que el comando `aws ec2 create-key-pair` no crea un archivo `.pem` válido, sino uno _fake_ que no permite una correcta conexión mediante SSH.
  - Sumado a esto, en el contenedor que crea floci para simular la instancia tampoco viene instalado `openssh-server` por defecto, de manera que no había ninguna forma de conectarse por SSH. Esto es algo esperable de un emulador de servicios de AWS, y son la clase de detalles que se deben gestionar manualmente.
    - En este caso, instalé `openssh-server` en el contenedor manualmente y agregué la llave creada manualmente en mi equipo hacia el archivo `/root/.ssh/authorized_keys` para poder simular correctamente el comportamiento de SSH hacia una instancia de EC2.
    - Por defecto, floci expone el puerto `2200` para conectarse hacia la instancia; hay que ver qué otros puertos expone a medida que se crean más instancias.
  - Para conectar utilizaba el siguiente comando:
    ```
    ssh -i floci-ec2-key -p 2200 root@192.168.1.10
    ```
    Para próximas pruebas sería bueno configurar el usuario `ec2-user` en el contenedor, ya que este es el usuario por defecto que crea AWS en sus instancias para conexiones SSH, en lugar del `root` que finalmente quedó.

- Por lo demás: instalación de Java JDK 17, copiar el archivo `.jar` generado mediante `scp` y levantar mediante `java -jar`.
  - **Importante:** el contenedor que simula la instancia se crea en la misma red de Docker (`floci_default`) que la instancia de RDS, por lo que se puede conectar directamente mediante la IP interna del contenedor tal como se haría en una red privada de VPC.
  - Al momento de crear reglas de entrada en el _security-group_ de la instancia, lo que hace floci es generar un contenedor con la imagen `alpine/socat` para exponer el puerto declarado hacia un puerto del _host_ (en mi caso, configuré el tráfico para que pudiera entrar por el puerto `8080` a la instancia, y floci lo expuso hacia el puerto `30000` del _host_, aplicando la misma lógica manual que se utilizó para exponer el tráfico de la instancia de RDS).
  - Por esta configuración el `application.properties` quedó apuntando hacia una base de datos en `172.18.0.5:3306`, y por esto se tuvieron que saltar los _tests_ de Maven (desde el equipo local no hay conexión hacia esa dirección IP).

**🔍 Para investigar**

- Quiero hacer este mismo ejercicio mediante un aprovisionamiento con **AWS CDK**, montando un _API Gateway_ que redirija hacia una _Lambda_ y de ahí se vaya a un _task_ montado en ECS (tal como se hace en el trabajo actualmente).

> ✅ **Cierre de sección 15.**

---

## Sección 16 — Despliegue en Tomcat (WAR)

### 📅 09-08-2026 (Parte 2)

**Temas vistos**

- Despliegue del aplicativo en servidor **Tomcat 11** con compilación WAR (para volver a la compilación JAR hay que revertir este _commit_).
- La ruta base de la API se vuelve `crud-jpa/**` al ser Tomcat un servidor para web.

> ✅ **Cierre de sección 16.**

---

## Secciones 17–21 — Frontend (React y Angular)

### 📅 10-08-2026 (Parte 1)

**Temas vistos**

- Creación y configuración del proyecto de React.

**Observaciones**

- Toda la sección 17 es sobre la creación de una aplicación React para comunicarse con un _backend_ de Spring Boot. Luego me di cuenta de que el nombre del curso es _"Construye aplicaciones web con SpringBoot, Thymeleaf, React, Angular"_. No le estoy poniendo mucha atención: lo tengo al 1.25x y solo voy replicando el código, ya que sinceramente no me interesa aprender React, pero lo necesito para avanzar, así que X.

---

### 📅 10-08-2026 (Parte 2)

**Temas vistos**

- Se creó un _backend_ básico para conectarlo con la aplicación _frontend_ de React; nada muy avanzado, únicamente comunicación entre _frontend_ y _backend_ y una pequeña configuración de CORS.
- Se hizo el mismo ejercicio con una aplicación de Angular. No hice nada más allá de lo que ya sé en el framework: pasé de utilizar Angular 22 por el uso de _signals_ (de los que no conozco mucho y el enfoque no está en actualizar conocimientos de Angular), así que hice la _App_ con Angular 19 y seguí el tuto casi al pie de la regla.

> ✅ **Cierre de sección 21.**

---

## 🎯 Cuadro de prioridades — temas y preguntas a abordar

A continuación, los temas y preguntas que surgieron a lo largo de las notas, **ordenados por prioridad de mayor a menor** según mi criterio. La priorización se basa en **qué tan fundacionales son** (lo último que aprendas depende de lo primero) y en **qué tan relevantes son para sistemas reales / producción**.

> Criterio de prioridad:
> - 🔴 **Alta** — fundacional para entender JPA/Security o crítico en producción.
> - 🟡 **Media** — importante, pero se apoya en conceptos de prioridad alta.
> - 🟢 **Baja** — específica a un contexto o ejercicio concreto.

### 🔴 Prioridad alta

| # | Tema / Pregunta | Sección | ¿Por qué es prioritario? | Recursos |
|---|---|---|---|---|
| 1 | **N+1, Lazy Loading, Fetch Join y Entity Graph** | §10 | El problema de rendimiento #1 en JPA. Todo lo demás sobre consultas depende de entender esto. | [Thorben Janssen — N+1](https://thorben-janssen.com/avoid-n+1-selects-in-hibernate/) · [Entity Graphs](https://thorben-janssen.com/entity-graphs/) |
| 2 | **Persistence Context, Dirty Checking y Flush** | §10 | Es _cómo funciona JPA por dentro_. Sin esto, el comportamiento de guardado/actualización parece mágico. | [Hibernate — Persistence Context](https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#pc) · [Dirty Checking](https://thorben-janssen.com/dirty-checking/) |
| 3 | **Ciclos de vida de las entities** (más allá de `@PrePersist`/`@PreUpdate`) | §10 | Completa lo que viste y conecta con los _callbacks_ y _listeners_. | [Hibernate — Entity Lifecycle Events](https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#events) |
| 4 | **Cascading y tipos de `CascadeType`** | §11 | Ya lo usaste (`CascadeType.ALL`) sin profundizar; entender cada tipo evita borrar/insertar datos sin querer. | [Thorben Janssen — Cascading](https://thorben-janssen.com/cascading-with-jpa-hibernate/) |
| 5 | **JWT en sistemas distribuidos / microservicios** | §13 | Pregunta clave para arquitecturas reales. Define cómo se valida identidad entre servicios. | [Spring Security — OAuth2 Resource Server](https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/jwt.html) · [JWT Best Practices (RFC 8725)](https://datatracker.ietf.org/doc/html/rfc8725) |
| 6 | **RBAC vs ABAC vs permissions** | §13 | Directamente relacionado con tu duda sobre "permisos además de roles" y configuración masiva. | [OWASP — Access Control](https://owasp.org/www-project-top-ten/2017/A5_2017-Broken_Access_Control) · [NIST ABAC](https://csrc.nist.gov/projects/attribute-based-access-control) |
| 7 | **JWT symmetric vs asymmetric signing** | §13 | Decisión de diseño que ya te planteaste; en microservicios la firma asimétrica es clave. | [RFC 7519 (JWT)](https://datatracker.ietf.org/doc/html/rfc7519) · [jwt.io](https://jwt.io/) |

### 🟡 Prioridad media

| # | Tema / Pregunta | Sección | ¿Por qué es importante? | Recursos |
|---|---|---|---|---|
| 8 | **Locking y Optimistic Lock** | §10 | Clave para concurrencia: evita _lost updates_ cuando dos transacciones tocan el mismo registro. | [Hibernate — Locking](https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#locking) · [Vlad Mihalcea — Optimistic Locking](https://vladmihalcea.com/jpa-optimistic-locking-version-property/) |
| 9 | **Second Level Cache** | §10 | Optimización de rendimiento; entenderlo evita consultas repetitivas a la BD. | [Hibernate — Caching](https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#caching) |
| 10 | **Restricciones de rutas/roles a nivel masivo y en microservicios** | §13 | Continuación natural de los temas #5 y #6 aplicado a autorización por ruta. | [Spring Security — Authorization](https://docs.spring.io/spring-security/reference/servlet/authorization.html) · [Method Security](https://docs.spring.io/spring-security/reference/servlet/authorization/method-security.html) |
| 11 | **Desacoplar la validación de objetos** | §12 | Duda arquitectónica válida; `_Validator_` y _Bean Validation_ permiten separar reglas del modelo. | [Spring — Bean Validation](https://docs.spring.io/spring-framework/reference/core/beans/validation.html) · [Hibernate Validator](https://hibernate.org/validator/) |
| 12 | **Inmutabilidad con relaciones bidireccionales** (y el patrón correcto) | §10/§11 | Problema recurrente en tus notas. Hay patrones (constructores, _builders_, _withers_) que lo resuelven sin _setters_. | [Project Lombok](https://projectlombok.org/) · [Baeldung — Immutable Entities](https://www.baeldung.com/java-jpa-immutable-entities) |

### 🟢 Prioridad baja

| # | Tema / Pregunta | Sección | Notas | Recursos |
|---|---|---|---|---|
| 13 | **AWS CDK + API Gateway + Lambda + ECS** | §15 | Es un ejercicio práctico que mencionaste para replicar lo del trabajo. Muy valioso pero muy específico. | [AWS CDK — Getting Started](https://docs.aws.amazon.com/cdk/v2/guide/getting_started.html) · [AWS — Serverless Backend](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/serverless-backend-with-api-gateway-lambda-and-ecs.html) |
| 14 | **Nombres reservados de query methods en Hibernate** | §10 | Ya tienes el link oficial en tus notas (§10, 22-07). Es referencia más que estudio profundo. | [Spring Data JPA — Query Methods](https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html) |
| 15 | **Manejo de tokens vencidos a nivel de BD** | §13 | Tú mismo lo marcaste como anti-patrón. Revisarlo solo para confirmar el porqué NO hacerlo. | [JWT Best Practices (RFC 8725) — §4](https://datatracker.ietf.org/doc/html/rfc8725#section-4) |
| 16 | **Joins en JPA** | §10 | Duda que ya se resuelve naturalmente al abordar el tema #1 (Fetch Join). | — _(se cubre en el tema #1)_ |

---

_Última actualización: 10-08-2026._
