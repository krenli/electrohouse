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
public class Recibos {
    private int idrecibo;
    CuentasClientes cuentas;
    private String montoenletras;

    public Recibos() {
    }

    public Recibos(int idrecibo, CuentasClientes cuentas, String montoenletras) {
        this.idrecibo = idrecibo;
        this.cuentas = cuentas;
        this.montoenletras = montoenletras;
    }

    public int getIdrecibo() {
        return idrecibo;
    }

    public void setIdrecibo(int idrecibo) {
        this.idrecibo = idrecibo;
    }

    public CuentasClientes getCuentas() {
        return cuentas;
    }

    public void setCuentas(CuentasClientes cuentas) {
        this.cuentas = cuentas;
    }

    public String getMontoenletras() {
        return montoenletras;
    }

    public void setMontoenletras(String montoenletras) {
        this.montoenletras = montoenletras;
    }

   
    
}
