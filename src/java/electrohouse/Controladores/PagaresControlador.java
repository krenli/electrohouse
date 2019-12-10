/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electrohouse.Controladores;

import electrohouse.modelos.Pagares;
import electrohouse.utiles.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author alumno
 */
public class PagaresControlador {
    public static boolean agregar(Pagares pagare){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into pagares (idfactura_venta, montoenletras)" 
                    + "values ('" + pagare.getVentas().getIdfactura_venta() + "','"
                    + pagare.getMontoenletras() + "')";
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int idpagare = keyset.getInt(1);
                    pagare.setIdpagare(idpagare);
                   
                }
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(PagaresControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    
}
