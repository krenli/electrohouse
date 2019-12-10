function fechaHoy() {

    var hoy = new new Date().toJSON().slice(0, 10);



    console.log(hoy);
    $("#fecha_factura_venta").val(hoy);
}
function addZero(i) {
    if (i < 10) {
        i = '0' + i;
    }
    return i;
}



function buscarIdFacturaVenta() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarId.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#idfactura_venta").val(json.idfactura_venta);
            $("#idcliente").val(json.idcliente);
            $("#nombre_cliente").val(json.nombre_cliente);
            $("#ruc_cliente").val(json.ruc_cliente);
            $("#fecha_factura_venta").val(json.fecha_factura_venta);
            $("#idtipo_factura").val(json.idtipo_factura);
            $("#nombre_tipo_factura").val(json.nombre_tipo_factura);

            $("#cantidad_cuotas").val(json.cantidad_cuotas);
            $("#numero_factura_venta").val(json.numero_factura_venta);
            $("#idtimbrado").val(json.idtimbrado);
            $("#idtipo_personal").val(json.idtipo_personal);
            var a = 0;
            var b = 0;
            while (a === 0) {
                b = b + 1;
                $("#idtimbrado").val(b);
                buscarIdTimbrado();
                a = $("#idtimbrado").val();
            }

            //var fecha = $("#fecha_factura_venta").serialize();


            $("#contenidoDetalle").html(json.contenido_detalle);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                $("#botonCuenta").prop('disabled', true);
                //siguienteCampo("#id_tipofactura", "#botonAgregar", true);
                $("#detalle").prop('hidden', true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                if (json.nombre_tipo_factura === "CONTADO") {
                    $("#botonCuenta").prop('disabled', true);
                } else {
                    if (json.nombre_tipo_factura === "CREDITO") {
                        $("#botonCuenta").prop('disabled', false);
                    }

                }

                //siguienteCampo("#id_tipopedido", "#botonModificar", true);
                $("#detalle").prop('hidden', false);
            }

        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreFacturaVenta() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombre.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#idfactura_venta").val(id);
                $("#nombre_cliente").focus();
                buscarIdFacturaVenta();
                setTimeout('buscarIdTotal()',1000);
                
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function agregarFacturaVenta() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {

            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#botonAgregar").prop('disabled', true);
            $("#botonImprimir").prop('disabled', true);
            $("#detalle").prop('hidden', false);
            $("#idfactura_venta").val(json.idfactura_venta);
            buscarIdFacturaVenta();
            if (json.nombre_tipo_factura === "CONTADO") {

                $("#botonCuenta").prop('disabled', true);
            } else {

                $("#botonCuenta").prop('disabled', false);
            }
            // $("#id_pedido").focus;
            //$("#id_pedido").select();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function agregarCuenta(n) {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarCuenta.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {

            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            //limpiarFormulario();

            $("#mensajes").html(json.mensaje);
            if (n > 0) {
                $("#mensajes").html("Cuenta agregada");
            }
            //alert("logrado");
            $("#botonCuenta").prop('disabled', true);
            $("#botonImprimir").prop('disabled', true);
            //$("#detalle").prop('hidden', false);
            //$("#id_factura_venta").val(json.id_factura_venta);
            //buscarIdFacturaVenta();

            $("#idfactura_venta").focus;
            $("#idfactura_venta").select();

            buscarIdFacturaVenta();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar la cuenta.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function modificarFacturaVenta() {
    var datosFormulario = $("#formPrograma").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/modificar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#idfactura_venta").focus;
            $("#idfactura_venta").select();
            buscarIdFacturaVenta();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function eliminarFacturaVenta() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            eliminarFacturaDetalle();
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#idfactura_venta").focus;
            $("#idfactura_venta").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}


function buscarIdCliente() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCliente.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#idcliente").val(json.idcliente);
            $("#nombre_cliente").val(json.nombre_cliente);
            $("#ruc_cliente").val(json.ruc_cliente);

        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreCliente() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreCliente.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#idcliente").val(id);
                $("#nombre_cliente").focus();
                buscarIdCliente();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}


