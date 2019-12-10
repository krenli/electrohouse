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
public class Proveedores {
    int idproveedor;
    String nombre_proveedor;
    String ruc_proveedor;
    String telefono_proveedor;
    String direccion_proveedor;
    String email_proveedor;
    Ciudades ciudad;
  
   
    public Proveedores() {
    }

    public Proveedores(int idproveedor, String nombre_proveedor, String ruc_proveedor, String telefono_proveedor, String direccion_proveedor, String email_proveedor, Ciudades ciudad) {
        this.idproveedor = idproveedor;
        this.nombre_proveedor = nombre_proveedor;
        this.ruc_proveedor = ruc_proveedor;
        this.telefono_proveedor = telefono_proveedor;
        this.direccion_proveedor = direccion_proveedor;
        this.email_proveedor = email_proveedor;
        this.ciudad = ciudad;
    }

    public int getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(int idproveedor) {
        this.idproveedor = idproveedor;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public String getRuc_proveedor() {
        return ruc_proveedor;
    }

    public void setRuc_proveedor(String ruc_proveedor) {
        this.ruc_proveedor = ruc_proveedor;
    }

    public String getTelefono_proveedor() {
        return telefono_proveedor;
    }

    public void setTelefono_proveedor(String telefono_proveedor) {
        this.telefono_proveedor = telefono_proveedor;
    }

    public String getDireccion_proveedor() {
        return direccion_proveedor;
    }

    public void setDireccion_proveedor(String direccion_proveedor) {
        this.direccion_proveedor = direccion_proveedor;
    }

    public String getEmail_proveedor() {
        return email_proveedor;
    }

    public void setEmail_proveedor(String email_proveedor) {
        this.email_proveedor = email_proveedor;
    }

    public Ciudades getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudades ciudad) {
        this.ciudad = ciudad;
    }

   
   
    
}
