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
public class Clientes {
    int idcliente;
    String nombre_cliente;
    String apellido_cliente;
    String ruc_cliente;
    String telefono_cliente;
    String fecha_nac_cliente;
    String direccion_cliente;  
    Ciudades ciudad;
    Estadosciviles estadocivil;

    
    public Clientes() {
    }

    public Clientes(int idcliente, String nombre_cliente, String apellido_cliente, String ruc_cliente, String telefono_cliente, String fecha_nac_cliente, String direccion_cliente, Ciudades ciudad, Estadosciviles estadocivil) {
        this.idcliente = idcliente;
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
        this.ruc_cliente = ruc_cliente;
        this.telefono_cliente = telefono_cliente;
        this.fecha_nac_cliente = fecha_nac_cliente;
        this.direccion_cliente = direccion_cliente;
        this.ciudad = ciudad;
        this.estadocivil = estadocivil;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getApellido_cliente() {
        return apellido_cliente;
    }

    public void setApellido_cliente(String apellido_cliente) {
        this.apellido_cliente = apellido_cliente;
    }

    public String getRuc_cliente() {
        return ruc_cliente;
    }

    public void setRuc_cliente(String ruc_cliente) {
        this.ruc_cliente = ruc_cliente;
    }

    public String getTelefono_cliente() {
        return telefono_cliente;
    }

    public void setTelefono_cliente(String telefono_cliente) {
        this.telefono_cliente = telefono_cliente;
    }

    public String getFecha_nac_cliente() {
        return fecha_nac_cliente;
    }

    public void setFecha_nac_cliente(String fecha_nac_cliente) {
        this.fecha_nac_cliente = fecha_nac_cliente;
    }

    public String getDireccion_cliente() {
        return direccion_cliente;
    }

    public void setDireccion_cliente(String direccion_cliente) {
        this.direccion_cliente = direccion_cliente;
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

  
}
