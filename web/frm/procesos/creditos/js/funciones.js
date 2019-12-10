function agregarAprobacionCredito() {
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
            $("#idaprobacioncredito").focus();
            $("#idaprobacioncredito").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error){
            $("#idaprobacioncredito").focus();
        }
    });
}

function modificarAprobacionCredito() {
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
            $("#idaprobacioncredito").focus();
            $("#idaprobacioncredito").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
            
        }
    });
}

function eliminarAprobacionCredito() {
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
            $("#idaprobacioncredito").focus();
            $("#idaprobacioncredito").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo eliminar los datos.");
        },
        complete: function (objeto, exito, error){
            if (exito === "success") {
                
            }
        }
    });
}

function buscarIdAprobacionCredito() {
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
            $("#idaprobacioncredito").val(json.idaprobacioncredito);
            $("#fecha_aprobacioncredito").val(json.fecha_aprobacioncredito);
            $("#descripcion_aprobacioncredito").val(json.descripcion_aprobacioncredito);
            $("#estado_aprobacioncredito").val(json.estado_aprobacioncredito);
            $("#referencia_personal").val(json.referencia_personal);
            $("#referencia_comercial").val(json.referencia_comercial);
            $("#limite_credito").val(json.limite_credito);
            if (json.nuevo === "true") {
               $("#botonAgregar").prop('disabled', false);
               $("#botonModificar").prop('disabled', true);
               $("#botonEliminar").prop('disabled', true);
               siguienteCampo("#nombre_aprobacioncredito", "#botonAgregar", true);
           } else {
               $("#botonAgregar").prop('disabled', true);
               $("#botonModificar").prop('disabled', false);
               $("#botonEliminar").prop('disabled', true);
               siguienteCampo("#nombre_aprobacioncredito", "#botonModificar", true);
           }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error){
           if (exito === "success"){
           }
        }
    });
}

function buscarNombreAprobacionCredito() {
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
       success: function (json){
           $("#mensajes").html(json.mensaje);
           $("#contenidoBusqueda").html(json.contenido);
           $("#contenidoBusqueda").fadeIn("slow");
           $("tbody tr").on("click", function(){
              var id = $(this).find("td:first").html();
              $("#panelBuscar").html("");
              $("#idaprobacioncredito").val(id);
              $("#estado_aprobacioncredito").focus();
              buscarIdAprobacionCredito();
              $("#buscar").fadeOut("slow");
              $("#panelPrograma").fadeIn("slow");
          });
       },
       error: function(e) {
           $("#mensajes").html("No se pudo recuperar los datos.");
       },
       complete: function(objeto, exito, error) {
           if (exito === "success"){
               
           }
       }
    });
}

function buscarAprobacionCredito() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarAprobacionCredito.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            
            
            if (json.nuevo === "false") {
               $("#nombre_aprobacioncredito").val("");
               $("#nombre_aprobacioncredito").focus();
           } else {
               
           }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error){
           if (exito === "success"){
           }
        }
    });
}

function validarFormulario() {
    var valor = true;
    if ($("#fecha_aprobacioncredito").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Fecha no puede estar vacio.");
        $("#fecha_aprobacioncredito").focus();
    }
    
    if ($("#descripcion_aprobacioncredito").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Descripcion no puede estar vacio.");
        $("#descripcion_aprobacioncredito").focus();
    }
    
    if ($("#estado_aprobacioncredito").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Estado no puede estar vacio.");
        $("#estado_aprobacioncredito").focus();
    }
    
    if ($("#referencia_comercial").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Referencia comercial no puede estar vacio.");
        $("#referencia_comercial").focus();
    }
    
    if ($("#referencia_personal").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Referencia personal no puede estar vacio.");
        $("#referencia_personal").focus();
    }
    
    if ($("#limite_credito").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Limite no puede estar vacio.");
        $("#limite_credito").focus();
    }

    return valor;
}

function limpiarFormulario() {
    $("#idaprobacioncredito").val("");
    $("#nombre_aprobacioncredito").val("");
    
}
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