function buscarArticulos() {
    var xhr = new XMLHttpRequest();
    var text = document.getElementById("nombre-articulo");
    xhr.onreadystatechange = function () {
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
    xhr.open("GET", "lista/buscar/" + text.value, true);
    xhr.send();
}

function construirTabla(data) {
    var tablaArticulos = document.getElementById("tabla-articulos");
    limpiarTabla(tablaArticulos);
    crearEncabezado(tablaArticulos);
    crearFilasDatos(data, tablaArticulos);
}

function limpiarTabla(tabla) {
    while (tabla.firstChild) {
        tabla.removeChild(tabla.firstChild);
    }
}

function crearEncabezado(tabla) {
    var encabezado = document.createElement("tr");
    var encabezadoColumnas = ["Código", "Nombre", "Precio", "Disponible", "Cantidad a llevar"];

    encabezadoColumnas.forEach(function (titulo) {
        var celda = document.createElement("th");
        celda.textContent = titulo;
        encabezado.appendChild(celda);
    });

    tabla.appendChild(encabezado);
}

function crearEncabezadoCarrito(tabla) {
    var encabezado = document.createElement("tr");
    var encabezadoColumnas = ["Código", "Nombre", "Precio", "Cantidad", "Subtotal", "Borrar"];

    encabezadoColumnas.forEach(function (titulo) {
        var celda = document.createElement("th");
        celda.textContent = titulo;
        encabezado.appendChild(celda);
    });

    tabla.appendChild(encabezado);
}


function crearFilasDatos(data, tabla) {
    data.forEach(function (articulo) {
        var fila = document.createElement("tr");
        var columnas = [articulo.cod_articulo, articulo.nombre, articulo.precio_unit, articulo.cantidad];

        columnas.forEach(function (valor) {
            var celda = document.createElement("td");
            celda.textContent = valor;
            fila.appendChild(celda);
        });

        var celdaNumero = document.createElement("td");
        var inputNumero = document.createElement("input");
        inputNumero.type = "number";
        inputNumero.min = 0;
        inputNumero.max = articulo.cantidad;

        inputNumero.addEventListener("input", function () {agregarAlCarrito(articulo);} );
        inputNumero.id="cant-" + articulo.cod_articulo;
        celdaNumero.appendChild(inputNumero);
        fila.appendChild(celdaNumero);

        tabla.appendChild(fila);
    });
}

var carritoArticulos = []; // Array para almacenar los artículos del carrito

function agregarAlCarrito(articulo) {
    var cantidadInput = document.getElementById("cant-" + articulo.cod_articulo);
    var cantidad = parseInt(cantidadInput.value);

    if (cantidad > 0) {
        // Verificar si el artículo ya está en el carrito
        var indiceExistente = carritoArticulos.findIndex(function (item) {
            return item.cod_articulo === articulo.cod_articulo;
        });

        if (indiceExistente !== -1) {
            // Actualizar la cantidad y subtotal del artículo existente en el carrito
            var itemExistente = carritoArticulos[indiceExistente];
            itemExistente.cantidad = cantidad;
            itemExistente.subtotal = cantidad * articulo.precio_unit;
        } else {
            // Agregar un nuevo artículo al carrito
            var nuevoItem = {
                cod_articulo: articulo.cod_articulo,
                nombre: articulo.nombre,
                precio_unit: articulo.precio_unit,
                cantidad: cantidad,
                subtotal: cantidad * articulo.precio_unit
            };
            carritoArticulos.push(nuevoItem);
        }

        actualizarTablaCarrito();
    }
}

function actualizarTablaCarrito() {
    var carrito = document.getElementById("tabla-carrito");
    limpiarTabla(carrito);
    crearEncabezadoCarrito(carrito)

    var totalCarrito = 0; // Variable para almacenar la suma de los subtotales
    var indice = 0;

    carritoArticulos.forEach(function (articulo) {
        var filaCarrito = document.createElement("tr");
        filaCarrito.id = "carrito-" + articulo.cod_articulo;

        var celdaCodigo = document.createElement("td");
        var inputCodigo = document.createElement("input");
        inputCodigo.type="text";
        inputCodigo.name="detalleFacturas[" + indice.toString() + "].articulo.cod_articulo";
        inputCodigo.value = articulo.cod_articulo;
        celdaCodigo.appendChild(inputCodigo);
        filaCarrito.appendChild(celdaCodigo);

        var celdaNombre = document.createElement("td");
        var inputNombre = document.createElement("input");
        inputNombre.type="text";
        inputNombre.name="detalleFacturas[" + indice.toString() + "].articulo.nombre";
        inputNombre.value = articulo.nombre;
        celdaNombre.appendChild(inputNombre);
        filaCarrito.appendChild(celdaNombre);

        var celdaPrecio = document.createElement("td");
        var inputPrecio = document.createElement("input");
        inputPrecio.type="text";
        inputPrecio.name="detalleFacturas[" + indice.toString() + "].precio";
        inputPrecio.value = articulo.precio_unit;
        celdaPrecio.appendChild(inputPrecio);
        filaCarrito.appendChild(celdaPrecio);

        var celdaCantidadCarrito = document.createElement("td");
        var inputCantidadCarrito = document.createElement("input");
        inputCantidadCarrito.type="text";
        inputCantidadCarrito.name="detalleFacturas[" + indice.toString() + "].cantidad";
        inputCantidadCarrito.value = articulo.cantidad;
        celdaCantidadCarrito.appendChild(inputCantidadCarrito);
        filaCarrito.appendChild(celdaCantidadCarrito);

        var celdaSubtotal = document.createElement("td");
        var subtotal = articulo.subtotal;
        celdaSubtotal.textContent = subtotal.toFixed(2);
        filaCarrito.appendChild(celdaSubtotal);

        var celdaBorrar = document.createElement("td");
        var botonBorrar = document.createElement("button");
        botonBorrar.textContent = "Borrar";
        botonBorrar.addEventListener("click", function () {
            eliminarDelCarrito(articulo.cod_articulo);
        });
        celdaBorrar.appendChild(botonBorrar);
        filaCarrito.appendChild(celdaBorrar);

        carrito.appendChild(filaCarrito);

        totalCarrito += subtotal; // Sumar el subtotal al total del carrito
        indice++;
    });

    var totalCarritoElemento = document.getElementById("total-carrito");
    totalCarritoElemento.textContent = "$" + totalCarrito.toFixed(2);

}

function eliminarDelCarrito(cod_articulo) {
    var indice = carritoArticulos.findIndex(function (item) {
        return item.cod_articulo === cod_articulo;
    });

    if (indice !== -1) {
        var articuloEliminado = carritoArticulos[indice]; // Definir la variable articuloEliminado
        carritoArticulos.splice(indice, 1);
        actualizarTablaCarrito();
        actualizarCantidadALlevar(articuloEliminado.cod_articulo, 0);
    }

}

function actualizarCantidadALlevar(cod_articulo, cantidad) {
    var cantidadInput = document.getElementById("cant-" + cod_articulo);
    cantidadInput.value = cantidad;
}

window.addEventListener('load', function() {
    var nombresInput = document.getElementById("nombres");
    var apellidosInput = document.getElementById("apellidos");
    var nombresCompletosInput = document.getElementById("nombresCompletos");

    nombresInput.addEventListener("input", actualizarNombresCompletos);
    apellidosInput.addEventListener("input", actualizarNombresCompletos);

    function actualizarNombresCompletos() {
        var nombre = nombresInput.value;
        var apellido = apellidosInput.value;
        var nombreCompleto = nombre + " " + apellido;
        nombresCompletosInput.value = nombreCompleto;
        console.log(nombreCompleto)
    }
});

