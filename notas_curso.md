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
