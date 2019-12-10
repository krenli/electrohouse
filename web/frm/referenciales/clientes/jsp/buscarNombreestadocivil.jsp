<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.EstadoscivilesControlador"%>
<%  
String nombre_estadocivil = request.getParameter("bnombre_estadocivil");
int pagina =  Integer.parseInt (request.getParameter("bpagina"));
//System.out.println("nru"+nombre_estadocivil);


String mensaje = "Busqueda exitosa";
String contenido = EstadoscivilesControlador.buscarNombre(nombre_estadocivil, pagina);


JSONObject obj = new JSONObject();
obj.put("mensaje", mensaje);
obj.put("contenido", contenido);


out.print(obj);
out.flush();
%>
