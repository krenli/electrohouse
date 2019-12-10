/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.Controladores;
import electrohouse.modelos.Formas_pagos;
import electrohouse.utiles.Conexion;
import electrohouse.utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Formas_pagosControlador {
    public static boolean agregar(Formas_pagos forma_pago){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into formas_pagos (nombre_forma_pago)" 
                    + "values ('" + forma_pago.getNombre_forma_pago() + "')";
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(Formas_pagosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean modificar(Formas_pagos forma_pago){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update formas_pagos set nombre_forma_pago='" + forma_pago.getNombre_forma_pago() + "'"
                    + " where idforma_pago=" + forma_pago.getIdforma_pago();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(Formas_pagosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(Formas_pagos forma_pago){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from formas_pagos where idforma_pago=" + forma_pago.getIdforma_pago();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(Formas_pagosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static Formas_pagos buscarId(Formas_pagos forma_pago) {
        if (Conexion.conectar()){
            String sql = "select * from formas_pagos where idforma_pago ='"+forma_pago.getIdforma_pago()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    forma_pago.setIdforma_pago(rs.getInt("idforma_pago"));
                    forma_pago.setNombre_forma_pago(rs.getString("nombre_forma_pago"));
                } else {
                    forma_pago.setIdforma_pago(0);
                    forma_pago.setNombre_forma_pago("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return forma_pago;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from formas_pagos where upper(nombre_forma_pago) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by idforma_pago offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("idforma_pago") + "</td>"
                                + "<td>" + rs.getString("nombre_forma_pago") + "</td>"
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
    
    public static Formas_pagos buscarFormaPago(Formas_pagos forma_pago) {
        if (Conexion.conectar()){
            String sql = "select * from formas_pagos where nombre_forma_pago ='"+forma_pago.getNombre_forma_pago()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    forma_pago.setIdforma_pago(0);
                    
                } else {
                    forma_pago.setIdforma_pago(1);
                    
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return forma_pago;
    }
}
