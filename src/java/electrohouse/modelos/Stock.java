/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.modelos;

/**
 *
 * @author alumno
 */
public class Stock {
    private int idstock;
    private Productos producto;
    private int cantidad_stock;
    private Depositos deposito;

    public Stock() {
    }

    public Stock(int idstock, Productos producto, int cantidad_stock, Depositos deposito) {
        this.idstock = idstock;
        this.producto = producto;
        this.cantidad_stock = cantidad_stock;
        this.deposito = deposito;
    }

    public int getIdstock() {
        return idstock;
    }

    public void setIdstock(int idstock) {
        this.idstock = idstock;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public int getCantidad_stock() {
        return cantidad_stock;
    }

    public void setCantidad_stock(int cantidad_stock) {
        this.cantidad_stock = cantidad_stock;
    }

    public Depositos getDeposito() {
        return deposito;
    }

    public void setDeposito(Depositos deposito) {
        this.deposito = deposito;
    }

  
   
    
    
}
