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
public class Personales {
    int idpersonal;
    String nombre_personal;
    String ci_personal;
    String telefono_personal;
    String fecha_nac_personal;
    String direccion_personal;  
    Ciudades ciudad;
    Estadosciviles estadocivil;
    Tipos_personales tipo_personal;
    
    public Personales() {
    }

    public Personales(int idpersonal, String nombre_personal, String ci_personal, String telefono_personal, String fecha_nac_personal, String direccion_personal, Ciudades ciudad, Estadosciviles estadocivil, Tipos_personales tipo_personal) {
        this.idpersonal = idpersonal;
        this.nombre_personal = nombre_personal;
        this.ci_personal = ci_personal;
        this.telefono_personal = telefono_personal;
        this.fecha_nac_personal = fecha_nac_personal;
        this.direccion_personal = direccion_personal;
        this.ciudad = ciudad;
        this.estadocivil = estadocivil;
        this.tipo_personal = tipo_personal;
    }

    public int getIdpersonal() {
        return idpersonal;
    }

    public void setIdpersonal(int idpersonal) {
        this.idpersonal = idpersonal;
    }

    public String getNombre_personal() {
        return nombre_personal;
    }

    public void setNombre_personal(String nombre_personal) {
        this.nombre_personal = nombre_personal;
    }

    public String getCi_personal() {
        return ci_personal;
    }

    public void setCi_personal(String ci_personal) {
        this.ci_personal = ci_personal;
    }

    public String getTelefono_personal() {
        return telefono_personal;
    }

    public void setTelefono_personal(String telefono_personal) {
        this.telefono_personal = telefono_personal;
    }

    public String getFecha_nac_personal() {
        return fecha_nac_personal;
    }

    public void setFecha_nac_personal(String fecha_nac_personal) {
        this.fecha_nac_personal = fecha_nac_personal;
    }

    public String getDireccion_personal() {
        return direccion_personal;
    }

    public void setDireccion_personal(String direccion_personal) {
        this.direccion_personal = direccion_personal;
    }

    public Ciudades getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudades ciudad) {
        this.ciudad = ciudad;
    }

    public Estadosciviles getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(Estadosciviles estadocivil) {
        this.estadocivil = estadocivil;
    }

    public Tipos_personales getTipo_personal() {
        return tipo_personal;
    }

    public void setTipo_personal(Tipos_personales tipo_personal) {
        this.tipo_personal = tipo_personal;
    }
  
}
