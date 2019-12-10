/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.modelos;

import java.sql.Date;

/**
 *
 * @author alumno
 */
public class CuentasClientes {
    private int idcuenta;
    private FacturaVentas facturaventa;
    private int monto_total;
    private int nro_cuota;
    private Date vencimiento;
    private int monto_cuota;
    private String estado_cuenta;
    private int total_cuota;

    public CuentasClientes() {
    }

    public CuentasClientes(int idcuenta, FacturaVentas facturaventa, int monto_total, int nro_cuota, Date vencimiento, int monto_cuota, String estado_cuenta, int total_cuota) {
        this.idcuenta = idcuenta;
        this.facturaventa = facturaventa;
        this.monto_total = monto_total;
        this.nro_cuota = nro_cuota;
        this.vencimiento = vencimiento;
        this.monto_cuota = monto_cuota;
        this.estado_cuenta = estado_cuenta;
        this.total_cuota = total_cuota;
    }

    public int getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(int idcuenta) {
        this.idcuenta = idcuenta;
    }

    public FacturaVentas getFacturaventa() {
        return facturaventa;
    }

    public void setFacturaventa(FacturaVentas facturaventa) {
        this.facturaventa = facturaventa;
    }

    public int getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(int monto_total) {
        this.monto_total = monto_total;
    }

    public int getNro_cuota() {
        return nro_cuota;
    }

    public void setNro_cuota(int nro_cuota) {
        this.nro_cuota = nro_cuota;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public int getMonto_cuota() {
        return monto_cuota;
    }

    public void setMonto_cuota(int monto_cuota) {
        this.monto_cuota = monto_cuota;
    }

    public String getEstado_cuenta() {
        return estado_cuenta;
    }

    public void setEstado_cuenta(String estado_cuenta) {
        this.estado_cuenta = estado_cuenta;
    }

    public int getTotal_cuota() {
        return total_cuota;
    }

    public void setTotal_cuota(int total_cuota) {
        this.total_cuota = total_cuota;
    }

   
    
    
}
