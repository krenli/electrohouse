<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.MenusControlador"%>
<%@page import="electrohouse.modelos.Menus"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idmenu = Integer.parseInt(request.getParameter("idmenu"));
    String nombre_menu = request.getParameter("nombre_menu");
    String codigo_menu = request.getParameter("codigo_menu");

  
    String tipo = "error";
    String mensaje = "Dato ya existente";
    
    Menus menu = new Menus();
    menu.setIdmenu(idmenu);
    menu.setNombre_menu(nombre_menu);
    menu.setCodigo_menu(codigo_menu);
    
    if (MenusControlador.agregar(menu)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>