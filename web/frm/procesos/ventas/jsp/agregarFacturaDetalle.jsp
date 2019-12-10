
<%@page import="electrohouse.Controladores.FacturaDetalleVentasControlador"%>
<%@page import="electrohouse.Controladores.StockControlador"%>
<%@page import="electrohouse.modelos.Stock"%>
<%@page import="electrohouse.modelos.Productos"%>
<%@page import="electrohouse.modelos.FacturaVentas"%>
<%@page import="electrohouse.modelos.FacturaDetalle"%>
<%--<%@page import="modelos.Pedidos"%>--%>
<%--<%@page import="modelos.DetallesPedidos"%>--%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    //int id_detallepedido = Integer.parseInt(request.getParameter("id_detallepedido"));
    int idfactura_venta = Integer.parseInt(request.getParameter("idfactura_venta"));

    int idproducto = Integer.parseInt(request.getParameter("idproducto"));
    int cantidad_venta = Integer.parseInt(request.getParameter("cantidad_venta"));
    //int subtotal_venta = Integer.parseInt(request.getParameter("subtotal_venta"));
    /*int subtotal_5 = Integer.parseInt(request.getParameter("ssubtotal_5"));
    int subtotal_10 = Integer.parseInt(request.getParameter("ssubtotal_10"));
    int subtotal_exenta = Integer.parseInt(request.getParameter("ssubtotal_exenta"));*/
    
    
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";

    FacturaDetalle facturadetalle = new FacturaDetalle();
    //detallepedido.setId_detallepedido(id_detallepedido);
    facturadetalle.setCantidad_venta(cantidad_venta);
    //facturadetalle.setSubtotal_venta(subtotal_venta);

    FacturaVentas facturaventa = new FacturaVentas();
    facturaventa.setIdfactura_venta(idfactura_venta);
    /*facturaventa.setSubtotal_10(subtotal_10);
    facturaventa.setSubtotal_5(subtotal_5);
    facturaventa.setSubtotal_exenta(subtotal_exenta);*/
    

    Productos producto = new Productos();
    producto.setIdproducto(idproducto);

    facturadetalle.setFacturaventa(facturaventa);
    facturadetalle.setProducto(producto);
    Stock stock = new Stock();
    stock.setCantidad_stock(cantidad_venta);
    stock.setProducto(producto);
    StockControlador.descontar(stock);
    
    
    if (stock.getCantidad_stock()!=-1){
        //FacturaVentasControlador.subtotaliva(facturaventa);
        if (FacturaDetalleVentasControlador.agregar(facturadetalle)) {
        
        tipo = "success";
        mensaje = "Datos agregados.";
        }
    }else{
        tipo = "success";
        mensaje = "Stock insuficiente";
    }
    

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("cantidad_stock", stock.getCantidad_stock());
    out.print(obj);
    out.flush();

%>