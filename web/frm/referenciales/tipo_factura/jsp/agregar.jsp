<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.Tipo_facturasControlador"%>
<%@page import="electrohouse.modelos.Tipo_facturas"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idtipo_factura = Integer.parseInt(request.getParameter("idtipo_factura"));
    String nombre_tipo_factura = request.getParameter("nombre_tipo_factura");
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Tipo_facturas tipo_factura = new Tipo_facturas();
    tipo_factura.setIdtipo_factura(idtipo_factura);
    tipo_factura.setNombre_tipo_factura(nombre_tipo_factura);
    
    if (Tipo_facturasControlador.agregar(tipo_factura)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>

