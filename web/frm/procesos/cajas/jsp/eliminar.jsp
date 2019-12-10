
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idcaja = Integer.parseInt(request.getParameter("idcaja"));
    
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Cajas caja = new Cajas();
    caja.setIdcaja(idcaja);
    
    if (CajasControlador.eliminar(caja)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>