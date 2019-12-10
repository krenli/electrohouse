function fechaHoy() {

    var hoy = new new Date().toJSON().slice(0, 10);



    console.log(hoy);
    //$("#fecha_actual_timbrado").val(hoy);
}
function addZero(i) {
    if (i < 10) {
        i = '0' + i;
    }
    return i;
}

function agregarTimbrado() {
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
            $("#idtimbrado").focus();
            $("#idtimbrado").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#idtimbrado").focus();
        }
    });
}

function modificarTimbrado() {
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
            $("#idtimbrado").focus();
            $("#idtimbrado").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {

        }
    });
}

function eliminarTimbrado() {
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
            $("#idtimbrado").focus();
            $("#idtimbrado").select();
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

function buscarIdTimbrado() {
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
            $("#idtimbrado").val(json.idtimbrado);
            $("#numero_timbrado").val(json.numero_timbrado);
            $("#fecha_inicio_timbrado").val(json.fecha_inicio_timbrado);
            $("#fecha_vencimiento_timbrado").val(json.fecha_vencimiento_timbrado);
            //$("#fecha_actual_timbrado").val(json.fecha_actual_timbrado);
            $("#desde_timbrado").val(json.desde_timbrado);
            $("#hasta_timbrado").val(json.hasta_timbrado);
            $("#estado_timbrado").val(json.estado_timbrado);
            $("#idtipo_personal").val(json.idtipo_personal);
            $("#nombre_tipo_personal").val(json.nombre_tipo_personal);
            var fecha = $("#fecha_factura_venta").serialize();

            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                siguienteCampo("#numero_timbrado", "#botonAgregar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', true);
                siguienteCampo("#numero_timbrado", "#botonModificar", true);
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

function buscarNumeroTimbrado() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNumero.jsp',
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
                $("#idtimbrado").val(id);
                $("#numero_timbrado").focus();
                buscarIdTimbrado();
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

function buscarTimbrado() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarTimbrado.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);


            if (json.nuevo === "false") {
                $("#numero_timbrado").val("");
                $("#numero_timbrado").focus();
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

function validarFormulario() {
    var valor = true;
    if ($("#numero_timbrado").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Nª timbrado no puede estar vacio.");
        $("#numero_timbrado").focus();
    }

    if ($("#fecha_inicio_timbrado").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Fecha inicio no puede estar vacio.");
        $("#fecha_inicio_timbrado").focus();
    }

    if ($("#fecha_vencimiento_timbrado").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Fecha vencimiento no puede estar vacio.");
        $("#fecha_vencimiento_timbrado").focus();
    }

    /*if ($("#fecha_actual_timbrado").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Fecha actual no puede estar vacio.");
        $("#fecha_actual_timbrado").focus();
    }*/
    
    if ($("#desde_timbrado").val().trim() === "" || $("#desde_timbrado").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("Desde timbrado no puede estar vacio.");
        $("#desde_timbrado").focus();
    }
    
    if ($("#hasta_timbrado").val().trim() === "" || $("#hasta_timbrado").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("Hasta timbrado no puede estar vacio.");
        $("#hasta_timbrado").focus();
    }
    
    if ($("#estado_timbrado").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Estado timbrado no puede estar vacio.");
        $("#estado_timbrado").focus();
    }
    
    if ($("#idtipo_personal").val().trim() === "" || $("#idtipo_personal").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("Tipo Personal no puede estar vacio.");
        $("#idtipo_personal").focus();
    }  

    return valor;
}

function limpiarFormulario() {
    $("#idtimbrado").val("");
    $("#numero_timbrado").val("");
    $("#fecha_inicio_timbrado").val("");
    $("#fecha_vencimiento_timbrado").val("");
    //$("#fecha_actual_timbrado").val("");
    $("#desde_timbrado").val("");
    $("#hasta_timbrado").val("");
    $("#estado_timbrado").val("");
    $("#idtipo_personal").val("");
    $("#nombre_tipo_personal").val("");
   
}


function buscarIdTipo_personal() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdTipo_personal.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#idtipo_personal").val(json.idtipo_personal);
            $("#nombre_tipo_personal").val(json.nombre_tipo_personal);
            
            
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

function buscarNombreTipo_personal() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarNombreTipo_personal.jsp',
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
              $("#idtipo_personal").val(id);
              $("#nombre_tipo_personal").focus();
              buscarIdTipo_personal();
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