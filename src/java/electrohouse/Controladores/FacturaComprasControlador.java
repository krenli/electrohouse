/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.Controladores;


import electrohouse.modelos.FacturaCompras;
import electrohouse.modelos.Proveedores;
import electrohouse.modelos.Tipo_facturas;
import electrohouse.utiles.Conexion;
import electrohouse.utiles.Utiles;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class FacturaComprasControlador {

    public static FacturaCompras buscarId(int id) {
        FacturaCompras facturacompra = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from factura_compras fc "
                        + "left join proveedores p on fc.idproveedor=p.idproveedor "
                        + "left join tipo_facturas tf on fc.idtipo_factura=tf.idtipo_factura "
                        + "where idfactura_compra=?";
                System.out.println("sql ::"+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        facturacompra = new FacturaCompras();
                        facturacompra.setIdfactura_compra(rs.getInt("idfactura_compra"));
                        Proveedores proveedor = new Proveedores();
                        proveedor.setIdproveedor(rs.getInt("idproveedor"));
                        proveedor.setNombre_proveedor(rs.getString("nombre_proveedor"));
                        proveedor.setRuc_proveedor(rs.getString("ruc_proveedor"));
                        facturacompra.setProveedor(proveedor);
                        facturacompra.setFecha_factura_compra(rs.getDate("fecha_factura_compra"));
                        Tipo_facturas tipofactura = new Tipo_facturas();
                        tipofactura.setIdtipo_factura(rs.getInt("idtipo_factura"));
                        tipofactura.setNombre_tipo_factura(rs.getString("nombre_tipo_factura"));
                        facturacompra.setTipofactura(tipofactura);
                        
                        
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return facturacompra;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from factura_compras fc "
                        + "left join proveedores p on p.idproveedor=fc.idproveedor "
                        + "where upper(nombre_proveedor) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by idfactura_compra "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("idfactura_compra") + "</td>"
                                + "<td>" + rs.getString("idproveedor") + "</td>"
                                + "<td>" + rs.getString("nombre_proveedor") + "</td>"
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

    public static boolean agregar(FacturaCompras facturacompra) {
        boolean valor = false;
        if (Conexion.conectar()) {
            int v1 = facturacompra.getProveedor().getIdproveedor();
            Date v2 = facturacompra.getFecha_factura_compra();
            int v3 = facturacompra.getTipofactura().getIdtipo_factura();
            String sql = "insert into factura_compras(idproveedor, fecha_factura_compra, idtipo_factura) "
                    + "values('" + v1 + "','" + v2 + "','" + v3 + "')";
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int idfactura_compra = keyset.getInt(1);
                    facturacompra.setIdfactura_compra(idfactura_compra);
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

    public static boolean modificar(FacturaCompras facturacompra) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update factura_compras set idproveedor=?,"
                    + "idtipo_factura=? "
                    + "where idfactura_compra=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setInt(1, facturacompra.getProveedor().getIdproveedor());
                ps.setInt(2, facturacompra.getTipofactura().getIdtipo_factura());
                ps.setInt(3, facturacompra.getIdfactura_compra());
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

    public static boolean eliminar(FacturaCompras facturacompra  ) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from factura_compras where idfactura_compra=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, facturacompra.getIdfactura_compra());
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
}