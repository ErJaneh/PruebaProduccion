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
import cr.ac.una.reg.info.beans.DistritoBean;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.util.ArrayList;

public interface DistritoDao {

    ArrayList<DistritoBean> ListarDistritoPorCanton(String canton) throws ExceptionConnection;

    DistritoBean DistritoPorCodigo(String codigo) throws ExceptionConnection;

}
