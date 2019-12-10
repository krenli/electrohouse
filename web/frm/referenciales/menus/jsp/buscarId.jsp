<%@page import="electrohouse.Controladores.MenusControlador"%>
<%@page import="electrohouse.modelos.Menus"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%   
int idmenu =  Integer.parseInt (request.getParameter("idmenu"));



String tipo="error";
String mensaje = "Datos no encontrados";
String nuevo = "true";
Menus menu = new Menus();
menu.setIdmenu(idmenu);

 MenusControlador.buscarId(menu);

if(menu.getIdmenu()!=0){
    tipo = "success";
    mensaje = "Datos encontrados";
    nuevo = "false";
 
}

JSONObject obj = new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje", mensaje);
obj.put("nuevo", nuevo);
obj.put("idmenu", menu.getIdmenu());
obj.put("nombre_menu", menu.getNombre_menu());
obj.put("codigo_menu", menu.getCodigo_menu());

out.print(obj);
out.flush();
%>