/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.modelos;

import java.sql.Date;

public class FacturaCompras {

    private int idfactura_compra;
    
    private Proveedores proveedor;
    private Date fecha_factura_compra;
    private Tipo_facturas tipofactura;
    
    

    
    

    public FacturaCompras() {
    }

    public FacturaCompras(int idfactura_compra, Proveedores proveedor, Date fecha_factura_compra, Tipo_facturas tipofactura) {
        this.idfactura_compra = idfactura_compra;
        this.proveedor = proveedor;
        this.fecha_factura_compra = fecha_factura_compra;
        this.tipofactura = tipofactura;
    }

    public int getIdfactura_compra() {
        return idfactura_compra;
    }

    public void setIdfactura_compra(int idfactura_compra) {
        this.idfactura_compra = idfactura_compra;
    }

    public Proveedores getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedores proveedor) {
        this.proveedor = proveedor;
    }

    public Date getFecha_factura_compra() {
        return fecha_factura_compra;
    }

    public void setFecha_factura_compra(Date fecha_factura_compra) {
        this.fecha_factura_compra = fecha_factura_compra;
    }

    public Tipo_facturas getTipofactura() {
        return tipofactura;
    }

    public void setTipofactura(Tipo_facturas tipofactura) {
        this.tipofactura = tipofactura;
    }

    
}
