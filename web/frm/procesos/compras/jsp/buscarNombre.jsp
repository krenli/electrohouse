<%@page import="electrohouse.Controladores.FacturaComprasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_factura_compra = request.getParameter("bnombre_factura_compra");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
   
    String mensaje = "B�squeda exitosa.";
    String contenido = FacturaComprasControlador.buscarNombre(nombre_factura_compra, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>