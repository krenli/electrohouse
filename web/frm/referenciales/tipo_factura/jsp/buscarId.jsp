<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.modelos.Tipo_facturas"%>
<%@page import="electrohouse.Controladores.Tipo_facturasControlador"%>
<%@page import="electrohouse.modelos.Tipo_facturas"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idtipo_factura = Integer.parseInt(request.getParameter("idtipo_factura"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Tipo_facturas tipo_factura = new Tipo_facturas();
    tipo_factura.setIdtipo_factura(idtipo_factura);
    
   Tipo_facturasControlador.buscarId(tipo_factura);
    if (tipo_factura.getIdtipo_factura()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }else {
        tipo_factura = new Tipo_facturas();
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("idtipo_factura", tipo_factura.getIdtipo_factura());
    obj.put("nombre_tipo_factura", tipo_factura.getNombre_tipo_factura());
    out.print(obj);
    out.flush();
%>
