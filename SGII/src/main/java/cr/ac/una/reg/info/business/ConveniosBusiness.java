/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.business;

import cr.ac.una.reg.info.beans.ConvenioBean;
import cr.ac.una.reg.info.dao.ConveniosDaoImp;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.exceptions.ExceptionGeneral;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author MSI
 */
public class ConveniosBusiness implements Serializable{
    private final ConveniosDaoImp conveniosDaoImp;

    public ConveniosBusiness() throws ExceptionConnection {
        this.conveniosDaoImp = new ConveniosDaoImp();
    }
    
    public List<ConvenioBean> ListarConvenios() throws ExceptionGeneral {
        List<ConvenioBean> listaPromociones = null;
        try {
            listaPromociones = this.conveniosDaoImp.ListarConvenios();
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "convenioBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar los registros ...", ex.toString(), 1, false, 1, "convenioBusiness", 1);
        }
        return listaPromociones;
    }
    
}
