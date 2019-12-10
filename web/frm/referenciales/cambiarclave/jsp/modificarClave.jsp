<%@page import="electrohouse.utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.UsuariosControlador"%>
<%@page import="electrohouse.modelos.Roles"%>
<%@page import="electrohouse.modelos.Usuarios"%>
<%
    HttpSession sesion = request.getSession();

    int idusuario = 0;

    String login_usuario = "";
    String clave_usuario = "";
    String nombre_usuario = "";
    String claveactual_usuario = Utiles.md5(request.getParameter("claveactual_usuario"));
    String clavenueva_usuario = request.getParameter("clavenueva_usuario");
    int idrol = 0;

    String tipo = "error";
    String mensaje = "Datos no modificados";

    Usuarios usuarioLogueado = (Usuarios) sesion.getAttribute("usuarioLogueado");
    if (usuarioLogueado != null) {

        idusuario = usuarioLogueado.getIdusuario();
        login_usuario = usuarioLogueado.getLogin_usuario();
        clave_usuario = usuarioLogueado.getClave_usuario();
        nombre_usuario = usuarioLogueado.getNombre_usuario();
        idrol = usuarioLogueado.getRol().getIdrol();

        System.out.println("usuarioLogueado - clave (jsp-actual): " + clave_usuario);
        System.out.println("del formulario - clave actual: " + claveactual_usuario);

        if (claveactual_usuario.equals(clave_usuario)) {
            Usuarios usuario = new Usuarios();
            usuario.setIdusuario(idusuario);
            usuario.setLogin_usuario(login_usuario);
            usuario.setClave_usuario(clavenueva_usuario);
            usuario.setNombre_usuario(nombre_usuario);

            Roles rol = new Roles();
            rol.setIdrol(idrol);

            usuario.setRol(rol);

            if (UsuariosControlador.modificar(usuario)) {
                tipo = "success";
                mensaje = "Datos modificados correctamente";
                System.out.println("Cambiado!!");
            }

        } else {
            tipo = "error";
            mensaje = "Clave actual incorrecta";
        }

    }

    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("tipo", tipo);

    obj.put("idusuario", idusuario);
    obj.put("login_usuario", login_usuario);
    obj.put("clave_usuario", clave_usuario);
    obj.put("nombre_usuario", nombre_usuario);

    out.print(obj);
    out.flush();
%>