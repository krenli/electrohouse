
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int iddetallecuenta = Integer.parseInt(request.getParameter("iddetallecuenta"));
    int importe = Integer.parseInt(request.getParameter("importe"));
    int idcuenta = Integer.parseInt(request.getParameter("idcuenta"));
    int idventa = Integer.parseInt(request.getParameter("idventa"));
  //  int costo_cuenta = Integer.parseInt(request.getParameter("costo_cuenta"));    
  //  int total_detallecuenta = cantidad_cuentaventa * costo_cuenta;
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    DetallesCuentas detallecuenta = new DetallesCuentas();
    detallecuenta.setIddetallecuenta(iddetallecuenta);
    detallecuenta.setImporte(importe);
   
    Ventas venta = new Ventas();
    venta.setIdventa(idventa);
    
    Cuentas cuenta = new Cuentas();
    cuenta.setIdcuenta(idcuenta);
    
    detallecuenta.setVenta(venta);
    detallecuenta.setCuenta(cuenta);
    
    if (DetallesCuentasControlador.modificar(detallecuenta)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>