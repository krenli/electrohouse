<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.PermisosControlador"%>
<%@page import="electrohouse.modelos.Permisos"%>
<%@page import="electrohouse.modelos.Formularios"%>
<%@page import="electrohouse.modelos.Roles"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idpermiso = Integer.parseInt(request.getParameter("idpermiso"));
    int idrol = Integer.parseInt(request.getParameter("idrol"));
    int idformulario = Integer.parseInt(request.getParameter("idformulario"));

    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Roles rol = new Roles();
    rol.setIdrol(idrol);
    
    Formularios formulario = new Formularios();
    formulario.setIdformulario(idformulario);
   
    
    Permisos permiso = new Permisos();
    permiso.setIdpermiso(idpermiso);
    permiso.setRol(rol);
    permiso.setFormulario(formulario);
    
    if (PermisosControlador.agregar(permiso)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>