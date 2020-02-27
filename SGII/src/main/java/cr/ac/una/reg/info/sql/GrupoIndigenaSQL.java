/**

* @author Janel Garces Castillo
* @version 1.0
* @since 1.0

**/

package cr.ac.una.reg.info.sql;

public class GrupoIndigenaSQL {
        
    public synchronized static String consultaGrupoPorCodigo() {
        String sql = "SELECT DESCRIPCION, "
                + "CODIGO FROM DR_SGII_GRUPO_INDIGENA WHERE CODIGO =?";
        return sql;
    }
    
    /** Sheng Yuan Lin **/
    public synchronized static String listarGruposIndigenas() {
        String sql = "SELECT CODIGO, DESCRIPCION FROM DR_SGII_GRUPO_INDIGENA";
        return sql;
    }
}
