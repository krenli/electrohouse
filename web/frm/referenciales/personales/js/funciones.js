function agregarPersonal(){
    alert("Exito");
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto){
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json){
            $("#mensajes").html(json.mensaje);
            var mensaje1 = $("#mensajes").html(json.mensaje);
            //console.log(mensaje1);
            limpiarFormulario();
            $("#idpersonal").focus();
            $("#idpersonal").select();
        },
        error: function (e){
             $("#mensajes").html("No se pudo modificar los datos. ");
        },
        complete: function (objeto, exito, error){
            $("#idpersonal").focus();
        }
                
        } );
   
}
function buscarIdPersonal(){
    var datosFormulario = $("#formPrograma").serialize();
    //alert (datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarId.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto){
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json){
            $("#mensajes").html(json.mensaje);
              //$("#idpersonal").val(json.id_personal);
            
           //console.log(json.id_personal);
           // alert(json.mensaje);
            limpiarFormulario();
            $("#idpersonal").val(json.idpersonal);
            $("#nombre_personal").val(json.nombre_personal);
            $("#ci_personal").val(json.ci_personal);
            $("#telefono_personal").val(json.telefono_personal);
            $("#fecha_nac_personal").val(json.fecha_nac_personal);
            $("#direccion_personal").val(json.direccion_personal);
           
            $("#idciudad").val(json.idciudad);
            $("#nombre_ciudad").val(json.nombre_ciudad);
           
            $("#idestadocivil").val(json.idestadocivil);
            $("#nombre_estadocivil").val(json.nombre_estadocivil);
            
            $("#idtipo_personal").val(json.idtipo_personal);
            $("#nombre_tipo_personal").val(json.nombre_tipo_personal);
            
             //console.log(json.nombre_personal);
            if (json.nuevo === "true"){
                //alert("true");
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                $("#nombre_personal").focus();
                //siguienteCampo("#nombre_personal", "#botonAgregar", true);
            }else{
                  //alert("false");
                  $("#botonAgregar").prop('disabled', true);
                  $("#botonModificar").prop('disabled', false);
                  $("#botonEliminar").prop('disabled', false);
                //siguienteCampo("#nombre_personal", "#botonModificar", true);
            }
        },
        error: function (e){
             $("#mensajes").html("No se pudo recuperar los datos. ");
        },
        complete: function (objeto, exito, error){
            if(exito === "success"){
                
            }
        }
                
        } );
   
}

function buscarNombrePersonal(){
   
    var datosFormulario = $("#formBuscar").serialize();
 
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombre.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto){
            $("#mensajes").html("Enviando datos al servidor...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json){
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function(){
                var id= $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#idpersonal").val(id);
                $("#nombre_personal").focus();
                buscarIdPersonal();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e){
             $("#mensajes").html("No se pudo buscar Registros.");
        },
        complete: function (objeto, exito, error){
            if(exito === "success"){
                
            }
        }
                
        });
   
}
function modificarPersonal(){
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/modificar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto){
            $("#mensajes").html("Enviando datos al servidor...");
            
        },
        success: function (json){
            $("#mensajes").html(json.mensaje);
             limpiarFormulario();
            $("#idpersonal").focus();
            $("idpersonal").select();
               
            },
    
        error: function (e){
             $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){        
            }
          
        });
   
}  
function eliminarPersonal(){
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto){
            $("#mensajes").html("Enviando datos al servidor...");
            
        },
        success: function (json){
            $("#mensajes").html(json.mensaje);
             limpiarFormulario();
            $("#idpersonal").focus();
            $("idpersonal").select();
               
            },
    
        error: function (e){
             $("#mensajes").html("No se pudo eliminar los datos.");
        },
        complete: function (objeto, exito, error){        
        if(exito === "success"){
            
        }    
        }
          
        });
   
}  

