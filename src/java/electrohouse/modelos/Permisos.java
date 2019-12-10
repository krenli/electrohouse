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
public class Permisos {
    int idpermiso;
    Roles rol;
    Formularios formulario;
    
  
   
    public Permisos() {
    }

    public Permisos(int idpermiso, Roles rol, Formularios formulario) {
        this.idpermiso = idpermiso;
        this.rol = rol;
        this.formulario = formulario;
    }

    public int getIdpermiso() {
        return idpermiso;
    }

    public void setIdpermiso(int idpermiso) {
        this.idpermiso = idpermiso;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    public Formularios getFormulario() {
        return formulario;
    }

    public void setFormulario(Formularios formulario) {
        this.formulario = formulario;
    }

   
    
}
