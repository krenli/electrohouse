/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.Controladores;


import electrohouse.modelos.Ciudades;
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
public class ProveedoresControlador {
    public static boolean agregar(Proveedores proveedor) {
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "insert into proveedores(nombre_proveedor, ruc_proveedor, telefono_proveedor, direccion_proveedor, email_proveedor, idciudad) "
                    + "values('" + proveedor.getNombre_proveedor() + "','" + proveedor.getRuc_proveedor() + "' , '" + proveedor.getTelefono_proveedor() + "', '" + proveedor.getDireccion_proveedor() + "', '" + proveedor.getEmail_proveedor() + "','" + proveedor.getCiudad().getIdciudad() + "')";
            
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
     public static Proveedores buscarId(Proveedores proveedor) {
         if(Conexion.conectar()){
             String sql = "select * from proveedores d, ciudades c where d.idciudad=c.idciudad and d.idproveedor='" + proveedor.getIdproveedor() + "'";
             try {
                 ResultSet rs = Conexion.getSt().executeQuery(sql);
             if(rs.next()){
                 proveedor.setIdproveedor(rs.getInt("idproveedor"));
                 proveedor.setNombre_proveedor(rs.getString("nombre_proveedor"));
                 proveedor.setRuc_proveedor(rs.getString("ruc_proveedor"));
                 proveedor.setTelefono_proveedor(rs.getString("telefono_proveedor"));
                 proveedor.setDireccion_proveedor(rs.getString("direccion_proveedor"));
                 proveedor.setEmail_proveedor(rs.getString("email_proveedor"));
                 
                 Ciudades ciudad = new Ciudades();
                 ciudad.setIdciudad(rs.getInt("idciudad"));
                 ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));
                 proveedor.setCiudad(ciudad);
               
             }else{
                 proveedor.setIdproveedor(0);
                 proveedor.setNombre_proveedor("");
                 proveedor.setRuc_proveedor("");
                 proveedor.setTelefono_proveedor("");
                 proveedor.setDireccion_proveedor("");
                 proveedor.setEmail_proveedor("");
                 
                 Ciudades ciudad = new Ciudades();
                 ciudad.setIdciudad(rs.getInt(0));
                 ciudad.setNombre_ciudad(rs.getString(""));
                 proveedor.setCiudad(ciudad);
                 //return null;
                 //return proveedor;
             }
             } catch (Exception ex) {
                 System.out.println("Error: " + ex);
             }
         }
         return proveedor;
     }
      public static String buscarNombre(String nombre, int pagina) {
       int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
       String valor = "";
          System.out.println("off"+nombre);
       if(Conexion.conectar()){
           try {
               String sql = "select * from proveedores d, ciudades c where d.idciudad=c.idciudad and upper (nombre_proveedor) like '%" +
                       nombre.toUpperCase() + "%'"
                       + "order by idproveedor offset " + offset + " limit " +
                       Utiles.REGISTROS_PAGINA;
               System.out.println("--->" + sql);
               try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                   ResultSet rs = ps.executeQuery();
                   String tabla = "";
                   while(rs.next()){
                       tabla += "<tr>"
                               + "<td>" + rs.getString("idproveedor") + "</td>"
                               + "<td>" + rs.getString("nombre_proveedor") + "</td>"
                               + "<td>" + rs.getString("ruc_proveedor") + "</td>"
                               + "<td>" + rs.getString("telefono_proveedor") + "</td>"
                               + "<td>" + rs.getString("direccion_proveedor") + "</td>"
                               + "<td>" + rs.getString("email_proveedor") + "</td>"
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
     public static boolean modificar(Proveedores proveedor){
         boolean valor = false;
         if(Conexion.conectar()){
             String sql = "update proveedores set nombre_proveedor= '" + proveedor.getNombre_proveedor() + "', ruc_proveedor= '" + proveedor.getRuc_proveedor() + "', telefono_proveedor= '" + proveedor.getTelefono_proveedor() + "', direccion_proveedor= '" + proveedor.getDireccion_proveedor() + "', email_proveedor= '" + proveedor.getEmail_proveedor() + "',  idciudad= '" + proveedor.getCiudad().getIdciudad() + "'"
                     + "where idproveedor=" + proveedor.getIdproveedor();
             try {
                 Conexion.getSt().executeUpdate(sql);
                 valor = true;
             } catch (SQLException ex) {
                 System.err.println("Error: " + ex);
             }
         }
         return valor;
     }
      public static boolean eliminar(Proveedores proveedor){
         boolean valor = false;
         if(Conexion.conectar()){
             String sql = "delete from proveedores where idproveedor= " + proveedor.getIdproveedor();
  
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
