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
public class EscuelaSQL {
    
    public synchronized static String listarEscuelas() {
        String sql = "SELECT STVCOLL_CODE,STVCOLL_DESC FROM REGISTRO.STVCOLL";
        return sql;
    }
}
