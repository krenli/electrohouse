
<%@page import="electrohouse.utiles.Conexion"%>
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
    int desde_id=Integer.parseInt(request.getParameter("d"));
    int hasta_id=Integer.parseInt(request.getParameter("h"));
    String jasperReport=listado+".jasper";
    JasperPrint print=null;
    Connection conn=null;
    
    //Ciudades ciudadLogueado=(Ciudades) sesion.getAttribute("ciudadLoagueado");
    
    try{
        Conexion.conectar();
        conn=Conexion.getConn();
        Map parameters=new HashMap();
        parameters.put("desde_id",desde_id);
        parameters.put("hasta_id",hasta_id);
        //parameters.put("USUARIO",ciudadLogueado.getCiudad_ciudad());
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
