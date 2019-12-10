<%@page import="electrohouse.modelos.Depositos"%>
<%@page import="electrohouse.modelos.Proveedores"%>
<%@page import="electrohouse.modelos.Marcas"%>
<%@page import="electrohouse.modelos.Familias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.ProductosControlador"%>
<%@page import="electrohouse.modelos.Categorias"%>
<%@page import="electrohouse.modelos.Productos"%>
<%
    
int idproducto =  Integer.parseInt (request.getParameter("idproducto"));
String nombre_producto = request.getParameter("nombre_producto");
String cod_barras_producto = request.getParameter("cod_barras_producto");
int costo_producto = Integer.parseInt (request.getParameter("costo_producto"));
int precio_producto = Integer.parseInt (request.getParameter("precio_producto"));
int minimo_producto = Integer.parseInt (request.getParameter("minimo_producto"));
int iva_producto = Integer.parseInt (request.getParameter("iva_producto"));
int idfamilia =  Integer.parseInt (request.getParameter("idfamilia"));
int idmarca =  Integer.parseInt (request.getParameter("idmarca"));

String tipo="error";
String mensaje = "Datos no agregados";

Productos producto = new Productos();
producto.setIdproducto(idproducto);
producto.setNombre_producto(nombre_producto);
producto.setCod_barras_producto(cod_barras_producto);
producto.setCosto_producto(costo_producto);
producto.setPrecio_producto(precio_producto);
producto.setMinimo_producto(minimo_producto);
producto.setIva_producto(iva_producto);

Familias familia = new Familias();
familia .setIdfamilia (idfamilia );
producto.setFamilia (familia );

Marcas marca = new Marcas();
marca.setIdmarca(idmarca);
producto.setMarca(marca);

if(ProductosControlador.modificar(producto)){
tipo = "success";
mensaje = "Datos modificados";
}

JSONObject obj = new JSONObject();
obj.put("tipo",tipo);
obj.put("mensaje", mensaje);
System.out.println("tipo" +tipo);
out.print(obj);
out.flush();
%>