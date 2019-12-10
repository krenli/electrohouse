<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.Tipos_personalesControlador"%>
<%@page import="electrohouse.modelos.Tipos_personales"%>
<%
    
int idtipo_personal =  Integer.parseInt (request.getParameter("idtipo_personal"));
String nombre_tipo_personal = request.getParameter("nombre_tipo_personal");


String tipo="error";
String mensaje = "Datos no agregados";

Tipos_personales tipo_personal = new Tipos_personales();
tipo_personal.setIdtipo_personal(idtipo_personal);
tipo_personal.setNombre_tipo_personal(nombre_tipo_personal);


if(Tipos_personalesControlador.eliminar(tipo_personal)){
tipo = "success";
mensaje = "Datos eliminados";
}

JSONObject obj = new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje", mensaje);
System.out.println("tipo" +tipo);
out.print(obj);
out.flush();
%>