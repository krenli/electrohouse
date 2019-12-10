<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.TimbradosControlador"%>
<%@page import="electrohouse.modelos.Timbrados"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idtimbrado = Integer.parseInt(request.getParameter("idtimbrado"));
    
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Timbrados timbrado = new Timbrados();
    timbrado.setIdtimbrado(idtimbrado);
    
    if (TimbradosControlador.eliminar(timbrado)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>