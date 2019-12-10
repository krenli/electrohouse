<%@page import="electrohouse.modelos.Productos"%>
<%@page import="electrohouse.modelos.Ajustes"%>
<%@page import="electrohouse.Controladores.DetallesAjustesControlador"%>
<%@page import="electrohouse.modelos.DetallesAjustes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idajuste_stock_detalle = Integer.parseInt(request.getParameter("idajuste_stock_detalle"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    DetallesAjustes detalleajuste = DetallesAjustesControlador.buscarId(idajuste_stock_detalle);
    if (detalleajuste != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detalleajuste = new DetallesAjustes();
        detalleajuste.setIdajuste_stock_detalle(0);
        detalleajuste.setCantidad_ajuste_stock(0);
        
        Ajustes ajuste = new Ajustes();
        ajuste.setIdajuste_stock(0);
        detalleajuste.setAjuste(ajuste);
        
        Productos producto = new Productos();
        producto.setIdproducto(0);
        producto.setNombre_producto("");
        producto.setCosto_producto(0);
        detalleajuste.setProducto(producto);
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("idajuste_stock_detalle", detalleajuste.getIdajuste_stock_detalle());
    obj.put("idajuste_stock", detalleajuste.getAjuste().getIdajuste_stock());
    obj.put("idproducto", detalleajuste.getProducto().getIdproducto());
    obj.put("nombre_producto", detalleajuste.getProducto().getNombre_producto());
    obj.put("costo_producto", detalleajuste.getProducto().getCosto_producto());
    obj.put("cantidad_ajuste_stock", detalleajuste.getCantidad_ajuste_stock());    
    out.print(obj);
    out.flush();
%>