/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.Controladores;


import electrohouse.modelos.Categorias;
import electrohouse.modelos.Familias;
import electrohouse.utiles.Conexion;
import electrohouse.utiles.Utiles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class FamiliasControlador {
    public static boolean agregar(Familias familia) {
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "insert into familias(nombre_familia, idcategoria) "
                    + "values('" + familia.getNombre_familia() + "', '" + familia.getCategoria().getIdcategoria() + "')";
            
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
     public static Familias buscarId(Familias familia) {
         if(Conexion.conectar()){
             String sql = "select * from familias f, categorias c where f.idcategoria=c.idcategoria and f.idfamilia='" + familia.getIdfamilia() + "'";
             System.out.println("sql"+sql);
             try {
                 ResultSet rs = Conexion.getSt().executeQuery(sql);
             if(rs.next()){
                 familia.setIdfamilia(rs.getInt("idfamilia"));
                 familia.setNombre_familia(rs.getString("nombre_familia"));
                 
                 Categorias categoria = new Categorias();
                 categoria.setIdcategoria(rs.getInt("idcategoria"));
                 categoria.setNombre_categoria(rs.getString("nombre_categoria"));
                 familia.setCategoria(categoria);
               
             }else{
                 familia.setIdfamilia(0);
                 familia.setNombre_familia("");
                 
                 Categorias categoria = new Categorias();
                 categoria.setIdcategoria(rs.getInt(0));
                 categoria.setNombre_categoria(rs.getString(""));
                 familia.setCategoria(categoria);
                
                 //return null;
                 //return familia;
             }
             } catch (Exception ex) {
                 System.out.println("Error: " + ex);
             }
         }
         return familia;
     }
      public static String buscarNombre(String nombre, int pagina) {
       int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
       String valor = "";
          System.out.println("off"+nombre);
       if(Conexion.conectar()){
           try {
               String sql = "select * from familias f, categorias c where f.idcategoria=c.idcategoria and upper (nombre_familia) like '%" +
                       nombre.toUpperCase() + "%'"
                       + "order by idfamilia offset " + offset + " limit " +
                       Utiles.REGISTROS_PAGINA;
               System.out.println("--->" + sql);
               try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                   ResultSet rs = ps.executeQuery();
                   String tabla = "";
                   while(rs.next()){
                       tabla += "<tr>"
                               + "<td>" + rs.getString("idfamilia") + "</td>"
                               + "<td>" + rs.getString("nombre_familia") + "</td>"
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
     public static boolean modificar(Familias familia){
         boolean valor = false;
         if(Conexion.conectar()){
             String sql = "update familias set nombre_familia= '" + familia.getNombre_familia() + "',  idcategoria= '" + familia.getCategoria().getIdcategoria() + "'"
                     + "where idfamilia=" + familia.getIdfamilia();
             try {
                 Conexion.getSt().executeUpdate(sql);
                 valor = true;
             } catch (SQLException ex) {
                 System.err.println("Error: " + ex);
             }
         }
         return valor;
     }
      public static boolean eliminar(Familias familia){
         boolean valor = false;
         if(Conexion.conectar()){
             String sql = "delete from familias where idfamilia= " + familia.getIdfamilia();
  
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
