<%@page import="electrohouse.Controladores.FacturaComprasControlador"%>
<%@page import="electrohouse.modelos.FacturaCompras"%>
<%@page import="electrohouse.modelos.Tipo_facturas"%>
<%@page import="electrohouse.modelos.Proveedores"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idfactura_compra = Integer.parseInt(request.getParameter("idfactura_compra"));
    int idproveedor = Integer.parseInt(request.getParameter("idproveedor"));
    int idtipo_factura = Integer.parseInt(request.getParameter("idtipo_factura"));

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    Proveedores proveedor = new Proveedores();
    proveedor.setIdproveedor(idproveedor);
    
    Tipo_facturas tipofactura = new Tipo_facturas();
    tipofactura.setIdtipo_factura(idtipo_factura);

    FacturaCompras facturacompra = new FacturaCompras();
    facturacompra.setIdfactura_compra(idfactura_compra);
    facturacompra.setProveedor(proveedor);
    facturacompra.setTipofactura(tipofactura);
   
    if (FacturaComprasControlador.modificar(facturacompra)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>