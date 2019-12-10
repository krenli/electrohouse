<%@page import="electrohouse.modelos.Productos"%>
<%@page import="electrohouse.modelos.FacturaCompras"%>
<%@page import="electrohouse.Controladores.FacturaDetalleComprasControlador"%>
<%@page import="electrohouse.modelos.FacturaDetalleCompras"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idfactura_detalle_compra = Integer.parseInt(request.getParameter("idfactura_detalle_compra"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    FacturaDetalleCompras facturadetallecompra = FacturaDetalleComprasControlador.buscarId(idfactura_detalle_compra);
    if (facturadetallecompra != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        facturadetallecompra = new FacturaDetalleCompras();
        facturadetallecompra.setIdfactura_detalle_compra(0);
        //facturadetallecompra.setSubtotal_compra(0);
        
        FacturaCompras facturacompra = new FacturaCompras();
        facturacompra.setIdfactura_compra(0);
        facturadetallecompra.setFacturacompra(facturacompra);
        
        Productos producto = new Productos();
        producto.setIdproducto(0);
        producto.setNombre_producto("");
        facturadetallecompra.setProducto(producto);
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("idfactura_detalle_compra", String.valueOf(facturadetallecompra.getIdfactura_detalle_compra()));
    obj.put("idfactura_compra", String.valueOf(facturadetallecompra.getFacturacompra().getIdfactura_compra()));
    obj.put("idproducto", String.valueOf(facturadetallecompra.getProducto().getIdproducto()));
    obj.put("nombre_producto", facturadetallecompra.getProducto().getNombre_producto());
    obj.put("costo_producto", facturadetallecompra.getProducto().getCosto_producto());
    obj.put("cantidad_compra", String.valueOf(facturadetallecompra.getCantidad_compra()));
    //obj.put("subtotal_compra", String.valueOf(facturadetallecompra.getSubtotal_compra()));
    
    out.print(obj);
    out.flush();
%>