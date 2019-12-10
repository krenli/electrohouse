


<%@page import="electrohouse.Controladores.PagaresControlador"%>
<%@page import="electrohouse.modelos.Pagares"%>
<%@page import="electrohouse.utiles.Utiles"%>
<%@page import="electrohouse.Controladores.FacturaVentasControlador"%>
<%@page import="electrohouse.modelos.FacturaVentas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idpagare = 0;
    
    int idfactura_venta = Integer.parseInt(request.getParameter("idfactura_venta"));
    //String nro_cuota = request.getParameter("cantidad_cuotas");
    //String stotal = request.getParameter("total");
    //String montoenletras = Utiles.cantidadConLetra(stotal);
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
   FacturaVentas ventas = new FacturaVentas();
    ventas.setIdfactura_venta(idfactura_venta);
   
    
    
    //recibo.setMontoenletras(montoenletras);
    FacturaVentas facturaventa = FacturaVentasControlador.buscarTotalf(idfactura_venta);
    if (facturaventa != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        //nuevo = "false";
    } else {
        facturaventa = new FacturaVentas();
        facturaventa.setIdfactura_venta(0);
        facturaventa.setTotal(0);
    }
    int monto = facturaventa.getTotal();
    String montoaconvertir = String.valueOf(monto);
    String montoenletras = Utiles.cantidadConLetra(montoaconvertir);
    Pagares pagare = new Pagares();
    pagare.setIdpagare(idpagare);
    pagare.setVentas(ventas);
    pagare.setMontoenletras(montoenletras);
    
    
    if (PagaresControlador.agregar(pagare)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("idfactura_venta", facturaventa.getIdfactura_venta());
    System.out.println("idfactura==" + facturaventa.getIdfactura_venta());
    obj.put("total", facturaventa.getTotal());
    //obj.put("id", recibo.getId_recibo());
    out.print(obj);
    out.flush();
%>

