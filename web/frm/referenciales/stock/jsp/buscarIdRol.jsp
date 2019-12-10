<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.RolesControlador"%>
<%@page import="electrohouse.modelos.Roles"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idrol = Integer.parseInt(request.getParameter("idrol"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Roles rol = new Roles();
    rol.setIdrol(idrol);
    
   RolesControlador.buscarId(rol);
    if (rol.getIdrol()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }else {
        rol = new Roles();
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("idrol", rol.getIdrol());
    obj.put("nombre_rol", rol.getNombre_rol());
    out.print(obj);
    out.flush();
%>
