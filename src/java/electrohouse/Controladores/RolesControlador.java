/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.Controladores;

import electrohouse.modelos.Roles;
import electrohouse.utiles.Conexion;
import electrohouse.utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class RolesControlador {
    public static boolean agregar(Roles rol){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into roles (nombre_rol)" 
                    + "values ('" + rol.getNombre_rol() + "')";
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(RolesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean modificar(Roles rol){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update roles set nombre_rol='" + rol.getNombre_rol() + "'"
                    + " where idrol=" + rol.getIdrol();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(RolesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(Roles rol){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from roles where idrol=" + rol.getIdrol();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(RolesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static Roles buscarId(Roles rol) {
        if (Conexion.conectar()){
            String sql = "select * from roles where idrol ='"+rol.getIdrol()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    rol.setIdrol(rs.getInt("idrol"));
                    rol.setNombre_rol(rs.getString("nombre_rol"));
                } else {
                    rol.setIdrol(0);
                    rol.setNombre_rol("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return rol;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from roles where upper(nombre_rol) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by idrol offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("idrol") + "</td>"
                                + "<td>" + rs.getString("nombre_rol") + "</td>"
                                + "</tr>";
                    }   
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2> No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("Error: " + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("Error: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }
}
