# Sapamercat

## Clases

**Producto:** Esta es una clase abstracta que define las propiedades y métodos comunes a todos los productos. Las propiedades incluyen nombre, precio y codigoBarras. También declara un método abstracto calcularPrecio() que debe ser implementado por cada subclase de Producto. 

**Alimentacion:** Esta es una subclase de Producto que representa un tipo específico de producto: los alimentos. Tiene una propiedad adicional fechaCaducidad que es específica para los productos alimenticios. Implementa el método calcularPrecio() de la clase Producto con una fórmula específica para los productos alimenticios.  

**Textil:** Esta es una subclase de Producto que representa un tipo específico de producto: los textiles. Tiene una propiedad adicional composicion que es específica para los productos textiles. Implementa el método calcularPrecio() de la clase Producto que simplemente devuelve el precio base del producto, ya que no se proporcionó una fórmula específica para los productos textiles.

**Electronica:** Esta es una subclase de producto. Tienen una propiedad adicional diasGarantia que representa el número de días de garantía del producto. El precio de los productos electrónicos se calcula sumando al precio base el resultado de multiplicar el precio base por la fracción de días de garantía sobre 365 y por 0.1.

**ProductoCarrito:** Esta clase representa un producto en el carrito de compras. Tiene una propiedad producto que es una instancia de Producto y una propiedad cantidad que representa la cantidad de ese producto en el carrito. También tiene un método getPrecioTotal() que calcula el precio total de ese producto en el carrito multiplicando la cantidad por el precio del producto.

**CarroCompra:** Esta clase representa el carrito de compras. Tiene una propiedad productos que es un mapa de ProductoCarrito. Esta clase tiene métodos para añadir productos al carrito, pasar por caja (que muestra un recibo y vacía el carrito), mostrar el carrito y vaciar el carrito.  

**Main:** Esta es la clase principal que contiene el método main(). Esta clase maneja la interacción con el usuario, mostrando menús y leyendo la entrada del usuario. Dependiendo de la entrada del usuario, llama a los métodos apropiados en las otras clases para realizar acciones como añadir productos al carrito o pasar por caja


## El uso de Hashmap

He utilizado un map en la clase CarroCompra porque necesitabamos rastrear la cantidad de cada produco único, así almacenabamos los productos con su cantidad correspondiente.

## El uso de DecimalFormat

He utilizado el DecimalFormat en la clase carroCompra por ejemplo.

**¿Por qué?**


DecimalFormat es una clase de Java que proporciona un gran control sobre el formato de números decimales. Se puede usar para formatear números decimales para mostrarlos como strings con un número fijo de decimales, enmascarar números con patrones no numéricos como comas para miles, y permite localización.

Por otro lado, Math.round() es una función que se utiliza para redondear los números a la unidad más cercana, pero por sí sola no te permite especificar el número de decimales a los que quieres redondear, y siempre devuelve un número entero.

Para mostrar números con dos decimales podria combinar Math.round() con otra lógica para mover el punto decimal, pero esto creo que es más engorroso y propenso a errores. Por eso decidío buscar otra alternativa por internet y decían que el DecimalFormat era mejor para eso junto con el link de oracle: https://docs.oracle.com/javase/8/docs/api/java/text/DecimalFormat.html

## La clase comparador ProductoComparator

El método compare de esta clase se utiliza para determinar cómo se deben comparar dos objetos Producto.
En este caso, los productos se comparan primero por su precio. Si dos productos tienen el mismo precio, entonces se comparan por su nombre.

## En la clase Alimentacion, ¿por que hay una función diasHastaCaducidad?

Lo hice para poder calcular cuantos días faltan para que caduque el producto y poderlo mostrar cuando el usuario busque
por código de barras de forma completa.

## ¿Por qué en el menú hay dos formas de buscar por código de barras?

El 4 es para buscar el nombre del producto por código de barras usando stream como pedía el ejercicio.
Sin embargo, el 5 es algo que hice yo extra que busca el produco por código de barras usando stream pero no solo muestra el nombre
sino tambien muestra toda la información correspondiente del producto.

## ¿Cómo buscar en base de código de barras?

Cuando añades un producto nuevo pones el código de barras (ejemplo: 1234). Sin embargo, internamente se guarda:
Si es alimento:
A1234
Si es Textil:
T1234
Si es Electrónica:
E1234

Tengo en cuenta para buscar por código de barras porque tendrás que añadir: A, T o E antes del código de barras para que pueda buscarlo correctamente.
En nuestro ejemplo: A1234, T1234, E1234

