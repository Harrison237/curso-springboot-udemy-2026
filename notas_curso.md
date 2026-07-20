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