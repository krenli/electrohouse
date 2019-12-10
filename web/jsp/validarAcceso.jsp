<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.UsuariosControlador"%>
<%@page import="electrohouse.modelos.Usuarios"%>
<%
    String login_usuario = request.getParameter("login_usuario");
    String clave_usuario = request.getParameter("clave_usuario");
    String acceso = "false";
    //Usuarios usuario = new Usuarios(0, "", password_usuario, login_usuario);
    Usuarios usuario = new Usuarios();
    usuario.setLogin_usuario(login_usuario);
    usuario.setClave_usuario(clave_usuario);
    if (UsuariosControlador.validarAcceso(usuario, request) != null) {
        acceso = "true";
    }
    JSONObject obj = new JSONObject();
    obj.put("acceso", acceso);
    out.print(obj);
    out.flush();
%>
