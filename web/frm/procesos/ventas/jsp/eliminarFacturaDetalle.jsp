

<%@page import="electrohouse.Controladores.FacturaDetalleVentasControlador"%>
<%@page import="electrohouse.Controladores.StockControlador"%>
<%@page import="electrohouse.modelos.Stock"%>
<%@page import="electrohouse.modelos.Productos"%>
<%@page import="electrohouse.modelos.FacturaDetalle"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idfactura_detalle_venta = Integer.parseInt(request.getParameter("idfactura_detalle_venta"));
    int idproducto = Integer.parseInt(request.getParameter("idproducto"));
    int cantidad_venta = Integer.parseInt(request.getParameter("cantidad_venta"));
    //int id_factura_venta = Integer.parseInt(request.getParameter("id_factura_venta"));
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    //FacturaVentas facturaventa = new FacturaVentas();
    //facturaventa.setId_factura_venta(id_factura_venta);

    FacturaDetalle facturadetalle = new FacturaDetalle();
    facturadetalle.setIdfactura_detalle_venta(idfactura_detalle_venta);
    //facturadetalle.setFacturaventa(facturaventa);

    Productos producto = new Productos();
    producto.setIdproducto(idproducto);
    Stock stock = new Stock();
    stock.setCantidad_stock(cantidad_venta);
    stock.setProducto(producto);
    StockControlador.sumar(stock);
    if (FacturaDetalleVentasControlador.eliminar(facturadetalle)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>