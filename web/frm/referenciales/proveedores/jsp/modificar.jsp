<%@page import="electrohouse.modelos.Ciudades"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.ProveedoresControlador"%>
<%@page import="electrohouse.modelos.Proveedores"%>
<%
    
int idproveedor =  Integer.parseInt (request.getParameter("idproveedor"));
String nombre_proveedor = request.getParameter("nombre_proveedor");
String ruc_proveedor = request.getParameter("ruc_proveedor");
String telefono_proveedor = request.getParameter("telefono_proveedor");
String direccion_proveedor = request.getParameter("direccion_proveedor");
String email_proveedor = request.getParameter("email_proveedor");
int idciudad =  Integer.parseInt (request.getParameter("idciudad"));

String tipo="error";
String mensaje = "Dato ya existente";

Proveedores proveedor = new Proveedores();
proveedor.setIdproveedor(idproveedor);
proveedor.setNombre_proveedor(nombre_proveedor);
proveedor.setRuc_proveedor(ruc_proveedor);
proveedor.setTelefono_proveedor(telefono_proveedor);
proveedor.setDireccion_proveedor(direccion_proveedor);
proveedor.setEmail_proveedor(email_proveedor);

Ciudades ciudad = new Ciudades();
ciudad.setIdciudad(idciudad);
proveedor.setCiudad(ciudad);

if(ProveedoresControlador.modificar(proveedor)){
tipo = "success";
mensaje = "Datos modificados";
}

JSONObject obj = new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje", mensaje);
System.out.println("tipo" +tipo);
out.print(obj);
out.flush();
%>