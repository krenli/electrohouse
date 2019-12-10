/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.Controladores;



import electrohouse.modelos.Ciudades;
import electrohouse.modelos.Estadosciviles;
import electrohouse.modelos.Personales;
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
public class PersonalesControlador {
    public static boolean agregar(Personales personal) {
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "insert into personales(nombre_personal, ci_personal, telefono_personal, fecha_nac_personal, direccion_personal, idciudad, idestadocivil, idtipo_personal) "
                    + "values('" + personal.getNombre_personal() + "', '" + personal.getCi_personal() + "', '" + personal.getTelefono_personal() + "', '" + personal.getFecha_nac_personal() + "', '" + personal.getDireccion_personal() + "','" + personal.getCiudad().getIdciudad() + "','" + personal.getEstadocivil().getIdestadocivil() + "','" + personal.getTipo_personal().getIdtipo_personal() + "')";
            
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
     public static Personales buscarId(Personales personal) {
         if(Conexion.conectar()){ 
             String sql = "select * from personales p, ciudades c, estadosciviles ec, tipo_personal tp where p.idciudad=c.idciudad and p.idestadocivil=ec.idestadocivil and p.idtipo_personal=tp.idtipo_personal and p.idpersonal='" + personal.getIdpersonal() + "'";
             System.out.println("sql"+sql);
             try {
                 ResultSet rs = Conexion.getSt().executeQuery(sql);
             if(rs.next()){
                 personal.setIdpersonal(rs.getInt("idpersonal"));
                 personal.setNombre_personal(rs.getString("nombre_personal"));
                 personal.setCi_personal(rs.getString("ci_personal"));
                 personal.setTelefono_personal(rs.getString("telefono_personal"));
                 personal.setFecha_nac_personal(rs.getString("fecha_nac_personal"));
                 personal.setDireccion_personal(rs.getString("direccion_personal"));
                 
                 Ciudades ciudad = new Ciudades();
                 ciudad.setIdciudad(rs.getInt("idciudad"));
                 ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));
                 personal.setCiudad(ciudad);
                 
                 Estadosciviles estadocivil = new Estadosciviles();
                 estadocivil.setIdestadocivil(rs.getInt("idestadocivil"));
                 estadocivil.setNombre_estadocivil(rs.getString("nombre_estadocivil"));
                 personal.setEstadocivil(estadocivil);
               
                 Tipos_personales tipo_personal = new Tipos_personales();
                 tipo_personal.setIdtipo_personal(rs.getInt("idtipo_personal"));
                 tipo_personal.setNombre_tipo_personal(rs.getString("nombre_tipo_personal"));
                 personal.setTipo_personal(tipo_personal);
                 
             }else{
                 personal.setIdpersonal(0);
                 personal.setNombre_personal("");
                 personal.setCi_personal("");
                 personal.setTelefono_personal("");
                 personal.setFecha_nac_personal("");
                 personal.setDireccion_personal("");
                  
                 Ciudades ciudad = new  Ciudades();
                 ciudad.setIdciudad(rs.getInt(0));
                 ciudad.setNombre_ciudad(rs.getString(""));
                 personal.setCiudad(ciudad);
                
                 Estadosciviles estadocivil = new Estadosciviles();
                 estadocivil.setIdestadocivil(rs.getInt(0));
                 estadocivil.setNombre_estadocivil(rs.getString(""));
                 personal.setEstadocivil(estadocivil);
               
                 Tipos_personales tipo_personal = new Tipos_personales();
                 tipo_personal.setIdtipo_personal(rs.getInt(0));
                 tipo_personal.setNombre_tipo_personal(rs.getString(""));
                 personal.setTipo_personal(tipo_personal);
                 //return null;
                 //return personal;
             }
             } catch (Exception ex) {
                 System.out.println("Error: " + ex);
             }
         }
         return personal;
     }
      public static String buscarNombre(String nombre, int pagina) {
       int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
       String valor = "";
          System.out.println("off"+nombre);
       if(Conexion.conectar()){
           try {
               String sql = "select * from personales p, ciudades c, estadosciviles ec, tipo_personal tp where p.idciudad=c.idciudad and p.idestadocivil=ec.idestadocivil and p.idtipo_personal=tp.idtipo_personal and upper (nombre_personal) like '%" +
                       nombre.toUpperCase() + "%'"
                       + "order by idpersonal offset " + offset + " limit " +
                       Utiles.REGISTROS_PAGINA;
               System.out.println("--->" + sql);
               try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                   ResultSet rs = ps.executeQuery();
                   String tabla = "";
                   while(rs.next()){
                       tabla += "<tr>"
                               + "<td>" + rs.getString("idpersonal") + "</td>"
                               + "<td>" + rs.getString("nombre_personal") + "</td>"
                               + "<td>" + rs.getString("ci_personal") + "</td>"
                               + "<td>" + rs.getString("telefono_personal") + "</td>"
                               + "<td>" + rs.getString("fecha_nac_personal") + "</td>"
                               + "<td>" + rs.getString("direccion_personal") + "</td>"
                               + "<td>" + rs.getString("nombre_ciudad") + "</td>"
                               + "<td>" + rs.getString("nombre_estadocivil") + "</td>"
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
     public static boolean modificar(Personales personal){
         boolean valor = false;
         if(Conexion.conectar()){
             String sql = "update personales set nombre_personal= '" + personal.getNombre_personal() + "', ci_personal= '" + personal.getCi_personal() + "', telefono_personal= '" + personal.getTelefono_personal() + "', fecha_nac_personal= '" + personal.getFecha_nac_personal() + "', direccion_personal= '" + personal.getDireccion_personal() + "', idciudad= '" + personal.getCiudad().getIdciudad() + "'  ,  idestadocivil= '" + personal.getEstadocivil().getIdestadocivil() + "',  idtipo_personal= '" + personal.getTipo_personal().getIdtipo_personal() + "'"
                     + "where idpersonal=" + personal.getIdpersonal();
             try {
                 Conexion.getSt().executeUpdate(sql);
                 valor = true;
             } catch (SQLException ex) {
                 System.err.println("Error: " + ex);
             }
         }
         return valor;
     }
      public static boolean eliminar(Personales personal){
         boolean valor = false;
         if(Conexion.conectar()){
             String sql = "delete from personales where idpersonal= " + personal.getIdpersonal();
  
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
