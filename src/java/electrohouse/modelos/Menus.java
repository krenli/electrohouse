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
public class Menus {
    int idmenu;
    private String nombre_menu;
    private String codigo_menu;
   
    public Menus() {
    }

    public Menus(int idmenu, String nombre_menu, String codigo_menu) {
        this.idmenu = idmenu;
        this.nombre_menu = nombre_menu;
        this.codigo_menu = codigo_menu;
    }

    public int getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(int idmenu) {
        this.idmenu = idmenu;
    }

    public String getNombre_menu() {
        return nombre_menu;
    }

    public void setNombre_menu(String nombre_menu) {
        this.nombre_menu = nombre_menu;
    }

    public String getCodigo_menu() {
        return codigo_menu;
    }

    public void setCodigo_menu(String codigo_menu) {
        this.codigo_menu = codigo_menu;
    }

   
    
}
