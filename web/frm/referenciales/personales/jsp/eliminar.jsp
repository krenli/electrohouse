<%@page import="electrohouse.modelos.Tipos_personales"%>
<%@page import="electrohouse.modelos.Estadosciviles"%>
<%@page import="electrohouse.modelos.Ciudades"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.PersonalesControlador"%>
<%@page import="electrohouse.modelos.Categorias"%>
<%@page import="electrohouse.modelos.Personales"%>
<%
    
int idpersonal =  Integer.parseInt (request.getParameter("idpersonal"));
String nombre_personal = request.getParameter("nombre_personal");
String ci_personal = request.getParameter("ci_personal");
String telefono_personal = request.getParameter("telefono_personal");
String fecha_nac_personal = request.getParameter("fecha_nac_personal");
String direccion_personal = request.getParameter("direccion_personal");
int idciudad =  Integer.parseInt (request.getParameter("idciudad"));
int idestadocivil =  Integer.parseInt (request.getParameter("idestadocivil"));
int idtipo_personal =  Integer.parseInt (request.getParameter("idtipo_personal"));

String tipo="error";
String mensaje = "Datos no agregados";

Personales personal = new Personales();
personal.setIdpersonal(idpersonal);
personal.setNombre_personal(nombre_personal);
personal.setCi_personal(ci_personal);
personal.setTelefono_personal(telefono_personal);
personal.setFecha_nac_personal(fecha_nac_personal);
personal.setDireccion_personal(direccion_personal);

Ciudades ciudad = new Ciudades();
ciudad.setIdciudad(idciudad);
personal.setCiudad(ciudad);

Estadosciviles estadocivil = new Estadosciviles();
estadocivil.setIdestadocivil(idestadocivil);
personal.setEstadocivil(estadocivil);

Tipos_personales tipo_personal = new Tipos_personales();
tipo_personal.setIdtipo_personal(idtipo_personal);
personal.setTipo_personal(tipo_personal);


if(PersonalesControlador.eliminar(personal)){
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