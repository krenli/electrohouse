<%@page import="electrohouse.utiles.Conexion"%>
<%@page import="electrohouse.modelos.Usuarios"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String mensaje = "Menu No Generado.";
    String menu_principal = "";
    HttpSession sesion = request.getSession();
 
    Usuarios usuarioLogueado = (Usuarios) sesion.getAttribute("usuarioLogueado");

    if (Conexion.conectar()) {
        String sql = "select * from permisos p "
                + "left join roles r on p.idrol=r.idrol "
                + "left join formularios f on p.idformulario=f.idformulario "
                + "left join menus m on f.idmenu=m.idmenu "
                + "where p.idrol = " + usuarioLogueado.getRol().getIdrol() + " "
                + "order by m.idmenu";
        System.out.println("---> " + sql);
        ResultSet rs = Conexion.getSt().executeQuery(sql);
        int gidmenu = 0;
        while (rs.next()) {
            if (gidmenu != rs.getInt("idmenu")) {
                if (gidmenu != 0) {
                    menu_principal += "</ul>";
                    menu_principal += "</li>";
                }

                menu_principal += "<li class='dropdown'>";
                menu_principal += rs.getString("codigo_menu");
                menu_principal += "<ul class='dropdown-menu' role='menu'>";

                gidmenu = rs.getInt("idmenu");

            }
            menu_principal += rs.getString("codigo_formulario");

        }
        if (gidmenu != 0) {
            menu_principal += "</ul>";
            menu_principal += "</li>";
        }
        Conexion.cerrar();
    }
    System.out.println("---> " + menu_principal);
    mensaje = "Menu Generado.";
    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("menu_principal", menu_principal);

    out.print(obj);
    out.flush();
%>