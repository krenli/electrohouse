<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.MarcasControlador"%>
<%@page import="electrohouse.modelos.Marcas"%>
<%   
int idmarca =  Integer.parseInt (request.getParameter("idmarca"));
String nombre_marca = request.getParameter("nombre_marca");


String tipo="error";
String mensaje = "Datos no encontrados";
String nuevo = "true";
Marcas marca = new Marcas();
marca.setIdmarca(idmarca);
marca.setNombre_marca(nombre_marca);
MarcasControlador.buscarId(marca);

if(marca.getIdmarca()!=0){
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
obj.put("idmarca", marca.getIdmarca());
obj.put("nombre_marca", marca.getNombre_marca());

out.print(obj);
out.flush();
%>
