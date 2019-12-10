<%@page import="electrohouse.Controladores.FacturaComprasControlador"%>
<%@page import="electrohouse.modelos.FacturaCompras"%>
<%@page import="electrohouse.modelos.Tipo_facturas"%>
<%@page import="electrohouse.modelos.Proveedores"%>
<%@page import="electrohouse.utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int idfactura_compra = Integer.parseInt(request.getParameter("idfactura_compra"));
    int idproveedor = Integer.parseInt(request.getParameter("idproveedor"));
    int idtipo_factura = Integer.parseInt(request.getParameter("idtipo_factura"));
    //int idtipo_factura = Integer.parseInt(request.getParameter("idtipo_factura"));
    //String ruc_factura_compra = (request.getParameter("ruc_factura_compra"));

    
    String sfecha_factura_compra = request.getParameter("fecha_factura_compra");
    java.sql.Date fecha_factura_compra = Utiles.stringToSqlDate(sfecha_factura_compra);
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Proveedores proveedor = new Proveedores();
    proveedor.setIdproveedor(idproveedor);
    
    Tipo_facturas tipofactura = new Tipo_facturas();
    tipofactura.setIdtipo_factura(idtipo_factura);
        
    
    /*Tipo_facturas tipofactura = new Tipo_facturas();
    tipofactura.setId_tipo_factura(idtipo_factura);*/
   
    
    FacturaCompras facturacompra = new FacturaCompras();
    facturacompra.setIdfactura_compra(idfactura_compra);
    //facturacompra.setRuc_factura_compra(ruc_factura_compra);
    facturacompra.setFecha_factura_compra(fecha_factura_compra);
    facturacompra.setProveedor(proveedor);
    facturacompra.setTipofactura(tipofactura);
    //facturacompra.setTipofactura(tipofactura);
      
    
    if (FacturaComprasControlador.agregar(facturacompra)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("idfactura_compra", String.valueOf(facturacompra.getIdfactura_compra()));
    out.print(obj);
    out.flush();
    
%>