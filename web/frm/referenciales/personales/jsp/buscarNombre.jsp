<%@page import="electrohouse.Controladores.PersonalesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%  
String nombre_personal = request.getParameter("bnombre_personal");
String nombre_ciudad = request.getParameter("bnombre_ciudad");
String nombre_estadocivil = request.getParameter("bnombre_estadocivil");
String nombre_tipo_personal = request.getParameter("bnombre_tipo_personal");
int pagina =  Integer.parseInt (request.getParameter("bpagina"));
System.out.println("nru"+nombre_personal+nombre_ciudad+nombre_estadocivil+nombre_tipo_personal);


String mensaje = "Busqueda exitosa";
String contenido = PersonalesControlador.buscarNombre(nombre_personal, pagina);


JSONObject obj = new JSONObject();
obj.put("mensaje", mensaje);
obj.put("contenido", contenido);


out.print(obj);
out.flush();
%>
