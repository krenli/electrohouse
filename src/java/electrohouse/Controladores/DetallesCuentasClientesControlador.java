/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.Controladores;
import electrohouse.modelos.CuentasClientes;
import electrohouse.modelos.CuentasClientesDetalle;
import electrohouse.modelos.FacturaVentas;
import electrohouse.utiles.Conexion;
import electrohouse.utiles.Utiles;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.logging.Logger;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DetallesCuentasClientesControlador {
    
    
    
    
    public static boolean agregarcuentadetalle(CuentasClientesDetalle cuentasclientesdetalle){
        boolean valor = false;
        if (Conexion.conectar()){
            try {
                String sql = "select * from cuentas c left join factura_ventas fv on fv.idfactura_venta=c.idfactura_venta where c.idcuenta='" + cuentasclientesdetalle.getCuentasclientes().getIdcuenta() +"'";
                System.out.println("sql ::"+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    //BigDecimal total = BigDecimal.ZERO;
                    int nro_cuota = 0;
                    String vence = "";
                    while (rs.next()) {
                        Calendar vencimiento = GregorianCalendar.getInstance();
                        vencimiento.setTime(rs.getDate("fecha_factura_venta"));
                        System.out.println("FECHA"+ rs.getDate("fecha_factura_venta"));
                        //vencimiento.add(Calendar.DATE, 30);
                        int totalmonto = rs.getInt("monto_total");
                        int totalcuota = rs.getInt("total_cuota");
                        
                        int monto_cuota = totalmonto / totalcuota;
                        System.out.println("nro " + totalcuota + ", monto " + monto_cuota);
                        
                        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        

                        //if (rs.getInt("matricula_cursohabilitado") > 0) {
                            /*try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {
                                psi.setInt(1, rs.getInt("id_factura_venta"));
                                psi.setInt(2, 1);
                                psi.setInt(3, 1);
                                psi.setInt(4, 1);
                                psi.setInt(5, rs.getInt("matricula_cursohabilitado"));
                                psi.setDate(6, rs.getDate("fecha_inscripcion"));
                                psi.executeUpdate();
                                psi.close();
                                Conexion.getConn().setAutoCommit(false);
                            } catch (SQLException ex) {
                                System.out.println("--> " + ex.getLocalizedMessage());
                                try {
                                    Conexion.getConn().rollback();
                                } catch (SQLException ex1) {
                                    System.out.println("--> " + ex1.getLocalizedMessage());
                                }
                            }*/

                        //}
                        int cuenta = rs.getInt("idcuenta");
                        String estado = "PENDIENTE";
                        ///condicion si es que tiene cuota definida
                        for (int i = 0; i < totalcuota; i++) {
                            nro_cuota = nro_cuota + 1;
                          //  nrocuota = nrocuota+1;
                               //System.out.println("nro " + nrocuota + ", cuota " + nro_cuota +" "+ cuotavence);
                               
                            vence = sdf.format(vencimiento.getTime());
                            java.sql.Date cuotavence = Utiles.stringToSqlDate(vence);
                            System.out.println("nro " + totalcuota + ", cuota " + nro_cuota +" "+ cuotavence + "f "+ cuenta);
                            //cuotas 
                            String sqli = "insert into detallescuentas (idcuenta, nro_cuota, vencimiento_cuota, monto_cuota, estado_cuota)"
                                
                                + "values (?,?,?,?,?)";
                            System.out.println("sqlcuenta "+ sqli);
                            try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {
                                psi.setInt(1, cuenta);
                              
                                psi.setInt(2, nro_cuota);
                                psi.setDate(3, cuotavence);
                                psi.setInt(4, monto_cuota);
                                psi.setString(5, estado);
                               
                                psi.executeUpdate();
                                psi.close();
                                Conexion.getConn().commit();
                            } catch (SQLException ex) {
                                System.out.println("--> " + ex.getLocalizedMessage());
                                try {
                                    Conexion.getConn().rollback();
                                } catch (SQLException ex1) {
                                    System.out.println("--> " + ex1.getLocalizedMessage());
                                }
                            }
                            vencimiento.add(Calendar.DATE, 30);
                        }
                    }
                    ps.close();
                    System.out.println("--> " + valor);
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }    
        }
        
        return valor;
        
    }
    
    public static boolean modificar(CuentasClientesDetalle cuentasclientesdetalles){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update detallescuentas set estado_cuota='COBRADO'" 
                    + " where nro_cuota=" + cuentasclientesdetalles.getCuentasclientes().getNro_cuota();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(DetallesCuentasClientesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(CuentasClientes cuentasclientes){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from cuentas where idcuenta=" + cuentasclientes.getIdcuenta();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(DetallesCuentasClientesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static CuentasClientes buscarId(CuentasClientes cuentasclientes) {
        if (Conexion.conectar()){
            String sql = "select * from cuentas where idcuenta ='"+cuentasclientes.getIdcuenta()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    cuentasclientes.setIdcuenta(rs.getInt("idcuenta"));
                    FacturaVentas facturaventa = new FacturaVentas();
                    facturaventa.setIdfactura_venta(rs.getInt("idfactura_venta"));
                    cuentasclientes.setFacturaventa(facturaventa);
                    cuentasclientes.setMonto_total(rs.getInt("monto_total"));
                    cuentasclientes.setNro_cuota(rs.getInt("nro_cuota"));
                    cuentasclientes.setVencimiento(rs.getDate("vencimiento"));
                    cuentasclientes.setMonto_cuota(rs.getInt("monto_cuota"));
                    cuentasclientes.setEstado_cuenta(rs.getString("estado_cuenta"));
                } 
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return cuentasclientes;
    }
    
    public static CuentasClientes buscarId1(CuentasClientes cuentasclientes) {
        if (Conexion.conectar()){
            String sql = "select * from cuentas ct, factura_ventas f, clientes c where idcuenta='"+cuentasclientes.getIdcuenta()+"' and f.idtipo_factura=2 and f.idfactura_venta=ct.idfactura_venta and estado_cuenta='ACTIVO'";
                /*String sql = "select * from cuentas where upper(estado_cuenta) like '%" +
                        nombre.toUpperCase() + "%'";*/
                        
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    cuentasclientes.setIdcuenta(rs.getInt("idcuenta"));
                    FacturaVentas facturaventa = new FacturaVentas();
                    facturaventa.setIdfactura_venta(rs.getInt("idfactura_venta"));
                    cuentasclientes.setFacturaventa(facturaventa);
                    cuentasclientes.setMonto_total(rs.getInt("monto_total"));
                    cuentasclientes.setNro_cuota(rs.getInt("nro_cuota"));
                    cuentasclientes.setVencimiento(rs.getDate("vencimiento"));
                    cuentasclientes.setMonto_cuota(rs.getInt("monto_cuota"));
                    cuentasclientes.setEstado_cuenta(rs.getString("estado_cuenta"));
                } else {
                    cuentasclientes.setIdcuenta(0);
                    FacturaVentas facturaventa = new FacturaVentas();
                    facturaventa.setIdfactura_venta(0);
                    cuentasclientes.setFacturaventa(facturaventa);
                    cuentasclientes.setMonto_total(0);
                    cuentasclientes.setNro_cuota(0);
                    cuentasclientes.setVencimiento(null);
                    cuentasclientes.setMonto_cuota(0);
                    cuentasclientes.setEstado_cuenta("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return cuentasclientes;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                  String sql = "select * from cuentas ct, factura_ventas f, clientes c, tipo_facturas tf where f.idfactura_venta=ct.idfactura_venta and tf.nombre_tipo_factura= 'CREDITO' and estado_cuenta='ACTIVO' and f.idcliente=c.idcliente and ruc_cliente like '%"+nombre+"%'"
                /*String sql = "select * from cuentas where upper(estado_cuenta) like '%" +
                        nombre.toUpperCase() + "%'";*/
                        + " order by idcuenta offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("idcuenta") + "</td>"
                                + "<td>" + rs.getString("idfactura_venta") + "</td>"
                                + "<td>" + rs.getString("nombre_cliente") + "</td>"
                                + "<td>" + rs.getString("monto_cuota") + "</td>"
                                + "<td>" + rs.getString("vencimiento") + "</td>"
                                + "<td>" + rs.getString("monto_total") + "</td>"
                                + "<td>" + rs.getString("estado_cuenta") + "</td>"
                                + "</tr>";
                    }   
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=4> No existen registros...</td></tr>";
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
