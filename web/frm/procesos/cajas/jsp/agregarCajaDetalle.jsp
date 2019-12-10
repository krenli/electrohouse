
<%@page import="electrohouse.Controladores.DetallesCajasControlador"%>
<%@page import="electrohouse.Controladores.FacturaVentasControlador"%>
<%@page import="electrohouse.Controladores.CuentasClientesControlador"%>
<%@page import="electrohouse.Controladores.DetallesCuentasClientesControlador"%>
<%@page import="electrohouse.modelos.CuentasClientesDetalle"%>
<%@page import="electrohouse.modelos.CuentasClientes"%>
<%@page import="electrohouse.modelos.Formas_pagos"%>
<%@page import="electrohouse.modelos.Cajas"%>
<%@page import="electrohouse.modelos.FacturaVentas"%>
<%@page import="electrohouse.modelos.DetallesCajas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int iddetallecaja = Integer.parseInt(request.getParameter("iddetallecaja"));
    int importe = Integer.parseInt(request.getParameter("importe"));
    int idcaja = Integer.parseInt(request.getParameter("idcaja"));
   int idcuenta = Integer.parseInt(request.getParameter("idcuenta"));
   int nro_cuota = Integer.parseInt(request.getParameter("nro_cuota"));
    int idfactura_venta = Integer.parseInt(request.getParameter("idfactura_venta"));
    //int idfactura_venta1 = Integer.parseInt(request.getParameter("sidfactura_venta"));
    //int numero_factura_venta = Integer.parseInt(request.getParameter("numero_factura_venta"));
    int total = Integer.parseInt(request.getParameter("total"));
    //int total1 = Integer.parseInt(request.getParameter("stotal"));
    int idforma_pago = Integer.parseInt(request.getParameter("idforma_pago"));
    String nombre_forma_pago = request.getParameter("nombre_forma_pago");
    int totald = importe - total;
    String tipo = "error";

    String mensaje = "Datos no agregados.";

    DetallesCajas detallecaja = new DetallesCajas();
    detallecaja.setIddetallecaja(iddetallecaja);
    detallecaja.setVuelto(totald);
    detallecaja.setImporte(importe);
    //  detallecaja.setCantidad_cajaventa(cantidad_cajaventa);

    FacturaVentas facturaventa = new FacturaVentas();
    facturaventa.setIdfactura_venta(idfactura_venta);
    //facturaventa.setNumero_factura_venta(numero_factura_venta);
    Cajas caja = new Cajas();
    caja.setIdcaja(idcaja);

    Formas_pagos pago = new Formas_pagos();
    pago.setIdforma_pago(idforma_pago);
    pago.setNombre_forma_pago(nombre_forma_pago);
    detallecaja.setFacturaventa(facturaventa);
    detallecaja.setCaja(caja);
    detallecaja.setPago(pago);
    CuentasClientes cuentasclientes = new CuentasClientes();
    cuentasclientes.setIdcuenta(idcuenta);
    cuentasclientes.setNro_cuota(nro_cuota);
    CuentasClientesDetalle cuentasClientesDetalle = new CuentasClientesDetalle();
    cuentasClientesDetalle.setCuentasclientes(cuentasclientes);
    //int idcuenta = Integer.parseInt(request.getParameter("idcuenta"));
    if (idcuenta != 0 || String.valueOf(idcuenta) != "" && nro_cuota != 0 || String.valueOf(nro_cuota) != "") {
        //FacturaVentasControlador.modificarestadocobro(facturaventa);
        //}else{
        DetallesCuentasClientesControlador.modificar(cuentasClientesDetalle);
        CuentasClientesControlador.modificar(cuentasclientes);

    }
    //FacturaVentasControlador.modificarestadocobro(facturaventa);
    FacturaVentasControlador.modificarestadocobro(facturaventa);
    if (DetallesCajasControlador.agregar(detallecaja)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    facturaventa = new FacturaVentas();
    facturaventa.setIdfactura_venta(idfactura_venta);
    facturaventa.setNumero_factura_venta(0);
    pago = new Formas_pagos();
    pago.setIdforma_pago(idforma_pago);
    pago.setNombre_forma_pago(nombre_forma_pago);
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);

    out.print(obj);
    out.flush();

%>