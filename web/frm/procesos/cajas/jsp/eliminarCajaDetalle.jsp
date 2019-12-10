
<%@page import="electrohouse.modelos.FacturaVentas"%>
<%@page import="electrohouse.modelos.DetallesCajas"%>
<%@page import="electrohouse.modelos.Cajas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idcaja = Integer.parseInt(request.getParameter("idcaja"));
    int idfactura_venta = Integer.parseInt(request.getParameter("idfactura_venta"));
    int iddetallecaja = Integer.parseInt(request.getParameter("idcaja"));
    int importe = Integer.parseInt(request.getParameter("importe"));
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    Cajas caja = new Cajas();
    caja.setIdcaja(idcaja);
    
    DetallesCajas detallecaja = new DetallesCajas();
    detallecaja.setIddetallecaja(iddetallecaja);
    FacturaVentas venta = new FacturaVentas();
    venta.setIdfactura_venta(idfactura_venta);



    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>