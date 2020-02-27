/**
 *
 * @author Gabriel Araya Ruiz
 * @version 1.0
 * @since 1.0
 *
 */
package cr.ac.una.reg.info.business;

import cr.ac.una.reg.info.beans.PromocionBean;
import cr.ac.una.reg.info.beans.ResponsablePromocionBean;
import cr.ac.una.reg.info.dao.PromocionDaoImp;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.exceptions.ExceptionGeneral;
import java.util.List;

public class PromocionBusiness {

    private final PromocionDaoImp promocionDaoImp;

    public PromocionBusiness() throws ExceptionConnection {
        this.promocionDaoImp = new PromocionDaoImp();
    }

    /**
     * Obtiene la lista de las promocione
     *
     * @return List de promociones
     * @throws cr.ac.una.reg.info.exceptions.ExceptionGeneral
     */
    public List<PromocionBean> ListarPromociones() throws ExceptionGeneral {
        List<PromocionBean> listaPromociones = null;
        try {
            listaPromociones = this.promocionDaoImp.ListarPromociones();
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "promocionBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar los registros ...", ex.toString(), 1, false, 1, "promocionBusiness", 1);
        }
        return listaPromociones;
    }

    public List<PromocionBean> ListarPromocionesPorConvenio(String codigo) throws ExceptionGeneral {
        List<PromocionBean> listaPromociones = null;
        try {
            listaPromociones = this.promocionDaoImp.ListarPromocionesPorConvenio(codigo);
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "promocionBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar los registros ...", ex.toString(), 1, false, 1, "promocionBusiness", 1);
        }
        return listaPromociones;
    }

    public void ingresarPromociones(PromocionBean promocion) throws ExceptionGeneral {
        try {
            this.promocionDaoImp.ingresarPromocion(promocion);
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "promocionBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar los registros ...", ex.toString(), 1, false, 1, "promocionBusiness", 1);
        }
    }
    
    public void ingresarResponsablesPromocion(List<ResponsablePromocionBean> responsables) throws ExceptionGeneral {
        try {
            this.promocionDaoImp.ingresarResponsablesPromocion(responsables);
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "promocionBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas al ingresar los registros ...", ex.toString(), 1, false, 1, "promocionBusiness", 1);
        }
    }
    
    public int getUltimoIndexPromociones() throws ExceptionGeneral {
        try {
            return this.promocionDaoImp.getUltimoIndexPromociones();
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "promocionBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar los registros ...", ex.toString(), 1, false, 1, "promocionBusiness", 1);
        }
    }
    
    public void actualizarPromocion(PromocionBean promocion) throws ExceptionGeneral{
        try {
            this.promocionDaoImp.actualizarPromocion(promocion);
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "promocionBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas al actualizar los registros ...", ex.toString(), 1, false, 1, "promocionBusiness", 1);
        }
    }
    
    public void eliminarResponsablesPromocion(List<ResponsablePromocionBean> responsables) throws ExceptionGeneral {
        try {
            this.promocionDaoImp.eliminarResponsablesPromocion(responsables);
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "promocionBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas al ingresar los registros ...", ex.toString(), 1, false, 1, "promocionBusiness", 1);
        }
    }
    
}
