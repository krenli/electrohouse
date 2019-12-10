<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.FamiliasControlador"%>
<%  
String nombre_familia = request.getParameter("bnombre_familia");
int pagina =  Integer.parseInt (request.getParameter("bpagina"));
//System.out.println("nru"+nombre_familia);


String mensaje = "Busqueda exitosa";
String contenido = FamiliasControlador.buscarNombre(nombre_familia, pagina);


JSONObject obj = new JSONObject();
obj.put("mensaje", mensaje);
obj.put("contenido", contenido);


out.print(obj);
out.flush();
%>
