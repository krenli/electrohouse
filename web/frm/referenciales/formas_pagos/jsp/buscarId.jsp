<%@page import="electrohouse.Controladores.Formas_pagosControlador"%>
<%@page import="electrohouse.modelos.Formas_pagos"%>
<%@page import="org.json.simple.JSONObject"%>

<%   
int idforma_pago =  Integer.parseInt (request.getParameter("idforma_pago"));



String tipo="error";
String mensaje = "Datos no encontrados";
String nuevo = "true";
Formas_pagos forma_pago = new Formas_pagos();
forma_pago.setIdforma_pago(idforma_pago);

 Formas_pagosControlador.buscarId(forma_pago);

if(forma_pago.getIdforma_pago()!=0){
    tipo = "success";
    mensaje = "Datos encontrados";
    nuevo = "false";
 
}

JSONObject obj = new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje", mensaje);
obj.put("nuevo", nuevo);
obj.put("idforma_pago", forma_pago.getIdforma_pago());
obj.put("nombre_forma_pago", forma_pago.getNombre_forma_pago());


out.print(obj);
out.flush();
%>
