<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.TimbradosControlador"%>
<%@page import="electrohouse.modelos.Timbrados"%>
<%@page import="electrohouse.modelos.Tipos_personales"%>
<%@page import="electrohouse.utiles.Utiles"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idtimbrado = Integer.parseInt(request.getParameter("idtimbrado"));
    int desde_timbrado = Integer.parseInt(request.getParameter("desde_timbrado"));
    int hasta_timbrado = Integer.parseInt(request.getParameter("hasta_timbrado"));
    int idtipo_personal = Integer.parseInt(request.getParameter("idtipo_personal"));
    String numero_timbrado = request.getParameter("numero_timbrado");
    String estado_timbrado = request.getParameter("estado_timbrado");
    String sfecha_inicio_timbrado = request.getParameter("fecha_inicio_timbrado");
    String sfecha_vencimiento_timbrado = request.getParameter("fecha_vencimiento_timbrado");
    //String sfecha_actual_timbrado = request.getParameter("fecha_actual_timbrado");
    
    java.sql.Date fecha_inicio_timbrado = Utiles.stringToSqlDate(sfecha_inicio_timbrado);
    java.sql.Date fecha_vencimiento_timbrado = Utiles.stringToSqlDate(sfecha_vencimiento_timbrado);
    //java.sql.Date fecha_actual_timbrado = Utiles.stringToSqlDate(sfecha_actual_timbrado);
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Tipos_personales tipo_personal = new Tipos_personales();
    tipo_personal.setIdtipo_personal(idtipo_personal);
    

    
    Timbrados timbrado = new Timbrados();
    timbrado.setIdtimbrado(idtimbrado);
    timbrado.setNumero_timbrado(numero_timbrado);
    timbrado.setFecha_inicio_timbrado(fecha_inicio_timbrado);
    timbrado.setFecha_vencimiento_timbrado(fecha_vencimiento_timbrado);
    //timbrado.setFecha_actual_timbrado(fecha_actual_timbrado);
    timbrado.setDesde_timbrado(desde_timbrado);
    timbrado.setHasta_timbrado(hasta_timbrado);
    timbrado.setEstado_timbrado(estado_timbrado);
    timbrado.setTipo_personal(tipo_personal);
    
    if (TimbradosControlador.agregar(timbrado)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>

