<%@page import="electrohouse.Controladores.DetallesAjustesControlador"%>
<%@page import="electrohouse.Controladores.StockControlador"%>
<%@page import="electrohouse.modelos.Stock"%>
<%@page import="electrohouse.modelos.Productos"%>
<%@page import="electrohouse.modelos.Ajustes"%>
<%@page import="electrohouse.modelos.DetallesAjustes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    int idajuste_stock_detalle = Integer.parseInt(request.getParameter("idajuste_stock_detalle"));
    int cantidad_ajuste_stock = Integer.parseInt(request.getParameter("cantidad_ajuste_stock"));
    int idajuste_stock = Integer.parseInt(request.getParameter("idajuste_stock"));
    int idproducto = Integer.parseInt(request.getParameter("idproducto"));
    // int total_ajuste= con + 0; 

    //int con=total_detalleajuste;
    String tipo = "error";
    String mensaje = "Datos no agregados.";

    DetallesAjustes detalleajuste = new DetallesAjustes();
    detalleajuste.setIdajuste_stock_detalle(idajuste_stock_detalle);
    detalleajuste.setCantidad_ajuste_stock(cantidad_ajuste_stock);

    Ajustes ajuste = new Ajustes();
    ajuste.setIdajuste_stock(idajuste_stock);

    Productos producto = new Productos();
    producto.setIdproducto(idproducto);
    detalleajuste.setAjuste(ajuste);
    detalleajuste.setProducto(producto);
    Stock stock = new Stock();
    stock.setCantidad_stock(cantidad_ajuste_stock);
    stock.setProducto(producto);
    StockControlador.sumar(stock);

    if (DetallesAjustesControlador.agregar(detalleajuste)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    producto = new Productos();
    producto.setIdproducto(idproducto); //el de abajo puede ser un problema
   // ProductosControlador.modificara(producto);

    /*Stock stock = new Stock();
    stock.setCantidad_stock(cantidad_ajuste_stock);
    stock.setArticulo(articulo);
    StockControlador.sumar(stock);*/

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>