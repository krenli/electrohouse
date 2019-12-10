<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.Tipos_personalesControlador"%>
<%  
String nombre_tipo_personal = request.getParameter("bnombre_tipo_personal");
int pagina =  Integer.parseInt (request.getParameter("bpagina"));
//System.out.println("nru"+nombre_tipo_personal);


String mensaje = "Busqueda exitosa";
String contenido = Tipos_personalesControlador.buscarNombre(nombre_tipo_personal, pagina);


JSONObject obj = new JSONObject();
obj.put("mensaje", mensaje);
obj.put("contenido", contenido);


out.print(obj);
out.flush();
%>
