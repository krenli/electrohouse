/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.Controladores;

import electrohouse.modelos.Clientes;
import electrohouse.modelos.FacturaVentas;
import electrohouse.modelos.Timbrados;
import electrohouse.modelos.Tipo_facturas;
import electrohouse.modelos.Tipos_personales;
import electrohouse.utiles.Conexion;
import electrohouse.utiles.Utiles;
import java.math.BigDecimal;

//import modelos.Tipo_facturas;

import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FacturaVentasControlador {

    public static FacturaVentas buscarId(int id) {
        FacturaVentas facturaventa = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from factura_ventas fv "
                        + "left join clientes c on fv.idcliente=c.idcliente "
                        + "left join tipo_facturas tf on fv.idtipo_factura=tf.idtipo_factura "
                        + "left join timbrados t on fv.idtimbrado=t.idtimbrado "
                        + "where estado_venta != 'COBRADO' and idfactura_venta=?";
                System.out.println("sql ::" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        facturaventa = new FacturaVentas();
                        facturaventa.setIdfactura_venta(rs.getInt("idfactura_venta"));
                        /*facturaventa.setSubtotal_5(rs.getInt("subtotal_5"));
                        facturaventa.setSubtotal_10(rs.getInt("subtotal_10"));
                        facturaventa.setSubtotal_exenta(rs.getInt("subtotal_exenta"));*/
                        facturaventa.setCantidad_cuotas(rs.getInt("cantidad_cuotas"));
                        facturaventa.setNumero_factura_venta(rs.getInt("numero_factura_venta"));
                        Clientes cliente = new Clientes();
                        cliente.setIdcliente(rs.getInt("idcliente"));
                        cliente.setNombre_cliente(rs.getString("nombre_cliente"));
                        cliente.setRuc_cliente(rs.getString("ruc_cliente"));
                        facturaventa.setCliente(cliente);
                        facturaventa.setFecha_factura_venta(rs.getDate("fecha_factura_venta"));
                        Tipo_facturas tipofactura = new Tipo_facturas();
                        tipofactura.setIdtipo_factura(rs.getInt("idtipo_factura"));
                        tipofactura.setNombre_tipo_factura(rs.getString("nombre_tipo_factura"));
                        facturaventa.setTipofactura(tipofactura);
                        
                        Tipos_personales tipo_personal = new Tipos_personales();
                        tipo_personal.setIdtipo_personal(rs.getInt("idtipo_personal"));
                  


                        Timbrados timbrado = new Timbrados();
                        timbrado.setIdtimbrado(rs.getInt("idtimbrado"));
                        timbrado.setTipo_personal(tipo_personal);
                        facturaventa.setTimbrado(timbrado);

                    } else {
                        try {

                            String sqli = "SELECT COUNT(*) AS Ultimo, COUNT(numero_factura_venta) FROM factura_ventas ";

                            System.out.println("sss" + sqli);
                            try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {
                                int num = 0;
                                ResultSet rsi = psi.executeQuery();
                                if (rsi.next()) {
                                    facturaventa = new FacturaVentas();
                                    num = rsi.getInt("Ultimo");
                                    facturaventa.setNumero_factura_venta(num + 1);

                                    facturaventa.setIdfactura_venta(0);
                                    //facturaventa.setEstado_venta("ACTIVO");
                                    java.sql.Date fecha_venta = new java.sql.Date(new java.util.Date().getTime());
                                    facturaventa.setFecha_factura_venta(fecha_venta);

                                    Clientes cliente = new Clientes();
                                    cliente.setIdcliente(0);
                                    cliente.setNombre_cliente("");
                                    cliente.setRuc_cliente("");
                                    facturaventa.setCliente(cliente);

                                    Tipo_facturas pago = new Tipo_facturas();
                                    pago.setIdtipo_factura(0);
                                    pago.setNombre_tipo_factura("");
                                    facturaventa.setTipofactura(pago);
                                    
                                    Tipos_personales tipo_personal = new Tipos_personales();
                                    tipo_personal.setIdtipo_personal(0);
                                  

                                 

                                    Timbrados timbrado = new Timbrados();
                                    timbrado.setIdtimbrado(0);
                                    timbrado.setTipo_personal(tipo_personal);
                                    facturaventa.setTimbrado(timbrado);
                                } else {
                                    num = 1;
                                    facturaventa = new FacturaVentas();
                                    
                                    facturaventa.setNumero_factura_venta(num);

                                    facturaventa.setIdfactura_venta(0);
                                    java.sql.Date fecha_venta = new java.sql.Date(new java.util.Date().getTime());
                                    facturaventa.setFecha_factura_venta(fecha_venta);

                                    Clientes cliente = new Clientes();
                                    cliente.setIdcliente(0);
                                    cliente.setNombre_cliente("");
                                    cliente.setRuc_cliente("");
                                    facturaventa.setCliente(cliente);

                                    Tipo_facturas pago = new Tipo_facturas();
                                    pago.setIdtipo_factura(0);
                                    pago.setNombre_tipo_factura("");
                                    facturaventa.setTipofactura(pago);
                                    
                                    Tipos_personales tipo_personal = new Tipos_personales();
                                    tipo_personal.setIdtipo_personal(0);
                                   

                                    Timbrados timbrado = new Timbrados();
                                    timbrado.setIdtimbrado(0);
                                    timbrado.setTipo_personal(tipo_personal);
                                    facturaventa.setTimbrado(timbrado);
                                }

                                psi.close();
                            }
                        } catch (SQLException ex) {
                            System.out.println("--> " + ex.getLocalizedMessage());

                        }
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return facturaventa;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from factura_ventas fv "
                        + "left join clientes c on c.idcliente=fv.idcliente "
                        + "where upper(nombre_cliente) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "and fv.estado_venta!='COBRADO' order by idfactura_venta "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("idfactura_venta") + "</td>"
                                + "<td>" + rs.getString("idcliente") + "</td>"
                                + "<td>" + rs.getString("nombre_cliente") + "</td>"
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
    
    public static String buscarNombreContado(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from factura_ventas fv "
                        + "left join clientes c on c.idcliente=fv.idcliente "
                        + "left join tipo_facturas tf on fv.idtipo_factura=tf.idtipo_factura "
                        + "where tf.nombre_tipo_factura = 'CONTADO' and estado_venta='PENDIENTE' and upper(ruc_cliente) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by idfactura_venta "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("idfactura_venta") + "</td>"
                                + "<td>" + rs.getString("idcliente") + "</td>"
                                + "<td>" + rs.getString("nombre_cliente") + "</td>"
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

    public static boolean agregar(FacturaVentas facturaventa) {
        boolean valor = false;
        if (Conexion.conectar()) {
            int v1 = facturaventa.getCliente().getIdcliente();
            Date v2 = facturaventa.getFecha_factura_venta();
            int v3 = facturaventa.getTipofactura().getIdtipo_factura();
            /*int v4 = facturaventa.getSubtotal_5();
            int v5 = facturaventa.getSubtotal_10();
            int v6 = facturaventa.getSubtotal_exenta();*/
            int v4 = facturaventa.getCantidad_cuotas();
            //int v5 = facturaventa.getPedido().getId_pedido();
            int v5 = facturaventa.getNumero_factura_venta();
            int v7 = facturaventa.getTimbrado().getIdtimbrado();
            int v8 = facturaventa.getUsuario().getIdusuario();
            String v6 = facturaventa.getEstado_venta();
            String sql = "insert into factura_ventas(idcliente, fecha_factura_venta, idtipo_factura, cantidad_cuotas, numero_factura_venta, estado_venta, idtimbrado, idusuario) "
                    + "values('" + v1 + "','" + v2 + "','" + v3 + "','" + v4 + "','" + v5 + "','" + v6 + "','" + v7 + "','" + v8 + "')";
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int idfactura_venta = keyset.getInt(1);
                    facturaventa.setIdfactura_venta(idfactura_venta);
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

    public static boolean modificar(FacturaVentas facturaventa) {
        boolean valor = false;
        if (Conexion.conectar()) {
            int v1 = facturaventa.getCliente().getIdcliente();
            int v2 = facturaventa.getTipofactura().getIdtipo_factura();
            int v3 = facturaventa.getIdfactura_venta();
            int v4 = facturaventa.getCantidad_cuotas();
            String sql = "update factura_ventas set idcliente='" + v1 + "',"
                    + "idtipo_factura='" + v2 + "',"
                    + "cantidad_cuotas='" + v4 + "' "
                    + "where idfactura_venta='" + v3 + "'";
            System.out.println("--> sql " + sql);
            try {
                Conexion.getSt().executeUpdate(sql);
                //Conexion.getConn().commit();
                System.out.println("--> Grabado y modificado");
                valor = true;
            } catch (SQLException ex) {
                //System.out.println("--> " + ex.getLocalizedMessage());
                Logger.getLogger(FacturaVentasControlador.class.getName()).log(Level.SEVERE, null, ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    //System.out.println("--> " + ex1.getLocalizedMessage());
                    Logger.getLogger(FacturaVentasControlador.class.getName()).log(Level.SEVERE, null, ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    /*public static boolean modificar(FacturaVentas facturaventa) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update factura_ventas set id_cliente=?,"
                    + "id_tipo_factura=?,"
                    + "cantidad_cuotas=? "
                    + "where id_factura_venta=?";
            
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setInt(1, facturaventa.getCliente().getId_cliente());
                ps.setInt(2, facturaventa.getTipofactura().getId_tipo_factura());
                ps.setInt(3, facturaventa.getCantidad_cuotas());
                ps.setInt(4, facturaventa.getId_factura_venta());
                ps.executeUpdate();
                System.out.println("--> cerro "+ sql);
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> Grabado y modificado");
                valor = true;
            } catch (SQLException ex) {
                //System.out.println("--> " + ex.getLocalizedMessage());
                Logger.getLogger(FacturaVentasControlador.class.getName()).log(Level.SEVERE, null, ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    //System.out.println("--> " + ex1.getLocalizedMessage());
                    Logger.getLogger(FacturaVentasControlador.class.getName()).log(Level.SEVERE, null, ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }*/
    public static boolean Anular(FacturaVentas facturaventa) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update factura_ventas set estado_venta = 'ANULADO' where idfactura_venta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, facturaventa.getIdfactura_venta());
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

    /*public static boolean subtotaliva(FacturaVentas facturaventa) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update factura_ventas set subtotal_5=subtotal_5+?,"
                    + "subtotal_10=subtotal_10+?,"
                    + "subtotal_exenta=subtotal_exenta+? "
                    + "where id_factura_venta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setInt(1, facturaventa.getSubtotal_5());
                ps.setInt(2, facturaventa.getSubtotal_10());
                ps.setInt(3, facturaventa.getSubtotal_exenta());
                ps.setInt(4, facturaventa.getId_factura_venta());
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
    }*/
    /*public static boolean agregarFPedidos(FacturaVentas facturaventa) {
        boolean valor = false;
        if (Conexion.conectar()) {
            int v1 = facturaventa.getCliente().getId_cliente();
            Date v2 = facturaventa.getFecha_factura_venta();
            int v3 = facturaventa.getTipofactura().getId_tipo_factura();
            /*int v4 = facturaventa.getSubtotal_5();
            int v5 = facturaventa.getSubtotal_10();
            int v6 = facturaventa.getSubtotal_exenta();
            int v4 = facturaventa.getCantidad_cuotas();
            int v5 = facturaventa.getPedido().getId_pedido();
            String sql = "insert into factura_ventas(id_cliente, fecha_factura_venta, id_tipo_factura, cantidad_cuotas, id_pedido) "
                    + "values('" + v1 + "','" + v2 + "','" + v3 + "','" + v4 + "','" + v5 + "')";
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_factura_venta = keyset.getInt(1);
                    facturaventa.setId_factura_venta(id_factura_venta);
                    Conexion.getConn().setAutoCommit(false);
                }
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
            Conexion.cerrar();
        }

        return valor;
    }*/

    public static FacturaVentas buscarTotalfactura(int id) {
        FacturaVentas facturaventa = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select fdv.idfactura_venta, tf.nombre_tipo_factura, fv.estado_venta, sum(cantidad_venta * precio_producto) as TotalaPagar from factura_detalle_ventas fdv left join factura_ventas fv on fv.idfactura_venta=fdv.idfactura_venta \n"
                        + "left join tipo_facturas tf on fv.idtipo_factura=tf.idtipo_factura\n"
                        + "left join productos a on a.idproducto=fdv.idproducto where fdv.idfactura_venta=? and fv.estado_venta= 'PENDIENTE' and tf.nombre_tipo_factura= 'CONTADO' group by fdv.idfactura_venta, tf.nombre_tipo_factura, fv.estado_venta";
                System.out.println("sql ::" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        facturaventa = new FacturaVentas();
                        facturaventa.setIdfactura_venta(rs.getInt("idfactura_venta"));
                        facturaventa.setTotal(rs.getInt("totalapagar"));
                        //facturaventa.setNumero_factura_venta(rs.getInt("numero_factura_venta"));

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return facturaventa;
    }
    
    public static FacturaVentas buscarTotalf(int id) {
        FacturaVentas facturaventa = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select fdv.idfactura_venta, tf.nombre_tipo_factura, fv.estado_venta, sum(cantidad_venta * precio_producto) as TotalaPagar from factura_detalle_ventas fdv left join factura_ventas fv on fv.idfactura_venta=fdv.idfactura_venta \n"
                        + "left join tipo_facturas tf on fv.idtipo_factura=tf.idtipo_factura\n"
                        + "left join productos a on a.idproducto=fdv.idproducto where fdv.idfactura_venta=? and fv.estado_venta= 'PENDIENTE' and tf.nombre_tipo_factura= 'CREDITO' group by fdv.idfactura_venta, tf.nombre_tipo_factura, fv.estado_venta";
                System.out.println("sql ::" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        facturaventa = new FacturaVentas();
                        facturaventa.setIdfactura_venta(rs.getInt("idfactura_venta"));
                        facturaventa.setTotal(rs.getInt("totalapagar"));
                        //facturaventa.setNumero_factura_venta(rs.getInt("numero_factura_venta"));

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return facturaventa;
    }

    public static boolean modificarestadocobro(FacturaVentas facturaventa) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "select * from factura_ventas fv, tipo_facturas tf where fv.idtipo_factura=tf.idtipo_factura and tf.nombre_tipo_factura= 'CONTADO' and estado_venta = 'PENDIENTE'";
            System.out.println("idfv-> " + sql);
            try {
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        String sql2 = "update factura_ventas set estado_venta='COBRADO'  "
                                + " where idfactura_venta=" + facturaventa.getIdfactura_venta();
                        System.out.println("idfv-> " + sql2);
                        try {

                            Conexion.getSt().executeUpdate(sql2);

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
                    } else {
                        String sql3 = "select * from cuentas c, factura_ventas fv where c.idfactura_venta = fv.idfactura_venta and c.estado_cuenta= 'ACTIVO'";
                        try {
                            try (PreparedStatement ps1 = Conexion.getConn().prepareStatement(sql3)) {
                                ResultSet rs1 = ps1.executeQuery();
                                if (!rs1.next()) {
                                    String sql4 = "update factura_ventas set estado_venta='COBRADO'  "
                                            + " where idfactura_venta=" + facturaventa.getIdfactura_venta();
                                    System.out.println("idfv-> " + sql4);
                                    try {

                                        Conexion.getSt().executeUpdate(sql4);

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
                                ps1.close();
                            }
                        } catch (SQLException ex) {
                            System.out.println("Error: " + ex);
                        }

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }

    /*public static boolean modificarestadocobro(FacturaVentas facturaventa) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "select * from cuentas c, factura_ventas fv where c.id_factura_venta = fv.id_factura_venta and c.estado_cuenta= 'ACTIVO'";
            System.out.println("idfv-> " + sql);
            try {
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        String sql2 = "update factura_ventas set estado_venta='COBRADO'  "
                                + " where id_factura_venta=" + facturaventa.getId_factura_venta();
                        System.out.println("idfv-> " + sql2);
                        try {

                            Conexion.getSt().executeUpdate(sql2);

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
                    } else {
                        String sql3 = "select * from factura_ventas fv, tipo_facturas tf where fv.id_tipo_factura=tf.id_tipo_factura and tf.nombre_tipo_factura= 'CONTADO' and estado_venta = 'PENDIENTE'";
                        try {
                            try (PreparedStatement ps1 = Conexion.getConn().prepareStatement(sql3)) {
                                ResultSet rs1 = ps1.executeQuery();
                                if (rs1.next()) {
                                    String sql4 = "update factura_ventas set estado_venta='COBRADO'  "
                                            + " where id_factura_venta=" + facturaventa.getId_factura_venta();
                                    System.out.println("idfv-> " + sql4);
                                    try {

                                        Conexion.getSt().executeUpdate(sql4);

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
                                ps1.close();
                            }
                        } catch (SQLException ex) {
                            System.out.println("Error: " + ex);
                        }

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }*/
}
