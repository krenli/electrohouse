<%@page import="electrohouse.modelos.Menus"%>
<%@page import="electrohouse.Controladores.FormulariosControlador"%>
<%@page import="electrohouse.modelos.Formularios"%>
<%@page import="org.json.simple.JSONObject"%>

<%   
int idformulario =  Integer.parseInt (request.getParameter("idformulario"));



String tipo="error";
String mensaje = "Datos no encontrados";
String nuevo = "true";
   Formularios formulario = FormulariosControlador.buscarId(idformulario);



  if(formulario!=null){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }else{
        formulario = new Formularios();
        formulario.setIdformulario(0);
        Menus menu = new Menus();
        formulario.setMenu(menu);
    }

JSONObject obj = new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje", mensaje);
obj.put("nuevo", nuevo);
obj.put("idformulario", formulario.getIdformulario());
obj.put("nombre_formulario", formulario.getNombre_formulario());
obj.put("codigo_formulario", formulario.getCodigo_formulario());
obj.put("idmenu", formulario.getMenu().getIdmenu());
obj.put("nombre_menu", formulario.getMenu().getNombre_menu());
out.print(obj);
out.flush();
%>
