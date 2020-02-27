/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.sql;

/**
 *
 * @author Mary
 */
public class NacionalidadSQL {
    
    public synchronized static String consultaCodigosNacionalidad(){
        String sql = "SELECT STVNATN_CODE,STVNATN_NATION FROM REGISTRO.STVNATN";
        return sql;
    }
}
