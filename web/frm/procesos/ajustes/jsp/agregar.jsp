<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.modelos.Ajustes"%>
<%@page import="electrohouse.Controladores.AjustesControlador"%>
<%@page import="electrohouse.modelos.Usuarios"%>
<%@page import="electrohouse.utiles.Utiles"%>
<%@page import="java.sql.ResultSet"%>
<%
    int idajuste_stock = Integer.parseInt(request.getParameter("idajuste_stock"));
    int idusuario = Integer.parseInt(request.getParameter("sid_usuario"));
    String sfecha_ajuste_stock = request.getParameter("fecha_ajuste_stock");
    java.sql.Date fecha_ajuste_stock = Utiles.stringToSqlDate(sfecha_ajuste_stock);
      String motivo_ajuste_stock = request.getParameter("motivo_ajuste_stock");
    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Usuarios usuario = new Usuarios();
    usuario.setIdusuario(idusuario);

    Ajustes ajuste = new Ajustes();
    ajuste.setIdajuste_stock(idajuste_stock);
    ajuste.setFecha_ajuste_stock(fecha_ajuste_stock);
    ajuste.setMotivo_ajuste_stock(motivo_ajuste_stock);
    ajuste.setUsuario(usuario);
    if (AjustesControlador.agregar(ajuste)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("idajuste_stock", String.valueOf(ajuste.getIdajuste_stock()));
    out.print(obj);
    out.flush();

%>