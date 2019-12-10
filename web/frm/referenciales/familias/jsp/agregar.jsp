<%@page import="electrohouse.modelos.Familias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.FamiliasControlador"%>
<%@page import="electrohouse.modelos.Categorias"%>
<%
   
int idfamilia =  Integer.parseInt (request.getParameter("idfamilia"));
String nombre_familia = request.getParameter("nombre_familia");
int idcategoria =  Integer.parseInt (request.getParameter("idcategoria"));


    String tipo="error";
    String mensaje = "Dato ya existente";
     
Familias familia = new Familias();
familia.setIdfamilia(idfamilia);
familia.setNombre_familia(nombre_familia);



Categorias categoria = new Categorias();
categoria.setIdcategoria(idcategoria);
familia.setCategoria(categoria);



if(FamiliasControlador.agregar(familia)){
tipo = "success";
mensaje = "Datos agregados";
}

JSONObject obj = new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje", mensaje);
System.out.println("tipo" +tipo);
out.print(obj);
out.flush();
%>