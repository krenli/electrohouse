<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.EstadoscivilesControlador"%>
<%@page import="electrohouse.modelos.Estadosciviles"%>
<%   
int idestadocivil =  Integer.parseInt (request.getParameter("idestadocivil"));
String nombre_estadocivil = request.getParameter("nombre_estadocivil");


String tipo="error";
String mensaje = "Datos no encontrados";
String nuevo = "true";
Estadosciviles estadocivil = new Estadosciviles();
estadocivil.setIdestadocivil(idestadocivil);
estadocivil.setNombre_estadocivil(nombre_estadocivil);
EstadoscivilesControlador.buscarId(estadocivil);

if(estadocivil.getIdestadocivil()!=0){
    tipo = "success";
    mensaje = "Datos encontrados";
    nuevo = "false";
 
}

JSONObject obj = new JSONObject();
obj.put("tipo", tipo);
System.out.println("tipo " + tipo);
obj.put("mensaje", mensaje);
System.out.println("mensaje " + mensaje);
obj.put("nuevo", nuevo);
obj.put("idestadocivil", estadocivil.getIdestadocivil());
obj.put("nombre_estadocivil", estadocivil.getNombre_estadocivil());

out.print(obj);
out.flush();
%>
