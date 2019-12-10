function agregarProducto(){
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
            $("#idproducto").focus();
            $("#idproducto").select();
        },
        error: function (e){
             $("#mensajes").html("No se pudo modificar los datos. ");
        },
        complete: function (objeto, exito, error){
            $("#idproducto").focus();
        }
                
        } );
   
}
function buscarIdProducto(){
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
              //$("#idproducto").val(json.id_producto);
            
           //console.log(json.id_producto);
           // alert(json.mensaje);
            limpiarFormulario();
            $("#idproducto").val(json.idproducto);
            $("#nombre_producto").val(json.nombre_producto);
            $("#cod_barras_producto").val(json.cod_barras_producto);
            $("#costo_producto").val(json.costo_producto);
            $("#precio_producto").val(json.precio_producto);
            $("#minimo_producto").val(json.minimo_producto);
            $("#iva_producto").val(json.iva_producto);
           
            $("#idfamilia").val(json.idfamilia);
            $("#nombre_familia").val(json.nombre_familia);
           
            $("#idmarca").val(json.idmarca);
            $("#nombre_marca").val(json.nombre_marca);
            
          
             //console.log(json.nombre_producto);
            if (json.nuevo === "true"){
                //alert("true");
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                $("#nombre_producto").focus();
                //siguienteCampo("#nombre_producto", "#botonAgregar", true);
            }else{
                  //alert("false");
                  $("#botonAgregar").prop('disabled', true);
                  $("#botonModificar").prop('disabled', false);
                  $("#botonEliminar").prop('disabled', false);
                //siguienteCampo("#nombre_producto", "#botonModificar", true);
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

function buscarNombreProducto(){
   
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
                $("#idproducto").val(id);
                $("#nombre_producto").focus();
                buscarIdProducto();
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
function modificarProducto(){
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
            $("#idproducto").focus();
            $("idproducto").select();
               
            },
    
        error: function (e){
             $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){        
            }
          
        });
   
}  
function eliminarProducto(){
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
            $("#idproducto").focus();
            $("idproducto").select();
               
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
    if($("#nombre_producto").val().trim() === ""){
      valor = false;
      $("#mensajes").html("Nombre no puede estar vacio");
      $("#nombre_producto").focus();
      
      
    }
    if($("#cod_barras_producto").val().trim() === ""){
      valor = false;
      $("#mensajes").html("Código de Barras no puede estar vacio");
      $("#cod_barras_producto").focus();
      
      
    }
    if($("#costo_producto").val().trim() === ""){
      valor = false;
      $("#mensajes").html("Costo no puede estar vacio");
      $("#costo_producto").focus();
      
      
    }
    if($("#precio_producto").val().trim() === ""){
      valor = false;
      $("#mensajes").html("Precio no puede estar vacio");
      $("#precio_producto").focus();
      
      
    }
    if($("#minimo_producto").val().trim() === ""){
      valor = false;
      $("#mensajes").html("Cantidad mínima no puede estar vacio");
      $("#minimo_producto").focus();
      
      
    }
    
     if($("#iva_producto").val().trim() === ""){
      valor = false;
      $("#mensajes").html("Iva no puede estar vacio");
      $("#iva_producto").focus();
      
      
    }
    if($("#nombre_familia").val().trim() === ""){
      valor = false;
      $("#mensajes").html("Familia no puede estar vacio");
      $("#nombre_familia").focus();
      
      
    }
     if($("#nombre_marca").val().trim() === ""){
      valor = false;
      $("#mensajes").html("Marca no puede estar vacio");
      $("#nombre_marca").focus();
      
      
    }
   
    return valor;
}
function limpiarFormulario(){
    $("#idproducto").val("0");
    $("#nombre_producto").val("");
    $("#cod_barras_producto").val("");
    $("#costo_producto").val("0");
    $("#precio_producto").val("0");
    $("#minimo_producto").val("0");
    $("#iva_producto").val("0");
    $("#idfamilia").val("0");
    $("#nombre_familia").val("");
    $("#idmarca").val("0");
    $("#nombre_marca").val("");
}
function buscarIdFamilia(){
    var datosFormulario = $("#formPrograma").serialize();
    //alert (datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdFamilia.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto){
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json){
            $("#mensajes").html(json.mensaje);
          //alert(json.mensaje);
          
            $("#idfamilia").val(json.idfamilia);
            $("#nombre_familia").val(json.nombre_familia);
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
function buscarNombreFamilia(){
   
    var datosFormulario = $("#formBuscar").serialize();
 
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombrefamilia.jsp',
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
                $("#idfamilia").val(id);
                $("#nombre_familia").focus();
                buscarIdFamilia();
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
function buscarIdMarca(){
    var datosFormulario = $("#formPrograma").serialize();
    //alert (datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdMarca.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto){
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json){
            $("#mensajes").html(json.mensaje);
          //alert(json.mensaje);
          
            $("#idmarca").val(json.idmarca);
            $("#nombre_marca").val(json.nombre_marca);
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
function buscarNombreMarca(){
   
    var datosFormulario = $("#formBuscar").serialize();
 
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombremarca.jsp',
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
                $("#idmarca").val(id);
                $("#nombre_marca").focus();
                buscarIdMarca();
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

