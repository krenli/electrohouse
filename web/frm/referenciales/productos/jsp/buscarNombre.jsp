<%@page import="electrohouse.Controladores.ProductosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%  
String nombre_producto = request.getParameter("bnombre_producto");
String nombre_familia = request.getParameter("bnombre_familia");
String nombre_marca = request.getParameter("bnombre_marca");
String nombre_proveedor = request.getParameter("bnombre_proveedor");
String nombre_deposito = request.getParameter("bnombre_deposito");
int pagina =  Integer.parseInt (request.getParameter("bpagina"));
System.out.println("nru"+nombre_producto+nombre_familia+nombre_marca+nombre_proveedor+nombre_deposito);


String mensaje = "Busqueda exitosa";
String contenido = ProductosControlador.buscarNombre(nombre_producto, pagina);


JSONObject obj = new JSONObject();
obj.put("mensaje", mensaje);
obj.put("contenido", contenido);


out.print(obj);
out.flush();
%>
