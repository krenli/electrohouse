<%@page import="electrohouse.Controladores.FormulariosControlador"%>
<%@page import="org.json.simple.JSONObject"%>

<%  
String nombre_formulario = request.getParameter("bnombre_formulario");
String nombre_menu = request.getParameter("bnombre_menu");
int pagina =  Integer.parseInt (request.getParameter("bpagina"));
System.out.println("nru"+nombre_formulario+nombre_menu);


String mensaje = "Busqueda exitosa";
String contenido = FormulariosControlador.buscarNombre(nombre_formulario, pagina);


JSONObject obj = new JSONObject();
obj.put("mensaje", mensaje);
obj.put("contenido", contenido);


out.print(obj);
out.flush();
%>
