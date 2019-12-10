


<%@page import="electrohouse.modelos.FacturaDetalle"%>
<%@page import="electrohouse.Controladores.FacturaDetalleVentasControlador"%>
<%@page import="electrohouse.Controladores.FacturaVentasControlador"%>
<%@page import="electrohouse.modelos.FacturaVentas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idfactura_venta = Integer.parseInt(request.getParameter("idfactura_venta"));
     //String sfecha_factura_venta = request.getParameter("fecha_factura_venta");
    //java.sql.Date fecha_factura_venta = Utiles.stringToSqlDate(sfecha_factura_venta);
    //int id_tipo_factura = Integer.parseInt(request.getParameter("id_tipo_factura"));
    //String ruc_factura_venta = request.getParameter("ruc_factura_venta");
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    //java.sql.Date ssFecha_factura_venta = new java.sql.Date(new java.util.Date().getTime());
    FacturaVentas facturaventa = FacturaVentasControlador.buscarId(idfactura_venta);
    if (facturaventa.getIdfactura_venta()!= 0) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } 
    

    String contenido_detalle = FacturaDetalleVentasControlador.buscarIdFacturaVenta(idfactura_venta);
    
  
   
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    System.out.println("idfactura"+idfactura_venta);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("idfactura_venta", facturaventa.getIdfactura_venta());
    obj.put("fecha_factura_venta", String.valueOf(facturaventa.getFecha_factura_venta()));
    obj.put("idcliente", facturaventa.getCliente().getIdcliente());
    obj.put("nombre_cliente", facturaventa.getCliente().getNombre_cliente());
    obj.put("ruc_cliente", facturaventa.getCliente().getRuc_cliente());
    obj.put("idtipo_factura", facturaventa.getTipofactura().getIdtipo_factura());
    obj.put("nombre_tipo_factura", facturaventa.getTipofactura().getNombre_tipo_factura());
    /*obj.put("subtotal_5", facturaventa.getSubtotal_5());
    obj.put("subtotal_10", facturaventa.getSubtotal_10());
    obj.put("subtotal_exenta", facturaventa.getSubtotal_exenta());*/
    obj.put("cantidad_cuotas", facturaventa.getCantidad_cuotas());
    obj.put("numero_factura_venta", facturaventa.getNumero_factura_venta());
     obj.put("totalp", facturaventa.getTotalP());
     System.out.println("de toniiiii" +facturaventa.getTotalP());
    obj.put("idtimbrado", facturaventa.getTimbrado().getIdtimbrado());
    System.out.println("idtimbrado= " + facturaventa.getTimbrado().getIdtimbrado());
    obj.put("idtipo_personal", facturaventa.getTimbrado().getTipo_personal().getIdtipo_personal());
    obj.put("contenido_detalle", contenido_detalle);
    out.print(obj);
    out.flush();
%>