<%@page import="electrohouse.Controladores.MarcasControlador"%>
<%@page import="org.json.simple.JSONObject"%>

<%  
String nombre_marca = request.getParameter("bnombre_marca");
int pagina =  Integer.parseInt (request.getParameter("bpagina"));
System.out.println("nru"+nombre_marca);


String mensaje = "Busqueda exitosa";
String contenido = MarcasControlador.buscarNombre(nombre_marca, pagina);


JSONObject obj = new JSONObject();
obj.put("mensaje", mensaje);
obj.put("contenido", contenido);


out.print(obj);
out.flush();
%>
