<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.CreditosControlador"%>
<%@page  import="java.sql.ResultSet"%>
<%
    String estado_aprobacioncredito = request.getParameter("bnombre");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    

    String mensaje = "Busqueda exitosa";
    String contenido = CreditosControlador.buscarNombre(estado_aprobacioncredito, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>
