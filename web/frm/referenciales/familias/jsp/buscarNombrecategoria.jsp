<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.CategoriasControlador"%>
<%  
String nombre_categoria = request.getParameter("bnombre_categoria");
int pagina =  Integer.parseInt (request.getParameter("bpagina"));
//System.out.println("nru"+nombre_categoria);


String mensaje = "Busqueda exitosa";
String contenido = CategoriasControlador.buscarNombre(nombre_categoria, pagina);


JSONObject obj = new JSONObject();
obj.put("mensaje", mensaje);
obj.put("contenido", contenido);


out.print(obj);
out.flush();
%>
