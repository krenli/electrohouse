/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.Controladores;


import electrohouse.modelos.Marcas;
import electrohouse.utiles.Conexion;
import electrohouse.utiles.Utiles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class MarcasControlador {
    public static boolean agregar(Marcas marca) {
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "insert into marcas(nombre_marca) "
                    + "values('" + marca.getNombre_marca() + "')";
            
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
     public static Marcas buscarId(Marcas marca) {
         if(Conexion.conectar()){
             String sql = "select * from marcas where idmarca='" + marca.getIdmarca() + "'";
             try {
                 ResultSet rs = Conexion.getSt().executeQuery(sql);
             if(rs.next()){
                 marca.setIdmarca(rs.getInt("idmarca"));
                 marca.setNombre_marca(rs.getString("nombre_marca"));
               
             }else{
                 marca.setIdmarca(0);
                 marca.setNombre_marca("");
                
                 //return null;
                 //return marca;
             }
             } catch (Exception ex) {
                 System.out.println("Error: " + ex);
             }
         }
         return marca;
     }
      public static String buscarNombre(String nombre, int pagina) {
       int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
       String valor = "";
          System.out.println("off"+nombre);
       if(Conexion.conectar()){
           try {
               String sql = "select * from marcas where upper (nombre_marca) like '%" +
                       nombre.toUpperCase() + "%'"
                       + "order by idmarca offset " + offset + " limit " +
                       Utiles.REGISTROS_PAGINA;
               System.out.println("--->" + sql);
               try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                   ResultSet rs = ps.executeQuery();
                   String tabla = "";
                   while(rs.next()){
                       tabla += "<tr>"
                               + "<td>" + rs.getString("idmarca") + "</td>"
                               + "<td>" + rs.getString("nombre_marca") + "</td>"
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
     public static boolean modificar(Marcas marca){
         boolean valor = false;
         if(Conexion.conectar()){
             String sql = "update marcas set nombre_marca= '" + marca.getNombre_marca() + "'"
                     + "where idmarca=" + marca.getIdmarca();
             try {
                 Conexion.getSt().executeUpdate(sql);
                 valor = true;
             } catch (SQLException ex) {
                 System.err.println("Error: " + ex);
             }
         }
         return valor;
     }
      public static boolean eliminar(Marcas marca){
         boolean valor = false;
         if(Conexion.conectar()){
             String sql = "delete from marcas where idmarca= " + marca.getIdmarca();
  
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
