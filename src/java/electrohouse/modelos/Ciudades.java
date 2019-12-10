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
public class Ciudades {
    int idciudad;
    String nombre_ciudad;
  
   
    public Ciudades() {
    }

    public Ciudades(int idciudad, String nombre_ciudad) {
        this.idciudad = idciudad;
        this.nombre_ciudad = nombre_ciudad;
    }

    public int getIdciudad() {
        return idciudad;
    }

    public void setIdciudad(int idciudad) {
        this.idciudad = idciudad;
    }

    public String getNombre_ciudad() {
        return nombre_ciudad;
    }

    public void setNombre_ciudad(String nombre_ciudad) {
        this.nombre_ciudad = nombre_ciudad;
    }

   
    
}
