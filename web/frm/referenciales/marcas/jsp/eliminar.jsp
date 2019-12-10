<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.MarcasControlador"%>
<%@page import="electrohouse.modelos.Marcas"%>
<%
    
int idmarca =  Integer.parseInt (request.getParameter("idmarca"));
String nombre_marca = request.getParameter("nombre_marca");


String tipo="error";
String mensaje = "Datos no agregados";

Marcas marca = new Marcas();
marca.setIdmarca(idmarca);
marca.setNombre_marca(nombre_marca);


if(MarcasControlador.eliminar(marca)){
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