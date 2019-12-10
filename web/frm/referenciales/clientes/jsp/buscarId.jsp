<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.ClientesControlador"%>
<%@page import="electrohouse.modelos.Clientes"%>
<%   
int idcliente =  Integer.parseInt (request.getParameter("idcliente"));



String tipo="error";
String mensaje = "Datos no encontrados";
String nuevo = "true";
Clientes cliente = new Clientes();
cliente.setIdcliente(idcliente);

 ClientesControlador.buscarId(cliente);

if(cliente.getIdcliente()!=0){
    tipo = "success";
    mensaje = "Datos encontrados";
    nuevo = "false";
 
}

JSONObject obj = new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje", mensaje);
obj.put("nuevo", nuevo);
obj.put("idcliente", cliente.getIdcliente());
obj.put("nombre_cliente", cliente.getNombre_cliente());
obj.put("apellido_cliente", cliente.getApellido_cliente());
obj.put("ruc_cliente", cliente.getRuc_cliente());
obj.put("telefono_cliente", cliente.getTelefono_cliente());
obj.put("fecha_nac_cliente", cliente.getFecha_nac_cliente());
obj.put("direccion_cliente", cliente.getDireccion_cliente());
obj.put("idciudad", cliente.getCiudad().getIdciudad());
obj.put("nombre_ciudad", cliente.getCiudad().getNombre_ciudad());
obj.put("idestadocivil", cliente.getEstadocivil().getIdestadocivil());
obj.put("nombre_estadocivil", cliente.getEstadocivil().getNombre_estadocivil());
out.print(obj);
out.flush();
%>
