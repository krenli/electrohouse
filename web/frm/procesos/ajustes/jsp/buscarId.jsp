<%@page import="electrohouse.Controladores.DetallesAjustesControlador"%>
<%@page import="electrohouse.Controladores.AjustesControlador"%>
<%@page import="electrohouse.modelos.Ajustes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idajuste_stock = Integer.parseInt(request.getParameter("idajuste_stock"));
//int id_ajuste_stock = 0;
    /*if (request.getParameter("id_ajuste_stock") != "") {
        id_ajuste_stock = Integer.parseInt(request.getParameter("id_ajuste_stock")); 
    }*/
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    Ajustes ajustes = AjustesControlador.buscarId(idajuste_stock);
    if (ajustes !=null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {

        ajustes = new Ajustes();

        ajustes.setIdajuste_stock(0);

        java.sql.Date fecha_ajuste_stock = new java.sql.Date(new java.util.Date().getTime());
        ajustes.setFecha_ajuste_stock(fecha_ajuste_stock);
        ajustes.setMotivo_ajuste_stock("");
    }

    String contenido_detalle = DetallesAjustesControlador.buscarIdAjuste(idajuste_stock);
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("idajuste_stock", ajustes.getIdajuste_stock());
    obj.put("fecha_ajuste_stock", String.valueOf(ajustes.getFecha_ajuste_stock()));
    obj.put("motivo_ajuste_stock", ajustes.getMotivo_ajuste_stock());
    obj.put("contenido_detalle", contenido_detalle);
    out.print(obj);
    out.flush();
%>