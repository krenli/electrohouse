function hidden() {
    var t = $("#id_tfac").val();
    
    if (t === "1") {


        $("#tf").prop('hidden', false);
        $("#tf1").prop('hidden', true);
        $("#idfactura_venta").prop('readonly', false);
        $("#idcuenta").val(0);
        $("#idfactura_venta").val(0);
        $("#total").val(0);
        $("#vuelto").prop('style', 'color: black');
        $("#importe").val(0);
        $("#idforma_pago").val(0);
        $("#vuelto").val(0);

    }else{
        if (t === "2"){
            $("#tf").prop('hidden', true);
            $("#tf1").prop('hidden', false);
            $("#idfactura_venta").prop('readonly', true);
            $("#idfactura_venta").val(0);
            $("#nro_cuota").val(0);
            $("#total").val(0);
            $("#vuelto").prop('style', 'color: black');
            $("#importe").val(0);
        $("#idforma_pago").val(0);
        $("#vuelto").val(0);
            
        }
        
    }
}

function agregarCaja() {
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
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#idcaja").focus();
            $("#idcaja").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#idcaja").focus();
        }
    });
}

function modificarCaja() {
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
            limpiarFormulario();
            $("#idcaja").focus();
            $("#idcaja").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {

        }
    });
}

function eliminarCaja() {
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
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#idcaja").focus();
            $("#idcaja").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo eliminar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {

            }
        }
    });
}

