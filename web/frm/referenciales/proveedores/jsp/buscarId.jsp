<%@page import="electrohouse.Controladores.ProveedoresControlador"%>
<%@page import="electrohouse.modelos.Proveedores"%>
<%@page import="org.json.simple.JSONObject"%>

<%   
int idproveedor =  Integer.parseInt (request.getParameter("idproveedor"));



String tipo="error";
String mensaje = "Datos no encontrados";
String nuevo = "true";
Proveedores proveedor = new Proveedores();
proveedor.setIdproveedor(idproveedor);

 ProveedoresControlador.buscarId(proveedor);

if(proveedor.getIdproveedor()!=0){
    tipo = "success";
    mensaje = "Datos encontrados";
    nuevo = "false";
 
}

JSONObject obj = new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje", mensaje);
obj.put("nuevo", nuevo);
obj.put("idproveedor", proveedor.getIdproveedor());
obj.put("nombre_proveedor", proveedor.getNombre_proveedor());
obj.put("ruc_proveedor", proveedor.getRuc_proveedor());
obj.put("telefono_proveedor", proveedor.getTelefono_proveedor());
obj.put("direccion_proveedor", proveedor.getDireccion_proveedor());
obj.put("email_proveedor", proveedor.getEmail_proveedor());
obj.put("idciudad", proveedor.getCiudad().getIdciudad());
obj.put("nombre_ciudad", proveedor.getCiudad().getNombre_ciudad());
out.print(obj);
out.flush();
%>
