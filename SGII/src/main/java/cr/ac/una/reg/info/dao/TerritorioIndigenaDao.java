/**

* @author Janel Garces Castillo
* @version 1.0
* @since 1.0

**/

package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.TerritorioIndigenaBean;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.util.ArrayList;

public interface TerritorioIndigenaDao {
    TerritorioIndigenaBean buscarTerritorioPorCodigo(TerritorioIndigenaBean territorio) throws ExceptionConnection;
    
    
    /** Sheng Yuan Lin **/
    ArrayList<TerritorioIndigenaBean>ListarTerritoriosIndigenas( ) throws ExceptionConnection;
}
