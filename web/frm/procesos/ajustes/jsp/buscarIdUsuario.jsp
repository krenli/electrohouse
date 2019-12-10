<%@page import="electrohouse.Controladores.UsuariosControlador"%>
<%@page import="electrohouse.modelos.Usuarios"%>
<%@page import="org.json.simple.JSONObject"%>

<%
    int idusuario = 0;
    if (request.getParameter("idusuario") != "") {
        idusuario = Integer.parseInt(request.getParameter("idusuario"));
    }
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Usuarios usuario = new Usuarios();
    usuario.setIdusuario(idusuario);

    usuario = UsuariosControlador.buscarId(usuario);
    if (idusuario!= 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("idusuario", usuario.getIdusuario());
    obj.put("nombre_usuario", usuario.getNombre_usuario());
    obj.put("clave_usuario", usuario.getClave_usuario());
    obj.put("login_usuario", usuario.getLogin_usuario());
    obj.put("idrol",usuario.getRol().getIdrol());
    obj.put("nombre_rol",usuario.getRol().getNombre_rol());
    out.print(obj);
    out.flush();
%>