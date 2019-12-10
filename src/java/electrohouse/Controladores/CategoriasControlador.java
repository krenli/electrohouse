/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.Controladores;


import electrohouse.modelos.Categorias;
import electrohouse.utiles.Conexion;
import electrohouse.utiles.Utiles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class CategoriasControlador {
    public static boolean agregar(Categorias categoria) {
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "insert into categorias(nombre_categoria) "
                    + "values('" + categoria.getNombre_categoria() + "')";
            
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
     public static Categorias buscarId(Categorias categoria) {
         if(Conexion.conectar()){
             String sql = "select * from categorias where idcategoria='" + categoria.getIdcategoria() + "'";
             try {
                 ResultSet rs = Conexion.getSt().executeQuery(sql);
             if(rs.next()){
                 categoria.setIdcategoria(rs.getInt("idcategoria"));
                 categoria.setNombre_categoria(rs.getString("nombre_categoria"));
               
             }else{
                 categoria.setIdcategoria(0);
                 categoria.setNombre_categoria("");
                
                 //return null;
                 //return categoria;
             }
             } catch (Exception ex) {
                 System.out.println("Error: " + ex);
             }
         }
         return categoria;
     }
      public static String buscarNombre(String nombre, int pagina) {
       int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
       String valor = "";
          System.out.println("off"+nombre);
       if(Conexion.conectar()){
           try {
               String sql = "select * from categorias where upper (nombre_categoria) like '%" +
                       nombre.toUpperCase() + "%'"
                       + "order by idcategoria offset " + offset + " limit " +
                       Utiles.REGISTROS_PAGINA;
               System.out.println("--->" + sql);
               try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                   ResultSet rs = ps.executeQuery();
                   String tabla = "";
                   while(rs.next()){
                       tabla += "<tr>"
                               + "<td>" + rs.getString("idcategoria") + "</td>"
                               + "<td>" + rs.getString("nombre_categoria") + "</td>"
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
     public static boolean modificar(Categorias categoria){
         boolean valor = false;
         if(Conexion.conectar()){
             String sql = "update categorias set nombre_categoria= '" + categoria.getNombre_categoria() + "'"
                     + "where idcategoria=" + categoria.getIdcategoria();
             try {
                 Conexion.getSt().executeUpdate(sql);
                 valor = true;
             } catch (SQLException ex) {
                 System.err.println("Error: " + ex);
             }
         }
         return valor;
     }
      public static boolean eliminar(Categorias categoria){
         boolean valor = false;
         if(Conexion.conectar()){
             String sql = "delete from categorias where idcategoria= " + categoria.getIdcategoria();
  
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
