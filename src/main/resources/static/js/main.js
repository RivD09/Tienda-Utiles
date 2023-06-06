function buscarArticulos() {
    var xhr = new XMLHttpRequest();
    var text = document.getElementById("nombre-articulo");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                console.log("200")
                var data = JSON.parse(xhr.responseText);
                console.log(data)
                construirTabla(data);
            } else {
                console.log(text.value)
                alert("a" + text.value + " b");
            }
        }
    };
    xhr.open("GET", "lista/buscar/" + text.value  , true);
    xhr.send();
}

function construirTabla(data) {

    // Obtener una referencia al elemento contenedor de la tabla
    var tablaArticulos = document.getElementById("tabla-articulos");

    // Eliminar la tabla existente, si la hay
    while (tablaArticulos.firstChild) {
        tablaArticulos.removeChild(tablaArticulos.firstChild);
    }

    // Crear la fila de encabezado
    var encabezado = document.createElement("tr");

    // Crear las celdas de encabezado
    var encabezadoCodArticulo = document.createElement("th");
    encabezadoCodArticulo.textContent = "Código";
    encabezado.appendChild(encabezadoCodArticulo);

    var encabezadoNombre = document.createElement("th");
    encabezadoNombre.textContent = "Nombre";
    encabezado.appendChild(encabezadoNombre);

    var encabezadoPrecio = document.createElement("th");
    encabezadoPrecio.textContent = "Precio";
    encabezado.appendChild(encabezadoPrecio);

    var encabezadoDisponible = document.createElement("th");
    encabezadoDisponible.textContent = "Disponible";
    encabezado.appendChild(encabezadoDisponible);

    var encabezadoCantidad = document.createElement("th");
    encabezadoCantidad.textContent = "Cantidad a llevar";
    encabezado.appendChild(encabezadoCantidad);

    // Agregar la fila de encabezado a la tabla
    tablaArticulos.appendChild(encabezado);

    // Recorrer los datos y crear las filas de datos
    data.forEach(function(articulo) {
        var fila = document.createElement("tr");

        var celdaCodArticulo = document.createElement("td");
        celdaCodArticulo.textContent = articulo.cod_articulo;
        fila.appendChild(celdaCodArticulo);

        var celdaNombre = document.createElement("td");
        celdaNombre.textContent = articulo.nombre;
        fila.appendChild(celdaNombre);

        var celdaPrecio = document.createElement("td");
        celdaPrecio.textContent = articulo.precio_unit;
        fila.appendChild(celdaPrecio);

        var celdaDisponible = document.createElement("td");
        celdaDisponible.textContent = articulo.cantidad;
        fila.appendChild(celdaDisponible);

        var celdaNumero = document.createElement("td");
        var inputNumero = document.createElement("input");
        inputNumero.type = "number";
        inputNumero.min = 0;
        inputNumero.max = articulo.cantidad;

        // Añadir el evento input al campo de entrada
        inputNumero.addEventListener("input", generarTablaValidada);

        celdaNumero.appendChild(inputNumero);
        fila.appendChild(celdaNumero);

        // Agregar la fila de datos a la tabla
        tablaArticulos.appendChild(fila);
    });

}

function generarTablaValidada() {
    // Obtener una referencia a la tabla principal utilizando el id
    var tablaPrincipal = document.getElementById("tabla-articulos");

    // Obtener una referencia al elemento contenedor de la tabla validada
    var tablaCarrito = document.getElementById("tabla-carrito");

    // Eliminar la tabla validada existente, si la hay
    while (tablaCarrito.firstChild) {
        tablaCarrito.removeChild(tablaCarrito.firstChild);
    }

    // Crear la fila de encabezado
    var encabezado = document.createElement("tr");

    // Crear las celdas de encabezado
    var encabezadoCodArticulo = document.createElement("th");
    encabezadoCodArticulo.textContent = "Código";
    encabezado.appendChild(encabezadoCodArticulo);

    var encabezadoNombre = document.createElement("th");
    encabezadoNombre.textContent = "Nombre";
    encabezado.appendChild(encabezadoNombre);

    var encabezadoPrecio = document.createElement("th");
    encabezadoPrecio.textContent = "Precio";
    encabezado.appendChild(encabezadoPrecio);

    var encabezadoCantidad = document.createElement("th");
    encabezadoCantidad.textContent = "Cantidad";
    encabezado.appendChild(encabezadoCantidad);

    var encabezadoSubtotal = document.createElement("th");
    encabezadoSubtotal.textContent = "Subtotal";
    encabezado.appendChild(encabezadoSubtotal);

    // Agregar la fila de encabezado a la tabla
    tablaCarrito.appendChild(encabezado);

    // Obtener todas las filas de la tabla principal
    var filas = tablaPrincipal.querySelectorAll("tr");

    // Recorrer las filas y generar la tabla validada
    for (var i = 1; i < filas.length; i++) {
        var fila = filas[i];
        var inputNumero = fila.querySelector("input");
        if (inputNumero && inputNumero.value > 0) {
            var codigo = fila.children[0].textContent;
            var nombre = fila.children[1].textContent;
            var precio = fila.children[2].textContent;
            var cantidad = inputNumero.value;
            var subtotal = inputNumero.value;

            // Crear las celdas de la fila validada
            var celdaCodArticuloValidado = document.createElement("td");
            celdaCodArticuloValidado.textContent = codigo;

            var celdaNombreValidado = document.createElement("td");
            celdaNombreValidado.textContent = nombre;

            var celdaPrecioValidado = document.createElement("td");
            celdaPrecioValidado.textContent = precio;

            var celdaCantidadValidado = document.createElement("td");
            celdaCantidadValidado.textContent = cantidad;

            var celdaSubtotalValidado = document.createElement("td");
            celdaSubtotalValidado.textContent = subtotal;

            // Crear la fila validada y agregar las celdas
            var filaValidada = document.createElement("tr");
            filaValidada.appendChild(celdaCodArticuloValidado);
            filaValidada.appendChild(celdaNombreValidado);
            filaValidada.appendChild(celdaPrecioValidado);
            filaValidada.appendChild(celdaCantidadValidado);
            filaValidada.appendChild(celdaSubtotalValidado);

            // Agregar la fila validada a la tabla validada
            tablaCarrito.appendChild(filaValidada);
        }
    }
}
