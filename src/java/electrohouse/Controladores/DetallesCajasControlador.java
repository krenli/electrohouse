package electrohouse.Controladores;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import electrohouse.modelos.Cajas;
import electrohouse.modelos.DetallesCajas;
import electrohouse.modelos.FacturaVentas;
import electrohouse.modelos.Formas_pagos;
import electrohouse.utiles.Conexion;
import electrohouse.utiles.Utiles;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;


//import javawebts.modelos.Ventas;
//import javawebts.modelos.DetallesCajas;
//import javawebts.modelos.Cajas;
//import javawebts.utiles.Conexion;
//import javawebts.utiles.Utiles;
/**
 *
 * @author Administrator
 */
public class DetallesCajasControlador {

    public static DetallesCajas buscarId(int id) throws SQLException {
        DetallesCajas detallecaja = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallescajas dp "
                        + "left join cajas p on p.idcaja=dp.idcaja "
                        + "left join factura_ventas a on a.idfactura_venta=dp.idfactura_venta "
                        + "left join formas_pagos t on t.idforma_pago=dp.idforma_pago "
                        + "where dp.iddetallecaja=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    System.out.println("detallecj--> " + sql+id);
                    if (rs.next()) {
                        
                        detallecaja = new DetallesCajas();
                        detallecaja.setIddetallecaja(rs.getInt("iddetallecaja"));
                        detallecaja.setImporte(rs.getInt("importe"));
                        //detallecaja.setVuelto(rs.getInt("vuelto"));

                        FacturaVentas venta = new FacturaVentas();
                        venta.setIdfactura_venta(rs.getInt("idfactura_venta"));
                        //venta.setNumero_factura_venta(rs.getInt("numero_factura_venta"));
                        detallecaja.setFacturaventa(venta);

                        Cajas caja = new Cajas();
                        caja.setIdcaja(rs.getInt("idcaja"));
                        detallecaja.setCaja(caja);

                        Formas_pagos pago = new Formas_pagos();
                        pago.setIdforma_pago(rs.getInt("idforma_pago"));
                        //pago.setNombre_forma_pago(rs.getString("nombre_forma_pago"));
                        detallecaja.setPago(pago);

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detallecaja;
    }

    public static String buscarIdCaja(int id) throws SQLException {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallescajas dp "
                        + "left join cajas p on p.idcaja=dp.idcaja "
                        + "left join factura_ventas v on v.idfactura_venta=dp.idfactura_venta "
                        + "left join formas_pagos a on a.idforma_pago=dp.idforma_pago "
                        + "where dp.idcaja=" + id + " "
                        + "order by iddetallecaja";
                System.out.println("dca--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat("#,###");
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    while (rs.next()) {
                        BigDecimal cantidad = rs.getBigDecimal("importe");
                        total = total.add(cantidad);
                        // System.out.println("total"+total);
                        tabla += "<tr>"
                              //  + "<td>" + rs.getString("iddetallecaja") + "</td>"
                                + "<td>" + rs.getString("idfactura_venta") + "</td>"
                                + "<td>" + rs.getString("numero_factura_venta") + "</td>"
                                //+ "<td>" + rs.getString("idforma_pago") + "</td>"
                                + "<td>" + rs.getString("nombre_forma_pago") + "</td>"
                               // + "<td>" + rs.getString("total") + "</td>"
                                + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                /*+ "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("iddetallecaja") + ")'"
                                + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"*/
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
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

    public static String buscarNombre(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallescajas dp "
                        + "left join cajas p on p.idcaja=dp.idcaja "
                        + "left join factura_ventas a on a.idfactura_venta=dp.idfactura_venta "
                        + "where upper(a.nombre_venta) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by iddetallecaja "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("iddetallecaja") + "</td>"
                                + "<td>" + rs.getString("idcaja") + "</td>"
                                + "<td>" + rs.getString("idfactura_venta") + "</td>"
                                + "<td>" + rs.getString("numero_factura_venta") + "</td>"
                                + "<td>" + rs.getInt("total") + "</td>"
                                + "<td>" + rs.getInt("iva_venta") + "</td>"
                                + "<td>" + rs.getInt("importe") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
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

    public static boolean agregar(DetallesCajas detallecaja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into detallescajas "
                    + "(idfactura_venta,idforma_pago,idcaja,importe) "
                    + "values (?,?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                
                ps.setInt(1, detallecaja.getFacturaventa().getIdfactura_venta());
                ps.setInt(2, detallecaja.getPago().getIdforma_pago());
                ps.setInt(3, detallecaja.getCaja().getIdcaja());
                ps.setInt(4, detallecaja.getImporte());
                //ps.setInt(5, detallecaja.getVuelto());

                ps.executeUpdate();
                System.out.println("agragercajadetalle "+sql);
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

    public static boolean modificar(DetallesCajas detallecaja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detallescajas set "
                    + "idcaja=?,"
                    + "idfactura_venta=?,"
                    + "cantidad_ventacaja=? "
                    + "where iddetallecaja=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallecaja.getCaja().getIdcaja());
                ps.setInt(2, detallecaja.getFacturaventa().getIdfactura_venta());
                //  ps.setInt(3, detallecaja.getCantidad_ventacaja());
                ps.setInt(4, detallecaja.getIddetallecaja());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
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

    public static boolean eliminar(DetallesCajas detallecaja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallescajas where iddetallecaja=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallecaja.getIddetallecaja());
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

    public static boolean eliminarc(DetallesCajas detallecaja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallescajas dp "
                        + "left join cajas p on p.idcaja=dp.idcaja "
                        + "left join ventas a on a.idfactura_venta=dp.idfactura_venta "
                        + " where p.idcaja= " + detallecaja.getCaja().getIdcaja();
                System.out.println("detalle " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {

                        String sqli = "update stock set cantidad_stock= cantidad_stock - " + rs.getInt("cantidad_ventacaja") + " where idfactura_venta=" + rs.getInt("idfactura_venta") + "";

                        System.out.println(" descontar stock" + sqli);
                        try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {

                            psi.executeUpdate();
                            psi.close();
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

                    ps.close();
                    valor = true;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminarVentaCaja(Cajas caja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallescajas where idcaja=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, caja.getIdcaja());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> " + caja.getIdcaja());
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
