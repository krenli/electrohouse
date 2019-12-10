
<%@page import="electrohouse.Controladores.CajasControlador"%>
<%@page import="electrohouse.modelos.Cajas"%>
<%@page import="electrohouse.modelos.Usuarios"%>
<%@page import="electrohouse.utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idcaja = Integer.parseInt(request.getParameter("idcaja"));
    String sfecha_apertura = request.getParameter("fecha_apertura");
    java.sql.Date fecha_apertura = Utiles.stringToSqlDate(sfecha_apertura);
    int monto_inicial = Integer.parseInt(request.getParameter("monto_inicial"));
    String estado_caja = "ABIERTO";
    int idusuario = Integer.parseInt(request.getParameter("sid_usuario"));
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    Usuarios usuario = new Usuarios();
    usuario.setIdusuario(idusuario);
    
    Cajas caja = new Cajas();
    caja.setIdcaja(idcaja);
    caja.setFecha_apertura(fecha_apertura);
    caja.setMonto_inicial(monto_inicial);
    caja.setEstado_caja(estado_caja);
    caja.setUsuario(usuario);
    
    if (CajasControlador.agregar(caja)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("idcaja", caja.getIdcaja());
    out.print(obj);
    out.flush();
%>

