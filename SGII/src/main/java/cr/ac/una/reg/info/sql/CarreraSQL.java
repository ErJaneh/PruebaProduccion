/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.sql;

/**
 *
 * @author MSI
 */
public class CarreraSQL {
    
    public synchronized static String listarCarreras() {
        String sql = "SELECT CODIGO, DESCRIPCION FROM REGISTRO.REG_GENERAL_CARRERA";
        return sql;
    }
    
    public synchronized static String getDescripcionCarrera(String cod) {
        String sql = "SELECT DESCRIPCION FROM REGISTRO.REG_GENERAL_CARRERA WHERE CODIGO = '%s' ";
        sql = String.format(sql, cod);
        return sql;
    }


}
