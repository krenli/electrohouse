/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.modelos;

import java.sql.Date;

/**
 *
 * @author alumno
 */
public class Timbrados {
    private int idtimbrado;
    private String numero_timbrado;
    private Date fecha_inicio_timbrado;
    private Date fecha_vencimiento_timbrado;
    private Date fecha_actual_timbrado;
    private int desde_timbrado;
    private int hasta_timbrado;
    private String estado_timbrado;
    private Tipos_personales tipo_personal;

    public Timbrados() {
    }

    public Timbrados(int idtimbrado, String numero_timbrado, Date fecha_inicio_timbrado, Date fecha_vencimiento_timbrado, Date fecha_actual_timbrado, int desde_timbrado, int hasta_timbrado, String estado_timbrado, Tipos_personales tipo_personal) {
        this.idtimbrado = idtimbrado;
        this.numero_timbrado = numero_timbrado;
        this.fecha_inicio_timbrado = fecha_inicio_timbrado;
        this.fecha_vencimiento_timbrado = fecha_vencimiento_timbrado;
        this.fecha_actual_timbrado = fecha_actual_timbrado;
        this.desde_timbrado = desde_timbrado;
        this.hasta_timbrado = hasta_timbrado;
        this.estado_timbrado = estado_timbrado;
        this.tipo_personal = tipo_personal;
    }

    public int getIdtimbrado() {
        return idtimbrado;
    }

    public void setIdtimbrado(int idtimbrado) {
        this.idtimbrado = idtimbrado;
    }

    public String getNumero_timbrado() {
        return numero_timbrado;
    }

    public void setNumero_timbrado(String numero_timbrado) {
        this.numero_timbrado = numero_timbrado;
    }

    public Date getFecha_inicio_timbrado() {
        return fecha_inicio_timbrado;
    }

    public void setFecha_inicio_timbrado(Date fecha_inicio_timbrado) {
        this.fecha_inicio_timbrado = fecha_inicio_timbrado;
    }

    public Date getFecha_vencimiento_timbrado() {
        return fecha_vencimiento_timbrado;
    }

    public void setFecha_vencimiento_timbrado(Date fecha_vencimiento_timbrado) {
        this.fecha_vencimiento_timbrado = fecha_vencimiento_timbrado;
    }

    public Date getFecha_actual_timbrado() {
        return fecha_actual_timbrado;
    }

    public void setFecha_actual_timbrado(Date fecha_actual_timbrado) {
        this.fecha_actual_timbrado = fecha_actual_timbrado;
    }

    public int getDesde_timbrado() {
        return desde_timbrado;
    }

    public void setDesde_timbrado(int desde_timbrado) {
        this.desde_timbrado = desde_timbrado;
    }

    public int getHasta_timbrado() {
        return hasta_timbrado;
    }

    public void setHasta_timbrado(int hasta_timbrado) {
        this.hasta_timbrado = hasta_timbrado;
    }

    public String getEstado_timbrado() {
        return estado_timbrado;
    }

    public void setEstado_timbrado(String estado_timbrado) {
        this.estado_timbrado = estado_timbrado;
    }

    public Tipos_personales getTipo_personal() {
        return tipo_personal;
    }

    public void setTipo_personal(Tipos_personales tipo_personal) {
        this.tipo_personal = tipo_personal;
    }

   
    

    
    
    
}
