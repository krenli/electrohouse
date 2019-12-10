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
public class Estadosciviles {
    int idestadocivil;
    String nombre_estadocivil;
  
   
    public Estadosciviles() {
    }

    public Estadosciviles(int idestadocivil, String nombre_estadocivil) {
        this.idestadocivil = idestadocivil;
        this.nombre_estadocivil = nombre_estadocivil;
    }

    public int getIdestadocivil() {
        return idestadocivil;
    }

    public void setIdestadocivil(int idestadocivil) {
        this.idestadocivil = idestadocivil;
    }

    public String getNombre_estadocivil() {
        return nombre_estadocivil;
    }

    public void setNombre_estadocivil(String nombre_estadocivil) {
        this.nombre_estadocivil = nombre_estadocivil;
    }



   
    
}
