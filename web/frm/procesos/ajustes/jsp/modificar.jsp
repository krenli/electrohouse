<%@page import="electrohouse.Controladores.AjustesControlador"%>
<%@page import="electrohouse.utiles.Utiles"%>
<%@page import="electrohouse.modelos.Ajustes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idajuste_stock = Integer.parseInt(request.getParameter("idajuste_stock"));
    String motivo_ajuste_stock = request.getParameter("motivo_ajuste_stock");
    //int id_proveedor = Integer.parseInt(request.getParameter("id_proveedor"));

     //String numero_factura = request.getParameter("numero_factura");
    // String timbrado_ajuste = request.getParameter("timbrado_ajuste");
   
     String sFecha_ajuste_stock = request.getParameter("fecha_ajuste_stock");
    java.sql.Date Fecha_ajuste_stock = Utiles.stringToSqlDate(sFecha_ajuste_stock);
    
    String tipo = "error";
    String mensaje = "Datos no modificados.";

    /*Proveedores proveedor = new Proveedores();
    proveedor.setId_proveedor(id_proveedor);*/

    Ajustes ajuste = new Ajustes();
    ajuste.setIdajuste_stock(idajuste_stock);
    //ajuste.setNumero_factura(numero_factura);
    //ajuste.setTimbrado_ajuste(timbrado_ajuste);
    ajuste.setFecha_ajuste_stock(Fecha_ajuste_stock);
    ajuste.setMotivo_ajuste_stock(motivo_ajuste_stock);
    //ajuste.setProveedor(proveedor);
   
    if (AjustesControlador.modificar(ajuste)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>