<%@page import="electrohouse.Controladores.EstadoscivilesControlador"%>
<%@page import="electrohouse.modelos.Estadosciviles"%>
<%@page import="org.json.simple.JSONObject"%>

<%   
int idestadocivil =  Integer.parseInt (request.getParameter("idestadocivil"));



String tipo="error";
String mensaje = "Datos no encontrados";
String nuevo = "true";
Estadosciviles estadocivil = new Estadosciviles();
estadocivil.setIdestadocivil(idestadocivil);

 EstadoscivilesControlador.buscarId(estadocivil);

if(estadocivil.getIdestadocivil()!=0){
    tipo = "success";
    mensaje = "Datos encontrados";
    nuevo = "false";
 
}

JSONObject obj = new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje", mensaje);
obj.put("nuevo", nuevo);
obj.put("idestadocivil", estadocivil.getIdestadocivil());
obj.put("nombre_estadocivil", estadocivil.getNombre_estadocivil());


out.print(obj);
out.flush();
%>
