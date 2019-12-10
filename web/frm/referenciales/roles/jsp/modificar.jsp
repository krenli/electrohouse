<%@page import="electrohouse.Controladores.RolesControlador"%>
<%@page import="electrohouse.modelos.Roles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idrol = Integer.parseInt(request.getParameter("idrol"));
    String nombre_rol = request.getParameter("nombre_rol");
    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Roles rol = new Roles();
    rol.setIdrol(idrol);
    rol.setNombre_rol(nombre_rol);
    
    if (RolesControlador.modificar(rol)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
