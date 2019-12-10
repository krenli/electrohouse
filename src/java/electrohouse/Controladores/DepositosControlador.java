/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.Controladores;


import electrohouse.modelos.Ciudades;
import electrohouse.modelos.Depositos;
import electrohouse.utiles.Conexion;
import electrohouse.utiles.Utiles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class DepositosControlador {
    public static boolean agregar(Depositos deposito) {
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "insert into depositos(nombre_deposito, idciudad) "
                    + "values('" + deposito.getNombre_deposito() + "', '" + deposito.getCiudad().getIdciudad() + "')";
            
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
     public static Depositos buscarId(Depositos deposito) {
         if(Conexion.conectar()){
             String sql = "select * from depositos d, ciudades c where d.idciudad=c.idciudad and d.iddeposito='" + deposito.getIddeposito() + "'";
             try {
                 ResultSet rs = Conexion.getSt().executeQuery(sql);
             if(rs.next()){
                 deposito.setIddeposito(rs.getInt("iddeposito"));
                 deposito.setNombre_deposito(rs.getString("nombre_deposito"));
                 
                 Ciudades ciudad = new Ciudades();
                 ciudad.setIdciudad(rs.getInt("idciudad"));
                 ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));
                 deposito.setCiudad(ciudad);
               
             }else{
                 deposito.setIddeposito(0);
                 deposito.setNombre_deposito("");
                
                 Ciudades ciudad = new Ciudades();
                 ciudad.setIdciudad(rs.getInt(0));
                 ciudad.setNombre_ciudad(rs.getString(""));
                 deposito.setCiudad(ciudad);
                 //return null;
                 //return deposito;
             }
             } catch (Exception ex) {
                 System.out.println("Error: " + ex);
             }
         }
         return deposito;
     }
      public static String buscarNombre(String nombre, int pagina) {
       int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
       String valor = "";
          System.out.println("off"+nombre);
       if(Conexion.conectar()){
           try {
               String sql = "select * from depositos d, ciudades c where d.idciudad=c.idciudad and upper (nombre_deposito) like '%" +
                       nombre.toUpperCase() + "%'"
                       + "order by iddeposito offset " + offset + " limit " +
                       Utiles.REGISTROS_PAGINA;
               System.out.println("--->" + sql);
               try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                   ResultSet rs = ps.executeQuery();
                   String tabla = "";
                   while(rs.next()){
                       tabla += "<tr>"
                               + "<td>" + rs.getString("iddeposito") + "</td>"
                               + "<td>" + rs.getString("nombre_deposito") + "</td>"
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
     public static boolean modificar(Depositos deposito){
         boolean valor = false;
         if(Conexion.conectar()){
             String sql = "update depositos set nombre_deposito= '" + deposito.getNombre_deposito() + "'"
                     + "where iddeposito=" + deposito.getIddeposito();
             try {
                 Conexion.getSt().executeUpdate(sql);
                 valor = true;
             } catch (SQLException ex) {
                 System.err.println("Error: " + ex);
             }
         }
         return valor;
     }
      public static boolean eliminar(Depositos deposito){
         boolean valor = false;
         if(Conexion.conectar()){
             String sql = "delete from depositos where iddeposito= " + deposito.getIddeposito();
  
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
