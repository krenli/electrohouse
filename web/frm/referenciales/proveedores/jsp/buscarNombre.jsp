<%@page import="electrohouse.Controladores.ProveedoresControlador"%>
<%@page import="org.json.simple.JSONObject"%>

<%  
String nombre_proveedor = request.getParameter("bnombre_proveedor");
String nombre_ciudad = request.getParameter("bnombre_ciudad");
int pagina =  Integer.parseInt (request.getParameter("bpagina"));
System.out.println("nru"+nombre_proveedor+nombre_ciudad);


String mensaje = "Busqueda exitosa";
String contenido = ProveedoresControlador.buscarNombre(nombre_proveedor, pagina);


JSONObject obj = new JSONObject();
obj.put("mensaje", mensaje);
obj.put("contenido", contenido);


out.print(obj);
out.flush();
%>
