<%@page import="electrohouse.Controladores.MenusControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%  
String nombre_menu = request.getParameter("bnombre_menu");
int pagina =  Integer.parseInt (request.getParameter("bpagina"));
//System.out.println("nru"+nombre_menu);


String mensaje = "Busqueda exitosa";
String contenido = MenusControlador.buscarNombre(nombre_menu, pagina);


JSONObject obj = new JSONObject();
obj.put("mensaje", mensaje);
obj.put("contenido", contenido);


out.print(obj);
out.flush();
%>
