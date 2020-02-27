/**
 *
 * @author Gabriel Araya Ruiz
 * @version 1.0
 * @since 1.0
 *
 */
package cr.ac.una.reg.info.sql;

public class PromocionSQL {

    /**
     * Obtiene de BD todas las promciones
     *
     * @return Sentencia SQL para obtener las promociones
     */
    public synchronized static String listarPromociones() {
        String sql;
        sql = "SELECT DR_SGII_PROMOCION.CODIGO_PROMOCION,DR_SGII_PROMOCION.NUMERO_PROMOCION,"
                + "DR_SGII_PROMOCION.DESCRIPCION_PROMOCION,"
                + "DR_SGII_PROMOCION.CODIGO_GRUPO_DE_INTERES,DR_SGII_PROMOCION.CODIGO_CARRERA,"
                + "SMRPRLE.SMRPRLE_PROGRAM_DESC AS DESCRIPCION_CARRERA,"
                + "DR_SGII_PROMOCION.CODIGO_PERIODO,DR_SGII_PROMOCION.CODIGO_UNIDAD_ACADEMICA,"
                + "DR_SGII_PROMOCION.CUPOS,DR_SGII_PROMOCION.FECHA_INICIO_PROMOCION,"
                + "DR_SGII_PROMOCION.FECHA_FIN_PROMOCION,DR_SGII_PROMOCION.ESTADO_PROMOCION "
                + "FROM DR_SGII_PROMOCION INNER JOIN REGISTRO.SMRPRLE ON "
                + "DR_SGII_PROMOCION.CODIGO_CARRERA = SMRPRLE.SMRPRLE_PROGRAM ORDER BY DR_SGII_PROMOCION.NUMERO_PROMOCION DESC";
        return sql;
    }

    public synchronized static String listarPromocionesPorConvenios() {
        String sql = "SELECT DR_SGII_PROMOCION.CODIGO_PROMOCION,DR_SGII_PROMOCION.NUMERO_PROMOCION,"
                + "DR_SGII_PROMOCION.DESCRIPCION_PROMOCION,"
                + "DR_SGII_PROMOCION.CODIGO_GRUPO_DE_INTERES,DR_SGII_PROMOCION.CODIGO_CARRERA,"
                + "SMRPRLE.SMRPRLE_PROGRAM_DESC AS DESCRIPCION_CARRERA,"
                + "DR_SGII_PROMOCION.CODIGO_PERIODO,DR_SGII_PROMOCION.CODIGO_UNIDAD_ACADEMICA,"
                + "DR_SGII_PROMOCION.CUPOS,DR_SGII_PROMOCION.FECHA_INICIO_PROMOCION,"
                + "DR_SGII_PROMOCION.FECHA_FIN_PROMOCION,DR_SGII_PROMOCION.ESTADO_PROMOCION "
                + "FROM DR_SGII_PROMOCION INNER JOIN REGISTRO.SMRPRLE ON "
                + "DR_SGII_PROMOCION.CODIGO_CARRERA = SMRPRLE.SMRPRLE_PROGRAM "
                + "WHERE DR_SGII_PROMOCION.CODIGO_GRUPO_DE_INTERES = ?" 
                + "ORDER BY DR_SGII_PROMOCION.NUMERO_PROMOCION DESC";
        return sql;
    }   

    public synchronized static String getResponsablesPromocion() {
        String sql = "SELECT INDEX_RESPONSABLES_PROMOCION,NUMERO_PROMOCION,CORREO,DESCRIPCION FROM DR_SGII_RESPONSABLES_PROMOCION WHERE NUMERO_PROMOCION = ?";
        return sql;
    }

    public synchronized static String ingresaPromocion() {
        String sql = "INSERT INTO DR_SGII_PROMOCION (CODIGO_PROMOCION,DESCRIPCION_PROMOCION,CODIGO_GRUPO_DE_INTERES,"
                + "CODIGO_CARRERA,CODIGO_PERIODO,CODIGO_UNIDAD_ACADEMICA,CUPOS,FECHA_INICIO_PROMOCION,"
                + "FECHA_FIN_PROMOCION,ESTADO_PROMOCION,NUMERO_PROMOCION) VALUES "
                + "(?,?,?,?,?,?,?,TO_DATE(?,'DD/MM/YYYY'),TO_DATE(?,'DD/MM/YYYY'),?,?)";
        return sql;
    }

    public synchronized static String ingresaResponsablePromocion() {
        String sql = "INSERT INTO DR_SGII_RESPONSABLES_PROMOCION (INDEX_RESPONSABLES_PROMOCION,NUMERO_PROMOCION,CORREO,DESCRIPCION) VALUES (index_responsables_promocion.NEXTVAL,?,?,?)";
        return sql;
    }

    public synchronized static String getUltimoIndexPromociones() {
        String sql = "SELECT MAX(NUMERO_PROMOCION) AS LASTNUMBER FROM DR_SGII_PROMOCION";
        return sql;
    }
    
    public synchronized static String actualizarPromocion(){
        String sql = "UPDATE DR_SGII_PROMOCION SET ESTADO_PROMOCION=?,CODIGO_PERIODO=?,"
                + "CODIGO_UNIDAD_ACADEMICA=?,FECHA_INICIO_PROMOCION=TO_DATE(?,'DD/MM/YYYY'),"
                + "FECHA_FIN_PROMOCION=TO_DATE(?,'DD/MM/YYYY'),"
                + "DESCRIPCION_PROMOCION=? WHERE NUMERO_PROMOCION=?";
        return sql;
    }
    
    public synchronized static String eliminarResponsablePromocion(){
        String sql = "DELETE FROM DR_SGII_RESPONSABLES_PROMOCION WHERE INDEX_RESPONSABLES_PROMOCION=?";
        return sql;
    }

    public synchronized static String getCarrerasPorPromocion(){
        String sql = "SELECT DR_SGII_PROMOCION.CODIGO_CARRERA, SMRPRLE.SMRPRLE_PROGRAM_DESC "
                + "FROM DR_SGII_PROMOCION,SMRPRLE WHERE NUMERO_PROMOCION=? AND "
                + "SMRPRLE.SMRPRLE_PROGRAM=DR_SGII_PROMOCION.CODIGO_CARRERA";
        return sql;
    }
    
    public synchronized static String getCarrerasPorCodPromocion(String codigo){
        String sql = "SELECT DR_SGII_PROMOCION.CODIGO_CARRERA, SMRPRLE.SMRPRLE_PROGRAM_DESC "
                + "FROM DR_SGII_PROMOCION,SMRPRLE WHERE CODIGO_PROMOCION like '%s%%' AND "
                + "SMRPRLE.SMRPRLE_PROGRAM=DR_SGII_PROMOCION.CODIGO_CARRERA;";
        sql= String.format(sql, codigo);
        return sql;
    }


}
