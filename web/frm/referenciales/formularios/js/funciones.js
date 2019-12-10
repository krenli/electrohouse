function agregarFormulario(){
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
            //alert(json.mensaje);
            limpiarFormulario();
            $("#idformulario").focus();
            $("#idformulario").select();
        },
        error: function (e){
             $("#mensajes").html("No se pudo modificar los datos. ");
        },
        complete: function (objeto, exito, error){
            $("#idformulario").focus();
        }
                
        } );
   
}
function buscarIdFormulario(){
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
           // alert(json.mensaje);
            limpiarFormulario();
            $("#idformulario").val(json.idformulario);
            $("#nombre_formulario").val(json.nombre_formulario);
            $("#codigo_formulario").val(json.codigo_formulario);
            $("#idmenu").val(json.idmenu);
            $("#nombre_menu").val(json.nombre_menu);
            
            if (json.nuevo === "true"){
                //alert("true");
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                $("#nombre_formulario").focus();
                //siguienteCampo("#nombre_formulario", "#botonAgregar", true);
            }else{
                  //alert("false");
                  $("#botonAgregar").prop('disabled', true);
                  $("#botonModificar").prop('disabled', false);
                  $("#botonEliminar").prop('disabled', false);
                 // siguienteCampo("#nombre_formulario", "#botonModificar", true);
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

function buscarNombreFormulario(){
   
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
                $("#idformulario").val(id);
                $("#nombre_formulario").focus();
                buscarIdFormulario();
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
function modificarFormulario(){
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
            $("#idformulario").focus();
            $("idformulario").select();
               
            },
    
        error: function (e){
             $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){        
            }
          
        });
   
}  
function eliminarFormulario(){
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
            $("#idformulario").focus();
            $("idformulario").select();
               
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
    if($("#nombre_formulario").val().trim() === ""){
      valor = false;
      $("#mensajes").html("Nombre no puede estar vacio");
      $("#nombre_formulario").focus();
    }
     if($("#codigo_formulario").val().trim() === ""){
      valor = false;
      $("#mensajes").html("Código no puede estar vacio");
      $("#codigo_formulario").focus();
    }
   
     if($("#nombre_menu").val().trim() === ""){
      valor = false;
      $("#mensajes").html("Nombre menu no puede estar vacio");
      $("#nombre_menu").focus();
    }
    return valor;
}
function limpiarFormulario(){
    $("#idformulario").val("0");
    $("#nombre_formulario").val("");
    $("#codigo_formulario").val("");
    $("#idmenu").val("0");
    $("#nombre_menu").val("");
}
function buscarIdMenu(){
    var datosFormulario = $("#formPrograma").serialize();
    //alert (datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdMenu.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto){
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json){
            $("#mensajes").html(json.mensaje);
          //alert(json.mensaje);
          
            $("#idmenu").val(json.idmenu);
            $("#nombre_menu").val(json.nombre_menu);
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
function buscarNombreMenu(){
   
    var datosFormulario = $("#formBuscar").serialize();
 
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombremenu.jsp',
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
                $("#idmenu").val(id);
                $("#nombre_menu").focus();
                buscarIdMenu();
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



