/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.Controladores;

import electrohouse.modelos.FacturaDetalle;
import electrohouse.modelos.FacturaVentas;
import electrohouse.modelos.Productos;
import electrohouse.utiles.Conexion;
import electrohouse.utiles.Utiles;
import java.math.BigDecimal;
//import modelos.Tipo_facturas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FacturaDetalleVentasControlador {

    public static FacturaDetalle buscarId(int id) {
        FacturaDetalle facturadetalle = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from factura_detalle_ventas fdv "
                        + "left join factura_ventas fv on fv.idfactura_venta=fdv.idfactura_venta "
                        + "left join productos a on a.idproducto=fdv.idproducto "
                        + "where fdv.idfactura_detalle_venta=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        facturadetalle = new FacturaDetalle();
                        facturadetalle.setIdfactura_detalle_venta(rs.getInt("idfactura_detalle_venta"));
                        facturadetalle.setCantidad_venta(rs.getInt("cantidad_venta"));
                        //facturadetalle.setSubtotal_venta(rs.getInt("subtotal_venta"));

                        Productos producto = new Productos();
                        producto.setIdproducto(rs.getInt("idproducto"));
                        producto.setNombre_producto(rs.getString("nombre_producto"));
                        producto.setCosto_producto(rs.getInt("costo_producto"));
                        producto.setPrecio_producto(rs.getInt("precio_producto"));

                        producto.setIva_producto(rs.getInt("iva_producto"));

                        facturadetalle.setProducto(producto);

                        FacturaVentas facturaventa = new FacturaVentas();
                        facturaventa.setIdfactura_venta(rs.getInt("idfactura_venta"));
                        facturadetalle.setFacturaventa(facturaventa);

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                //System.out.println("--> " + ex.getLocalizedMessage());
                System.out.println("aqui--> ");
                Logger.getLogger(FacturaDetalleVentasControlador.class.getName()).log(Level.SEVERE, null, ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return facturadetalle;
    }
    
    public static String totalp(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from factura_detalle_ventas fdv "
                        + "left join factura_ventas fv on fv.idfactura_venta=fdv.idfactura_venta "
                        + "left join productos a on a.idproducto=fdv.idproducto "
                        + //"left join ivas i on i.id_iva=a.id_iva "+
                        "where fdv.idfactura_venta=" + id + " "
                        + "order by idfactura_detalle_venta";
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat("#,###");
                    String tabla = "";
                    int total = 0;
                    int totale = 0;
                    int total5 = 0;
                    int total10 = 0;
                    int totalf = 0;
                    //BigDecimal total10 = BigDecimal.ZERO;
                    int iva = 0;
                    int totalexentas = 0;
                    int totaliva5 = 0;
                    int totaliva10 = 0;

                    int liqiva5 = 0;
                    int liqiva10 = 0;

                    // System.out.println("total"+total);
                    BigDecimal totalt = BigDecimal.ZERO;
                    BigDecimal total1 = BigDecimal.ZERO;
                    while (rs.next()) {
                        iva = rs.getInt("iva_producto");
                        int cantidad = rs.getInt("cantidad_venta");
                        int venta = rs.getInt("precio_producto");

                        if (iva == 0) {

                            totalexentas = venta * cantidad;
                            totaliva5 = 0;
                            totaliva10 = 0;
                            totale = totale + totalexentas;

                        } else if (iva == 5) {

                            totalexentas = 0;
                            totaliva10 = 0;

                            totaliva5 = venta * cantidad;
                            total5 = total5 + totaliva5;

                        } else {

                            totalexentas = 0;
                            totaliva5 = 0;
                            totaliva10 = venta * cantidad;
                            total10 = total10 + totaliva10;

                        }

                        liqiva5 = total5 / 21;
                        liqiva10 = total10 / 11;

                        total = liqiva5 + liqiva10;

                        totalf = total5 + totale + total10;

                        BigDecimal cantidad1 = rs.getBigDecimal("cantidad_venta");
                        BigDecimal precio = rs.getBigDecimal("precio_producto");
                        BigDecimal total_venta = precio.multiply(cantidad1);
                        totalt = totalt.add(cantidad1);
                        total1 = total1.add(total_venta);
                        // System.out.println("total"+total);
                        tabla += "<tr>"
                                //+ "<td>" + rs.getString("id_factura_detalle_venta") + "</td>"
                                + "<td>" + rs.getString("idproducto") + "</td>"
                                + "<td>" + rs.getString("nombre_producto") + "</td>"
                                + "<td class='centrado'>" + df.format(precio) + "</td>"
                                //+ "<td class='centrado'>" + rs.getString("precio_venta") + "</td>"

                                + "<td class='centrado'>" + rs.getInt("cantidad_venta") + "</td>"
                                + "<td class='centrado'>" + df.format(total_venta) + "</td>"
                                //+ "<td>" + df.format(totalexentas) + "</td>"
                                //+ "<td>" + df.format(totaliva5) + "</td>"
                                //+ "<td>" + df.format(totaliva10) + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("idfactura_detalle_venta") + ")'"
                                + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=5>No existen registros ...</td></tr>";
                    } else {
                        tabla += "<tr><td colspan=3>SUB-TOTAL</td><td class='centrado'>"
                                + "<td class='centrado'>" + df.format(total1) + "</td>";
                        //+ "<td class='centrado'>" + df.format(totale) + "</td>"
                        //+ "<td class='centrado'>" + df.format(total5) + "</td>"
                        //+ "<td class='centrado'>" + df.format(total10) + "</td></tr>";
                        //+ "<tr><td colspan=9><b>Lquidacion de IVA:</b>&nbsp;&nbsp;&nbsp; <b>(5%):</b> " + "" + df.format(liqiva5) + "  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   <b>(10%):</b>&nbsp;" + df.format(liqiva10) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b> TOTAL I.V.A:</b> &nbsp; " + df.format(total) + " </td> </tr>"
                        //+ "<tr><td colspan=9 ><b><P ALIGN=left>Total Grabada: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + df.format(totalf) + " Gs." + "</b></td></tr>";
                        tabla += "<tr><td colspan=5><b>Lquidacion de IVA:</b>&nbsp;&nbsp;&nbsp; <b>(5%):</b> " + "" + df.format(liqiva5) + "  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   <b>(10%):</b>&nbsp;" + df.format(liqiva10) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b> TOTAL I.V.A:</b> &nbsp; " + df.format(total) + " </td> </tr>";

                        tabla += "<tr><td colspan=5 ><b><P ALIGN=left>Total Grabada: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + df.format(totalf) + " Gs." + "</b></td></tr>";  //+ df.format(totald) +"</td></tr>";

                        String sql2 = "select sum(cantidad_venta * precio_producto) as TotalaPagar from factura_detalle_ventas fdv left join factura_ventas fv on fv.idfactura_venta=fdv.idfactura_venta left join productos a on a.idproducto=fdv.idproducto where fdv.idfactura_venta=" + id + "";
                        //String sql2 = "select sum(cantidad_venta * precio_venta) + " + total + " as TotalaPagar from factura_detalle_ventas fdv left join factura_ventas fv on fv.id_factura_venta=fdv.id_factura_venta left join articulos a on a.id_articulo=fdv.id_articulo where fdv.id_factura_venta="+ id + "";

                        try (PreparedStatement ps1 = Conexion.getConn().prepareStatement(sql2)) {

                            ResultSet rs1 = ps1.executeQuery();
                            if (rs1.next()) {
                                int total_pagar = rs1.getInt("TotalaPagar");
                                FacturaVentas factura = new FacturaVentas();
                                factura.setTotalP(rs.getInt(total_pagar));
                                tabla += "<tr ><td colspan=5  ><b><P ALIGN=left>Total a Pagar en Gs: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + df.format(total_pagar) + ".----" + "</td></tr>";
                            }
                            System.out.println("Total a Pagar " + sql2);
                            System.out.println(rs1.getString("TotalaPagar"));
                        } catch (SQLException ex) {
                            System.out.println("--> " + ex.getLocalizedMessage());

                        }
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                //System.out.println("--> " + ex.getLocalizedMessage());
                System.out.println("aqui--> ");
                Logger.getLogger(FacturaDetalleVentasControlador.class.getName()).log(Level.SEVERE, null, ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static String buscarIdFacturaVenta(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from factura_detalle_ventas fdv "
                        + "left join factura_ventas fv on fv.idfactura_venta=fdv.idfactura_venta "
                        + "left join productos a on a.idproducto=fdv.idproducto "
                        + //"left join ivas i on i.id_iva=a.id_iva "+
                        "where fdv.idfactura_venta=" + id + " "
                        + "order by idfactura_detalle_venta";
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat("#,###");
                    String tabla = "";
                    int total = 0;
                    int totale = 0;
                    int total5 = 0;
                    int total10 = 0;
                    int totalf = 0;
                    //BigDecimal total10 = BigDecimal.ZERO;
                    int iva = 0;
                    int totalexentas = 0;
                    int totaliva5 = 0;
                    int totaliva10 = 0;

                    int liqiva5 = 0;
                    int liqiva10 = 0;

                    // System.out.println("total"+total);
                    BigDecimal totalt = BigDecimal.ZERO;
                    BigDecimal total1 = BigDecimal.ZERO;
                    while (rs.next()) {
                        iva = rs.getInt("iva_producto");
                        int cantidad = rs.getInt("cantidad_venta");
                        int venta = rs.getInt("precio_producto");

                        if (iva == 0) {

                            totalexentas = venta * cantidad;
                            totaliva5 = 0;
                            totaliva10 = 0;
                            totale = totale + totalexentas;

                        } else if (iva == 5) {

                            totalexentas = 0;
                            totaliva10 = 0;

                            totaliva5 = venta * cantidad;
                            total5 = total5 + totaliva5;

                        } else {

                            totalexentas = 0;
                            totaliva5 = 0;
                            totaliva10 = venta * cantidad;
                            total10 = total10 + totaliva10;

                        }

                        liqiva5 = total5 / 21;
                        liqiva10 = total10 / 11;

                        total = liqiva5 + liqiva10;

                        totalf = total5 + totale + total10;

                        BigDecimal cantidad1 = rs.getBigDecimal("cantidad_venta");
                        BigDecimal precio = rs.getBigDecimal("precio_producto");
                        BigDecimal total_venta = precio.multiply(cantidad1);
                        totalt = totalt.add(cantidad1);
                        total1 = total1.add(total_venta);
                        // System.out.println("total"+total);
                        tabla += "<tr>"
                                //+ "<td>" + rs.getString("id_factura_detalle_venta") + "</td>"
                                + "<td>" + rs.getString("idproducto") + "</td>"
                                + "<td>" + rs.getString("nombre_producto") + "</td>"
                                + "<td class='centrado'>" + df.format(precio) + "</td>"
                                //+ "<td class='centrado'>" + rs.getString("precio_venta") + "</td>"

                                + "<td class='centrado'>" + rs.getInt("cantidad_venta") + "</td>"
                                + "<td class='centrado'>" + df.format(total_venta) + "</td>"
                                //+ "<td>" + df.format(totalexentas) + "</td>"
                                //+ "<td>" + df.format(totaliva5) + "</td>"
                                //+ "<td>" + df.format(totaliva10) + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("idfactura_detalle_venta") + ")'"
                                + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=5>No existen registros ...</td></tr>";
                    } else {
                        tabla += "<tr><td colspan=3>SUB-TOTAL</td><td class='centrado'>"
                                + "<td class='centrado'>" + df.format(total1) + "</td>";
                        //+ "<td class='centrado'>" + df.format(totale) + "</td>"
                        //+ "<td class='centrado'>" + df.format(total5) + "</td>"
                        //+ "<td class='centrado'>" + df.format(total10) + "</td></tr>";
                        //+ "<tr><td colspan=9><b>Lquidacion de IVA:</b>&nbsp;&nbsp;&nbsp; <b>(5%):</b> " + "" + df.format(liqiva5) + "  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   <b>(10%):</b>&nbsp;" + df.format(liqiva10) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b> TOTAL I.V.A:</b> &nbsp; " + df.format(total) + " </td> </tr>"
                        //+ "<tr><td colspan=9 ><b><P ALIGN=left>Total Grabada: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + df.format(totalf) + " Gs." + "</b></td></tr>";
                        tabla += "<tr><td colspan=5><b>Lquidacion de IVA:</b>&nbsp;&nbsp;&nbsp; <b>(5%):</b> " + "" + df.format(liqiva5) + "  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   <b>(10%):</b>&nbsp;" + df.format(liqiva10) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b> TOTAL I.V.A:</b> &nbsp; " + df.format(total) + " </td> </tr>";

                        tabla += "<tr><td colspan=5 ><b><P ALIGN=left>Total Grabada: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + df.format(totalf) + " Gs." + "</b></td></tr>";  //+ df.format(totald) +"</td></tr>";

                        String sql2 = "select sum(cantidad_venta * precio_producto) as TotalaPagar from factura_detalle_ventas fdv left join factura_ventas fv on fv.idfactura_venta=fdv.idfactura_venta left join productos a on a.idproducto=fdv.idproducto where fdv.idfactura_venta=" + id + "";
                        //String sql2 = "select sum(cantidad_venta * precio_venta) + " + total + " as TotalaPagar from factura_detalle_ventas fdv left join factura_ventas fv on fv.id_factura_venta=fdv.id_factura_venta left join articulos a on a.id_articulo=fdv.id_articulo where fdv.id_factura_venta="+ id + "";

                        try (PreparedStatement ps1 = Conexion.getConn().prepareStatement(sql2)) {

                            ResultSet rs1 = ps1.executeQuery();
                            if (rs1.next()) {
                                int total_pagar = rs1.getInt("TotalaPagar");
                                FacturaVentas factura = new FacturaVentas();
                                factura.setTotalP(rs.getInt(total_pagar));
                                tabla += "<tr ><td colspan=5  ><b><P ALIGN=left>Total a Pagar en Gs: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + df.format(total_pagar) + ".----" + "</td></tr>";
                            }
                            System.out.println("Total a Pagar " + sql2);
                            System.out.println(rs1.getString("TotalaPagar"));
                        } catch (SQLException ex) {
                            System.out.println("--> " + ex.getLocalizedMessage());

                        }
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                //System.out.println("--> " + ex.getLocalizedMessage());
                System.out.println("aqui--> ");
                Logger.getLogger(FacturaDetalleVentasControlador.class.getName()).log(Level.SEVERE, null, ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from factura_detalle_ventas fdv "
                        + "left join factura_ventas fv on fv.idfactura_venta=fdv.idfactura_venta "
                        + "left join productos a on a.idproducto=fdv.idproducto "
                        + "where upper(a.nombre_producto) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by idfactura_detalle_venta "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("idfactura_detalle_venta") + "</td>"
                                + "<td>" + rs.getString("idfactura_venta") + "</td>"
                                + "<td>" + rs.getString("idproducto") + "</td>"
                                + "<td>" + rs.getString("nombre_producto") + "</td>"
                                + "<td>" + rs.getInt("cantidad_venta") + "</td>"
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

    public static boolean agregar(FacturaDetalle facturadetalle) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into factura_detalle_ventas "
                    + "(idfactura_venta,idproducto,cantidad_venta) "
                    + "values (?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, facturadetalle.getFacturaventa().getIdfactura_venta());
                ps.setInt(2, facturadetalle.getProducto().getIdproducto());
                ps.setInt(3, facturadetalle.getCantidad_venta());
                //ps.setInt(4, facturadetalle.getSubtotal_venta());
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

    public static boolean modificar(FacturaDetalle facturadetalle) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update factura_detalle_ventas set "
                    + "idfactura_venta=?,"
                    + "idproducto=?,"
                    + "cantidad_venta=? "
                    //+ "subtotal_venta=? "
                    + "where idfactura_detalle_venta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, facturadetalle.getFacturaventa().getIdfactura_venta());
                ps.setInt(2, facturadetalle.getProducto().getIdproducto());
                ps.setInt(3, facturadetalle.getCantidad_venta());
                //ps.setInt(4, facturadetalle.getSubtotal_venta());
                ps.setInt(4, facturadetalle.getIdfactura_detalle_venta());
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

    public static boolean eliminar(FacturaDetalle facturadetalle) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from factura_detalle_ventas where idfactura_detalle_venta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, facturadetalle.getIdfactura_detalle_venta());
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

    public static boolean eliminarArticuloVentas(FacturaVentas facturaventa) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from factura_detalle_ventas where idfactura_venta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, facturaventa.getIdfactura_venta());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                System.out.println("--> " + facturaventa.getIdfactura_venta());
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

    /*public static boolean agregarFDPedido(FacturaDetalle facturadetalle) {
        boolean valor = false;
        if (Conexion.conectar()) {
            int d1 = facturadetalle.getFacturaventa().getId_factura_venta();
            String sql = "select * from detallespedidos p left join factura_ventas fv on p.id_pedido=fv.id_pedido where p.id_pedido='" + d1 + "'";
            System.out.println("sql detall" + sql);
            try {
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        String sql1 = "insert into factura_detalle_ventas(id_factura_venta,id_articulo,cantidad_venta)"
                                + "values('" + rs.getInt("id_factura_venta") + "',"
                                + "'" + rs.getInt("id_articulo") + "',"
                                + "'" + rs.getInt("cantidad_articulopedido") + "')";
                                //+ "'" + rs.getInt("subtotal_pedido") + "')";

                        System.out.println("inser detalle" + sql1);
                        try {
                            Conexion.getSt().executeUpdate(sql1);
                            valor = true;
                        } catch (SQLException ex) {
                            System.err.println("Error:" + ex);
                        }
                    }

                    ps.close();

                }

            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }

        return valor;
    }*/
}
