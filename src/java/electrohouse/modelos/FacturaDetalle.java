/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.modelos;


public class FacturaDetalle {

    private int idfactura_detalle_venta;
    private FacturaVentas facturaventa;
    private Productos producto;
    private int cantidad_venta;
    private int subtotal_venta;

    public FacturaDetalle() {
    }

    public FacturaDetalle(int idfactura_detalle_venta, FacturaVentas facturaventa, Productos producto, int cantidad_venta, int subtotal_venta) {
        this.idfactura_detalle_venta = idfactura_detalle_venta;
        this.facturaventa = facturaventa;
        this.producto = producto;
        this.cantidad_venta = cantidad_venta;
        this.subtotal_venta = subtotal_venta;
    }

    public int getIdfactura_detalle_venta() {
        return idfactura_detalle_venta;
    }

    public void setIdfactura_detalle_venta(int idfactura_detalle_venta) {
        this.idfactura_detalle_venta = idfactura_detalle_venta;
    }

    public FacturaVentas getFacturaventa() {
        return facturaventa;
    }

    public void setFacturaventa(FacturaVentas facturaventa) {
        this.facturaventa = facturaventa;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public int getCantidad_venta() {
        return cantidad_venta;
    }

    public void setCantidad_venta(int cantidad_venta) {
        this.cantidad_venta = cantidad_venta;
    }

    public int getSubtotal_venta() {
        return subtotal_venta;
    }

    public void setSubtotal_venta(int subtotal_venta) {
        this.subtotal_venta = subtotal_venta;
    }

   
}