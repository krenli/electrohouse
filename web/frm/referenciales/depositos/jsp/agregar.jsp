<%@page import="electrohouse.modelos.Ciudades"%>
<%@page import="electrohouse.Controladores.DepositosControlador"%>
<%@page import="electrohouse.modelos.Depositos"%>
<%@page import="org.json.simple.JSONObject"%>

<%
    
int iddeposito =  Integer.parseInt (request.getParameter("iddeposito"));
String nombre_deposito = request.getParameter("nombre_deposito");
int idciudad =  Integer.parseInt (request.getParameter("idciudad"));

String tipo="error";
String mensaje = "Dato ya existente";

Depositos deposito = new Depositos();
deposito.setIddeposito(iddeposito);
deposito.setNombre_deposito(nombre_deposito);

Ciudades ciudad = new Ciudades();
ciudad.setIdciudad(idciudad);
deposito.setCiudad(ciudad);

if(DepositosControlador.agregar(deposito)){
tipo = "success";
mensaje = "Datos Guardados";
}

JSONObject obj = new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje", mensaje);
System.out.println("tipo" +tipo);
out.print(obj);
out.flush();
%>