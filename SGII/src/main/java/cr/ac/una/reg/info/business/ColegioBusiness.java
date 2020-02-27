package cr.ac.una.reg.info.business;

import cr.ac.una.reg.info.beans.ColegioBean;
import cr.ac.una.reg.info.dao.ColegioDaoImp;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.exceptions.ExceptionGeneral;
import java.util.List;

/**
 *
 * @author Janel
 */
public class ColegioBusiness {

    private final ColegioDaoImp colegioDaoImp;

    public ColegioBusiness() throws ExceptionConnection {
        this.colegioDaoImp = new ColegioDaoImp();
    }

    public List<ColegioBean> ListarColegios() throws ExceptionGeneral {
        List<ColegioBean> listaColegios = null;
        try {
            listaColegios = this.colegioDaoImp.ListarColegios();
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "colegioBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar los registros ...", ex.toString(), 1, false, 1, "colegioBusiness", 1);
        }
        return listaColegios;
    }

    public ColegioBean colegioPorSolicitud(String codigoColegio) throws ExceptionGeneral {
        ColegioBean aux = null;
        try {
            aux = this.colegioDaoImp.colegioPorSolicitud(codigoColegio);
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "colegioBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar los registros ...", ex.toString(), 1, false, 1, "colegioBusiness", 1);
        }
        return aux;
    }

}
