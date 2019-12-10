<%@page import="electrohouse.modelos.Estadosciviles"%>
<%@page import="electrohouse.modelos.Clientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.ClientesControlador"%>
<%@page import="electrohouse.modelos.Ciudades"%>
<%
   
int idcliente =  Integer.parseInt (request.getParameter("idcliente"));
String nombre_cliente = request.getParameter("nombre_cliente");
String apellido_cliente = request.getParameter("apellido_cliente");
String ruc_cliente = request.getParameter("ruc_cliente");
String telefono_cliente = request.getParameter("telefono_cliente");
String fecha_nac_cliente = request.getParameter("fecha_nac_cliente");
String direccion_cliente = request.getParameter("direccion_cliente");
int idciudad =  Integer.parseInt (request.getParameter("idciudad"));
int idestadocivil =  Integer.parseInt (request.getParameter("idestadocivil"));

    String tipo="error";
    String mensaje = "Dato ya existente";
     
Clientes cliente = new Clientes();
cliente.setIdcliente(idcliente);
cliente.setNombre_cliente(nombre_cliente);
cliente.setApellido_cliente(apellido_cliente);
cliente.setRuc_cliente(ruc_cliente);
cliente.setTelefono_cliente(telefono_cliente);
cliente.setFecha_nac_cliente(fecha_nac_cliente);
cliente.setDireccion_cliente(direccion_cliente);


Ciudades ciudad = new Ciudades();
ciudad.setIdciudad(idciudad);
cliente.setCiudad(ciudad);

Estadosciviles estadocivil = new Estadosciviles();
estadocivil.setIdestadocivil(idestadocivil);
cliente.setEstadocivil(estadocivil);


if(ClientesControlador.agregar(cliente)){
tipo = "success";
mensaje = "Datos agregados";
}

JSONObject obj = new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje", mensaje);
System.out.println("tipo" +tipo);
out.print(obj);
out.flush();
%>