function validarFormulario() {
    var valor = true;
    if ($("#fecha_factura_venta").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Fecha no puede estar vacio.");
        $("#fecha_factura_venta").focus();
    }

    if ($("#idcliente").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("Cliente no puede estar vacio.");
        $("#idcliente").focus();
    }

    /*if ($("#nombre_cliente").val().length < 2) {
     valor = false;
     $("#mensajes").html("Cliente no puede estar vacio.");
     $("#id_proveedor").focus();
     }*/

    if ($("#idtipo_factura").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("Tipo Factura no puede estar vacio.");
        $("#idtipo_factura").focus();
    }

    if ($("#idtimbrado").val().trim() === "0" || $("#idtimbrado").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Timbrado no puede estar vacio.");
        $("#idtimbrado").focus();
    }

    /*if ($("#cantidad_cuotas").val().trim() === "") {
     valor = false;
     $("#mensajes").html("Cuotas no puede estar vacio.");
     $("#cantidad_cuotas").focus();
     }*/

    return valor;


}


function limpiarFormulario() {
    $("#idfactura_venta").val("");
    $("#fecha_factura_venta").val("");
    //$("#nombre_tipo_factura").val("");
    $("#nombre_cliente").val("");
    $("#idcliente").val("");
    $("#idtipo_factura").val("");
    /*$("#subtotal_5").val("");
     $("#subtotal_10").val("");
     $("#subtotal_exenta").val("");*/
    $("#cantidad_cuotas").val("");

}
function agregarLinea() {
    $("#idfactura_detalle_venta").val("0");
    $("#idproducto").val("0");
    $("#nombre_producto").val("");
    $("#cantidad_venta").val("0");
    $("#costo_producto").val("0");
    $("#precio_producto").val("0");
    $("#iva_producto").val("0");
    $("#subtotal_venta").val("0");
    /*$("#ssubtotal_5").val("0");
     $("#ssubtotal_10").val("0");
     $("#ssubtotal_exenta").val("0");*/
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#idproducto").focus();
    $("#idproducto").select();
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    //siguienteCampo("#horas_factura_detalle_ventas", "#botonAgregarLinea", true);
}
function editarLinea(id) {
    $("#idfactura_detalle_venta").val(id);
    $("#idproducto").val("0");
    $("#nombre_producto").val("");
    $("#cantidad_venta").val("0");
    $("#costo_producto").val("0");
    $("#precio_producto").val("0");
    $("#iva_producto").val("0");
    $("#subtotal_venta").val("0");
    /*$("#ssubtotal_5").val("0");
     $("#ssubtotal_10").val("0");
     $("#ssubtotal_exenta").val("0");*/
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#idproducto").focus();
    $("#idproducto").select();
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdFacturaDetalle();
    //siguienteCampo("#cantidad_venta", "#botonModificarLinea", true);
}
// pedidosproductos
function buscarIdFacturaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdFacturaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#idproducto").val(json.idproducto);
            $("#nombre_producto").val(json.nombre_producto);
            $("#costo_producto").val(json.costo_producto);
            $("#precio_producto").val(json.precio_producto);
            $("#iva_producto").val(json.iva_producto);
            $("#cantidad_venta").val(json.cantidad_venta);
            //$("#subtotal_venta").val(json.subtotal_venta);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
/*function buscarIdFacturaFacturaDetalle() {
 var datosFormulario = $("#formPrograma").serialize();
 $.ajax({
 type: 'POST',
 url: 'jsp/buscarIdFacturaFacturaDetalle.jsp',
 data: datosFormulario,
 dataType: 'json',
 beforeSend: function (objeto) {
 $("#mensajes").html("Enviando datos al Servidor ...");
 },
 success: function (json) {
 $("#mensajes").html(json.mensaje);
 $("#contenidoDetalle").html(json.contenido_detalle);
 },
 error: function (e) {
 $("#mensajes").html("No se pudo recuperar los datos.");
 },
 complete: function (objeto, exito, error) {
 if (exito === "success") {
 }
 }
 });
 }*/
