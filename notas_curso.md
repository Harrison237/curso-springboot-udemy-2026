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
