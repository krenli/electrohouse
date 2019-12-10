<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.CiudadesControlador"%>
<%@page import="electrohouse.modelos.Ciudades"%>
<%   
int idciudad =  Integer.parseInt (request.getParameter("idciudad"));
String nombre_ciudad = request.getParameter("nombre_ciudad");


String tipo="error";
String mensaje = "Datos no encontrados";
String nuevo = "true";
Ciudades ciudad = new Ciudades();
ciudad.setIdciudad(idciudad);
ciudad.setNombre_ciudad(nombre_ciudad);
CiudadesControlador.buscarId(ciudad);

if(ciudad.getIdciudad()!=0){
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
obj.put("idciudad", ciudad.getIdciudad());
obj.put("nombre_ciudad", ciudad.getNombre_ciudad());

out.print(obj);
out.flush();
%>