function agregarFacturaDetalle() {
    var datosFormulario = $("#formLinea").serialize();

    var idfactura_venta = $("#idfactura_venta").val();
    datosFormulario += "&idfactura_venta=" + idfactura_venta;
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarFacturaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            if (json.cantidad_stock !== -1) {
                $("#mensajes").html(json.mensaje);
                $("#panelLinea").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
                buscarIdFacturaVenta();
            } else {

                $("#mensajes").html(json.mensaje);
                $("#cantidad_venta").val("");
                $("#cantidad_venta").focus();
            }

        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function modificarFacturaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var idfactura_venta = $("#idfactura_venta").val();
    datosFormulario += "&idfactura_venta=" + idfactura_venta;
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarFacturaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdFacturaVenta();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function eliminarFacturaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var idfactura_venta = $("#idfactura_venta").val();
    datosFormulario += "&idfactura_venta=" + idfactura_venta;
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarFacturaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json)
        {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdFacturaVenta();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
//// productos
function buscarIdProducto() {
    var datosFormulario = $("#formLinea").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdProducto.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            var venta = dar_formato_numero(json.costo_producto, ",", ".");
            var compra = dar_formato_numero(json.precio_producto, ",", ".");
            $("#idproducto").val(json.idproducto);
            $("#nombre_producto").val(json.nombre_producto);
            $("#costo_producto").val(venta);
            $("#precio_producto").val(compra);
            $("#iva_producto").val(json.iva_producto);

            // alert(json.codigo_producto);
            /* $("#subtotal_5").val("");
             $("#subtotal_10").val("");
             $("#subtotal_exenta").val("");
             $("#subtotal_venta").val("");
             $("#cantidad_venta").val("");*/
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreProducto() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreProducto.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#idproducto").val(id);
                $("#nombre_producto").focus();
                buscarIdProducto();
                $("#buscar").fadeOut("slow");
                $("#panelLinea").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function buscarIdTipo_factura() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdTipoFactura.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#idtipo_factura").val(json.idtipo_factura);
            $("#nombre_tipo_factura").val(json.nombre_tipo_factura);
            if (json.nombre_tipo_factura === "CONTADO") {

                $("#cuota").prop('hidden', true);
            } else {

                $("#cuota").prop('hidden', false);
            }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function buscarNombreTipo_factura() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreTipoFactura.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#idtipo_factura").val(id);
                $("#nombre_tipo_factura").focus();
                buscarIdTipo_factura();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {

            }
        }
    });
}

function subtotal(e) {
    //document.getElementById(e.id).value;
    var valor, valor1, total;
    valor = $("#precio_producto").val();
    valor1 = $("#cantidad_venta").val();
    total = valor * valor1;
    $("#subtotal_venta").val(total);
    //iva();

}

/*function iva(e) {
 //document.getElementById(e.id).value;
 var valor, valor1, total, iva5,iva10;
 valor = $("#iva_producto").val();
 valor1 = $("#subtotal_venta").val();
 iva5= valor1 / 21;
 iva10=valor1 / 11;
 total = (valor1 * valor)/100;
 
 if ($("#iva_producto").val().trim() === "10"){
 
 $("#ssubtotal_10").val(iva10);
 $("#ssubtotal_5").val(0);
 $("#ssubtotal_exenta").val(0);
 }else{
 if ($("#iva_producto").val().trim() === "5"){
 
 $("#ssubtotal_5").val(iva5);
 $("#ssubtotal_10").val(0);
 $("#ssubtotal_exenta").val(0);
 }else{
 $("#ssubtotal_5").val(0);
 $("#ssubtotal_10").val(0);
 $("#ssubtotal_exenta").val(0);
 }
 }
 }*/

