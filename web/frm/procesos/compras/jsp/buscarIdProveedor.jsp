<%@page import="electrohouse.Controladores.ProveedoresControlador"%>
<%@page import="electrohouse.modelos.Proveedores"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet" %>
<%
    int idproveedor =Integer.parseInt(request.getParameter("idproveedor"));
    String nombre_proveedor =request.getParameter("nombre_proveedor");
    String ruc_proveedor =request.getParameter("ruc_proveedor");
    Proveedores proveedor= new Proveedores();
    proveedor.setIdproveedor(idproveedor);
    proveedor.setNombre_proveedor(nombre_proveedor);
    proveedor.setRuc_proveedor(ruc_proveedor);
    String mensaje= "Datos no encontrados";
    proveedor= ProveedoresControlador.buscarId(proveedor);
    //System.out.println("llega");
    if (proveedor.getIdproveedor()!=0){
        mensaje ="Datos encontrados";
    }
    String tipo="error";
    String nuevo="true";
    if (proveedor!=null){
        tipo= "success";
        mensaje= "Datos encontrados.";
        nuevo= "false";
    }
    JSONObject obj=new JSONObject ();
    obj.put("tipo",tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    obj.put("idproveedor",proveedor.getIdproveedor());
    obj.put("nombre_proveedor",proveedor.getNombre_proveedor());
    obj.put("ruc_proveedor",proveedor.getRuc_proveedor());
    //obj.put("idproveedor",proveedor.getProveedor(),getId_proveedor());
    //obj.put("nombre_proveedor",proveedor.getProveedor().getNombre_proveedor());
    out.print(obj);
    out.flush();
    %>