<%@page import="electrohouse.Controladores.UsuariosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>

<%
    String nombre_usuario = request.getParameter("bnombre_usuario");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje = "B�squeda exitosa.";
    String contenido = UsuariosControlador.buscarNombre(nombre_usuario, pagina );
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
      System.out.println("--->" + contenido);
    out.println(obj);
    out.flush();
%>