# Lab 04 - Carrito de compras en Jetpack Compose

### Nombre: Angieluz Vasquez Macalupu

## Descripción:

Aplicación de carrito de compras desarrollada con Kotlin y Jetpack Compose.

La aplicación permite ingresar el nombre, precio y cantidad de un producto, agregarlo al carrito y visualizar los productos registrados. También permite eliminar productos y muestra un resumen con la cantidad de productos, subtotal, IGV (18%) y total.

La interfaz utiliza componentes de Jetpack Compose como Column, Row, LazyColumn, Card, OutlinedTextField y Button.

## Capturas de pantalla
### Carrito vacío
<img width="336" height="722" alt="image" src="https://github.com/user-attachments/assets/a9bc1cd7-49cb-4be4-8626-2da8805072d8" />

## Carrito lleno
<img width="325" height="687" alt="image" src="https://github.com/user-attachments/assets/50b0e602-0a8b-4f28-a547-49773068798d" />

## Preguntas:
### a) ¿Por qué mutableStateListOf y no una MutableList normal?
Porque mutableStateListOf permite que Compose detecte los cambios en la lista y actualice la pantalla de manera automatica, si se usara MutableList, aunque se puede cambiar, Compose no detectaria los cambios para actualizar la interfaz mostrando que no hubo cambios.
### b) ¿Por qué la lista es val?
Porque no es necesario modificar la referencia de la lista, solo el contenido cuando se agrega o elimina un producto.
### c) ¿Qué hace weight(1f) en la LazyColumn?
Hace que LazyColumn ocupe el espacio disponible que queda dentro del Column.
