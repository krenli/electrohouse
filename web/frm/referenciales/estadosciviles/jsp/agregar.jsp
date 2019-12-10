<%@page import="electrohouse.Controladores.EstadoscivilesControlador"%>
<%@page import="electrohouse.modelos.Estadosciviles"%>
<%@page import="org.json.simple.JSONObject"%>

<%
    
int idestadocivil =  Integer.parseInt (request.getParameter("idestadocivil"));
String nombre_estadocivil = request.getParameter("nombre_estadocivil");


String tipo="error";
String mensaje = "Dato ya existente";

Estadosciviles estadocivil = new Estadosciviles();
estadocivil.setIdestadocivil(idestadocivil);
estadocivil.setNombre_estadocivil(nombre_estadocivil);


if(EstadoscivilesControlador.agregar(estadocivil)){
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