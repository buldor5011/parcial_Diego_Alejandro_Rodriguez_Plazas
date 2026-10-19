# Comprensión del diseño — Enunciado A
**Nombre:** Diego Alejandro Rodriguez Plazas
**Fecha:** 19/10/2026

Responde con tus palabras, basándote en `docs/UML-final.md`. Respuestas
concisas (2–4 líneas por pregunta). Este documento evidencia que entendiste el
contrato de diseño **antes** de programar.

---

### 1. Herencia y polimorfismo
¿Por qué `Sensor` es una clase abstracta y no una clase concreta? Menciona
qué método obliga a cada subclase a implementar y qué gana el sistema con eso.

> Tu respuesta: La clase Sensor es abstracta porque representa las características generales que tienen todos los sensores, pero cada tipo de sensor realiza una lectura diferente. El método leer() obliga a cada subclase a implementar su propia forma de obtener una lectura gracias a esto podemos trabajar con diferentes tipos de sensores usando una misma clase general

---

### 2. Composición vs. agregación
Explica con tus palabras la diferencia entre `NodoIoT ◆— Sensor` (composición)
y `CentroMonitoreo ◇— NodoIoT` (agregación). Da un ejemplo de qué pasaría con
los sensores si se elimina el nodo, y qué pasaría con los nodos si se elimina
el centro.

> _Tu respuesta:_ La composicion entre NodoIoT y Sensor significa que los sensores hacen parte de un nodo si el nodo deja de existir, los sensores dejan de formar parte de el
en cambio, la agregación entre CentroMonitoreo y NodoIoT significa que el centro puede tener varios nodos, pero estos pueden existir sin el centro. Por ejemplo, si se elimina el centro de monitoreo, los nodos podrían seguir funcionando y después conectarse a otro centro

---

### 3. Asociación
En `TecnicoMantenimiento — NodoIoT`, ¿quién es dueño de quién? ¿Por qué se
modela como N:M y no como composición?

> _Tu respuesta:_ La asociación entre TecnicoMantenimiento y NodoIoT significa que un tecnico puede estar encargado de varios nodos y un nodo puede tener varios tecnicos asignados.
No es composicion porque el tecnico y los nodos pueden existir por separado, si un tecnico deja de trabajar con un nodo, ninguno de los dos deja de existir  simplemente cambia la asignación


---

### 4. Dependencia
¿Por qué `Lectura` **depende** de `ValidadorLectura` en lugar de **tenerlo**
como atributo? ¿Qué diferencia práctica hay entre pasar un `ValidadorLectura`
como parámetro versus guardarlo en un campo?

> _Tu respuesta:_ La clase Lectura depende de ValidadorLectura porque necesita comprobar si el valor de una lectura es correcto.
El validador se puede recibir como parámetro cuando se necesita utilizar asi Lectura puede usarlo sin tener que guardarlo como una variable dentro de la clase. Esto ayuda a que el codigo sea más sencillo y a que cada clase tenga una función específica

---

### 5. Listas
En este proyecto se usan dos estructuras de datos distintas:
- `int[] ultimasLecturas` en `Sensor`
- `ArrayList<Sensor>` en `NodoIoT`

Explica por qué en cada caso se eligió esa estructura y no la otra. ¿Qué
ventaja tiene el `int[]` con buffer circular frente a un `ArrayList` para el
historial de lecturas?

> _Tu respuesta:_ En la clase Sensor se usa un arreglo de tamaño fijo para guardar las ultimas lecturas, cuando el arreglo se llena este reemplaza la lectura mas antigua por una nueva, permitiendo guardar siempre las lecturas recientes sin ocupar memoria adicional cada vez que llega un dato.
En la clase NodoIoT se usa un ArrayList<Sensor> porque la cantidad de sensores puede cambiar. Se pueden agregar o quitar sensores segun sea necesario sin tener que definir desde el principio cuántos habra.
La diferencia principal es que el arreglo circular sirve para guardar las ultimas lecturas en un espacio limitado, mientras que el ArrayList permite manejar una cantidad variable de sensores