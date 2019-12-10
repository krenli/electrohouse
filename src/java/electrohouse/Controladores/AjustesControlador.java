/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.Controladores;

import electrohouse.modelos.Ajustes;
import electrohouse.utiles.Conexion;
import electrohouse.utiles.Utiles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Administrator
 */
public class AjustesControlador {

    public static Ajustes buscarId(int id) throws SQLException {
        Ajustes ajustes = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from ajustes_stock "
                        + "where idajuste_stock=?";
                
                /*String sql = "select * from ajustes_stock c "
                        + "left join usuarios u on u.id_usuario=c.id_usuario "
                        + "where id_ajuste_stock=?";*/

                System.out.println(sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        ajustes = new Ajustes();
                        ajustes.setIdajuste_stock(rs.getInt("idajuste_stock"));
                        ajustes.setFecha_ajuste_stock(rs.getDate("fecha_ajuste_stock"));
                        ajustes.setMotivo_ajuste_stock(rs.getString("motivo_ajuste_stock"));
                    } 
                    ps.close();
                }
            } catch (SQLException ex) {
                //System.out.println("--> " + ex.getLocalizedMessage());
                Logger.getLogger(AjustesControlador.class.getName()).log(Level.SEVERE, null, ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return ajustes;
    }

    public static String buscarNombre(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from ajustes_stock "
                        + "where upper(motivo_ajuste_stock) like '%"
                        + nombre
                        + "%' "
                        + "order by idajuste_stock "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                
                /*String sql = "select * from ajustes c "
                        + "left join usuarios p on c.id_usuario=p.id_usuario "
                        + "where upper(nombre_usuario) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_ajuste "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;*/
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("idajuste_stock") + "</td>"
                                + "<td>" + rs.getDate("fecha_ajuste_stock") + "</td>"
                                + "<td>" + rs.getString("motivo_ajuste_stock") + "</td>"
                                //+ "<td>" + rs.getString("idusuario") + "</td>"
                                //+ "<td>" + rs.getString("nombre_usuario") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=5>No existen registros ...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean agregar(Ajustes ajuste) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into ajustes_stock(fecha_ajuste_stock, motivo_ajuste_stock, idusuario) "
                    + "values('" + ajuste.getFecha_ajuste_stock() + "','"
                    + ajuste.getMotivo_ajuste_stock() + "','"
                    + ajuste.getUsuario().getIdusuario() + "')";
            
            /*String sql = "insert into ajustes(idusuario,fecha_ajuste,motivo_ajuste) "
                    + "values('" + ajuste.getUsuario().getIdusuario() + "','"
                    + ajuste.getFecha_ajuste() + "','"
                    + ajuste.getMotivo_ajuste() + "')";*/
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int idajuste_stock = keyset.getInt(1);
                    ajuste.setIdajuste_stock(idajuste_stock);
                    Conexion.getConn().setAutoCommit(false);
                }
                valor = true;
            } catch (SQLException ex) {
                Logger.getLogger(AjustesControlador.class.getName()).log(Level.SEVERE, null, ex.getLocalizedMessage());
                //System.out.println("--> " + ex.getLocalizedMessage());
            }
            Conexion.cerrar();
        }

        return valor;
    }

    public static boolean modificar(Ajustes ajuste) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update ajustes_stock set motivo_ajuste_stock='" + ajuste.getMotivo_ajuste_stock() + "'"
                    + " where idajuste_stock=" + ajuste.getIdajuste_stock();
            
            /*String sql = "update ajustes set id_usuario=" + ajuste.getUsuario().getId_usuario() + ","
                    + "fecha_ajuste='" + ajuste.getFecha_ajuste() + "'"
                    + " where id_ajuste=" + ajuste.getId_ajuste();*/
            try {

                Conexion.getSt().executeUpdate(sql);

                Conexion.getConn().setAutoCommit(false);
                System.out.println("--> Grabado");
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminar(Ajustes ajuste) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from ajustes_stock where idajuste_stock=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, ajuste.getIdajuste_stock());
                System.out.println("sqlAjuste "+ ps);
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

//    public static boolean modificarestado(Ajustes ajuste) throws SQLException {
//        boolean valor = false;
//        if (Conexion.conectar()) {
//            String sql = "update ajustes set estado_ajuste='ANULADO'  "
//                    + " where idajuste=" + ajuste.getIdajuste();
//            try {
//
//                Conexion.getSt().executeUpdate(sql);
//
//                Conexion.getConn().commit();
//                System.out.println("--> Grabado");
//                valor = true;
//            } catch (SQLException ex) {
//                System.out.println("--> " + ex.getLocalizedMessage());
//                try {
//                    Conexion.getConn().rollback();
//                } catch (SQLException ex1) {
//                    System.out.println("--> " + ex1.getLocalizedMessage());
//                }
//            }
//        }
//        Conexion.cerrar();
//        return valor;
//    }
}
