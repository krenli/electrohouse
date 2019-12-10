<%@page import="electrohouse.modelos.Menus"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.FormulariosControlador"%>
<%@page import="electrohouse.modelos.Formularios"%>
<%
    
int idformulario =  Integer.parseInt (request.getParameter("idformulario"));
String nombre_formulario = request.getParameter("nombre_formulario");
String codigo_formulario = request.getParameter("codigo_formulario");
int idmenu =  Integer.parseInt (request.getParameter("idmenu"));

String tipo="error";
String mensaje = "Dato ya existente";

Formularios formulario = new Formularios();
formulario.setIdformulario(idformulario);
formulario.setNombre_formulario(nombre_formulario);
formulario.setCodigo_formulario(codigo_formulario);


Menus menu = new Menus();
menu.setIdmenu(idmenu);
formulario.setMenu(menu);

if(FormulariosControlador.modificar(formulario)){
tipo = "success";
mensaje = "Datos modificados";
}

JSONObject obj = new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje", mensaje);
System.out.println("tipo" +tipo);
out.print(obj);
out.flush();
%>