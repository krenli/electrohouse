
<%@page import="electrohouse.modelos.Formas_pagos"%>
<%@page import="electrohouse.modelos.FacturaVentas"%>
<%@page import="electrohouse.modelos.Cajas"%>
<%@page import="electrohouse.Controladores.DetallesCajasControlador"%>
<%@page import="electrohouse.modelos.DetallesCajas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int iddetallecaja = Integer.parseInt(request.getParameter("iddetallecaja"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    DetallesCajas detallecaja = DetallesCajasControlador.buscarId(iddetallecaja);
    if (detallecaja != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detallecaja = new DetallesCajas();
        detallecaja.setIddetallecaja(0);
        detallecaja.setImporte(0);

        Cajas caja = new Cajas();
        caja.setIdcaja(0);
        detallecaja.setCaja(caja);

        FacturaVentas facturaventa = new FacturaVentas();
        facturaventa.setIdfactura_venta(0);
        detallecaja.setFacturaventa(facturaventa);

        Formas_pagos pago = new Formas_pagos();
        pago.setIdforma_pago(0);
        pago.setNombre_forma_pago("");
        detallecaja.setFacturaventa(facturaventa);
    }
    //int id_factura_venta = detallecaja.getFacturaventa().getId_factura_venta();
    //System.out.println("idfactu "+ id_factura_venta);
    //FacturaVentas facturaventa = new FacturaVentas();
    //FacturaVentas facturaventa = FacturaVentasControlador.buscarTotalfactura(id_factura_venta);
    //int total1 = facturaventa.getTotal();
    //System.out.println("total1 "+ total1);
    //int importe = detallecaja.getImporte();
    //int vuelto1 = importe - total1;

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("iddetallecaja", String.valueOf(detallecaja.getIddetallecaja()));
    obj.put("idcaja", String.valueOf(detallecaja.getCaja().getIdcaja()));
    System.out.println("cajaid= "+String.valueOf(detallecaja.getCaja().getIdcaja()));
    obj.put("idfactura_venta", String.valueOf(detallecaja.getFacturaventa().getIdfactura_venta()));
    //obj.put("numero_factura_venta", String.valueOf(detallecaja.getFacturaventa().getNumero_factura_venta()));
    obj.put("idforma_pago", detallecaja.getPago().getIdforma_pago());
    //obj.put("nombre_forma_pago", detallecaja.getPago().getNombre_forma_pago());
    obj.put("importe", String.valueOf(detallecaja.getImporte()));
    //obj.put("vuelto", (vuelto1));
    //obj.put("total", facturaventa.getTotal());
    //System.out.println("total "+ facturaventa.getTotal());
    //System.out.println("vuelto "+ vuelto1);
    out.print(obj);
    out.flush();
%>
