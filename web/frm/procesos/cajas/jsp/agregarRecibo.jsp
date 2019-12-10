<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.RecibosControlador"%>
<%@page import="electrohouse.modelos.Recibos"%>
<%@page import="electrohouse.modelos.CuentasClientes"%>
<%@page import="electrohouse.utiles.Utiles"%>
<%
    int idrecibo = 0;
    
    int idcuenta = Integer.parseInt(request.getParameter("idcuenta"));
    int nro_cuota = Integer.parseInt(request.getParameter("nro_cuota"));
    String stotal = request.getParameter("total");
    String montoaconvertir = String.valueOf( stotal);
    String montoenletras = Utiles.cantidadConLetra(montoaconvertir);
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
   CuentasClientes cuentas = new CuentasClientes();
    cuentas.setIdcuenta(idcuenta);
    cuentas.setNro_cuota(nro_cuota);
    
    Recibos recibo = new Recibos();
    recibo.setIdrecibo(idrecibo);
    recibo.setCuentas(cuentas);
    recibo.setMontoenletras(montoenletras);
    
    
    if (RecibosControlador.agregar(recibo)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    //obj.put("id", recibo.getId_recibo());
    out.print(obj);
    out.flush();
%>

