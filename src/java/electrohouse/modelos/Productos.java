/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.modelos;

/**
 *
 * @author Administrator
 */
public class Productos {
    int idproducto;
    String nombre_producto;
    String cod_barras_producto;
    int costo_producto;
    int precio_producto;
    int minimo_producto;
    int iva_producto;
    Familias familia;
    Marcas marca;
  
    public Productos() {
    }

    public Productos(int idproducto, String nombre_producto, String cod_barras_producto, int costo_producto, int precio_producto, int minimo_producto, int iva_producto, Familias familia, Marcas marca) {
        this.idproducto = idproducto;
        this.nombre_producto = nombre_producto;
        this.cod_barras_producto = cod_barras_producto;
        this.costo_producto = costo_producto;
        this.precio_producto = precio_producto;
        this.minimo_producto = minimo_producto;
        this.iva_producto = iva_producto;
        this.familia = familia;
        this.marca = marca;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getCod_barras_producto() {
        return cod_barras_producto;
    }

    public void setCod_barras_producto(String cod_barras_producto) {
        this.cod_barras_producto = cod_barras_producto;
    }

    public int getCosto_producto() {
        return costo_producto;
    }

    public void setCosto_producto(int costo_producto) {
        this.costo_producto = costo_producto;
    }

    public int getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(int precio_producto) {
        this.precio_producto = precio_producto;
    }

    public int getMinimo_producto() {
        return minimo_producto;
    }

    public void setMinimo_producto(int minimo_producto) {
        this.minimo_producto = minimo_producto;
    }

    public int getIva_producto() {
        return iva_producto;
    }

    public void setIva_producto(int iva_producto) {
        this.iva_producto = iva_producto;
    }

    public Familias getFamilia() {
        return familia;
    }

    public void setFamilia(Familias familia) {
        this.familia = familia;
    }

    public Marcas getMarca() {
        return marca;
    }

    public void setMarca(Marcas marca) {
        this.marca = marca;
    }

  

  
}
