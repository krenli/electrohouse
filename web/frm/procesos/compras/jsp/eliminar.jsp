<%@page import="electrohouse.Controladores.FacturaComprasControlador"%>
<%@page import="electrohouse.modelos.FacturaCompras"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idfactura_compra = Integer.parseInt(request.getParameter("idfactura_compra"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    FacturaCompras facturacompra = new FacturaCompras();
    facturacompra.setIdfactura_compra(idfactura_compra);

    if (FacturaComprasControlador.eliminar(facturacompra)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>