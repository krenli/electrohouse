<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.PersonalesControlador"%>
<%@page import="electrohouse.modelos.Personales"%>
<%   
int idpersonal =  Integer.parseInt (request.getParameter("idpersonal"));



String tipo="error";
String mensaje = "Datos no encontrados";
String nuevo = "true";
Personales personal = new Personales();
personal.setIdpersonal(idpersonal);

 PersonalesControlador.buscarId(personal);

if(personal.getIdpersonal()!=0){
    tipo = "success";
    mensaje = "Datos encontrados";
    nuevo = "false";
 
}

JSONObject obj = new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje", mensaje);
obj.put("nuevo", nuevo);
obj.put("idpersonal", personal.getIdpersonal());
obj.put("nombre_personal", personal.getNombre_personal());
obj.put("ci_personal", personal.getCi_personal());
obj.put("telefono_personal", personal.getTelefono_personal());
obj.put("fecha_nac_personal", personal.getFecha_nac_personal());
obj.put("direccion_personal", personal.getDireccion_personal());
obj.put("idciudad", personal.getCiudad().getIdciudad());
obj.put("nombre_ciudad", personal.getCiudad().getNombre_ciudad());
obj.put("idestadocivil", personal.getEstadocivil().getIdestadocivil());
obj.put("nombre_estadocivil", personal.getEstadocivil().getNombre_estadocivil());
obj.put("idtipo_personal", personal.getTipo_personal().getIdtipo_personal());
obj.put("nombre_tipo_personal", personal.getTipo_personal().getNombre_tipo_personal());
out.print(obj);
out.flush();
%>
