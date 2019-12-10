<%@page import="electrohouse.Controladores.RolesControlador"%>
<%@page import="electrohouse.modelos.Roles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idrol = Integer.parseInt(request.getParameter("idrol"));
    
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Roles rol = new Roles();
    rol.setIdrol(idrol);
    
    if (RolesControlador.eliminar(rol)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>