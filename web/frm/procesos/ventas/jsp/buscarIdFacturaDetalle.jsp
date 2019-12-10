


<%@page import="electrohouse.modelos.FacturaVentas"%>
<%@page import="electrohouse.Controladores.FacturaDetalleVentasControlador"%>
<%@page import="electrohouse.modelos.FacturaDetalle"%>
<%@page import="electrohouse.modelos.Productos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idfactura_detalle_venta = Integer.parseInt(request.getParameter("idfactura_detalle_venta"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    FacturaDetalle facturadetalle = FacturaDetalleVentasControlador.buscarId(idfactura_detalle_venta);
    if (facturadetalle != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        facturadetalle = new FacturaDetalle();
        facturadetalle.setIdfactura_detalle_venta(0);
        facturadetalle.setSubtotal_venta(0);
        
        FacturaVentas facturaventa = new FacturaVentas();
        facturaventa.setIdfactura_venta(0);
        facturadetalle.setFacturaventa(facturaventa);
        
        Productos producto = new Productos();
        producto.setIdproducto(0);
        producto.setNombre_producto("");
        
        facturadetalle.setProducto(producto);
        
        
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("idfactura_detalle_venta", String.valueOf(facturadetalle.getIdfactura_detalle_venta()));
    obj.put("idfactura_venta", String.valueOf(facturadetalle.getFacturaventa().getIdfactura_venta()));
    obj.put("idproducto", String.valueOf(facturadetalle.getProducto().getIdproducto()));
    obj.put("nombre_producto", facturadetalle.getProducto().getNombre_producto());
    obj.put("costo_producto", facturadetalle.getProducto().getCosto_producto());
    obj.put("precio_producto", facturadetalle.getProducto().getPrecio_producto());
    //obj.put("id_iva", facturadetalle.getProducto().getIva().getId_iva());
    obj.put("iva_producto", facturadetalle.getProducto().getIva_producto());
    obj.put("cantidad_venta", String.valueOf(facturadetalle.getCantidad_venta()));
    //obj.put("subtotal_venta", String.valueOf(facturadetalle.getSubtotal_venta()));
    
    out.print(obj);
    out.flush();
%>