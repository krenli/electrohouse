<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.Formas_pagosControlador"%>
<%@page import="electrohouse.modelos.Formas_pagos"%>
<%
    
int idforma_pago =  Integer.parseInt (request.getParameter("idforma_pago"));
String nombre_forma_pago = request.getParameter("nombre_forma_pago");


String tipo="error";
String mensaje = "Datos no agregados";

Formas_pagos forma_pago = new Formas_pagos();
forma_pago.setIdforma_pago(idforma_pago);
forma_pago.setNombre_forma_pago(nombre_forma_pago);


if(Formas_pagosControlador.eliminar(forma_pago)){
tipo = "success";
mensaje = "Datos eliminados";
}

JSONObject obj = new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje", mensaje);
System.out.println("tipo" +tipo);
out.print(obj);
out.flush();
%>