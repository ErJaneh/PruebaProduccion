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
public class ConvenioSQL {
    
    public synchronized static String listarConvenios() {
        String sql = "SELECT STVATTS_CODE,STVATTS_DESC FROM STVATTS";
        return sql;
    }
    
}
