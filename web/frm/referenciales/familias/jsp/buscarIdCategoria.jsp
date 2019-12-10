<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.CategoriasControlador"%>
<%@page import="electrohouse.modelos.Categorias"%>
<%   
int idcategoria =  Integer.parseInt (request.getParameter("idcategoria"));
String nombre_categoria = request.getParameter("nombre_categoria");


String tipo="error";
String mensaje = "Datos no encontrados";
String nuevo = "true";
Categorias categoria = new Categorias();
categoria.setIdcategoria(idcategoria);
categoria.setNombre_categoria(nombre_categoria);
CategoriasControlador.buscarId(categoria);

if(categoria.getIdcategoria()!=0){
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
obj.put("idcategoria", categoria.getIdcategoria());
obj.put("nombre_categoria", categoria.getNombre_categoria());

out.print(obj);
out.flush();
%>
