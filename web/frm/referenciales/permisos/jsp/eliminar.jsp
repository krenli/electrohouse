<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.PermisosControlador"%>
<%@page import="electrohouse.modelos.Permisos"%>
<%@page import="java.sql.ResultSet"%>
<%
     int idpermiso = Integer.parseInt(request.getParameter("idpermiso"));
     
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Permisos permiso = new Permisos();
    permiso.setIdpermiso(idpermiso);
    
    if (PermisosControlador.eliminar(permiso)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>