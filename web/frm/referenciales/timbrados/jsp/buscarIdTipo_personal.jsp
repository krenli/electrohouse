<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.Tipos_personalesControlador"%>
<%@page import="electrohouse.modelos.Tipos_personales"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idtipo_personal = Integer.parseInt(request.getParameter("idtipo_personal"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Tipos_personales tipo_personal = new Tipos_personales();
    tipo_personal.setIdtipo_personal(idtipo_personal);
    
   Tipos_personalesControlador.buscarId(tipo_personal);
    if (tipo_personal.getIdtipo_personal()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("idtipo_personal", tipo_personal.getIdtipo_personal());
    obj.put("nombre_tipo_personal", tipo_personal.getNombre_tipo_personal());
    out.print(obj);
    out.flush();
%>
