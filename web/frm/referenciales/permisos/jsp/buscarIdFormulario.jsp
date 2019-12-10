<%@page import="electrohouse.Controladores.FormulariosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.modelos.Formularios"%>
<%@page import="electrohouse.modelos.Formularios"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idformulario = Integer.parseInt(request.getParameter("idformulario"));

   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    
    Formularios formulario = FormulariosControlador.buscarId(idformulario);
    if(formulario!=null){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }else{
        formulario = new Formularios();
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("idformulario", formulario.getIdformulario());
    obj.put("nombre_formulario", formulario.getNombre_formulario());
    
    out.print(obj);
    out.flush();
%>