function validarcantidad() {
    var valor = true;
    if ($("#cantidad_venta").val().trim() === "" || $("#cantidad_venta").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("Cantidad debe ser mayor a 0.");
        $("#cantidad_venta").focus();
    }


    return valor;
}

/*function cuotas(e) {
 var valor1;
 valor1 = $("#nombre_tipo_factura").val();
 if (valor1 === "CONTADO" || valor1 === "Contado" || valor1 === "contado") {
 $("#cantidad_cuotas").val(100);
 }else{
 
 }
 }*/
function buscarIdTimbrado() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdTimbrado.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#idtimbrado").val(json.idtimbrado);
            //$("#numero_timbrado").val(json.numero_timbrado);
            //$("#fecha_inicio_timbrado").val(json.fecha_inicio_timbrado);
            //$("#fecha_vencimiento_timbrado").val(json.fecha_vencimiento_timbrado);
            //$("#fecha_actual_timbrado").val(json.fecha_actual_timbrado);
            //$("#desde_timbrado").val(json.desde_timbrado);
            //$("#hasta_timbrado").val(json.hasta_timbrado);
            //$("#estado_timbrado").val(json.estado_timbrado);
            $("#idtipo_personal").val(json.idtipo_personal);
            //$("#nombre_puesto").val(json.nombre_puesto);
            //$("#id_establecimiento").val(json.id_establecimiento);
            //$("#nombre_establecimiento").val(json.nombre_establecimiento);
            var fecha = $("#fecha_factura_venta").serialize();


        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function soloLetras(e) {
    key = e.keyCode || e.which;
    tecla = String.fromCharCode(key).toLowerCase();
    letras = " áéíóúabcdefghijklmnñopqrstuvwxyz";
    especiales = "8-37-39-46";

    tecla_especial = false;
    for (var i in especiales) {
        if (key === especiales[i]) {
            tecla_especial = true;
            break;
        }
    }

    if (letras.indexOf(tecla) === -1 && !tecla_especial) {
        alert("ingrese solo letras");
        return false;
    }
}
/*function soloLetras(e){
 tecla = (document.all)? e.keyCode : e.which;
 if (tecla===8) return true;
 
 patron =/[A-Za-zñÑ]/;
 te = String.fromCharCode(tecla);
 return patron.test(te);
 
 }*/

function SoloNumeros(evt) {
    if (window.event) {//asignamos el valor de la tecla a keynum
        keynum = evt.keyCode; //IE
    } else {
        keynum = evt.which; //FF
    }
    //comprobamos si se encuentra en el rango numérico y que teclas no recibirá.
    if ((keynum > 47 && keynum < 58) || keynum === 8 || keynum === 13 || keynum === 6) {
        return true;
    } else {
        alert("Solo ingrese numeros");
        return false;
    }
}

function agregarPagare() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarPagare.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);

            //$("#id").val(json.id);
            //$("#id_caja").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#idcaja").focus();
        }
    });
}

function NumerosaLetras(cantidad) {

    var numero = 0;
    cantidad = parseFloat(cantidad);

    if (cantidad == "0.00" || cantidad == "0") {
        return "CERO con 00/100 ";
    } else {
        var ent = cantidad.toString().split(".");
        var arreglo = separar_split(ent[0]);
        var longitud = arreglo.length;

        switch (longitud) {
            case 1:
                numero = unidades(arreglo[0]);
                break;
            case 2:
                numero = decenas(arreglo[0], arreglo[1]);
                break;
            case 3:
                numero = centenas(arreglo[0], arreglo[1], arreglo[2]);
                break;
            case 4:
                numero = unidadesdemillar(arreglo[0], arreglo[1], arreglo[2], arreglo[3]);
                break;
            case 5:
                numero = decenasdemillar(arreglo[0], arreglo[1], arreglo[2], arreglo[3], arreglo[4]);
                break;
            case 6:
                numero = centenasdemillar(arreglo[0], arreglo[1], arreglo[2], arreglo[3], arreglo[4], arreglo[5]);
                break;
        }

        ent[1] = isNaN(ent[1]) ? '00' : ent[1];

        return numero + " con " + ent[1] + "/100";
    }
}

