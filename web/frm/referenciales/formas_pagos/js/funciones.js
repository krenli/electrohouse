function agregarForma_pago(){
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
            $("#idforma_pago").focus();
            $("#idforma_pago").select();
        },
        error: function (e){
             $("#mensajes").html("No se pudo modificar los datos. ");
        },
        complete: function (objeto, exito, error){
            $("#idforma_pago").focus();
        }
                
        } );
   
}
function buscarIdForma_pago(){
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
            $("#idforma_pago").val(json.idforma_pago);
            $("#nombre_forma_pago").val(json.nombre_forma_pago);
           
            if (json.nuevo === "true"){
                //alert("true");
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                $("#nombre_forma_pago").focus();
                //siguienteCampo("#nombre_forma_pago", "#botonAgregar", true);
            }else{
                  //alert("false");
                  $("#botonAgregar").prop('disabled', true);
                  $("#botonModificar").prop('disabled', false);
                  $("#botonEliminar").prop('disabled', false);
                 // siguienteCampo("#nombre_forma_pago", "#botonModificar", true);
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

function buscarNombreForma_pago(){
   
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
                $("#idforma_pago").val(id);
                $("#nombre_forma_pago").focus();
                buscarIdForma_pago();
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
function modificarForma_pago(){
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
            $("#idforma_pago").focus();
            $("idforma_pago").select();
               
            },
    
        error: function (e){
             $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){        
            }
          
        });
   
}  
function eliminarForma_pago(){
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
            $("#idforma_pago").focus();
            $("idforma_pago").select();
               
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
    if($("#nombre_forma_pago").val().trim() === ""){
      valor = false;
      $("#mensajes").html("Nombre no puede estar vacio");
      $("#nombre_forma_pago").focus();
    }
    return valor;
}
function limpiarFormulario(){
    $("#idforma_pago").val("0");
    $("#nombre_forma_pago").val("");
   
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



