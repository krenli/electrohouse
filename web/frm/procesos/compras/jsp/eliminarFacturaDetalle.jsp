<%@page import="electrohouse.Controladores.FacturaDetalleComprasControlador"%>
<%@page import="electrohouse.Controladores.StockControlador"%>
<%@page import="electrohouse.modelos.Stock"%>
<%@page import="electrohouse.modelos.Productos"%>
<%@page import="electrohouse.modelos.FacturaDetalleCompras"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idfactura_detalle_compra = Integer.parseInt(request.getParameter("idfactura_detalle_compra"));
    int idproducto = Integer.parseInt(request.getParameter("idproducto"));
    int cantidad_compra = Integer.parseInt(request.getParameter("cantidad_compra"));
    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    FacturaDetalleCompras facturadetallecompra = new FacturaDetalleCompras();
    facturadetallecompra.setIdfactura_detalle_compra(idfactura_detalle_compra);

    Productos producto = new Productos();
    producto.setIdproducto(idproducto);
    Stock stock = new Stock();
    stock.setCantidad_stock(cantidad_compra);
    stock.setProducto(producto);
    StockControlador.descontar(stock);
    if (FacturaDetalleComprasControlador.eliminar(facturadetallecompra)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>