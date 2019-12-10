

<%@page import="electrohouse.Controladores.FacturaVentasControlador"%>
<%@page import="electrohouse.Controladores.StockControlador"%>
<%@page import="electrohouse.modelos.Stock"%>
<%@page import="electrohouse.modelos.FacturaVentas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idfactura_venta = Integer.parseInt(request.getParameter("idfactura_venta"));

    String tipo = "error";
    String mensaje = "Datos no anulados.";

    FacturaVentas facturaventa = new FacturaVentas();
    facturaventa.setIdfactura_venta(idfactura_venta);
    
    
    Stock stock = new Stock();
    stock.setIdstock(idfactura_venta);
    StockControlador.sumarStock(stock);
    //FacturaDetalleVentasControlador.eliminarArticuloVentas(facturaventa);

    if (FacturaVentasControlador.Anular(facturaventa)) {
        tipo = "success";
        mensaje = "Datos anulados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>