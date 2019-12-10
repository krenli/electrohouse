
<%@page import="electrohouse.Controladores.FacturaVentasControlador"%>
<%@page import="electrohouse.Controladores.DetallesCuentasClientesControlador"%>
<%@page import="electrohouse.modelos.CuentasClientesDetalle"%>
<%@page import="electrohouse.Controladores.CuentasClientesControlador"%>
<%@page import="electrohouse.modelos.CuentasClientes"%>
<%@page import="electrohouse.modelos.FacturaVentas"%>
<%--<%@page import="modelos.Pedidos"%>--%>
<%--<%@page import="modelos.DetallesPedidos"%>--%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    //int id_detallepedido = Integer.parseInt(request.getParameter("id_detallepedido"));
    int idcuenta = 0;
    //int monto_total = Integer.parseInt(request.getParameter("id_factura_venta"));
    int idfactura_venta = Integer.parseInt(request.getParameter("idfactura_venta"));

    //int id_articulo = Integer.parseInt(request.getParameter("id_articulo"));
    //int cantidad_venta = Integer.parseInt(request.getParameter("cantidad_venta"));
    //int subtotal_venta = Integer.parseInt(request.getParameter("subtotal_venta"));
    /*int subtotal_5 = Integer.parseInt(request.getParameter("ssubtotal_5"));
    int subtotal_10 = Integer.parseInt(request.getParameter("ssubtotal_10"));
    int subtotal_exenta = Integer.parseInt(request.getParameter("ssubtotal_exenta"));*/
    String tipo = "error";
    String mensaje = "Cuenta no agregada.";

    //FacturaDetalle facturadetalle = new FacturaDetalle();
    //detallepedido.setId_detallepedido(id_detallepedido);
    //facturadetalle.setCantidad_venta(cantidad_venta);
    //facturadetalle.setSubtotal_venta(subtotal_venta);
    //FacturaVentas facturaventa = new FacturaVentas();
    //facturaventa.setId_factura_venta(id_factura_venta);
    /*facturaventa.setSubtotal_10(subtotal_10);
    facturaventa.setSubtotal_5(subtotal_5);
    facturaventa.setSubtotal_exenta(subtotal_exenta);*/
    //Articulos articulo = new Articulos();
    //articulo.setId_articulo(id_articulo);

    /*facturadetalle.setFacturaventa(facturaventa);
    facturadetalle.setArticulo(articulo);*/
    //Stock stock = new Stock();
    //stock.setCantidad_stock(cantidad_venta);
    // stock.setArticulo(articulo);
    //StockControlador.descontar(stock);
    FacturaVentas facturaventa = FacturaVentasControlador.buscarTotalfactura(idfactura_venta);

    facturaventa = new FacturaVentas();
    facturaventa.setIdfactura_venta(idfactura_venta);

    //int monto_total = facturaventa.getTotal();
    //System.out.println("totalcuenta "+monto_total);
    int total_cuota = Integer.parseInt(request.getParameter("cantidad_cuotas"));
    //int monto_cuota = monto_total / total_cuota;
    String estado_cuenta = "ACTIVO";

    CuentasClientes cuentasclientes = new CuentasClientes();
    cuentasclientes.setIdcuenta(idcuenta);
    cuentasclientes.setFacturaventa(facturaventa);
    //cuentasclientes.setMonto_total(monto_total);
    //cuentasclientes.setNro_cuota(nro_cuota);
    //cuentasclientes.setMonto_cuota(monto_cuota);
    cuentasclientes.setEstado_cuenta(estado_cuenta);
    cuentasclientes.setTotal_cuota(total_cuota);
    //if (facturaventa == null) {
   
    int n = 0;
    
    
    //nuevo = "false";    
    //} else {
    if (CuentasClientesControlador.agregarcuenta(cuentasclientes)) {
        tipo = "success";
        n = 1;
    }
    
    CuentasClientes cuentas = new CuentasClientes();
        int cuentanro = cuentasclientes.getIdcuenta();
        cuentas.setIdcuenta(cuentanro);
        System.out.println("cuenta N° "+ cuentanro);
        CuentasClientesDetalle cuentasclientesdetalle = new CuentasClientesDetalle();
        cuentasclientesdetalle.setCuentasclientes(cuentas);
        if (DetallesCuentasClientesControlador.agregarcuentadetalle(cuentasclientesdetalle)) {
            tipo = "success";
            mensaje = "Cuenta agregada.";
            n = 1;
        }
    //}
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("n", n);
    obj.put("mensaje", mensaje);
    obj.put("idcuenta", String.valueOf(cuentasclientes.getIdcuenta()));
    //System.out.println("cuenta N° "+ String.valueOf(cuentasclientes.getId_cuenta()));
    //obj.put("id_factura_venta", facturaventa.getId_factura_venta());
    obj.put("total", facturaventa.getTotal());
    out.print(obj);
    out.flush();

%>