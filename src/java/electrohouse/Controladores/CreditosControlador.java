package electrohouse.Controladores;
import electrohouse.modelos.Creditos;
import electrohouse.utiles.Conexion;
import electrohouse.utiles.Utiles;
import java.sql.Date;

import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class CreditosControlador {
    public static boolean agregar(Creditos aprobacioncredito){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into aprobacion_creditos (fecha_aprobacioncredito, descripcion_aprobacioncredito, estado_aprobacioncredito, referencia_comercial, referencia_personal, limite_credito)" 
                    + "values ('" + aprobacioncredito.getFecha_aprobacioncredito() + "','"
                    + aprobacioncredito.getDescripcion_aprobacioncredito() + "','"
                    + aprobacioncredito.getEstado_aprobacioncredito() + "','"
                    + aprobacioncredito.getReferencia_comercial() + "','"
                    + aprobacioncredito.getReferencia_personal() + "','"
                    + aprobacioncredito.getLimite_credito() + "')";
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(CreditosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean modificar(Creditos aprobacioncredito){
        boolean valor = false;
        if (Conexion.conectar()){ 
            Date v1 = aprobacioncredito.getFecha_aprobacioncredito();
            String v2 = aprobacioncredito.getDescripcion_aprobacioncredito();
            String v3 = aprobacioncredito.getEstado_aprobacioncredito();
            String v4 = aprobacioncredito.getReferencia_comercial();
            String v5 = aprobacioncredito.getReferencia_personal();
            String v6 = aprobacioncredito.getLimite_credito();
            
            String sql = "update aprobacion_creditos set fecha_aprobacioncredito='" + v1 + "', "
                    + "descripcion_aprobacioncredito='" + v2 + "', "
                    + "estado_aprobacioncredito='" + v3 + "', "
                    + "referencia_comercial='" + v4 + "', "
                    + "referencia_personal='" + v5 + "', "
                    + "limite_credito='" + v6 + "'"
                    + " where idaprobacioncredito=" + aprobacioncredito.getIdaprobacioncredito();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(CreditosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(Creditos aprobacioncredito){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from aprobacion_creditos where idaprobacioncredito=" + aprobacioncredito.getIdaprobacioncredito();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(CreditosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static Creditos buscarId(Creditos aprobacioncredito) {
        if (Conexion.conectar()){
            String sql = "select * from aprobacion_creditos where idaprobacioncredito ='"+aprobacioncredito.getIdaprobacioncredito()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    aprobacioncredito.setIdaprobacioncredito(rs.getInt("idaprobacioncredito"));
                    aprobacioncredito.setFecha_aprobacioncredito(rs.getDate("fecha_aprobacioncredito"));
                    aprobacioncredito.setDescripcion_aprobacioncredito(rs.getString("descripcion_aprobacioncredito"));
                    aprobacioncredito.setEstado_aprobacioncredito(rs.getString("estado_aprobacioncredito"));
                    aprobacioncredito.setReferencia_comercial(rs.getString("referencia_comercial"));
                    aprobacioncredito.setReferencia_personal(rs.getString("referencia_personal"));
                    aprobacioncredito.setLimite_credito(rs.getString("limite_credito"));
                } else {
                    aprobacioncredito.setIdaprobacioncredito(0);
                    aprobacioncredito.setFecha_aprobacioncredito(null);
                    aprobacioncredito.setDescripcion_aprobacioncredito("");
                    aprobacioncredito.setEstado_aprobacioncredito("");
                    aprobacioncredito.setReferencia_comercial("");
                    aprobacioncredito.setReferencia_personal("");
                    aprobacioncredito.setLimite_credito("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return aprobacioncredito;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from aprobacion_creditos where upper(estado_aprobacioncredito) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by idaprobacioncredito offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("idaprobacioncredito") + "</td>"
                                + "<td>" + rs.getString("estado_aprobacioncredito") + "</td>"
                                + "</tr>";
                    }   
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2> No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("Error: " + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("Error: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }
    
    public static Creditos buscarAprobacionCredito(Creditos aprobacioncredito) {
        if (Conexion.conectar()){
            String sql = "select * from aprobacion_creditos where estado_aprobacioncredito ='"+aprobacioncredito.getEstado_aprobacioncredito()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    aprobacioncredito.setIdaprobacioncredito(0);
                    
                } else {
                    aprobacioncredito.setIdaprobacioncredito(1);
                    
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return aprobacioncredito;
    }
}
