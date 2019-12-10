function agregarProveedor(){
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
            $("#idproveedor").focus();
            $("#idproveedor").select();
        },
        error: function (e){
             $("#mensajes").html("No se pudo modificar los datos. ");
        },
        complete: function (objeto, exito, error){
            $("#idproveedor").focus();
        }
                
        } );
   
}
function buscarIdProveedor(){
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
            $("#idproveedor").val(json.idproveedor);
            $("#nombre_proveedor").val(json.nombre_proveedor);
            $("#ruc_proveedor").val(json.ruc_proveedor);
            $("#telefono_proveedor").val(json.telefono_proveedor);
            $("#direccion_proveedor").val(json.direccion_proveedor);
            $("#email_proveedor").val(json.email_proveedor);
            $("#idciudad").val(json.idciudad);
            $("#nombre_ciudad").val(json.nombre_ciudad);
            
            if (json.nuevo === "true"){
                //alert("true");
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                $("#nombre_proveedor").focus();
                //siguienteCampo("#nombre_proveedor", "#botonAgregar", true);
            }else{
                  //alert("false");
                  $("#botonAgregar").prop('disabled', true);
                  $("#botonModificar").prop('disabled', false);
                  $("#botonEliminar").prop('disabled', false);
                 // siguienteCampo("#nombre_proveedor", "#botonModificar", true);
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

function buscarNombreProveedor(){
   
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
                $("#idproveedor").val(id);
                $("#nombre_proveedor").focus();
                buscarIdProveedor();
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
function modificarProveedor(){
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
            $("#idproveedor").focus();
            $("idproveedor").select();
               
            },
    
        error: function (e){
             $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){        
            }
          
        });
   
}  
function eliminarProveedor(){
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
            $("#idproveedor").focus();
            $("idproveedor").select();
               
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
    if($("#nombre_proveedor").val().trim() === ""){
      valor = false;
      $("#mensajes").html("Nombre no puede estar vacio");
      $("#nombre_proveedor").focus();
    }
     if($("#ruc_proveedor").val().trim() === ""){
      valor = false;
      $("#mensajes").html("RUC no puede estar vacio");
      $("#ruc_proveedor").focus();
    }
     if($("#telefono_proveedor").val().trim() === ""){
      valor = false;
      $("#mensajes").html("Teléfono no puede estar vacio");
      $("#telefono_proveedor").focus();
    }
     if($("#direccion_proveedor").val().trim() === ""){
      valor = false;
      $("#mensajes").html("Dirección no puede estar vacio");
      $("#direccion_proveedor").focus();
    }
      if($("#email_proveedor").val().trim() === ""){
      valor = false;
      $("#mensajes").html("Email no puede estar vacio");
      $("#email_proveedor").focus();
    }
     if($("#nombre_ciudad").val().trim() === ""){
      valor = false;
      $("#mensajes").html("Nombre ciudad no puede estar vacio");
      $("#nombre_ciudad").focus();
    }
    return valor;
}
function limpiarFormulario(){
    $("#idproveedor").val("0");
    $("#nombre_proveedor").val("");
    $("#ruc_proveedor").val("");
    $("#telefono_proveedor").val("");
    $("#direccion_proveedor").val("");
    $("#email_proveedor").val("");
    $("#idciudad").val("0");
    $("#nombre_ciudad").val("");
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
  
function validarCorreo(elemento){

  var texto = document.getElementById(elemento.id).value;
  var regex = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;
  
  if (!regex.test(texto)) {
      document.getElementById("resultado").innerHTML = "Correo invalido";
      $("#email_proveedor").val("");
      $("#email_proveedor").focus();
  } else {
    document.getElementById("resultado").innerHTML = "";
  }
//<div class="col-md-11">
    //<input id="correo_proveedor" name="correo_proveedor" type="email" class="form-control input-sm" placeholder="Correo" onkeypress="validarCorreo(this)">
       //<a id='resultado'></a>
       //</div>
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