function unidades(unidad) {
    var unidades = Array('UN ', 'DOS ', 'TRES ', 'CUATRO ', 'CINCO ', 'SEIS ', 'SIETE ', 'OCHO ', 'NUEVE ');


    return unidades[unidad - 1];
}

function decenas(decena, unidad) {
    var diez = Array('ONCE ', 'DOCE ', 'TRECE ', 'CATORCE ', 'QUINCE', 'DIECISEIS ', 'DIECISIETE ', 'DIECIOCHO ', 'DIECINUEVE ');
    var decenas = Array('DIEZ ', 'VEINTE ', 'TREINTA ', 'CUARENTA ', 'CINCUENTA ', 'SESENTA ', 'SETENTA ', 'OCHENTA ', 'NOVENTA ');

    if (decena == 0 && unidad == 0) {
        return "";
    }

    if (decena == 0 && unidad > 0) {
        return unidades(unidad);
    }

    if (decena == 1) {
        if (unidad == 0) {
            return decenas[decena - 1];
        } else {
            return diez[unidad - 1];
        }
    } else if (decena == 2) {
        if (unidad == 0) {
            return decenas[decena - 1];
        } else if (unidad == 1) {
            return veinte = "VEINTI" + "UNO";
        } else {
            return veinte = "VEINTI" + unidades(unidad);
        }
    } else {

        if (unidad == 0) {
            return decenas[decena - 1] + " ";
        }
        if (unidad == 1) {
            return decenas[decena - 1] + " Y " + "UNO";
        }

        return decenas[decena - 1] + " Y " + unidades(unidad);
    }
}

function centenas(centena, decena, unidad) {
    var centenas = Array("CIENTO ", "DOSCIENTOS ", "TRESCIENTOS ", "CUATROCIENTOS ", "QUINIENTOS ", "SEISCIENTOS ", "SETECIENTOS ", "OCHOCIENTOS ", "NOVECIENTOS ");

    if (centena == 0 && decena == 0 && unidad == 0) {
        return "";
    }
    if (centena == 1 && decena == 0 && unidad == 0) {
        return "CIEN ";
    }

    if (centena == 0 && decena == 0 && unidad > 0) {
        return unidades(unidad);
    }

    if (decena == 0 && unidad == 0) {
        return centenas[centena - 1] + "";
    }

    if (decena == 0) {
        var numero = centenas[centena - 1] + "" + decenas(decena, unidad);
        return numero.replace(" Y ", " ");
    }
    if (centena == 0) {

        return  decenas(decena, unidad);
    }

    return centenas[centena - 1] + "" + decenas(decena, unidad);

}

function unidadesdemillar(unimill, centena, decena, unidad) {
    var numero = unidades(unimill) + " MIL " + centenas(centena, decena, unidad);
    numero = numero.replace("UN  MIL ", "MIL ");
    if (unidad == 0) {
        return numero.replace(" Y ", " ");
    } else {
        return numero;
    }
}

function decenasdemillar(decemill, unimill, centena, decena, unidad) {
    var numero = decenas(decemill, unimill) + " MIL " + centenas(centena, decena, unidad);
    return numero;
}

function centenasdemillar(centenamill, decemill, unimill, centena, decena, unidad) {

    var numero = 0;
    numero = centenas(centenamill, decemill, unimill) + " MIL " + centenas(centena, decena, unidad);

    return numero;
}

function separar_split(texto) {
    var contenido = new Array();
    for (var i = 0; i < texto.length; i++) {
        contenido[i] = texto.substr(i, 1);
    }
    return contenido;
}
function buscarIdTotal() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscartotal.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#totalp").val(json.total);
        

        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}