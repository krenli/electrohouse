<%@page import="electrohouse.Controladores.CiudadesControlador"%>
<%@page import="electrohouse.modelos.Ciudades"%>
<%@page import="org.json.simple.JSONObject"%>

<%   
int idciudad =  Integer.parseInt (request.getParameter("idciudad"));



String tipo="error";
String mensaje = "Datos no encontrados";
String nuevo = "true";
Ciudades ciudad = new Ciudades();
ciudad.setIdciudad(idciudad);

 CiudadesControlador.buscarId(ciudad);

if(ciudad.getIdciudad()!=0){
    tipo = "success";
    mensaje = "Datos encontrados";
    nuevo = "false";
 
}

JSONObject obj = new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje", mensaje);
obj.put("nuevo", nuevo);
obj.put("idciudad", ciudad.getIdciudad());
obj.put("nombre_ciudad", ciudad.getNombre_ciudad());


out.print(obj);
out.flush();
%>
