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
public class Pagares {
    private int idpagare;
    FacturaVentas ventas;
    private String montoenletras;

    public Pagares() {
    }

    public Pagares(int idpagare, FacturaVentas ventas, String montoenletras) {
        this.idpagare = idpagare;
        this.ventas = ventas;
        this.montoenletras = montoenletras;
    }

    public int getIdpagare() {
        return idpagare;
    }

    public void setIdpagare(int idpagare) {
        this.idpagare = idpagare;
    }

    public FacturaVentas getVentas() {
        return ventas;
    }

    public void setVentas(FacturaVentas ventas) {
        this.ventas = ventas;
    }

    public String getMontoenletras() {
        return montoenletras;
    }

    public void setMontoenletras(String montoenletras) {
        this.montoenletras = montoenletras;
    }

  
    
}
