<%@page import="electrohouse.Controladores.FacturaDetalleComprasControlador"%>
<%@page import="electrohouse.modelos.Tipo_facturas"%>
<%@page import="electrohouse.modelos.Proveedores"%>
<%@page import="electrohouse.Controladores.FacturaComprasControlador"%>
<%@page import="electrohouse.modelos.FacturaCompras"%>
<%@page import="electrohouse.utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idfactura_compra = Integer.parseInt(request.getParameter("idfactura_compra"));
     String sfecha_factura_compra = request.getParameter("fecha_factura_compra");
    java.sql.Date fecha_factura_compra = Utiles.stringToSqlDate(sfecha_factura_compra);
    //int id_tipo_factura = Integer.parseInt(request.getParameter("id_tipo_factura"));
    //String ruc_factura_compra = request.getParameter("ruc_factura_compra");
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    FacturaCompras facturacompra = FacturaComprasControlador.buscarId(idfactura_compra);
    if (facturacompra != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        facturacompra = new FacturaCompras();
        facturacompra.setIdfactura_compra(0);
        facturacompra.setFecha_factura_compra(fecha_factura_compra);
  
        Proveedores proveedor = new Proveedores();
        facturacompra.setProveedor(proveedor);
        
        Tipo_facturas tipofactura = new Tipo_facturas();
        facturacompra.setTipofactura(tipofactura);
        }
    
        
    
    String contenido_detalle = FacturaDetalleComprasControlador.buscarIdFacturaCompra(idfactura_compra);
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("idfactura_compra", facturacompra.getIdfactura_compra());
    obj.put("fecha_factura_compra", String.valueOf(facturacompra.getFecha_factura_compra()));
    obj.put("idproveedor", facturacompra.getProveedor().getIdproveedor());
    obj.put("nombre_proveedor", facturacompra.getProveedor().getNombre_proveedor());
    obj.put("ruc_proveedor", facturacompra.getProveedor().getRuc_proveedor());
    obj.put("idtipo_factura", facturacompra.getTipofactura().getIdtipo_factura());
    obj.put("nombre_tipo_factura", facturacompra.getTipofactura().getNombre_tipo_factura());
    obj.put("contenido_detalle", contenido_detalle);
    
    out.print(obj);
    out.flush();
%>