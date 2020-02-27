/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.business;

import cr.ac.una.reg.info.beans.CantonBean;
import cr.ac.una.reg.info.dao.CantonDaoImp;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.exceptions.ExceptionGeneral;
import java.util.List;

/**
 *
 * @author sylin
 */
public class CantonBusiness {
    private final CantonDaoImp cantonDaoImp;

    public CantonBusiness() throws ExceptionConnection {
        this.cantonDaoImp = new CantonDaoImp();
    }
    
    public List<CantonBean> ListarCantonPorProvincia(String provincia) throws ExceptionGeneral {
        List<CantonBean> listaCantones = null;
        try {
            listaCantones = this.cantonDaoImp.ListarCantonPorProvincia(provincia);
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "cantonBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar los registros ...", ex.toString(), 1, false, 1, "cantonBusiness", 1);
        }
        return listaCantones;
    }
    
    public CantonBean cantonPorCodigo(String canton) throws ExceptionGeneral {
        CantonBean aux = null;

        String aux1 = String.valueOf(canton.charAt(0));
        String aux2 = String.valueOf(canton.charAt(1));
        String aux3 = String.valueOf(canton.charAt(2));

        String cantonFinal = aux1 + aux2 + aux3 + "00";

        try {
            aux = this.cantonDaoImp.CantonPorCodigo(cantonFinal);
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "provinciaBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar los registros ...", ex.toString(), 1, false, 1, "provinciaBusiness", 1);
        }
        return aux;
    }
}
