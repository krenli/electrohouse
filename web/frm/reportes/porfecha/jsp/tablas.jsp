<%@page import="electrohouse.utiles.Conexion"%>
<%@page import="electrohouse.utiles.Utiles"%>
<%@page import="java.sql.Date"%>
<%@page import="net.sf.jasperreports.engine.JasperExportManager"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="application/pdf"%>
<%
    String dirPath="/rpt";
    String realPath=this.getServletContext().getRealPath(dirPath);
    String listado=request.getParameter("l");
    
   
    String sdesde=request.getParameter("d");
    java.sql.Date d = Utiles.stringToSqlDate(sdesde);
    String shasta=request.getParameter("h");
    java.sql.Date h = Utiles.stringToSqlDate(shasta);
    String jasperReport=listado+".jasper";
    JasperPrint print=null;
    Connection conn=null;
    
    //Clientes clienteLogueado=(Clientes) sesion.getAttribute("clienteLoagueado");
    
    try{
        Conexion.conectar();
        conn=Conexion.getConn();
        Map parameters=new HashMap();
        parameters.put("d",d);
        parameters.put("h",h);
        //parameters.put("USUARIO",clienteLogueado.getCliente_cliente());
        print =JasperFillManager.fillReport(realPath+"//"+jasperReport, parameters,conn);
        response.setContentType("application/pdf");
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }catch(Exception ex){
        ex.printStackTrace();
        System.out.println(ex.getMessage());
    }
    finally{
        if(conn!=null){
            conn.close();
        }
    }
%>

