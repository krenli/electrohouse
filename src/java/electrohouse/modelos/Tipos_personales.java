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
public class Tipos_personales {
    int idtipo_personal;
    String nombre_tipo_personal;
  
   
    public Tipos_personales() {
    }

    public Tipos_personales(int idtipo_personal, String nombre_tipo_personal) {
        this.idtipo_personal = idtipo_personal;
        this.nombre_tipo_personal = nombre_tipo_personal;
    }

    public int getIdtipo_personal() {
        return idtipo_personal;
    }

    public void setIdtipo_personal(int idtipo_personal) {
        this.idtipo_personal = idtipo_personal;
    }

    public String getNombre_tipo_personal() {
        return nombre_tipo_personal;
    }

    public void setNombre_tipo_personal(String nombre_tipo_personal) {
        this.nombre_tipo_personal = nombre_tipo_personal;
    }

   
    
}
