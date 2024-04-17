# Sapamercat

##Clases

**Producto:** Esta es una clase abstracta que define las propiedades y métodos comunes a todos los productos. Las propiedades incluyen nombre, precio y codigoBarras. También declara un método abstracto calcularPrecio() que debe ser implementado por cada subclase de Producto. 

**Alimentacion:** Esta es una subclase de Producto que representa un tipo específico de producto: los alimentos. Tiene una propiedad adicional fechaCaducidad que es específica para los productos alimenticios. Implementa el método calcularPrecio() de la clase Producto con una fórmula específica para los productos alimenticios.  

**Textil:** Esta es una subclase de Producto que representa un tipo específico de producto: los textiles. Tiene una propiedad adicional composicion que es específica para los productos textiles. Implementa el método calcularPrecio() de la clase Producto que simplemente devuelve el precio base del producto, ya que no se proporcionó una fórmula específica para los productos textiles.

**Electronica:** Esta es una subclase de producto. Tienen una propiedad adicional diasGarantia que representa el número de días de garantía del producto. El precio de los productos electrónicos se calcula sumando al precio base el resultado de multiplicar el precio base por la fracción de días de garantía sobre 365 y por 0.1.

**ProductoCarrito:** Esta clase representa un producto en el carrito de compras. Tiene una propiedad producto que es una instancia de Producto y una propiedad cantidad que representa la cantidad de ese producto en el carrito. También tiene un método getPrecioTotal() que calcula el precio total de ese producto en el carrito multiplicando la cantidad por el precio del producto.

**CarroCompra:** Esta clase representa el carrito de compras. Tiene una propiedad productos que es un mapa de ProductoCarrito. Esta clase tiene métodos para añadir productos al carrito, pasar por caja (que muestra un recibo y vacía el carrito), mostrar el carrito y vaciar el carrito.  

**Main:** Esta es la clase principal que contiene el método main(). Esta clase maneja la interacción con el usuario, mostrando menús y leyendo la entrada del usuario. Dependiendo de la entrada del usuario, llama a los métodos apropiados en las otras clases para realizar acciones como añadir productos al carrito o pasar por caja


##Hashmap##

He utilizado un map en la clase CarroCompra  porque necesitabamos rastrear la cantidad de cada produco único, así almacenabamos los productos con su cantidad correspondiente.

