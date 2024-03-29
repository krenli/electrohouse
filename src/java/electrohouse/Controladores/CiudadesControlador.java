/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.Controladores;


import electrohouse.modelos.Ciudades;
import electrohouse.utiles.Conexion;
import electrohouse.utiles.Utiles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class CiudadesControlador {
    public static boolean agregar(Ciudades ciudad) {
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "insert into ciudades(nombre_ciudad) "
                    + "values('" + ciudad.getNombre_ciudad() + "')";
            
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
     public static Ciudades buscarId(Ciudades ciudad) {
         if(Conexion.conectar()){
             String sql = "select * from ciudades where idciudad='" + ciudad.getIdciudad() + "'";
             try {
                 ResultSet rs = Conexion.getSt().executeQuery(sql);
             if(rs.next()){
                 ciudad.setIdciudad(rs.getInt("idciudad"));
                 ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));
               
             }else{
                 ciudad.setIdciudad(0);
                 ciudad.setNombre_ciudad("");
                
                 //return null;
                 //return ciudad;
             }
             } catch (Exception ex) {
                 System.out.println("Error: " + ex);
             }
         }
         return ciudad;
     }
      public static String buscarNombre(String nombre, int pagina) {
       int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
       String valor = "";
          System.out.println("off"+nombre);
       if(Conexion.conectar()){
           try {
               String sql = "select * from ciudades where upper (nombre_ciudad) like '%" +
                       nombre.toUpperCase() + "%'"
                       + "order by idciudad offset " + offset + " limit " +
                       Utiles.REGISTROS_PAGINA;
               System.out.println("--->" + sql);
               try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                   ResultSet rs = ps.executeQuery();
                   String tabla = "";
                   while(rs.next()){
                       tabla += "<tr>"
                               + "<td>" + rs.getString("idciudad") + "</td>"
                               + "<td>" + rs.getString("nombre_ciudad") + "</td>"
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
     public static boolean modificar(Ciudades ciudad){
         boolean valor = false;
         if(Conexion.conectar()){
             String sql = "update ciudades set nombre_ciudad= '" + ciudad.getNombre_ciudad() + "'"
                     + "where idciudad=" + ciudad.getIdciudad();
             try {
                 Conexion.getSt().executeUpdate(sql);
                 valor = true;
             } catch (SQLException ex) {
                 System.err.println("Error: " + ex);
             }
         }
         return valor;
     }
      public static boolean eliminar(Ciudades ciudad){
         boolean valor = false;
         if(Conexion.conectar()){
             String sql = "delete from ciudades where idciudad= " + ciudad.getIdciudad();
  
             try {
                 Conexion.getSt().executeUpdate(sql);
                 valor = true;
             } catch (SQLException ex) {
                 System.err.println("Error: " + ex);
             }
         }
         return valor;
     }
     public static Ciudades noDuplicado(Ciudades ciudad) {
         if(Conexion.conectar()){
             String sql = "select * from ciudades where nombre_ciudad='" + ciudad.getNombre_ciudad() + "'";
             try {
                 ResultSet rs = Conexion.getSt().executeQuery(sql);
             if(rs.next()){
                 ciudad.setIdciudad(rs.getInt("idciudad"));
                 ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));
               
             }else{
                 ciudad.setIdciudad(0);
                 ciudad.setNombre_ciudad("");
                
                 //return null;
                 //return ciudad;
             }
             } catch (Exception ex) {
                 System.out.println("Error: " + ex);
             }
         }
         return ciudad;
     } 
      public static Ciudades buscarCiudad(Ciudades ciudad) {
        if (Conexion.conectar()){
            String sql = "select * from ciudades where nombre_ciudad ='"+ciudad.getNombre_ciudad()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    ciudad.setIdciudad(0);
                    
                } else {
                    ciudad.setIdciudad(1);
                    
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return ciudad;
    }
}
