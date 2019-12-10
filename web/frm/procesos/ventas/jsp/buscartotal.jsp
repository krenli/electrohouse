


<%@page import="electrohouse.Controladores.FacturaVentasControlador"%>
<%@page import="electrohouse.modelos.FacturaVentas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idfactura_venta = Integer.parseInt(request.getParameter("idfactura_venta"));
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    System.out.println("idfac " + idfactura_venta);
    FacturaVentas facturaventa = FacturaVentasControlador.buscarTotalfactura(idfactura_venta);
    if (facturaventa != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        facturaventa = new FacturaVentas();
        facturaventa.setIdfactura_venta(0);
        facturaventa.setTotal(0);
    }
  
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("idfactura_venta", facturaventa.getIdfactura_venta());
    System.out.println("idfactura==" + facturaventa.getIdfactura_venta());
    obj.put("total", facturaventa.getTotal());
    System.out.println("total " + facturaventa.getTotal());
   
    out.print(obj);
    out.flush();
%>