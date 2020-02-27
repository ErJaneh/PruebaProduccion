/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.sql;

/**
 *
 * @author Janel
 */
public class ColegioSQL {

     public synchronized static String listarColegios() {
        String sql = "SELECT CODIGO, DESCRIPCION, CODIGO_BANNER FROM REG_GENERAL_COLEGIO";
        return sql;
    }
    
    public synchronized static String colegioPorCodigo() {
        String sql = "select codigo, descripcion, codigo_banner from reg_general_colegio where codigo=? ";
        return sql;
    }
}
