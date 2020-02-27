/**
 *
 * @author Sheng Yuan Lin
 * @version 1.0
 * @since 1.0
 *
 *
 */
package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.CantonBean;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.util.ArrayList;


public interface CantonDao {
    
    ArrayList<CantonBean>ListarCantonPorProvincia(String provincia) throws ExceptionConnection;
    CantonBean CantonPorCodigo (String canton) throws ExceptionConnection;
}
