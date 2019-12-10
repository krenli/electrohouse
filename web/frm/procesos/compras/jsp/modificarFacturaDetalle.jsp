<%@page import="electrohouse.Controladores.FacturaDetalleComprasControlador"%>
<%@page import="electrohouse.modelos.Productos"%>
<%@page import="electrohouse.modelos.FacturaCompras"%>
<%@page import="electrohouse.modelos.FacturaDetalleCompras"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int idfactura_detalle_compra = Integer.parseInt(request.getParameter("idfactura_detalle_compra"));
    int cantidad_compra = Integer.parseInt(request.getParameter("cantidad_compra"));
    //int subtotal_compra = Integer.parseInt(request.getParameter("subtotal_compra"));
   int idfactura_compra = Integer.parseInt(request.getParameter("idfactura_compra"));
    int idproducto = Integer.parseInt(request.getParameter("idproducto")); 

    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    FacturaDetalleCompras facturadetallecompra = new FacturaDetalleCompras();
    facturadetallecompra.setIdfactura_detalle_compra(idfactura_detalle_compra);
    facturadetallecompra.setCantidad_compra(cantidad_compra);
    //facturadetallecompra.setSubtotal_compra(subtotal_compra);
    
    FacturaCompras facturacompra = new FacturaCompras();
    facturacompra.setIdfactura_compra(idfactura_compra);
    
    Productos producto = new Productos();
    producto.setIdproducto(idproducto);
    
    facturadetallecompra.setFacturacompra(facturacompra);
    facturadetallecompra.setProducto(producto);
      
    if (FacturaDetalleComprasControlador.modificar(facturadetallecompra)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>