<%@page import="electrohouse.Controladores.DetallesCajasControlador"%>
<%@page import="electrohouse.Controladores.CajasControlador"%>
<%@page import="electrohouse.modelos.Cajas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
   
    int idusuario = Integer.parseInt(request.getParameter("sid_usuario"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    
    
   Cajas caja = CajasControlador.buscarIdestado(idusuario);
    if (caja !=null){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }else{
        caja = new Cajas();
        caja.setIdcaja(0);
        java.sql.Date fechaapertura = new java.sql.Date(new java.util.Date().getTime());
        caja.setFecha_apertura(fechaapertura);
        caja.setMonto_inicial(0);
        caja.setEstado_caja("CERRADO");
        mensaje = "Debe abrir la caja.";
        
    }
    
int idcaja =caja.getIdcaja();

    String contenido_detalle = DetallesCajasControlador.buscarIdCaja(idcaja);
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("idcaja", caja.getIdcaja());
    obj.put("fecha_apertura", String.valueOf(caja.getFecha_apertura()));
    obj.put("monto_inicial", caja.getMonto_inicial());
    obj.put("estado_caja", caja.getEstado_caja());
    obj.put("contenido_detalle", contenido_detalle);
    out.print(obj);
    out.flush();
%>
