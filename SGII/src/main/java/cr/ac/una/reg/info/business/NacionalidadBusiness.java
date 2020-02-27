/**
*@autor: Maria Lopez Valverde
*version: 1.0
*since: 1.0
* En esta clase se listan las nacionalidades con una invocación a la clase NacionalidadSQL.consultaCodigosNacionalidad()
**/
package cr.ac.una.reg.info.business;

import cr.ac.una.reg.info.beans.NacionalidadBean;
import cr.ac.una.reg.info.dao.NacionalidadDaoImp;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.exceptions.ExceptionGeneral;
import java.util.List;

public class NacionalidadBusiness {
    private final NacionalidadDaoImp nacionalidadDaoImp;
    
    public NacionalidadBusiness() throws ExceptionConnection{
        this.nacionalidadDaoImp = new NacionalidadDaoImp();
    }
    
    public List<NacionalidadBean> ListarNacionalidad()throws ExceptionGeneral{
        
         List<NacionalidadBean> listaNacionalidad = null;
        try {
            listaNacionalidad = this.nacionalidadDaoImp.ListarNacionalidad();
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "nacionalidadBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar los registros ...", ex.toString(), 1, false, 1, "nacionalidadBusiness", 1);
        }
        return listaNacionalidad;
    }
}
