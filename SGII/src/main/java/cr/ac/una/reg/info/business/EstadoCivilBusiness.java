/**

* @author Sheng Yuan Lin 
* @version 1.0
* @since 1.0

**/
package cr.ac.una.reg.info.business;

import cr.ac.una.reg.info.beans.EstadoCivilBean;
import cr.ac.una.reg.info.dao.EstadoCivilDaoImp;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.exceptions.ExceptionGeneral;
import java.util.List;


public class EstadoCivilBusiness {
    private final EstadoCivilDaoImp estadoCivilDaoImp;

    public EstadoCivilBusiness() throws ExceptionConnection {
        this.estadoCivilDaoImp = new EstadoCivilDaoImp();
    }
    
    public List<EstadoCivilBean> ListarEstadoCivil() throws ExceptionGeneral {
        List<EstadoCivilBean> listaEstadoCivil = null;
        try {
            listaEstadoCivil = this.estadoCivilDaoImp.ListarEstadoCivil();
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "estadoCivilBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar los registros ...", ex.toString(), 1, false, 1, "estadoCivilBusiness", 1);
        }
        return listaEstadoCivil;
    } 
}
