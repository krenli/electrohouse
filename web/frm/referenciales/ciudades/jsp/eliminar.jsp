<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.CiudadesControlador"%>
<%@page import="electrohouse.modelos.Ciudades"%>
<%
    
int idciudad =  Integer.parseInt (request.getParameter("idciudad"));
String nombre_ciudad = request.getParameter("nombre_ciudad");


String tipo="error";
String mensaje = "No se puede eliminar porque está en uso";

Ciudades ciudad = new Ciudades();
ciudad.setIdciudad(idciudad);
ciudad.setNombre_ciudad(nombre_ciudad);


if(CiudadesControlador.eliminar(ciudad)){
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