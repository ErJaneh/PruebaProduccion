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
    
    public synchronized static String listarCarrerasPorEscuela(String escuela) {
        String sql = "SELECT SMRPRLE_PROGRAM AS CODIGO,SMRPRLE_PROGRAM_DESC FROM REGISTRO.SMRPRLE WHERE SMRPRLE_COLL_CODE = '%s' ";
        sql = String.format(sql, escuela);
        return sql;
    }
    
    public synchronized static String getDescripcionCarrera(String cod) {
        String sql = "SELECT SMRPRLE_PROGRAM_DESC AS DESCRIPCION FROM REGISTRO.SMRPRLE WHERE SMRPRLE_PROGRAM = '%s' ";
        sql = String.format(sql, cod);
        return sql;
    }


}
