<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.TimbradosControlador"%>
<%@page import="electrohouse.modelos.Timbrados"%>
<%@page import="java.sql.ResultSet"%>
<%
    String numero_timbrado = (request.getParameter("numero_timbrado"));
    
    String tipo = "error";
    String mensaje = "Datos no repetidos.";
    String nuevo = "true";
    Timbrados timbrado = new Timbrados();
    timbrado.setNumero_timbrado(numero_timbrado);
    
   TimbradosControlador.buscarTimbrado(timbrado);
    if (timbrado.getIdtimbrado()==0){
        tipo = "success";
        mensaje = "Timbrado repetido.";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    out.print(obj);
    out.flush();
%>
