<%@page import="electrohouse.Controladores.CiudadesControlador"%>
<%@page import="org.json.simple.JSONObject"%>

<%  
String nombre_ciudad = request.getParameter("bnombre_ciudad");
int pagina =  Integer.parseInt (request.getParameter("bpagina"));
System.out.println("nru"+nombre_ciudad);


String mensaje = "Busqueda exitosa";
String contenido = CiudadesControlador.buscarNombre(nombre_ciudad, pagina);


JSONObject obj = new JSONObject();
obj.put("mensaje", mensaje);
obj.put("contenido", contenido);


out.print(obj);
out.flush();
%>
