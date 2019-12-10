<%@page import="electrohouse.Controladores.CreditosControlador"%>
<%@page import="electrohouse.modelos.Creditos"%>
<%@page import="electrohouse.utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idaprobacioncredito = Integer.parseInt(request.getParameter("idaprobacioncredito"));
    String sfecha_aprobacioncredito = request.getParameter("fecha_aprobacioncredito");
    java.sql.Date fecha_aprobacioncredito = Utiles.stringToSqlDate(sfecha_aprobacioncredito);
    String descripcion_aprobacioncredito = request.getParameter("descripcion_aprobacioncredito");
    String estado_aprobacioncredito = request.getParameter("estado_aprobacioncredito");
    String referencia_comercial = request.getParameter("referencia_comercial");
    String referencia_personal = request.getParameter("referencia_personal");
    String limite_credito = request.getParameter("limite_credito");
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Creditos aprobacioncredito = new Creditos();
    aprobacioncredito.setIdaprobacioncredito(idaprobacioncredito);
    aprobacioncredito.setFecha_aprobacioncredito(fecha_aprobacioncredito);
    aprobacioncredito.setDescripcion_aprobacioncredito(descripcion_aprobacioncredito);
    aprobacioncredito.setEstado_aprobacioncredito(estado_aprobacioncredito);
    aprobacioncredito.setReferencia_comercial(referencia_comercial);
    aprobacioncredito.setReferencia_personal(referencia_personal);
    aprobacioncredito.setLimite_credito(limite_credito);
    
    if (CreditosControlador.agregar(aprobacioncredito)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("idaprobacioncredito", String.valueOf(aprobacioncredito.getIdaprobacioncredito()));
    out.print(obj);
    out.flush();
%>

