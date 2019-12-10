package electrohouse.Controladores;


import electrohouse.modelos.Formularios;
import electrohouse.modelos.Permisos;
import electrohouse.modelos.Roles;
import electrohouse.utiles.Conexion;
import electrohouse.utiles.Utiles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PermisosControlador {

    public static Permisos buscarId(int id) {
        Permisos permiso = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from permisos p "+
                             "left join roles r on p.idrol=r.idrol "+
                             "left join formularios f on p.idformulario=f.idformulario "+
                             "where idpermiso=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        permiso = new Permisos();
                        permiso.setIdpermiso(rs.getInt("idpermiso"));
                        Roles rol = new Roles();
                        rol.setIdrol(rs.getInt("idrol"));
                        rol.setNombre_rol(rs.getString("nombre_rol"));
                        permiso.setRol(rol);
                        Formularios formulario = new Formularios();
                        formulario.setIdformulario(rs.getInt("idformulario"));
                        formulario.setNombre_formulario(rs.getString("nombre_formulario"));
                        permiso.setFormulario(formulario);
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return permiso;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
        int offset=(pagina-1)*Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from permisos p "+
                        "left join roles r on p.idrol=r.idrol "+
                        "left join formularios f on p.idformulario=f.idformulario "+
                        "where upper(nombre_rol) like '%" + 
                        nombre.toUpperCase() + 
                        "%' "+
                        "order by idpermiso "+
                        "offset "+ offset + " limit "+ Utiles.REGISTROS_PAGINA;
                System.out.println("--> "+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                               + "<td>" + rs.getString("idpermiso") + "</td>"
                               + "<td>" + rs.getString("idrol") + "</td>"
                               + "<td>" + rs.getString("nombre_rol") + "</td>"
                               + "<td>" + rs.getString("idformulario") + "</td>"
                               + "<td>" + rs.getString("nombre_formulario") + "</td>"
                               + "</tr>";
                    }
                    if(tabla.equals("")){
                        tabla = "<tr><td  colspan=5>No existen registros ...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean agregar(Permisos permiso) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into permisos "
                    + "(idrol,idformulario) "
                    + "values (?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, permiso.getRol().getIdrol());
                ps.setInt(2, permiso.getFormulario().getIdformulario());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean modificar(Permisos permiso) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update permisos set "
                    + "idrol=?, "
                    + "idformulario=? "
                    + "where idpermiso=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, permiso.getRol().getIdrol());
                ps.setInt(2, permiso.getFormulario().getIdformulario());
                ps.setInt(3, permiso.getIdpermiso());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                System.out.println("--> Grabado");
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminar(Permisos permiso) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from permisos where idpermiso=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, permiso.getIdpermiso());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }
}
