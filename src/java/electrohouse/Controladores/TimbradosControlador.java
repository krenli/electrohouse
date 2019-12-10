/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.Controladores;
import electrohouse.modelos.Timbrados;
import electrohouse.modelos.Tipos_personales;
import electrohouse.utiles.Conexion;
import electrohouse.utiles.Utiles;
import java.sql.Date;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.logging.Logger;

public class TimbradosControlador {
    public static boolean agregar(Timbrados timbrado){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into timbrados (numero_timbrado, fecha_inicio_timbrado, fecha_vencimiento_timbrado, desde_timbrado, hasta_timbrado, estado_timbrado, idtipo_personal)" 
                    + "values ('" + timbrado.getNumero_timbrado() + "','"
                    + timbrado.getFecha_inicio_timbrado() + "','"
                    + timbrado.getFecha_vencimiento_timbrado() + "','"
                    //+ timbrado.getFecha_actual_timbrado() + "','"
                    + timbrado.getDesde_timbrado() + "','"
                    + timbrado.getHasta_timbrado() + "','"
                    + timbrado.getEstado_timbrado() + "','"
                    + timbrado.getTipo_personal().getIdtipo_personal() + "')";
                   
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(TimbradosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean modificar(Timbrados timbrado){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update timbrados set numero_timbrado='" + timbrado.getNumero_timbrado() + "', "
                    + "fecha_inicio_timbrado='" + timbrado.getFecha_inicio_timbrado() + "', "
                    + "fecha_vencimiento_timbrado='" + timbrado.getFecha_vencimiento_timbrado() + "', "
                    //+ "fecha_actual_timbrado='" + timbrado.getFecha_actual_timbrado() + "', "
                    + "desde_timbrado='" + timbrado.getDesde_timbrado() + "', "
                    + "hasta_timbrado='" + timbrado.getHasta_timbrado() + "', "
                    + "estado_timbrado='" + timbrado.getEstado_timbrado() + "', "
                    + "idtipo_personal='" + timbrado.getTipo_personal().getIdtipo_personal() + "'"
                    + " where idtimbrado=" + timbrado.getIdtimbrado();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(TimbradosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(Timbrados timbrado){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from timbrados where idtimbrado=" + timbrado.getIdtimbrado();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(TimbradosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static Timbrados buscarId(Timbrados timbrado) {
        if (Conexion.conectar()){
            String sql = "select * from timbrados t, tipo_personal p where t.idtipo_personal = p.idtipo_personal and t.idtimbrado ='"+timbrado.getIdtimbrado()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    timbrado.setIdtimbrado(rs.getInt("idtimbrado"));
                    timbrado.setNumero_timbrado(rs.getString("numero_timbrado"));
                    timbrado.setFecha_inicio_timbrado(rs.getDate("fecha_inicio_timbrado"));
                    timbrado.setFecha_vencimiento_timbrado(rs.getDate("fecha_vencimiento_timbrado"));
                    //timbrado.setFecha_actual_timbrado(rs.getDate("fecha_actual_timbrado"));
                    timbrado.setDesde_timbrado(rs.getInt("desde_timbrado"));
                    timbrado.setHasta_timbrado(rs.getInt("hasta_timbrado"));
                    timbrado.setEstado_timbrado(rs.getString("estado_timbrado"));
                    
                    Tipos_personales tipo_personal = new Tipos_personales();
                    tipo_personal.setIdtipo_personal(rs.getInt("idtipo_personal"));
                    tipo_personal.setNombre_tipo_personal(rs.getString("nombre_tipo_personal"));
                    timbrado.setTipo_personal(tipo_personal);
                   } else {
                    timbrado.setIdtimbrado(0);
                    timbrado.setNumero_timbrado("");
                    timbrado.setFecha_inicio_timbrado(null);
                    timbrado.setFecha_vencimiento_timbrado(null);
                    //timbrado.setFecha_actual_timbrado(null);
                    timbrado.setDesde_timbrado(0);
                    timbrado.setHasta_timbrado(0);
                    timbrado.setEstado_timbrado("");
                   
                    Tipos_personales tipo_personal = new Tipos_personales();
                    tipo_personal.setIdtipo_personal(0);
                    tipo_personal.setNombre_tipo_personal("");
                    timbrado.setTipo_personal(tipo_personal);
                    
                }   
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return timbrado;
    }
    
    public static String buscarNumero(String numero, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(numero);
                String sql = "select * from timbrados where upper(numero_timbrado) like '%" +
                        numero.toUpperCase() + "%'"
                        + "order by idtimbrado offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("idtimbrado") + "</td>"
                                + "<td>" + rs.getString("numero_timbrado") + "</td>"
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
    
    public static Timbrados buscarTimbrado(Timbrados timbrado) {
        if (Conexion.conectar()){
            String sql = "select * from timbrados where numero_timbrado ='"+timbrado.getNumero_timbrado()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    timbrado.setIdtimbrado(0);
                    
                } else {
                    timbrado.setIdtimbrado(1);
                    
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return timbrado;
    }
}
