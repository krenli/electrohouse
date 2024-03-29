<%@page import="electrohouse.utiles.Utiles"%>
<%@page import="electrohouse.utiles.numero"%>
<%@page import="net.sf.jasperreports.engine.JasperExportManager"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="electrohouse.utiles.Conexion"%>
<%@page import="java.sql.Connection"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page contentType="application/pdf" %>
<%@page import="javax.naming.InitialContext"%>

<%
    String dirPath = "/rpt";
    String realPath = this.getServletContext().getRealPath(dirPath);
    String listado = request.getParameter("l");
    int DESDE = Integer.parseInt(request.getParameter("d"));
    int LETRAp = Integer.parseInt(request.getParameter("h"));

    numero n = new numero();
    String LETRAS = n.convertirLetras(LETRAp) + "- - - - - -";

    String jasperReport = listado + ".jasper";
    JasperPrint print = null;
    Connection conn = null;

    try {

        Conexion.conectar();
        conn = Conexion.getConn();
        Map parameters = new HashMap();
        parameters.put("DESDE", DESDE);
        //  parameters.put("totaltotal", totaltotal);
        parameters.put("letras", LETRAS);
        print = JasperFillManager.fillReport(realPath + "//" + jasperReport, parameters, conn);
        response.setContentType("application/pdf");
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    } catch (Exception ex) {
        ex.printStackTrace();
        System.out.println(ex.getMessage());
    } finally {
        if (conn != null) {
            conn.close();
        }
    }
%>
