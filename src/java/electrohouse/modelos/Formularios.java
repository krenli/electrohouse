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
public class Formularios {
    int idformulario;
    String nombre_formulario;
    String codigo_formulario;
    Menus menu;
  
   
    public Formularios() {
    }

    public Formularios(int idformulario, String nombre_formulario, String codigo_formulario, Menus menu) {
        this.idformulario = idformulario;
        this.nombre_formulario = nombre_formulario;
        this.codigo_formulario = codigo_formulario;
        this.menu = menu;
    }

    public int getIdformulario() {
        return idformulario;
    }

    public void setIdformulario(int idformulario) {
        this.idformulario = idformulario;
    }

    public String getNombre_formulario() {
        return nombre_formulario;
    }

    public void setNombre_formulario(String nombre_formulario) {
        this.nombre_formulario = nombre_formulario;
    }

    public String getCodigo_formulario() {
        return codigo_formulario;
    }

    public void setCodigo_formulario(String codigo_formulario) {
        this.codigo_formulario = codigo_formulario;
    }

    public Menus getMenu() {
        return menu;
    }

    public void setMenu(Menus menu) {
        this.menu = menu;
    }

  
    
}
