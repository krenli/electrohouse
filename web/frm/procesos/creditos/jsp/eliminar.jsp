<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.CreditosControlador"%>
<%@page import="electrohouse.modelos.Creditos"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idaprobacioncredito = Integer.parseInt(request.getParameter("idaprobacioncredito"));
    
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Creditos aprobacioncredito = new Creditos();
    aprobacioncredito.setIdaprobacioncredito(idaprobacioncredito);
    
    if (CreditosControlador.eliminar(aprobacioncredito)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>