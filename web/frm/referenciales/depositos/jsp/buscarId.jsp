<%@page import="electrohouse.Controladores.DepositosControlador"%>
<%@page import="electrohouse.modelos.Depositos"%>
<%@page import="org.json.simple.JSONObject"%>

<%   
int iddeposito =  Integer.parseInt (request.getParameter("iddeposito"));



String tipo="error";
String mensaje = "Datos no encontrados";
String nuevo = "true";
Depositos deposito = new Depositos();
deposito.setIddeposito(iddeposito);

 DepositosControlador.buscarId(deposito);

if(deposito.getIddeposito()!=0){
    tipo = "success";
    mensaje = "Datos encontrados";
    nuevo = "false";
 
}

JSONObject obj = new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje", mensaje);
obj.put("nuevo", nuevo);
obj.put("iddeposito", deposito.getIddeposito());
obj.put("nombre_deposito", deposito.getNombre_deposito());
obj.put("idciudad", deposito.getCiudad().getIdciudad());
obj.put("nombre_ciudad", deposito.getCiudad().getNombre_ciudad());

out.print(obj);
out.flush();
%>
