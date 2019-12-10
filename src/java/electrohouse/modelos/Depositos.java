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
public class Depositos {
    int iddeposito;
    String nombre_deposito;
    Ciudades ciudad;
  
   
    public Depositos() {
    }

    public Depositos(int iddeposito, String nombre_deposito, Ciudades ciudad) {
        this.iddeposito = iddeposito;
        this.nombre_deposito = nombre_deposito;
        this.ciudad = ciudad;
    }

    public int getIddeposito() {
        return iddeposito;
    }

    public void setIddeposito(int iddeposito) {
        this.iddeposito = iddeposito;
    }

    public String getNombre_deposito() {
        return nombre_deposito;
    }

    public void setNombre_deposito(String nombre_deposito) {
        this.nombre_deposito = nombre_deposito;
    }

    public Ciudades getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudades ciudad) {
        this.ciudad = ciudad;
    }

   
   
    
}
