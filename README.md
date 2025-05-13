## Tema : Proyecto Parqueo Público

JDK: >22.0.0

#### INSTRUCCIONES:



* Programe en JAVA la solución que usted definió y que le permitan cumplir con los requerimientos del
sistema.

<br>
**Descripción del problema**: Se necesita una solución informática para administrar un parqueo de
vehículos. Dicho parqueo cuenta con varias entradas y varias salidas, por lo que en cada una de ellas
se va a instalar una máquina de autoservicio que les permita a los choferes registrar el ingreso del
vehículo, así como realizar la salida con el respectivo cobro de acuerdo al tiempo de uso del parqueo.

<br>
* **Requerimientos del negocio**:

1. Se va a instalar un servidor central para procesar todas las transacciones y almacenar el
registro de todos los vehículos (SERVIDOR CENTRAL).
2. Se instalarán máquinas de registro en cada entrada del parqueo (REGISTRO DEL VEHÍCULO).
3. Se instalarán máquinas de cobro en cada salida del parqueo (SALIDA DEL VEHÍCULO).

#### Requerimientos funcionales:

1. Cada chofer cuando ingrese al parqueo debe registrar el número de placa y tipo de vehículo,
existen 4 opciones: vehículo de combustión, motocicleta de combustión, vehículo eléctrico,
motocicleta eléctrica.
    1. El sistema debe generar un número de transacción consecutivo
    2. El sistema debe guardar la hora de entrada de cada vehículo.
2. Cuando un vehículo va a salir, el chofer debe ingresar el número de placa, y el sistema le debe
generar el cobro de acuerdo a las tarifas establecidas.

#### Requerimientos de diseño:

| Tipo De Vehículo | Código | Tarifa por segundo |
| ---------------- | ------ | ------------------ |
| Vehículo de combustión | 1 | ₡4 |
| Vehículo eléctrico | 2 | Primer Minuto gratis, luego<br>₡2, por cada segundo |
| Motocicleta de Combustión | 3 | ₡2 |
| Motocicleta eléctrica | 4 | Primer Minuto gratis, luego<br>₡1, por cada segundo |

##### ASPECTOS IMPORTANTES
<br>
1. El sistema deberá ser capaz de soportar la caída del servidor y posterior recuperación sin
perder los datos del día, para ello deberá tener un archivo actualizado con toda la información
necesaria para restaurar el servidor.

2. El sistema debe almacenar en un archivo histórico, todas las transacciones realizadas, dicho
registro debe contener al menos la siguiente información de cada vehículo que ha hecho uso del
parqueo: **Número de transacción, Fecha ingreso, hora ingreso, hora salida, placa, tipo de vehículo,
monto cobrado.**

3. El servidor deberá tener la opción de mostrar todos los vehículos que están dentro del parqueo,
indicando el número de placa y el tiempo que llevan acumulado desde su ingreso y cobro que
corresponde en ese momento.
4. El servidor deberá tener la opción de mostrar en pantalla el archivo histórico con todos los
datos almacenados.
5. Cuando una terminal inicie, el servidor debe enviar una carga de datos con los tipos de
vehículos, para que estos sean mostrados al chofer al momento del ingreso
6. Desde el servidor se puede modificar la función de las terminales, es decir, se puede pasar de
terminal de entrada a terminal de salida y viceversa.
