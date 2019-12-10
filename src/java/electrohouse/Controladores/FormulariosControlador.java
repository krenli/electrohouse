/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.Controladores;


import electrohouse.modelos.Cajas;
import electrohouse.modelos.Menus;
import electrohouse.modelos.Formularios;
import electrohouse.utiles.Conexion;
import electrohouse.utiles.Utiles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class FormulariosControlador {
    public static boolean agregar(Formularios formulario) {
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "insert into formularios(nombre_formulario, codigo_formulario, idmenu) "
                    + "values('" + formulario.getNombre_formulario() + "','" + formulario.getCodigo_formulario() + "' ,'" + formulario.getMenu().getIdmenu() + "')";
            
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
    public static Formularios buscarId(int id) {
        Formularios formularios = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from formularios f "+
                             "left join menus m on f.idmenu=m.idmenu "+
                             "where idformulario=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        formularios = new Formularios();
                        formularios.setIdformulario(rs.getInt("idformulario"));
                        formularios.setNombre_formulario(rs.getString("nombre_formulario"));
                        formularios.setCodigo_formulario(rs.getString("codigo_formulario"));
                        Menus menu = new Menus();
                        menu.setIdmenu(rs.getInt("idmenu"));
                        menu.setNombre_menu(rs.getString("nombre_menu"));
                        menu.setCodigo_menu(rs.getString("codigo_menu"));
                        formularios.setMenu(menu);
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return formularios;
    }
      public static String buscarNombre(String nombre, int pagina) {
       int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
       String valor = "";
          System.out.println("off"+nombre);
       if(Conexion.conectar()){
           try {
               String sql = "select * from formularios d, menus c where d.idmenu=c.idmenu and upper (nombre_formulario) like '%" +
                       nombre.toUpperCase() + "%'"
                       + "order by idformulario offset " + offset + " limit " +
                       Utiles.REGISTROS_PAGINA;
               System.out.println("--->" + sql);
               try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                   ResultSet rs = ps.executeQuery();
                   String tabla = "";
                   while(rs.next()){
                       tabla += "<tr>"
                               + "<td>" + rs.getString("idformulario") + "</td>"
                               + "<td>" + rs.getString("nombre_formulario") + "</td>"
                               + "<td>" + rs.getString("codigo_formulario") + "</td>"
                               + "<td>" + rs.getString("nombre_menu") + "</td>"
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
     public static boolean modificar(Formularios formulario){
         boolean valor = false;
         if(Conexion.conectar()){
             String sql = "update formularios set nombre_formulario= '" + formulario.getNombre_formulario() + "', codigo_formulario= '" + formulario.getCodigo_formulario() + "',  idmenu= '" + formulario.getMenu().getIdmenu() + "'"
                     + "where idformulario=" + formulario.getIdformulario();
             try {
                 Conexion.getSt().executeUpdate(sql);
                 valor = true;
             } catch (SQLException ex) {
                 System.err.println("Error: " + ex);
             }
         }
         return valor;
     }
      public static boolean eliminar(Formularios formulario){
         boolean valor = false;
         if(Conexion.conectar()){
             String sql = "delete from formularios where idformulario= " + formulario.getIdformulario();
  
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
