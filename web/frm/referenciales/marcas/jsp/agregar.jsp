<%@page import="electrohouse.Controladores.MarcasControlador"%>
<%@page import="electrohouse.modelos.Marcas"%>
<%@page import="org.json.simple.JSONObject"%>

<%
    
int idmarca =  Integer.parseInt (request.getParameter("idmarca"));
String nombre_marca = request.getParameter("nombre_marca");


String tipo="error";
String mensaje = "Dato ya existente";

Marcas marca = new Marcas();
marca.setIdmarca(idmarca);
marca.setNombre_marca(nombre_marca);


if(MarcasControlador.agregar(marca)){
tipo = "success";
mensaje = "Datos Guardados";
}

JSONObject obj = new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje", mensaje);
System.out.println("tipo" +tipo);
out.print(obj);
out.flush();
%>