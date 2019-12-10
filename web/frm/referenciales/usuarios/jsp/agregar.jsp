<%@page import="electrohouse.Controladores.UsuariosControlador"%>
<%@page import="electrohouse.modelos.Usuarios"%>
<%@page import="electrohouse.modelos.Roles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idusuario = Integer.parseInt(request.getParameter("idusuario"));
    int idrol = Integer.parseInt(request.getParameter("idrol"));
    String nombre_usuario = request.getParameter("nombre_usuario");
    String login_usuario = request.getParameter("login_usuario");
    String clave_usuario = request.getParameter("clave_usuario");
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Roles rol = new Roles();
    rol.setIdrol(idrol);
    
    Usuarios usuario = new Usuarios();
    usuario.setIdusuario(idusuario);
    usuario.setLogin_usuario(login_usuario);
    usuario.setNombre_usuario(nombre_usuario);
    usuario.setClave_usuario(clave_usuario);
    usuario.setRol(rol);
    
    if (UsuariosControlador.agregar(usuario)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>

