/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.Controladores;

import electrohouse.modelos.Cajas;
import electrohouse.utiles.Conexion;
import electrohouse.utiles.Utiles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author Administrator
 */
public class CajasControlador {

    public static Cajas buscarId(int id) {
        Cajas cajas = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from cajas "
                        //+ "left join usuarios u on c.idusuario=u.idusuario "
                        // + "left join tipospagos t on p.id_tipopago=t.id_tipopago "
                        + "where idcaja=?";
                System.out.println(sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        cajas = new Cajas();
                        cajas.setIdcaja(rs.getInt("idcaja"));
                        cajas.setFecha_apertura(rs.getDate("fecha_apertura"));
                        cajas.setMonto_inicial(rs.getInt("monto_inicial"));
                        cajas.setEstado_caja(rs.getString("estado_caja"));

                    }

                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return cajas;
    }
    
    
        /*public static Cajas buscarIdcaja(Cajas cajas) {
        if (Conexion.conectar()){
            String sql = "select * from cajas where idcaja='"+cajas.getIdcaja()+"'";
            System.out.println("sql "+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    cajas = new Cajas();
                        cajas.setIdcaja(rs.getInt("idcaja"));
                        cajas.setFecha_apertura(rs.getDate("fecha_apertura"));
                        cajas.setMonto_inicial(rs.getInt("monto_inicial"));
                        cajas.setEstado_caja(rs.getString("estado_caja"));
                } 
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return cajas;
    }*/
    
    
    public static Cajas buscarIdestado(int usuario) throws SQLException {
        Cajas cajas = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from cajas c "
                        + "left join usuarios u on c.idusuario=u.idusuario "
                        // + "left join tipospagos t on p.id_tipopago=t.id_tipopago "
                        + "where estado_caja='ABIERTO' and c.idusuario=?";
                System.out.println(sql+usuario);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                  
                    ps.setInt(1, usuario);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        cajas = new Cajas();
                        cajas.setIdcaja(rs.getInt("idcaja"));
                        cajas.setFecha_apertura(rs.getDate("fecha_apertura"));
                        cajas.setMonto_inicial(rs.getInt("monto_inicial"));
                        cajas.setEstado_caja(rs.getString("estado_caja"));

                    }

                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return cajas;
    }

    public static String buscarNombre(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from cajas c "
                        + "left join usuarios u on c.idusuario=u.idusuario "
                        //   + "left join tipospagos t on t.id_tipopago=p.id_tipopago "
                        + "where upper(estado_caja) like '%"
                        //+ nombre.toUpperCase()
                        + "%' "
                        + "order by idcaja "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("-->cajanombre " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("idcaja") + "</td>"
                                + "<td>" + rs.getString("fecha_apertura") + "</td>"
                                + "<td>" + rs.getString("monto_inicial") + "</td>"
                                + "<td>" + rs.getString("estado_caja") + "</td>"
                                + "<td>" + rs.getString("idusuario") + "</td>"
                                + "<td>" + rs.getString("nombre_usuario") + "</td>"
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

    public static boolean agregar(Cajas caja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            int v1 = caja.getUsuario().getIdusuario();

            String sql = "insert into cajas (fecha_apertura"
                    + ", monto_inicial, estado_caja, idusuario)"
                    + "values('"
                    + caja.getFecha_apertura() + "','"
                    + caja.getMonto_inicial() + "','"
                    + caja.getEstado_caja() + "','"
                    
                    + v1 + "')";

            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int idcaja = keyset.getInt(1);
                    caja.setIdcaja(idcaja);

                    Conexion.getConn().setAutoCommit(false);
                }
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }

            Conexion.cerrar();
        }

        return valor;
    }

    public static boolean modificar(Cajas caja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update cajas set fecha_apertura='" + caja.getFecha_apertura() + "', "
                    + "monto_inicial='" + caja.getMonto_inicial() + "' "
                    
                    //+ "estado_caja=" + caja.getEstado_caja() + ""
                    //+ "idusuario=" + caja.getUsuario().getIdusuario() + ""
                    + "where idcaja=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                //ps.setInt(1, caja.getUsuario().getIdusuario());
                ps.setInt(1, caja.getIdcaja());
                ps.executeUpdate();
                ps.close();
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

    public static boolean eliminar(Cajas caja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from cajas where idcaja=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, caja.getIdcaja());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
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

    public static boolean modificarestado(Cajas caja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update cajas set estado_caja='CERRADO' "
                    + " where idcaja=" + caja.getIdcaja();
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
}
