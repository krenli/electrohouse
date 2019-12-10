function agregarCliente(){
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
            $("#idcliente").focus();
            $("#idcliente").select();
        },
        error: function (e){
             $("#mensajes").html("No se pudo modificar los datos. ");
        },
        complete: function (objeto, exito, error){
            $("#idcliente").focus();
        }
                
        } );
   
}
function buscarIdCliente(){
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
              //$("#idcliente").val(json.id_cliente);
            
           //console.log(json.id_cliente);
           // alert(json.mensaje);
            limpiarFormulario();
            $("#idcliente").val(json.idcliente);
            $("#nombre_cliente").val(json.nombre_cliente);
            $("#apellido_cliente").val(json.apellido_cliente);
            $("#ruc_cliente").val(json.ruc_cliente);
            $("#telefono_cliente").val(json.telefono_cliente);
            $("#fecha_nac_cliente").val(json.fecha_nac_cliente);
            $("#direccion_cliente").val(json.direccion_cliente);
           
            $("#idciudad").val(json.idciudad);
            $("#nombre_ciudad").val(json.nombre_ciudad);
           
            $("#idestadocivil").val(json.idestadocivil);
            $("#nombre_estadocivil").val(json.nombre_estadocivil);
                   
             //console.log(json.nombre_cliente);
            if (json.nuevo === "true"){
                //alert("true");
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                $("#nombre_cliente").focus();
                //siguienteCampo("#nombre_cliente", "#botonAgregar", true);
            }else{
                  //alert("false");
                  $("#botonAgregar").prop('disabled', true);
                  $("#botonModificar").prop('disabled', false);
                  $("#botonEliminar").prop('disabled', false);
                //siguienteCampo("#nombre_cliente", "#botonModificar", true);
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

function buscarNombreCliente(){
   
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
                $("#idcliente").val(id);
                $("#nombre_cliente").focus();
                buscarIdCliente();
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
function modificarCliente(){
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
            $("#idcliente").focus();
            $("idcliente").select();
               
            },
    
        error: function (e){
             $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){        
            }
          
        });
   
}  
function eliminarCliente(){
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
            $("#idcliente").focus();
            $("idcliente").select();
               
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
    if($("#nombre_cliente").val().trim() === ""){
      valor = false;
      $("#mensajes").html("Nombre no puede estar vacio");
      $("#nombre_cliente").focus();
      
      
    }
     if($("#apellido_cliente").val().trim() === ""){
      valor = false;
      $("#mensajes").html("Apellido no puede estar vacio");
      $("#apellido_cliente").focus();
      
      
    }
    if($("#ruc_cliente").val().trim() === ""){
      valor = false;
      $("#mensajes").html("RUC / C.I. no puede estar vacio");
      $("#ruc_cliente").focus();
      
      
    }
    if($("#telefono_cliente").val().trim() === ""){
      valor = false;
      $("#mensajes").html("Teléfono no puede estar vacio");
      $("#telefono_cliente").focus();
      
      
    }
    if($("#fecha_nac_cliente").val().trim() === ""){
      valor = false;
      $("#mensajes").html("Fecha Nacimiento no puede estar vacio");
      $("#fecha_nac_cliente").focus();
      
      
    }
    if($("#direccion_cliente").val().trim() === ""){
      valor = false;
      $("#mensajes").html("Dirección no puede estar vacio");
      $("#direccion_cliente").focus();
      
      
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
   
    return valor;
}
function limpiarFormulario(){
    $("#idcliente").val("0");
    $("#nombre_cliente").val("");
    $("#apellido_cliente").val("");
    $("#ruc_cliente").val("");
    $("#telefono_cliente").val("");
    $("#fecha_nac_cliente").val("");
    $("#direccion_cliente").val("");
    $("#idciudad").val("0");
    $("#nombre_ciudad").val("");
    $("#idestadocivil").val("0");
    $("#nombre_estadocivil").val("");
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