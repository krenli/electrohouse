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
public class Formas_pagos {
    private int idforma_pago;
    private String nombre_forma_pago;

    public Formas_pagos() {
    }

    public Formas_pagos(int idforma_pago, String nombre_forma_pago) {
        this.idforma_pago = idforma_pago;
        this.nombre_forma_pago = nombre_forma_pago;
    }

    public int getIdforma_pago() {
        return idforma_pago;
    }

    public void setIdforma_pago(int idforma_pago) {
        this.idforma_pago = idforma_pago;
    }

    public String getNombre_forma_pago() {
        return nombre_forma_pago;
    }

    public void setNombre_forma_pago(String nombre_forma_pago) {
        this.nombre_forma_pago = nombre_forma_pago;
    }

   
    
}
