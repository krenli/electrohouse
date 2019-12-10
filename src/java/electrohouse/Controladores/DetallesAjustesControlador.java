package electrohouse.Controladores;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import electrohouse.modelos.Ajustes;
import electrohouse.modelos.DetallesAjustes;
import electrohouse.modelos.Productos;
import electrohouse.utiles.Conexion;
import electrohouse.utiles.Utiles;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;


//import javawebts.modelos.Productos;
//import javawebts.modelos.DetallesAjustes;
//import javawebts.modelos.Ajustes;
//import javawebts.utiles.Conexion;
//import javawebts.utiles.Utiles;
/**
 *
 * @author Administrator
 */
public class DetallesAjustesControlador {

    public static DetallesAjustes buscarId(int id) throws SQLException {
        DetallesAjustes detalleajuste = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from ajuste_stock_detalle dp "
                        + "left join ajustes_stock p on p.idajuste_stock=dp.idajuste_stock "
                        + "left join productos a on a.idproducto=dp.idproducto "
                        + "where dp.idajuste_stock_detalle=?";
                
                /*String sql = "select * from detallesajustes dp "
                        + "left join ajustes p on p.id_ajuste=dp.id_ajuste "
                        + "left join productos a on a.id_producto=dp.id_producto "
                        + "where dp.id_detalleajuste=?";*/
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        detalleajuste = new DetallesAjustes();
                        detalleajuste.setIdajuste_stock_detalle(rs.getInt("idajuste_stock_detalle"));
                        detalleajuste.setCantidad_ajuste_stock(rs.getInt("cantidad_ajuste_stock"));
                        
                        Productos producto = new Productos();
                        producto.setIdproducto(rs.getInt("idproducto"));
                        producto.setNombre_producto(rs.getString("nombre_producto"));
                        producto.setPrecio_producto(rs.getInt("precio_producto"));
                        detalleajuste.setProducto(producto);

                        /*Productos producto = new Productos();
                        producto.setId_producto(rs.getInt("id_producto"));*/

                        /*producto.setNombre_producto(rs.getString("nombre_producto"));
                        producto.setCosto_producto(rs.getInt("costo_producto"));
                        producto.setIva_producto(rs.getInt("iva_producto"));
                        detalleajuste.setProducto(producto);*/

                        Ajustes ajuste = new Ajustes();
                        ajuste.setIdajuste_stock(rs.getInt("idajuste_stock"));
                        detalleajuste.setAjuste(ajuste);

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detalleajuste;
    }

    public static String buscarIdAjuste(int id) throws SQLException {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from ajuste_stock_detalle  dp "
                        + "left join ajustes_stock p on p.idajuste_stock=dp.idajuste_stock "
                        + "left join productos a on a.idproducto=dp.idproducto "
                        
                        + "where dp.idajuste_stock=" + id + " "
                        + "order by idajuste_stock_detalle";
                //System.out.println("hola "+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat("#,###");
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    while (rs.next()) {
                        BigDecimal cantidad = rs.getBigDecimal("cantidad_ajuste_stock");
                        total = total.add(cantidad);
                        // System.out.println("total"+total);
                        tabla += "<tr>"
                                + "<td>" + rs.getString("idajuste_stock_detalle") + "</td>"
                                + "<td>" + rs.getString("idproducto") + "</td>"
                                + "<td>" + rs.getString("nombre_producto") + "</td>"
                                + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("idajuste_stock_detalle") + ")'"
                                + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                //System.out.println("--> " + ex.getLocalizedMessage());
                Logger.getLogger(DetallesAjustesControlador.class.getName()).log(Level.SEVERE, null, ex);
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
                String sql = "select * from ajuste_stock_detalle dp "
                        + "left join ajustes_stock p on p.idajuste_stock=dp.idajuste_stock "
                        + "left join productos a on a.idproducto=dp.idproducto "
                         + "left join stock s on s.idproducto=p.idproducto "
                        + "where upper(a.nombre_producto) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by idajuste_stock_detalle "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getInt("idajuste_stock_detalle") + "</td>"
                                + "<td>" + rs.getInt("idajuste_stock") + "</td>"
                                + "<td>" + rs.getString("idproducto") + "</td>"
                                + "<td>" + rs.getString("nombre_producto") + "</td>"
                                + "<td>" + rs.getInt("precio_producto") + "</td>"
                                //+ "<td>" + rs.getInt("iva_producto") + "</td>"
                                 // + "<td>" + rs.getInt("cantidad_exi") + "</td>"
                                + "<td>" + rs.getInt("cantidad_ajuste_stock") + "</td>"
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

    public static boolean agregar(DetallesAjustes detalleajuste) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into ajuste_stock_detalle (idajuste_stock, idproducto, cantidad_ajuste_stock) "
                    + "values (?,?,?)";
            
            /*String sql = "insert into detallesajustes "
                    + "(idajuste,idproducto,cantidad_ajuste) "
                    + "values (?,?,?)";*/

            System.out.println(sql);
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleajuste.getAjuste().getIdajuste_stock());
                ps.setInt(2, detalleajuste.getProducto().getIdproducto());
                ps.setInt(3, detalleajuste.getCantidad_ajuste_stock());

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

    public static boolean modificar(DetallesAjustes detalleajuste) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update ajuste_stock_detalle set "
                    + "idajuste_stock=?,"
                    + "idproducto=?,"
                    + "cantidad_ajuste_stock=? "
                    + "where idajuste_stock_detalle=?";
            
            /*String sql = "update detallesajustes set "
                    + "id_ajuste=?,"
                    + "id_producto=?,"
                    + "cantidad_ajuste=? "
                    + "where id_detalleajuste=?";*/
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleajuste.getAjuste().getIdajuste_stock());
                ps.setInt(2, detalleajuste.getProducto().getIdproducto());
                ps.setInt(3, detalleajuste.getCantidad_ajuste_stock());

                ps.setInt(5, detalleajuste.getIdajuste_stock_detalle());
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

    public static boolean eliminar(DetallesAjustes detalleajuste) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from ajuste_stock_detalle where idajuste_stock=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleajuste.getIdajuste_stock_detalle());
                System.out.println("detallesqlstock+ " + ps);
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

    /*public static boolean eliminarc(DetallesAjustes detalleajuste) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from ajuste_stock_detalle dp "
                        + "left join ajustes_stock p on p.id_ajuste_stock=dp.id_ajuste_stock "
                        + "left join productos a on a.id_producto=dp.id_producto "
                        + " where p.id_ajuste_stock= " + detalleajuste.getAjuste().getId_ajuste_stock();
                System.out.println("detalle " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {

                        String sqli = "update stock set cantidad_stock = cantidad_stock - " + rs.getInt("cantidad_ajuste_stock") + " where id_producto=" + rs.getInt("id_producto") + "";

                        System.out.println(" descontar stcok" + sqli);
                        try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {

                            psi.executeUpdate();
                            psi.close();
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

                    ps.close();
                    valor = true;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }*/
    
    public static boolean eliminarDetalle(DetallesAjustes detalleajuste) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from ajuste_stock_detalle where idajuste_stock=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleajuste.getAjuste().getIdajuste_stock());
                System.out.println("detallesqlstock+ " + ps);
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

    public static boolean eliminarProductoAjuste(Ajustes ajuste) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from ajuste_stock_detalle where idajuste_stock=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, ajuste.getIdajuste_stock());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                System.out.println("--> " + ajuste.getIdajuste_stock());
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
