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
public class PeriodoSQL {
    
    public synchronized static String listarPeriodos() {
        String sql = "SELECT STVTERM_CODE,STVTERM_DESC FROM REGISTRO.STVTERM WHERE STVTERM_END_DATE > TO_DATE(SYSDATE, 'DD/MM/RR') ORDER BY STVTERM_CODE ASC";
        return sql;
    }
}
