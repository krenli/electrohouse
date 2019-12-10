<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.MarcasControlador"%>
<%@page import="electrohouse.modelos.Marcas"%>
<%
    
int idmarca =  Integer.parseInt (request.getParameter("idmarca"));
String nombre_marca = request.getParameter("nombre_marca");


String tipo="error";
String mensaje = "Dato ya existente";

Marcas marca = new Marcas();
marca.setIdmarca(idmarca);
marca.setNombre_marca(nombre_marca);


if(MarcasControlador.modificar(marca)){
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