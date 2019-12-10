<%@page import="electrohouse.Controladores.Formas_pagosControlador"%>
<%@page import="org.json.simple.JSONObject"%>

<%  
String nombre_forma_pago = request.getParameter("bnombre_forma_pago");
int pagina =  Integer.parseInt (request.getParameter("bpagina"));
System.out.println("nru"+nombre_forma_pago);


String mensaje = "Busqueda exitosa";
String contenido = Formas_pagosControlador.buscarNombre(nombre_forma_pago, pagina);


JSONObject obj = new JSONObject();
obj.put("mensaje", mensaje);
obj.put("contenido", contenido);


out.print(obj);
out.flush();
%>
