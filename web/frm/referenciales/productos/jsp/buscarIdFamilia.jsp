<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.FamiliasControlador"%>
<%@page import="electrohouse.modelos.Familias"%>
<%   
int idfamilia =  Integer.parseInt (request.getParameter("idfamilia"));
String nombre_familia = request.getParameter("nombre_familia");


String tipo="error";
String mensaje = "Datos no encontrados";
String nuevo = "true";
Familias familia = new Familias();
familia.setIdfamilia(idfamilia);
familia.setNombre_familia(nombre_familia);
FamiliasControlador.buscarId(familia);

if(familia.getIdfamilia()!=0){
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
obj.put("idfamilia", familia.getIdfamilia());
obj.put("nombre_familia", familia.getNombre_familia());

out.print(obj);
out.flush();
%>
