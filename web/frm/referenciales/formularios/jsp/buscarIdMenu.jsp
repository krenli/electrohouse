<%@page import="electrohouse.Controladores.MenusControlador"%>
<%@page import="electrohouse.modelos.Menus"%>
<%@page import="org.json.simple.JSONObject"%>
<%   
int idmenu =  Integer.parseInt (request.getParameter("idmenu"));
String nombre_menu = request.getParameter("nombre_menu");


String tipo="error";
String mensaje = "Datos no encontrados";
String nuevo = "true";
Menus menu = new Menus();
menu.setIdmenu(idmenu);
menu.setNombre_menu(nombre_menu);
MenusControlador.buscarId(menu);

if(menu.getIdmenu()!=0){
    tipo = "success";
    mensaje = "Datos encontrados";
    nuevo = "false";
 
}

JSONObject obj = new JSONObject();
obj.put("tipo", tipo);
System.out.println("tipo " + tipo);
obj.put("mensaje", mensaje);
System.out.println("mensaje " + mensaje);
obj.put("nuevo", nuevo);
obj.put("idmenu", menu.getIdmenu());
obj.put("nombre_menu", menu.getNombre_menu());

out.print(obj);
out.flush();
%>
