
<%@page import="electrohouse.Controladores.FacturaVentasControlador"%>
<%@page import="electrohouse.modelos.FacturaVentas"%>
<%@page import="electrohouse.modelos.Timbrados"%>
<%@page import="electrohouse.modelos.Tipo_facturas"%>
<%@page import="electrohouse.modelos.Clientes"%>
<%@page import="electrohouse.utiles.Utiles"%>
<%@page import="electrohouse.modelos.Usuarios"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int idfactura_venta = Integer.parseInt(request.getParameter("idfactura_venta"));
    int numero_factura_venta = Integer.parseInt(request.getParameter("numero_factura_venta"));
    int idcliente = Integer.parseInt(request.getParameter("idcliente"));
    int idtipo_factura = Integer.parseInt(request.getParameter("idtipo_factura"));
    int idusuario = Integer.parseInt(request.getParameter("sid_usuario"));
    int cantidad_cuotas = Integer.parseInt(request.getParameter("cantidad_cuotas"));
    //int id_pedido = 0;
    //int id_tipo_factura = Integer.parseInt(request.getParameter("id_tipo_factura"));
    //String ruc_factura_venta = (request.getParameter("ruc_factura_venta"));
    String estado_venta = "PENDIENTE";
    int idtimbrado = Integer.parseInt(request.getParameter("idtimbrado"));

    Usuarios usuario = new Usuarios();
    usuario.setIdusuario(idusuario);
    
    String sfecha_factura_venta = request.getParameter("fecha_factura_venta");
    java.sql.Date fecha_factura_venta = Utiles.stringToSqlDate(sfecha_factura_venta);
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Clientes cliente = new Clientes();
    cliente.setIdcliente(idcliente);
    
    Tipo_facturas tipofactura = new Tipo_facturas();
    tipofactura.setIdtipo_factura(idtipo_factura);
    
      /*Pedidos pedido = new Pedidos();
    pedido.setId_pedido(id_pedido);*/  
    
    Timbrados timbrado = new Timbrados();
    timbrado.setIdtimbrado(idtimbrado);
    
    /*Tipo_facturas tipofactura = new Tipo_facturas();
    tipofactura.setId_tipo_factura(id_tipo_factura);*/
   
    
    FacturaVentas facturaventa = new FacturaVentas();
    facturaventa.setIdfactura_venta(idfactura_venta);
    //facturaventa.setRuc_factura_venta(ruc_factura_venta);
    facturaventa.setFecha_factura_venta(fecha_factura_venta);
    facturaventa.setCliente(cliente);
    facturaventa.setTipofactura(tipofactura);
    /*facturaventa.setSubtotal_5(subtotal_5);
    facturaventa.setSubtotal_10(subtotal_10);
    facturaventa.setSubtotal_exenta(subtotal_exenta);*/
    facturaventa.setCantidad_cuotas(cantidad_cuotas);
    facturaventa.setNumero_factura_venta(numero_factura_venta);
    facturaventa.setEstado_venta(estado_venta);
    //facturaventa.setPedido(pedido);
    facturaventa.setTimbrado(timbrado);
    facturaventa.setUsuario(usuario);
    //facturaventa.setTipofactura(tipofactura);
      
    
    if (FacturaVentasControlador.agregar(facturaventa)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("idfactura_venta", String.valueOf(facturaventa.getIdfactura_venta()));
    out.print(obj);
    out.flush();
    
%>