/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.Controladores;



import electrohouse.modelos.Depositos;
import electrohouse.modelos.Familias;
import electrohouse.modelos.Marcas;
import electrohouse.modelos.Productos;
import electrohouse.modelos.Proveedores;
import electrohouse.utiles.Conexion;
import electrohouse.utiles.Utiles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class ProductosControlador {
    public static boolean agregar(Productos producto) {
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "insert into productos(nombre_producto, cod_barras_producto, costo_producto, precio_producto, minimo_producto, iva_producto, idfamilia, idmarca) "
                    + "values('" + producto.getNombre_producto() + "', '" + producto.getCod_barras_producto() + "', '" + producto.getCosto_producto() + "', '" + producto.getPrecio_producto() + "', '" + producto.getMinimo_producto() + "', '" + producto.getIva_producto() + "', '" + producto.getFamilia().getIdfamilia() + "','" + producto.getMarca().getIdmarca() + "')";
            
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
     public static Productos buscarId(Productos producto) {
         if(Conexion.conectar()){ 
             String sql = "select * from productos p, familias f, marcas m where p.idfamilia=f.idfamilia and p.idmarca=m.idmarca and p.idproducto='" + producto.getIdproducto() + "'";
             System.out.println("sql"+sql);
             try {
                 ResultSet rs = Conexion.getSt().executeQuery(sql);
             if(rs.next()){
                 producto.setIdproducto(rs.getInt("idproducto"));
                 producto.setNombre_producto(rs.getString("nombre_producto"));
                 producto.setCod_barras_producto(rs.getString("cod_barras_producto"));
                 producto.setCosto_producto(rs.getInt("costo_producto"));
                 producto.setPrecio_producto(rs.getInt("precio_producto"));
                 producto.setMinimo_producto(rs.getInt("minimo_producto"));
                 producto.setIva_producto(rs.getInt("iva_producto"));
                 
                 Familias familia = new Familias();
                 familia.setIdfamilia(rs.getInt("idfamilia"));
                 familia.setNombre_familia(rs.getString("nombre_familia"));
                 producto.setFamilia(familia);
                 
                 Marcas marca = new Marcas();
                 marca.setIdmarca(rs.getInt("idmarca"));
                 marca.setNombre_marca(rs.getString("nombre_marca"));
                 producto.setMarca(marca);
               
                
                 
             }else{
                 producto.setIdproducto(0);
                 producto.setNombre_producto("");
                 producto.setCod_barras_producto("");
                 producto.setCosto_producto(0);
                 producto.setPrecio_producto(0);
                 producto.setMinimo_producto(0);
                 producto.setIva_producto(0);

                 
                
                 Familias familia = new Familias();
                 familia.setIdfamilia(rs.getInt(0));
                 familia.setNombre_familia(rs.getString(""));
                 producto.setFamilia(familia);
                 
                 Marcas marca = new Marcas();
                 marca.setIdmarca(rs.getInt(0));
                 marca.setNombre_marca(rs.getString(""));
                 producto.setMarca(marca);
               
               
                 //return null;
                 //return producto;
             }
             } catch (Exception ex) {
                 System.out.println("Error: " + ex);
             }
         }
         return producto;
     }
      public static String buscarNombre(String nombre, int pagina) {
       int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
       String valor = "";
          System.out.println("off"+nombre);
       if(Conexion.conectar()){
           try {
               String sql = "select * from productos p, familias f, marcas m where p.idfamilia=f.idfamilia and p.idmarca=m.idmarca and upper (nombre_producto) like '%" +
                       nombre.toUpperCase() + "%'"
                       + "order by idproducto offset " + offset + " limit " +
                       Utiles.REGISTROS_PAGINA;
               System.out.println("--->" + sql);
               try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                   ResultSet rs = ps.executeQuery();
                   String tabla = "";
                   while(rs.next()){
                       tabla += "<tr>"
                               + "<td>" + rs.getString("idproducto") + "</td>"
                               + "<td>" + rs.getString("nombre_producto") + "</td>"
                               + "<td>" + rs.getString("cod_barras_producto") + "</td>"
                               + "<td>" + rs.getString("costo_producto") + "</td>"
                               + "<td>" + rs.getString("precio_producto") + "</td>"
                               + "<td>" + rs.getString("minimo_producto") + "</td>"
                               + "<td>" + rs.getString("iva_producto") + "</td>"
                               + "<td>" + rs.getString("nombre_familia") + "</td>"
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
     public static boolean modificar(Productos producto){
         boolean valor = false;
         if(Conexion.conectar()){
             String sql = "update productos set nombre_producto= '" + producto.getNombre_producto() + "', cod_barras_producto= '" + producto.getCod_barras_producto() + "', costo_producto= '" + producto.getCosto_producto() + "', precio_producto= '" + producto.getPrecio_producto() + "', minimo_producto= '" + producto.getMinimo_producto() + "', iva_producto= '" + producto.getIva_producto() + "', idfamilia= '" + producto.getFamilia().getIdfamilia() + "'  ,  idmarca= '" + producto.getMarca().getIdmarca() + "'"
                     + "where idproducto=" + producto.getIdproducto();
             try {
                 Conexion.getSt().executeUpdate(sql);
                 valor = true;
             } catch (SQLException ex) {
                 System.err.println("Error: " + ex);
             }
         }
         return valor;
     }
      public static boolean eliminar(Productos producto){
         boolean valor = false;
         if(Conexion.conectar()){
             String sql = "delete from productos where idproducto= " + producto.getIdproducto();
  
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
