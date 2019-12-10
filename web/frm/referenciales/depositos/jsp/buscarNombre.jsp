<%@page import="electrohouse.Controladores.DepositosControlador"%>
<%@page import="org.json.simple.JSONObject"%>

<%  
String nombre_deposito = request.getParameter("bnombre_deposito");
String nombre_ciudad = request.getParameter("bnombre_ciudad");
int pagina =  Integer.parseInt (request.getParameter("bpagina"));
System.out.println("nru"+nombre_deposito+nombre_ciudad);


String mensaje = "Busqueda exitosa";
String contenido = DepositosControlador.buscarNombre(nombre_deposito, pagina);


JSONObject obj = new JSONObject();
obj.put("mensaje", mensaje);
obj.put("contenido", contenido);


out.print(obj);
out.flush();
%>
