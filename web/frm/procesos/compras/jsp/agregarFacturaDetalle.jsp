<%--<%@page import="modelos.Pedidos"%>--%>
<%@page import="electrohouse.Controladores.FacturaDetalleComprasControlador"%>
<%@page import="electrohouse.Controladores.StockControlador"%>
<%@page import="electrohouse.modelos.Productos"%>
<%@page import="electrohouse.modelos.Stock"%>
<%@page import="electrohouse.modelos.FacturaCompras"%>
<%@page import="electrohouse.modelos.FacturaDetalleCompras"%>
<%--<%@page import="modelos.DetallesPedidos"%>--%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    //int id_detallepedido = Integer.parseInt(request.getParameter("id_detallepedido"));
    int idfactura_compra = Integer.parseInt(request.getParameter("idfactura_compra"));

    int idproducto = Integer.parseInt(request.getParameter("idproducto"));
    int cantidad_compra = Integer.parseInt(request.getParameter("cantidad_compra"));
    //int subtotal_compra = Integer.parseInt(request.getParameter("subtotal_compra"));

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    FacturaDetalleCompras facturadetallecompra = new FacturaDetalleCompras();
    //detallepedido.setId_detallepedido(id_detallepedido);
    facturadetallecompra.setCantidad_compra(cantidad_compra);
    //facturadetallecompra.setSubtotal_compra(subtotal_compra);

    FacturaCompras facturacompra = new FacturaCompras();
    facturacompra.setIdfactura_compra(idfactura_compra);

    Productos producto = new Productos();
    producto.setIdproducto(idproducto);

    facturadetallecompra.setFacturacompra(facturacompra);
    facturadetallecompra.setProducto(producto);
    Stock stock = new Stock();
    stock.setCantidad_stock(cantidad_compra);
    stock.setProducto(producto);
    StockControlador.sumar(stock);
    if (FacturaDetalleComprasControlador.agregar(facturadetallecompra)) {
        tipo = "success";
        mensaje = "Datos agregados.";

    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>