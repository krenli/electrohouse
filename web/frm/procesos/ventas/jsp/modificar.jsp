
<%@page import="electrohouse.modelos.FacturaVentas"%>
<%@page import="electrohouse.modelos.Tipo_facturas"%>
<%@page import="electrohouse.modelos.Clientes"%>
<%@page import="electrohouse.Controladores.FacturaVentasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idfactura_venta = Integer.parseInt(request.getParameter("idfactura_venta"));
    int idcliente = Integer.parseInt(request.getParameter("idcliente"));
    int idtipo_factura = Integer.parseInt(request.getParameter("idtipo_factura"));
    int cantidad_cuotas = Integer.parseInt(request.getParameter("cantidad_cuotas"));

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    Clientes cliente = new Clientes();
    cliente.setIdcliente(idcliente);
    
    Tipo_facturas tipofactura = new Tipo_facturas();
    tipofactura.setIdtipo_factura(idtipo_factura);

    FacturaVentas facturaventa = new FacturaVentas();
    facturaventa.setIdfactura_venta(idfactura_venta);
    facturaventa.setCliente(cliente);
    facturaventa.setTipofactura(tipofactura);
    facturaventa.setCantidad_cuotas(cantidad_cuotas);
   
    if (FacturaVentasControlador.modificar(facturaventa)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>