<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.TimbradosControlador"%>
<%@page  import="java.sql.ResultSet"%>
<%
    String numero_timbrado = request.getParameter("bnumero");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    

    String mensaje = "Busqueda exitosa";
    String contenido = TimbradosControlador.buscarNumero(numero_timbrado, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>
