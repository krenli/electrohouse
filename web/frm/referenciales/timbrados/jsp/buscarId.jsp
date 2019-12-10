<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.TimbradosControlador"%>
<%@page import="electrohouse.modelos.Timbrados"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idtimbrado = Integer.parseInt(request.getParameter("idtimbrado"));
    
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Timbrados timbrado = new Timbrados();
    timbrado.setIdtimbrado(idtimbrado);
    
   TimbradosControlador.buscarId(timbrado);
    if (timbrado.getIdtimbrado()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("idtimbrado", timbrado.getIdtimbrado());
    obj.put("numero_timbrado", timbrado.getNumero_timbrado());
    obj.put("fecha_inicio_timbrado", String.valueOf(timbrado.getFecha_inicio_timbrado()));
    obj.put("fecha_vencimiento_timbrado", String.valueOf(timbrado.getFecha_vencimiento_timbrado()));
    //obj.put("fecha_actual_timbrado", String.valueOf(timbrado.getFecha_actual_timbrado()));
    obj.put("desde_timbrado", timbrado.getDesde_timbrado());
    obj.put("hasta_timbrado", timbrado.getHasta_timbrado());
    obj.put("estado_timbrado", timbrado.getEstado_timbrado());
    obj.put("idtipo_personal", timbrado.getTipo_personal().getIdtipo_personal());
    obj.put("nombre_tipo_personal", timbrado.getTipo_personal().getNombre_tipo_personal());
    out.print(obj);
    out.flush();
%>
