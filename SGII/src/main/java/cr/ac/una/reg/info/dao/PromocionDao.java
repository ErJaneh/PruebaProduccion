/**
 *
 * @author Gabriel Araya Ruiz
 * @version 1.0
 * @since 1.0
 *
 */
package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.CarreraBean;
import cr.ac.una.reg.info.beans.PromocionBean;
import cr.ac.una.reg.info.beans.ResponsablePromocionBean;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.util.ArrayList;
import java.util.List;

public interface PromocionDao {

    /**
     * Obtiene todas las Promociones existentes
     *
     * @return ArrayList de las promociones
     * @throws cr.ac.una.reg.info.exceptions.ExceptionConnection
     */
    ArrayList<PromocionBean> ListarPromociones() throws ExceptionConnection;

    ArrayList<PromocionBean> ListarPromocionesPorConvenio(String codigo) throws ExceptionConnection;

    ArrayList<ResponsablePromocionBean> getResponsablesPromocion(int numeroPromocion) throws ExceptionConnection;

    void ingresarPromocion(PromocionBean promocion) throws ExceptionConnection;

    void ingresarResponsablesPromocion(List<ResponsablePromocionBean> responsablesPromocion) throws ExceptionConnection;

    int getUltimoIndexPromociones() throws ExceptionConnection;

    void actualizarPromocion(PromocionBean promocion) throws ExceptionConnection;
    
    void eliminarResponsablesPromocion(List<ResponsablePromocionBean> responsablesPromocion) throws ExceptionConnection;
    
    ArrayList<CarreraBean> listarCarrerasPorPromocion(int numeroPromocion) throws ExceptionConnection;   
    
    ArrayList<CarreraBean> listarCarrerasPorCodPromocion(String codigoPromocion) throws ExceptionConnection;   
}
