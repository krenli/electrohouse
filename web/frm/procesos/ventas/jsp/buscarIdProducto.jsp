
<%@page import="electrohouse.Controladores.ProductosControlador"%>
<%@page import="electrohouse.modelos.Productos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idproducto = Integer.parseInt(request.getParameter("idproducto"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Productos producto = new Productos();
    producto.setIdproducto(idproducto);
    
   ProductosControlador.buscarId(producto);
    if (producto.getIdproducto()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("idproducto", producto.getIdproducto());
    obj.put("nombre_producto", producto.getNombre_producto());
    //obj.put("descripcion_producto", producto.getDescripcion_producto());
    //obj.put("precio_unitario", producto.getPrecio_unitario());
    obj.put("costo_producto", producto.getCosto_producto());
    obj.put("precio_producto", producto.getPrecio_producto());
    obj.put("iva_producto", producto.getIva_producto());
    obj.put("cod_barras_producto", producto.getCod_barras_producto());
    obj.put("idmarca", producto.getMarca().getIdmarca());
    obj.put("nombre_marca", producto.getMarca().getNombre_marca());
    obj.put("idfamilia", producto.getFamilia().getIdfamilia());
    obj.put("nombre_familia", producto.getFamilia().getNombre_familia());
    out.print(obj);
    out.flush();
%>