function buscarIdCaja() {
    var datosFormulario = $("#formPrograma").serialize();


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
            $("#idcaja").val(json.idcaja);
            $("#fecha_apertura").val(json.fecha_apertura);
            $("#monto_inicial").val(json.monto_inicial);
            $("#estado_caja").val(json.estado_caja);
            $("#contenidoDetalle").html(json.contenido_detalle);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                $("#detalle").prop('hidden', true);
                //siguienteCampo("#nombre_caja", "#botonAgregar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', true);
                $("#detalle").prop('hidden', false);
                //siguienteCampo("#nombre_caja", "#botonModificar", true);
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

function buscarNombreCaja() {
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
                $("#idcaja").val(id);
                //$("#nombre_caja").focus();
                buscarIdCaja();
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

/*function buscarNombreCaja() {
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
 $("#idcaja").val(id);
 //    $("#nombre_proveedor").focus();
 buscarIdCaja();
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
 }*/

function buscarCaja() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarCaja.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);


            if (json.nuevo === "false") {
                $("#nombre_caja").val("");
                $("#nombre_caja").focus();
            } else {

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

function buscarIdCajaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCajaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#iddetallecaja").val(json.iddetallecaja);
//            $("#id_factura_caja").val(json.id_articulo);
//            $("#precio_venta").val(json.precio_venta);
//            $("#precio_compra").val(json.precio_compra);
//            $("#iva_articulo").val(json.iva_articulo);
//            $("#cantidad_venta").val(json.cantidad_venta);

            $("#idfactura_venta").val(json.idfactura_venta);
            //$("#numero_factura_venta").val(json.numero_factura_venta);
            $("#idforma_pago").val(json.idforma_pago);
            //$("#nombre_forma_pago").val(json.nombre_forma_pago);



            //$("#total").val(json.total);
            $("#importe").val(json.importe);
            //$("#vuelto").val(json.vuelto);
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

function agregarCajaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var idcaja = $("#idcaja").val();
    datosFormulario += "&idcaja=" + idcaja;
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarCajaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            //$("#panelLinea").fadeOut("slow");
            //$("#panelPrograma").fadeIn("slow");
            $("#botonAgregarLinea").prop('disabled', true);
            buscarIdCaja();
            
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
function modificarCajaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var idcaja = $("#idcaja").val();
    datosFormulario += "&idcaja=" + idcaja;

    alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarCajaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdCaja();
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
function eliminarCajaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var idcaja = $("#idcaja").val();
    datosFormulario += "&idcaja=" + idcaja;
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarCajaDetalle.jsp',
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
            buscarIdCaja();

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

function buscarIdVenta() {
    var datosFormulario = $("#formLinea").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdVenta.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#idfactura_venta").val(json.idfactura_venta);
            $("#total").val(json.total);
            $("#idcuenta").val(0);
            //$("#vuelto").val(json.vuelto);
            //$("#idcliente").val(json.idcliente);
            //$("#nombre_cliente").val(json.nombre_cliente);
            //$("#ruc_cliente").val(json.ruc_cliente);
            //$("#fecha_factura_venta").val(json.fecha_factura_venta);
            //$("#idtipo_factura").val(json.idtipo_factura);
            //$("#nombre_tipo_factura").val(json.nombre_tipo_factura);
            /*$("#subtotal_5").val(json.subtotal_5);
             $("#subtotal_10").val(json.subtotal_10);
             $("#subtotal_exenta").val(json.subtotal_exenta);*/
            //$("#cantidad_cuotas").val(json.cantidad_cuotas);
            //var fecha = $("#fecha_factura_venta").serialize();

            //$("#contenidoDetalle").html(json.contenido_detalle);
            /*if (json.nombre_tipo_factura === "CONTADO") {
             
             $("#cuota").prop('hidden', true);
             } else {
             
             $("#cuota").prop('hidden', false);
             }*/

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

function buscarIdCuenta() {
    var datosFormulario = $("#formLinea").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCuenta.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#idcuenta").val(json.idcuenta);
            $("#nro_cuota").val(json.nro_cuota);
            $("#idfactura_venta").val(json.idfactura_venta);
            $("#total").val(json.total);
            //$("#vuelto").val(json.vuelto);
            //$("#idcliente").val(json.idcliente);
            //$("#nombre_cliente").val(json.nombre_cliente);
            //$("#ruc_cliente").val(json.ruc_cliente);
            //$("#fecha_factura_venta").val(json.fecha_factura_venta);
            //$("#idtipo_factura").val(json.idtipo_factura);
            //$("#nombre_tipo_factura").val(json.nombre_tipo_factura);
            /*$("#subtotal_5").val(json.subtotal_5);
             $("#subtotal_10").val(json.subtotal_10);
             $("#subtotal_exenta").val(json.subtotal_exenta);*/
            //$("#cantidad_cuotas").val(json.cantidad_cuotas);
            //var fecha = $("#fecha_factura_venta").serialize();

            //$("#contenidoDetalle").html(json.contenido_detalle);
            /*if (json.nombre_tipo_factura === "CONTADO") {
             
             $("#cuota").prop('hidden', true);
             } else {
             
             $("#cuota").prop('hidden', false);
             }*/

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
        url: 'jsp/buscarNombreVenta.jsp',
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
                buscarIdVenta();
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

function buscarNombreCuenta() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreCuenta.jsp',
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
                //var id1 = $(this).find("td:second").html();
                $("#panelBuscar").html("");
                //$("#idfactura_venta").val(id1);
                $("#idcuenta").val(id);
                //$("#total").val(json.total);
                buscarIdCuenta();
                //$("#nombre_cliente").focus();
                //buscarIdCuenta();
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

function validarFormulario() {
    var valor = true;
    if ($("#fecha_apertura").val().trim() === "" ) {
        valor = false;
        $("#mensajes").html("Seleccionar fecha.");
        $("#fecha_apertura").focus();
    }
    if ($("#monto_inicial").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Monto no puede estar vacio.");
        $("#monto_inicial").focus();
    }
    
    

    return valor;
}

function validarFormularioDetalle() {
    var valor = true;
    if ($("#idfactura_venta").val().trim() === "" || $("#idfactura_venta").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("Seleccionar Venta.");
        $("#idventa").focus();
    }
    if ($("#idforma_pago").val().trim() === "" || $("#idforma_pago").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("Seleccionar forma pago.");
        $("#idforma_pago").focus();
    }
    
    if ($("#importe").val().trim() === "" || $("#importe").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("Importe no puede estar vacio.");
        $("#importe").focus();
    }
    
    /*if ($("#importe").val().trim() < $("#total").val().trim()) {
        valor = false;
        $("#mensajes").html("Importe no debe ser menor que el total");
        $("#importe").focus();
    }
*/
    return valor;
}

function limpiarFormulario() {
    $("#idcaja").val("");
    $("#fecha_apertura").val("");
    $("#monto_inicial").val("");
    $("#estado_caja").val("");

}

function buscarIdForma_pago() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdPago.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#idforma_pago").val(json.idforma_pago);
            $("#nombre_forma_pago").val(json.nombre_forma_pago);

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

function buscarNombreForma_pago() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombrePago.jsp',
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
                $("#idforma_pago").val(id);
                $("#nombre_forma_pago").focus();
                buscarIdForma_pago();
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

function agregarLinea() {
    $("#iddetallecaja").val("0");
    $("#idfactura_venta").val("0");
    $("#numero_factura_venta").val("");
    // $("#costo_producto").val("0");
    //  $("#iva_producto").val("");
    //  $("#cantidad_productocaja").val("0");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#idfactura_venta").focus();
    $("#idfactura_venta").select();
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    //siguienteCampo("#horas_detallecaja", "#botonAgregarLinea", true);
}
function editarLinea(id) {
    $("#iddetallecaja").val(id);
    $("#idfactura_venta").val("0");
    //$("#numero_factura_venta").val("");
//    $("#costo_producto").val("");
//    $("#iva_producto").val("");
//    $("#cantidad_productocaja").val("0");
    $("#idcuenta").val("0");
    $("#importe").val("0");
    $("#vuelto").val("0");
    $("#total").val("0");
    $("#idforma_pago").val("0");
    //$("#nombre_forma_pago").val("");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    
    //$("#id_tfac").focus();
    //$("#id_tfac").select();
    /*if ($("#id_tfac").val().trim() === "CREDITO"){
       $("#idcuenta").focus();
    $("#idcuenta").select(); 
    }*/
    if ($("#id_tfac").val().trim() === "CONTADO"){
       $("#idfactura_venta").focus();
    $("#idfactura_venta").select(); 
    }
    //$("#idfactura_venta").focus();
    //$("#idfactura_venta").select();
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdCajaDetalle();
    buscarIdCuenta();
    //siguienteCampo("#cantidad_productocaja", "#botonModificarLinea", true);
}
// cajasproductos
function buscarIdCajaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    // alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCajaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#idfactura_venta").val(json.idfactura_venta);
            $("#numero_factura_venta").val(json.numero_factura_venta);
            $("#idforma_pago").val(json.idforma_pago);
            //$("#nombre_forma_pago").val(json.nombre_forma_pago);
//            buscarIdVenta();
//            var total1 = $("#total").val();
//            alert("total "+ total1);
//            var importe1 = $("#importe").val(json.importe);
//            var vuelto1 = (total1 - importe1);
            //$("#total").val(json.total);
            $("#importe").val(json.importe);
            //$("#vuelto").val(vuelto1);


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

function vuelto(e) {
    //document.getElementById(e.id).value;
    var valor1, valor2, vuelto;
    valor1 = $("#total").val();
    valor2 = $("#importe").val();
    
    vuelto = (valor2 - valor1);
    if (vuelto > 0){
        $("#vuelto").prop('style', 'color: green');
        $("#vuelto").val(vuelto);
    }else{
        if (vuelto < 0){
        $("#vuelto").prop('style', 'color: red');
        $("#vuelto").val(vuelto);
        }else{
            $("#vuelto").prop('style', 'color: black');
            $("#vuelto").val(vuelto);
        }
       
    }
    
    
}

function agregarRecibo() {
    var datosFormulario = $("#formLinea").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarRecibo.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            
            //$("#id").val(json.id);
            //$("#idcaja").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#idcaja").focus();
        }
    });
}

function limpiarLinea() {
    $("#iddetallecaja").val("0");
    $("#idfactura_venta").val("0");
    //$("#numero_factura_venta").val("");
//    $("#costo_producto").val("");
//    $("#iva_producto").val("");
//    $("#cantidad_productocaja").val("0");
    $("#idcuenta").val("0");
    $("#importe").val("0");
    $("#vuelto").val("0");
    $("#total").val("0");
    $("#nro_cuota").val("0");
    $("#idforma_pago").val("0");
    //$("#nombre_forma_pago").val("");
    //$("#panelLinea").fadeIn("slow");
    //$("#panelPrograma").fadeOut("slow");
    
    //$("#id_tfac").focus();
    //$("#id_tfac").select();
    /*if ($("#id_tfac").val().trim() === "CREDITO"){
       $("#idcuenta").focus();
    $("#idcuenta").select(); 
    }*/
    /*if ($("#id_tfac").val().trim() === "CONTADO"){
       $("#idfactura_venta").focus();
    $("#idfactura_venta").select(); 
    }*/
    //$("#idfactura_venta").focus();
    //$("#idfactura_venta").select();
    //$("#botonAgregarLinea").prop('disabled', false);
    //$("#botonModificarLinea").prop('disabled', true);
    //$("#botonEliminarLinea").prop('disabled', true);
    //buscarIdCajaDetalle();
    //buscarIdCuenta();
    //siguienteCampo("#cantidad_productocaja", "#botonModificarLinea", true);
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