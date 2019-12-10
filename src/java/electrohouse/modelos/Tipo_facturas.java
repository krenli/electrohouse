/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.modelos;

/**
 *
 * @author alumno
 */
public class Tipo_facturas {
    private int idtipo_factura;
    private String nombre_tipo_factura;

    public Tipo_facturas() {
    }

    public Tipo_facturas(int idtipo_factura, String nombre_tipo_factura) {
        this.idtipo_factura = idtipo_factura;
        this.nombre_tipo_factura = nombre_tipo_factura;
    }

    public int getIdtipo_factura() {
        return idtipo_factura;
    }

    public void setIdtipo_factura(int idtipo_factura) {
        this.idtipo_factura = idtipo_factura;
    }

    public String getNombre_tipo_factura() {
        return nombre_tipo_factura;
    }

    public void setNombre_tipo_factura(String nombre_tipo_factura) {
        this.nombre_tipo_factura = nombre_tipo_factura;
    }

  
    
}
