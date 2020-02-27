/**

* @author Janel Garces Castillo
* @version 1.0
* @since 1.0

**/

package cr.ac.una.reg.info.sql;

public class TerritorioIndigenaSQL {
    
    public synchronized static String consultaTerritorioPorCodigo() {
        String sql = "SELECT DESCRIPCION, "
                + "CODIGO FROM DR_SGII_TERRITORIO_INDIGENA WHERE CODIGO =?";
        return sql;
    }
    
    /** Sheng Yuan Lin **/
    public synchronized static String listarTerritoriosIndigenas() {
        String sql = "SELECT CODIGO, DESCRIPCION FROM DR_SGII_TERRITORIO_INDIGENA";
        return sql;
    }
}
