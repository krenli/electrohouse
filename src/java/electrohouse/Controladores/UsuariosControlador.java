/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.Controladores;
import electrohouse.modelos.Roles;
import electrohouse.modelos.Usuarios;
import electrohouse.utiles.Conexion;
import electrohouse.utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UsuariosControlador {
    public static Usuarios validarAcceso(Usuarios usuario, HttpServletRequest request) {
        if (Conexion.conectar()) {
            try {
                String sql = "select * from usuarios u "
                        + "left join roles r on u.idrol=r.idrol "
                        + "where login_usuario='" + Utiles.quitarGuiones(usuario.getLogin_usuario())
                        + "' and clave_usuario='" + Utiles.md5(Utiles.quitarGuiones(usuario.getClave_usuario())) + "'";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    
                    System.out.println("---> " + sql);
                    if (rs.next()) {
                        HttpSession sesion = request.getSession(true);
                        Roles rol = new Roles();
                        rol.setIdrol(rs.getInt("idrol"));
                        rol.setNombre_rol(rs.getString("nombre_rol"));
                        usuario = new Usuarios();
                        usuario.setIdusuario(rs.getInt("idusuario"));
                        usuario.setLogin_usuario(rs.getString("login_usuario"));
                        usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                        usuario.setClave_usuario(rs.getString("clave_usuario"));
                        usuario.setRol(rol);
                        sesion.setAttribute("usuarioLogueado", usuario);
                    } else {
                        usuario = null;
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return usuario;
    }

    public static boolean agregar(Usuarios usuario) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into usuarios (login_usuario, nombre_usuario, clave_usuario, idrol)"
                    + "values ('" + usuario.getLogin_usuario() + "','"
                    + usuario.getNombre_usuario() + "','"
                    + Utiles.md5(Utiles.quitarGuiones(usuario.getClave_usuario())) + "','"
                    + usuario.getRol().getIdrol() + "')";

            try {
                Conexion.getSt().executeUpdate(sql);

                valor = true;

            } catch (SQLException ex) {
                Logger.getLogger(UsuariosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return valor;

    }

    public static boolean modificar(Usuarios usuario) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update usuarios set login_usuario='" + usuario.getLogin_usuario() + "', "
                    + "nombre_usuario='" + usuario.getNombre_usuario() + "', "
                    + "clave_usuario='" + Utiles.md5(Utiles.quitarGuiones(usuario.getClave_usuario())) + "', "
                    + "idrol='" + usuario.getRol().getIdrol() + "' "
                    + "where idusuario=" + usuario.getIdusuario();

            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;

            } catch (SQLException ex) {
                Logger.getLogger(UsuariosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return valor;

    }

    public static boolean eliminar(Usuarios usuario){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from usuarios where idusuario=" + usuario.getIdusuario();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(UsuariosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }

    public static Usuarios buscarId(Usuarios usuario) {
        if (Conexion.conectar()) {
            String sql = "select * from usuarios u, roles r where u.idrol = r.idrol and u.idusuario ='" + usuario.getIdusuario() + "'";

            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    usuario.setIdusuario(rs.getInt("idusuario"));
                    usuario.setLogin_usuario(rs.getString("login_usuario"));
                    usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                    usuario.setClave_usuario(rs.getString("clave_usuario"));
                    Roles rol = new Roles();
                    rol.setIdrol(rs.getInt("idrol"));
                    rol.setNombre_rol(rs.getString("nombre_rol"));
                    usuario.setRol(rol);
                } else {
                    usuario.setIdusuario(0);
                    usuario.setLogin_usuario("");
                    usuario.setNombre_usuario("");
                    usuario.setClave_usuario("");
                    Roles rol = new Roles();
                    rol.setIdrol(0);
                    rol.setNombre_rol("");
                    usuario.setRol(rol);
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return usuario;
    }

    public static String buscarNombre(String nombre, int pagina) {

        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {

            try {
                System.out.println(nombre);
                String sql = "select * from usuarios where upper(nombre_usuario) like '%"
                        + nombre.toUpperCase() + "%'"
                        + "order by idusuario offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;

                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("idusuario") + "</td>"
                                + "<td>" + rs.getString("login_usuario") + "</td>"
                                + "<td>" + rs.getString("nombre_usuario") + "</td>"
                                + "<td>" + rs.getString("clave_usuario") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2> No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("Error: " + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("Error: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }
}
