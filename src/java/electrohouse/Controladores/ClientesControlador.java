/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.Controladores;



import electrohouse.modelos.Ciudades;
import electrohouse.modelos.Estadosciviles;
import electrohouse.modelos.Clientes;
import electrohouse.utiles.Conexion;
import electrohouse.utiles.Utiles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class ClientesControlador {
    public static boolean agregar(Clientes cliente) {
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "insert into clientes(nombre_cliente, apellido_cliente, ruc_cliente, telefono_cliente, fecha_nac_cliente, direccion_cliente, idciudad, idestadocivil) "
                    + "values('" + cliente.getNombre_cliente() + "', '" + cliente.getApellido_cliente() + "', '" + cliente.getRuc_cliente() + "', '" + cliente.getTelefono_cliente() + "', '" + cliente.getFecha_nac_cliente() + "', '" + cliente.getDireccion_cliente() + "','" + cliente.getCiudad().getIdciudad() + "','" + cliente.getEstadocivil().getIdestadocivil() + "')";
            
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
     public static Clientes buscarId(Clientes cliente) {
         if(Conexion.conectar()){ 
             String sql = "select * from clientes p, ciudades c, estadosciviles ec where p.idciudad=c.idciudad and p.idestadocivil=ec.idestadocivil and p.idcliente='" + cliente.getIdcliente() + "'";
             System.out.println("sql"+sql);
             try {
                 ResultSet rs = Conexion.getSt().executeQuery(sql);
             if(rs.next()){
                 cliente.setIdcliente(rs.getInt("idcliente"));
                 cliente.setNombre_cliente(rs.getString("nombre_cliente"));
                 cliente.setApellido_cliente(rs.getString("apellido_cliente"));
                 cliente.setRuc_cliente(rs.getString("ruc_cliente"));
                 cliente.setTelefono_cliente(rs.getString("telefono_cliente"));
                 cliente.setFecha_nac_cliente(rs.getString("fecha_nac_cliente"));
                 cliente.setDireccion_cliente(rs.getString("direccion_cliente"));
                 
                 Ciudades ciudad = new Ciudades();
                 ciudad.setIdciudad(rs.getInt("idciudad"));
                 ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));
                 cliente.setCiudad(ciudad);
                 
                 Estadosciviles estadocivil = new Estadosciviles();
                 estadocivil.setIdestadocivil(rs.getInt("idestadocivil"));
                 estadocivil.setNombre_estadocivil(rs.getString("nombre_estadocivil"));
                 cliente.setEstadocivil(estadocivil);
               
               
                 
             }else{
                 cliente.setIdcliente(0);
                 cliente.setNombre_cliente("");
                 cliente.setApellido_cliente("");
                 cliente.setRuc_cliente("");
                 cliente.setTelefono_cliente("");
                 cliente.setFecha_nac_cliente("");
                 cliente.setDireccion_cliente("");
                  
                 Ciudades ciudad = new  Ciudades();
                 ciudad.setIdciudad(rs.getInt(0));
                 ciudad.setNombre_ciudad(rs.getString(""));
                 cliente.setCiudad(ciudad);
                
                 Estadosciviles estadocivil = new Estadosciviles();
                 estadocivil.setIdestadocivil(rs.getInt(0));
                 estadocivil.setNombre_estadocivil(rs.getString(""));
                 cliente.setEstadocivil(estadocivil);
               
             
                 //return null;
                 //return cliente;
             }
             } catch (Exception ex) {
                 System.out.println("Error: " + ex);
             }
         }
         return cliente;
     }
      public static String buscarNombre(String nombre, int pagina) {
       int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
       String valor = "";
          System.out.println("off"+nombre);
       if(Conexion.conectar()){
           try {
               String sql = "select * from clientes p, ciudades c, estadosciviles ec where p.idciudad=c.idciudad and p.idestadocivil=ec.idestadocivil and upper (nombre_cliente) like '%" +
                       nombre.toUpperCase() + "%'"
                       + "order by idcliente offset " + offset + " limit " +
                       Utiles.REGISTROS_PAGINA;
               System.out.println("--->" + sql);
               try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                   ResultSet rs = ps.executeQuery();
                   String tabla = "";
                   while(rs.next()){
                       tabla += "<tr>"
                               + "<td>" + rs.getString("idcliente") + "</td>"
                               + "<td>" + rs.getString("nombre_cliente") + "</td>"
                               + "<td>" + rs.getString("apellido_cliente") + "</td>"
                               + "<td>" + rs.getString("ruc_cliente") + "</td>"
                               + "<td>" + rs.getString("telefono_cliente") + "</td>"
                               + "<td>" + rs.getString("fecha_nac_cliente") + "</td>"
                               + "<td>" + rs.getString("direccion_cliente") + "</td>"
                               + "<td>" + rs.getString("nombre_ciudad") + "</td>"
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
     public static boolean modificar(Clientes cliente){
         boolean valor = false;
         if(Conexion.conectar()){
             String sql = "update clientes set nombre_cliente= '" + cliente.getNombre_cliente() + "', apellido_cliente= '" + cliente.getApellido_cliente() + "', ruc_cliente= '" + cliente.getRuc_cliente() + "', telefono_cliente= '" + cliente.getTelefono_cliente() + "', fecha_nac_cliente= '" + cliente.getFecha_nac_cliente() + "', direccion_cliente= '" + cliente.getDireccion_cliente() + "', idciudad= '" + cliente.getCiudad().getIdciudad() + "'  ,  idestadocivil= '" + cliente.getEstadocivil().getIdestadocivil() + "'"
                     + "where idcliente=" + cliente.getIdcliente();
             try {
                 Conexion.getSt().executeUpdate(sql);
                 valor = true;
             } catch (SQLException ex) {
                 System.err.println("Error: " + ex);
             }
         }
         return valor;
     }
      public static boolean eliminar(Clientes cliente){
         boolean valor = false;
         if(Conexion.conectar()){
             String sql = "delete from clientes where idcliente= " + cliente.getIdcliente();
  
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
