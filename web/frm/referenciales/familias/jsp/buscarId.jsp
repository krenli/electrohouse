<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.FamiliasControlador"%>
<%@page import="electrohouse.modelos.Familias"%>
<%   
int idfamilia =  Integer.parseInt (request.getParameter("idfamilia"));



String tipo="error";
String mensaje = "Datos no encontrados";
String nuevo = "true";
Familias familia = new Familias();
familia.setIdfamilia(idfamilia);

 FamiliasControlador.buscarId(familia);

if(familia.getIdfamilia()!=0){
    tipo = "success";
    mensaje = "Datos encontrados";
    nuevo = "false";
 
}

JSONObject obj = new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje", mensaje);
obj.put("nuevo", nuevo);
obj.put("idfamilia", familia.getIdfamilia());
obj.put("nombre_familia", familia.getNombre_familia());
obj.put("idcategoria", familia.getCategoria().getIdcategoria());
obj.put("nombre_categoria", familia.getCategoria().getNombre_categoria());

out.print(obj);
out.flush();
%>
