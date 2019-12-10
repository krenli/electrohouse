/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.modelos;

/**
 *
 * @author ALUMNO
 */
public class DetallesAjustes {

    private int idajuste_stock_detalle;
    private Ajustes ajuste;
    private int cantidad_ajuste_stock;
    private Productos producto;
    //private Productos producto;

    public DetallesAjustes() {
    }

    public DetallesAjustes(int idajuste_stock_detalle, Ajustes ajuste, int cantidad_ajuste_stock, Productos producto) {
        this.idajuste_stock_detalle = idajuste_stock_detalle;
        this.ajuste = ajuste;
        this.cantidad_ajuste_stock = cantidad_ajuste_stock;
        this.producto = producto;
    }

    public int getIdajuste_stock_detalle() {
        return idajuste_stock_detalle;
    }

    public void setIdajuste_stock_detalle(int idajuste_stock_detalle) {
        this.idajuste_stock_detalle = idajuste_stock_detalle;
    }

    public Ajustes getAjuste() {
        return ajuste;
    }

    public void setAjuste(Ajustes ajuste) {
        this.ajuste = ajuste;
    }

    public int getCantidad_ajuste_stock() {
        return cantidad_ajuste_stock;
    }

    public void setCantidad_ajuste_stock(int cantidad_ajuste_stock) {
        this.cantidad_ajuste_stock = cantidad_ajuste_stock;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

  
}
