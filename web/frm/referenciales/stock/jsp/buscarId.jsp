<%@page import="electrohouse.Controladores.PermisosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.modelos.Formularios"%>
<%@page import="electrohouse.modelos.Roles"%>
<%@page import="electrohouse.modelos.Permisos"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idpermiso = Integer.parseInt(request.getParameter("idpermiso"));
    
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    
    Permisos permiso = PermisosControlador.buscarId(idpermiso);
    if(permiso!=null){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }else{
        Roles rol = new Roles();
        Formularios formulario = new Formularios();
        permiso = new Permisos();
        permiso.setRol(rol);
        permiso.setFormulario(formulario);
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("idpermiso", permiso.getIdpermiso());
    obj.put("idrol", permiso.getRol().getIdrol());
    obj.put("nombre_rol", permiso.getRol().getNombre_rol());
    obj.put("idformulario", permiso.getFormulario().getIdformulario());
    obj.put("nombre_formulario", permiso.getFormulario().getNombre_formulario());
    
    out.print(obj);
    out.flush();
%>