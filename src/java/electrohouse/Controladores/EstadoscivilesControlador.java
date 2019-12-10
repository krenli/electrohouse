/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.Controladores;


import electrohouse.modelos.Estadosciviles;
import electrohouse.utiles.Conexion;
import electrohouse.utiles.Utiles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class EstadoscivilesControlador {
    public static boolean agregar(Estadosciviles estadocivil) {
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "insert into estadosciviles(nombre_estadocivil) "
                    + "values('" + estadocivil.getNombre_estadocivil() + "')";
            
            System.out.println("sql " +sql);
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (Exception ex) {
                System.err.println("Error: " +ex);
            }
        }
        return valor;
    }
     public static Estadosciviles buscarId(Estadosciviles estadocivil) {
         if(Conexion.conectar()){
             String sql = "select * from estadosciviles where idestadocivil='" + estadocivil.getIdestadocivil() + "'";
             try {
                 ResultSet rs = Conexion.getSt().executeQuery(sql);
             if(rs.next()){
                 estadocivil.setIdestadocivil(rs.getInt("idestadocivil"));
                 estadocivil.setNombre_estadocivil(rs.getString("nombre_estadocivil"));
               
             }else{
                 estadocivil.setIdestadocivil(0);
                 estadocivil.setNombre_estadocivil("");
                
                 //return null;
                 //return estadocivil;
             }
             } catch (Exception ex) {
                 System.out.println("Error: " + ex);
             }
         }
         return estadocivil;
     }
      public static String buscarNombre(String nombre, int pagina) {
       int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
       String valor = "";
          System.out.println("off"+nombre);
       if(Conexion.conectar()){
           try {
               String sql = "select * from estadosciviles where upper (nombre_estadocivil) like '%" +
                       nombre.toUpperCase() + "%'"
                       + "order by idestadocivil offset " + offset + " limit " +
                       Utiles.REGISTROS_PAGINA;
               System.out.println("--->" + sql);
               try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                   ResultSet rs = ps.executeQuery();
                   String tabla = "";
                   while(rs.next()){
                       tabla += "<tr>"
                               + "<td>" + rs.getString("idestadocivil") + "</td>"
                               + "<td>" + rs.getString("nombre_estadocivil") + "</td>"
                               + "</tr>";
                   }
                   if (tabla.equals("")) {
                       tabla = "<tr><td colspan=2>No existen registros...</td></tr>";
                   }
                   ps.close();
                   valor = tabla;
                   
               } catch (SQLException ex) {
                   System.err.println("Error:"+ ex);
               }
               Conexion.cerrar();
               
           } catch (Exception ex) {
               System.err.println("Error: " + ex);
           }
           
       }
       Conexion.cerrar();
       return valor;
      }
     public static boolean modificar(Estadosciviles estadocivil){
         boolean valor = false;
         if(Conexion.conectar()){
             String sql = "update estadosciviles set nombre_estadocivil= '" + estadocivil.getNombre_estadocivil() + "'"
                     + "where idestadocivil=" + estadocivil.getIdestadocivil();
             try {
                 Conexion.getSt().executeUpdate(sql);
                 valor = true;
             } catch (SQLException ex) {
                 System.err.println("Error: " + ex);
             }
         }
         return valor;
     }
      public static boolean eliminar(Estadosciviles estadocivil){
         boolean valor = false;
         if(Conexion.conectar()){
             String sql = "delete from estadosciviles where idestadocivil= " + estadocivil.getIdestadocivil();
  
             try {
                 Conexion.getSt().executeUpdate(sql);
                 valor = true;
             } catch (SQLException ex) {
                 System.err.println("Error: " + ex);
             }
         }
         return valor;
     }
     
    
}
