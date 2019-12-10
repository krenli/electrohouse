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
public class Familias {
    int idfamilia;
    String nombre_familia;
  Categorias categoria;
  
   
    public Familias() {
    }

    public Familias(int idfamilia, String nombre_familia, Categorias categoria) {
        this.idfamilia = idfamilia;
        this.nombre_familia = nombre_familia;
        this.categoria = categoria;
    }

    public int getIdfamilia() {
        return idfamilia;
    }

    public void setIdfamilia(int idfamilia) {
        this.idfamilia = idfamilia;
    }

    public String getNombre_familia() {
        return nombre_familia;
    }

    public void setNombre_familia(String nombre_familia) {
        this.nombre_familia = nombre_familia;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

   
   
    
}
