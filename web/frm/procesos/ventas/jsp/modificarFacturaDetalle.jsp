
<%@page import="electrohouse.Controladores.FacturaDetalleVentasControlador"%>
<%@page import="electrohouse.modelos.Productos"%>
<%@page import="electrohouse.modelos.FacturaVentas"%>
<%@page import="electrohouse.modelos.FacturaDetalle"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int idfactura_detalle_venta = Integer.parseInt(request.getParameter("idfactura_detalle_venta"));
    int cantidad_venta = Integer.parseInt(request.getParameter("cantidad_venta"));
    int subtotal_venta = Integer.parseInt(request.getParameter("subtotal_venta"));
   int idfactura_venta = Integer.parseInt(request.getParameter("idfactura_venta"));
    int idproducto = Integer.parseInt(request.getParameter("idproducto")); 

    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    FacturaDetalle facturadetalle = new FacturaDetalle();
    facturadetalle.setIdfactura_detalle_venta(idfactura_detalle_venta);
    facturadetalle.setCantidad_venta(cantidad_venta);
    facturadetalle.setSubtotal_venta(subtotal_venta);
    
    FacturaVentas facturaventa = new FacturaVentas();
    facturaventa.setIdfactura_venta(idfactura_venta);
    
    Productos producto = new Productos();
    producto.setIdproducto(idproducto);
    
    facturadetalle.setFacturaventa(facturaventa);
    facturadetalle.setProducto(producto);
      
    if (FacturaDetalleVentasControlador.modificar(facturadetalle)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>