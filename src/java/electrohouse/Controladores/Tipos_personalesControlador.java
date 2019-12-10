/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.Controladores;


import electrohouse.modelos.Tipos_personales;
import electrohouse.utiles.Conexion;
import electrohouse.utiles.Utiles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class Tipos_personalesControlador {
    public static boolean agregar(Tipos_personales tipo_personal) {
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "insert into tipo_personal(nombre_tipo_personal) "
                    + "values('" + tipo_personal.getNombre_tipo_personal() + "')";
            
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
     public static Tipos_personales buscarId(Tipos_personales tipo_personal) {
         if(Conexion.conectar()){
             String sql = "select * from tipo_personal where idtipo_personal='" + tipo_personal.getIdtipo_personal() + "'";
             try {
                 ResultSet rs = Conexion.getSt().executeQuery(sql);
             if(rs.next()){
                 tipo_personal.setIdtipo_personal(rs.getInt("idtipo_personal"));
                 tipo_personal.setNombre_tipo_personal(rs.getString("nombre_tipo_personal"));
               
             }else{
                 tipo_personal.setIdtipo_personal(0);
                 tipo_personal.setNombre_tipo_personal("");
                
                 //return null;
                 //return tipo_personal;
             }
             } catch (Exception ex) {
                 System.out.println("Error: " + ex);
             }
         }
         return tipo_personal;
     }
      public static String buscarNombre(String nombre, int pagina) {
       int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
       String valor = "";
          System.out.println("off"+nombre);
       if(Conexion.conectar()){
           try {
               String sql = "select * from tipo_personal where upper (nombre_tipo_personal) like '%" +
                       nombre.toUpperCase() + "%'"
                       + "order by idtipo_personal offset " + offset + " limit " +
                       Utiles.REGISTROS_PAGINA;
               System.out.println("--->" + sql);
               try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                   ResultSet rs = ps.executeQuery();
                   String tabla = "";
                   while(rs.next()){
                       tabla += "<tr>"
                               + "<td>" + rs.getString("idtipo_personal") + "</td>"
                               + "<td>" + rs.getString("nombre_tipo_personal") + "</td>"
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
     public static boolean modificar(Tipos_personales tipo_personal){
         boolean valor = false;
         if(Conexion.conectar()){
             String sql = "update tipo_personal set nombre_tipo_personal= '" + tipo_personal.getNombre_tipo_personal() + "'"
                     + "where idtipo_personal=" + tipo_personal.getIdtipo_personal();
             try {
                 Conexion.getSt().executeUpdate(sql);
                 valor = true;
             } catch (SQLException ex) {
                 System.err.println("Error: " + ex);
             }
         }
         return valor;
     }
      public static boolean eliminar(Tipos_personales tipo_personal){
         boolean valor = false;
         if(Conexion.conectar()){
             String sql = "delete from tipo_personal where idtipo_personal= " + tipo_personal.getIdtipo_personal();
  
             try {
                 Conexion.getSt().executeUpdate(sql);
                 valor = true;
             } catch (SQLException ex) {
                 System.err.println("Error: " + ex);
             }
         }
         return valor;
     }
     public static Tipos_personales noDuplicado(Tipos_personales tipo_personal) {
         if(Conexion.conectar()){
             String sql = "select * from tipo_personal where nombre_tipo_personal='" + tipo_personal.getNombre_tipo_personal() + "'";
             try {
                 ResultSet rs = Conexion.getSt().executeQuery(sql);
             if(rs.next()){
                 tipo_personal.setIdtipo_personal(rs.getInt("idtipo_personal"));
                 tipo_personal.setNombre_tipo_personal(rs.getString("nombre_tipo_personal"));
               
             }else{
                 tipo_personal.setIdtipo_personal(0);
                 tipo_personal.setNombre_tipo_personal("");
                
                 //return null;
                 //return tipo_personal;
             }
             } catch (Exception ex) {
                 System.out.println("Error: " + ex);
             }
         }
         return tipo_personal;
     } 
    
}
