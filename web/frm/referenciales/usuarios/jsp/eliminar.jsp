<%@page import="electrohouse.Controladores.UsuariosControlador"%>
<%@page import="electrohouse.modelos.Usuarios"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idusuario = Integer.parseInt(request.getParameter("idusuario"));
    
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Usuarios usuario = new Usuarios();
    usuario.setIdusuario(idusuario);
    
    if (UsuariosControlador.eliminar(usuario)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>