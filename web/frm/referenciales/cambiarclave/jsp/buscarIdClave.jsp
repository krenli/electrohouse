
<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.modelos.Usuarios"%>
<%
    HttpSession sesion = request.getSession();

    int idusuario = 0;
    String login_usuario = "";
    String clave_usuario = "";
    String nombre_usuario = "";
    int idrol = 0;
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    //String nuevo = "true";

    Usuarios usuarioLogueado = (Usuarios) sesion.getAttribute("usuarioLogueado");
    if (usuarioLogueado != null) {

        idusuario = usuarioLogueado.getIdusuario();
        login_usuario = usuarioLogueado.getLogin_usuario();
        clave_usuario = usuarioLogueado.getClave_usuario();
        nombre_usuario = usuarioLogueado.getNombre_usuario();
        idrol = usuarioLogueado.getRol().getIdrol();

        tipo = "success";
        mensaje = "Datos encontrados";
//        nuevo = "false";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    //  obj.put("nuevo", nuevo);
    obj.put("idusuario", idusuario);
    obj.put("login_usuario", login_usuario);
    obj.put("clave_usuario", clave_usuario);
    obj.put("nombre_usuario", nombre_usuario);
    out.print(obj);
    out.flush();
%>