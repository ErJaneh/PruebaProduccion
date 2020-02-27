/**
 *
 * @author Sheng Yuan Lin
 * @version 1.0
 * @since 1.0
 *
 *
 */
package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.ProvinciaBean;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.util.ArrayList;

public interface ProvinciaDao {
    
    ArrayList<ProvinciaBean>ListarProvincias( ) throws ExceptionConnection;
    ProvinciaBean ProvinciaPorCodigo (String codigo) throws ExceptionConnection;
}