function validarFormulario(){
    var valor = true;
    if($("#nombre_personal").val().trim() === ""){
      valor = false;
      $("#mensajes").html("Nombre no puede estar vacio");
      $("#nombre_personal").focus();
      
      
    }
    if($("#ci_personal").val().trim() === ""){
      valor = false;
      $("#mensajes").html("C.I. no puede estar vacio");
      $("#ci_personal").focus();
      
      
    }
    if($("#telefono_personal").val().trim() === ""){
      valor = false;
      $("#mensajes").html("Teléfono no puede estar vacio");
      $("#telefono_personal").focus();
      
      
    }
    if($("#fecha_nac_personal").val().trim() === ""){
      valor = false;
      $("#mensajes").html("Fecha Nacimiento no puede estar vacio");
      $("#fecha_nac_personal").focus();
      
      
    }
    if($("#direccion_personal").val().trim() === ""){
      valor = false;
      $("#mensajes").html("Dirección no puede estar vacio");
      $("#direccion_personal").focus();
      
      
    }
    if($("#nombre_ciudad").val().trim() === ""){
      valor = false;
      $("#mensajes").html("Ciudad no puede estar vacio");
      $("#nombre_ciudad").focus();
      
      
    }
     if($("#nombre_estadocivil").val().trim() === ""){
      valor = false;
      $("#mensajes").html("Estado Civil no puede estar vacio");
      $("#nombre_estadocivil").focus();
      
      
    }
     if($("#nombre_tipo_personal").val().trim() === ""){
      valor = false;
      $("#mensajes").html("Tipo de Personal no puede estar vacio");
      $("#nombre_tipo_personal").focus();
      
      
    }
   
    return valor;
}
function limpiarFormulario(){
    $("#idpersonal").val("0");
    $("#nombre_personal").val("");
    $("#ci_personal").val("");
    $("#telefono_personal").val("");
    $("#fecha_nac_personal").val("");
    $("#direccion_personal").val("");
    $("#idciudad").val("0");
    $("#nombre_ciudad").val("");
    $("#idestadocivil").val("0");
    $("#nombre_estadocivil").val("");
    $("#idtipo_personal").val("0");
    $("#nombre_tipo_personal").val("");
}
function buscarIdCiudad(){
    var datosFormulario = $("#formPrograma").serialize();
    //alert (datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCiudad.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto){
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json){
            $("#mensajes").html(json.mensaje);
          //alert(json.mensaje);
          
            $("#idciudad").val(json.idciudad);
            $("#nombre_ciudad").val(json.nombre_ciudad);
        },
        error: function (e){
             $("#mensajes").html("No se pudo recuperar los datos. ");
        },
        complete: function (objeto, exito, error){
            if(exito === "success"){
                
            }
        }
                
        } );
   
}
function buscarNombreCiudad(){
   
    var datosFormulario = $("#formBuscar").serialize();
 
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreciudad.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto){
            $("#mensajes").html("Enviando datos al servidor...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json){
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function(){
                var id= $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#idciudad").val(id);
                $("#nombre_ciudad").focus();
                buscarIdCiudad();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e){
             $("#mensajes").html("No se pudo buscar Registros.");
        },
        complete: function (objeto, exito, error){
            if(exito === "success"){
                
            }
        }
                
        });
   
}
function buscarIdEstadocivil(){
    var datosFormulario = $("#formPrograma").serialize();
    //alert (datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdEstadocivil.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto){
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json){
            $("#mensajes").html(json.mensaje);
          //alert(json.mensaje);
          
            $("#idestadocivil").val(json.idestadocivil);
            $("#nombre_estadocivil").val(json.nombre_estadocivil);
        },
        error: function (e){
             $("#mensajes").html("No se pudo recuperar los datos. ");
        },
        complete: function (objeto, exito, error){
            if(exito === "success"){
                
            }
        }
                
        } );
   
}
function buscarNombreEstadocivil(){
   
    var datosFormulario = $("#formBuscar").serialize();
 
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreestadocivil.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto){
            $("#mensajes").html("Enviando datos al servidor...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json){
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function(){
                var id= $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#idestadocivil").val(id);
                $("#nombre_estadocivil").focus();
                buscarIdEstadocivil();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e){
             $("#mensajes").html("No se pudo buscar Registros.");
        },
        complete: function (objeto, exito, error){
            if(exito === "success"){
                
            }
        }
                
        });
   
}
function buscarIdTipo_personal(){
    var datosFormulario = $("#formPrograma").serialize();
    //alert (datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdTipo_personal.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto){
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json){
            $("#mensajes").html(json.mensaje);
          //alert(json.mensaje);
          
            $("#idtipo_personal").val(json.idtipo_personal);
            $("#nombre_tipo_personal").val(json.nombre_tipo_personal);
        },
        error: function (e){
             $("#mensajes").html("No se pudo recuperar los datos. ");
        },
        complete: function (objeto, exito, error){
            if(exito === "success"){
                
            }
        }
                
        } );
   
}
function buscarNombreTipo_personal(){
   
    var datosFormulario = $("#formBuscar").serialize();
 
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombretipo_personal.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto){
            $("#mensajes").html("Enviando datos al servidor...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json){
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function(){
                var id= $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#idtipo_personal").val(id);
                $("#nombre_tipo_personal").focus();
                buscarIdTipo_personal();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e){
             $("#mensajes").html("No se pudo buscar Registros.");
        },
        complete: function (objeto, exito, error){
            if(exito === "success"){
                
            }
        }
                
        });
   
}

function sololetras(e) {
    key = e.keyCode || e.which;

    teclado = String.fromCharCode(key).toLowerCase();


    letras = " abcdefghijklmnñopqrstvwxyzu/.";

    especiales = "8-37-38-46-164";

    teclado_especial = false;

    for (var i in especiales) {
        if (key == especiales[i]) {
            teclado_especial = true;
            break;
        }
    }
    if (letras.indexOf(teclado) == -1 && !teclado_especial) {
        // $("#mensajes").html("solo letras.");
        alert("solo letras");
        return false;
    }
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