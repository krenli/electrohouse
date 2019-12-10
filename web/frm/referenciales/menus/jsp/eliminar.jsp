<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.MenusControlador"%>
<%@page import="electrohouse.modelos.Menus"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idmenu = Integer.parseInt(request.getParameter("idmenu"));

  
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Menus menu = new Menus();
    menu.setIdmenu(idmenu);
    
    if (MenusControlador.eliminar(menu)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>