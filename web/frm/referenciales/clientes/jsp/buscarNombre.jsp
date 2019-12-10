<%@page import="electrohouse.Controladores.ClientesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%  
String nombre_cliente = request.getParameter("bnombre_cliente");
String nombre_ciudad = request.getParameter("bnombre_ciudad");
String nombre_estadocivil = request.getParameter("bnombre_estadocivil");
int pagina =  Integer.parseInt (request.getParameter("bpagina"));
System.out.println("nru"+nombre_cliente+nombre_ciudad+nombre_estadocivil);


String mensaje = "Busqueda exitosa";
String contenido = ClientesControlador.buscarNombre(nombre_cliente, pagina);


JSONObject obj = new JSONObject();
obj.put("mensaje", mensaje);
obj.put("contenido", contenido);


out.print(obj);
out.flush();
%>
