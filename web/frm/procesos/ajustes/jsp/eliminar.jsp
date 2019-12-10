<%@page import="electrohouse.Controladores.AjustesControlador"%>
<%@page import="electrohouse.Controladores.DetallesAjustesControlador"%>
<%@page import="electrohouse.Controladores.StockControlador"%>
<%@page import="electrohouse.modelos.Stock"%>
<%@page import="electrohouse.modelos.DetallesAjustes"%>
<%@page import="electrohouse.modelos.Ajustes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idajuste_stock = Integer.parseInt(request.getParameter("idajuste_stock"));
    System.out.println("EL STOCK"+ idajuste_stock);
    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    Ajustes ajuste = new Ajustes();
    ajuste.setIdajuste_stock(idajuste_stock);
    DetallesAjustes detalleajuste = new DetallesAjustes();
    detalleajuste.setAjuste(ajuste);

    /*ajuste = new Ajustes();
    ajuste.setId_ajuste_stock(id_ajuste_stock);*/
    Stock stock = new Stock();
    stock.setIdstock(idajuste_stock);
    //stock.setCantidad_stock(cantidad_ajuste_stock);
    //stock.setArticulo(articulo);
    if (StockControlador.sumarAjusteStock(stock)) {
        System.out.println("BORRADO EL STOCK");
        if (DetallesAjustesControlador.eliminarDetalle(detalleajuste)) {
            System.out.println("BORRADO EL DETALLE");
            if (AjustesControlador.eliminar(ajuste)) {
                tipo = "success";
                mensaje = "Datos eliminados.";
            }
        }

    }
    
    
    /*if (DetallesAjustesControlador.eliminarc(detalleajuste)) {
        tipo = "success";
        mensaje = "Datos eliminados.";

    }*/
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>