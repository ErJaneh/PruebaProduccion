/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.business;

import cr.ac.una.reg.info.beans.EscuelaBean;
import cr.ac.una.reg.info.dao.EscuelaDaoImp;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.exceptions.ExceptionGeneral;
import java.util.List;

/**
 *
 * @author MSI
 */
public class EscuelaBusiness {
    private final EscuelaDaoImp escuelaDaoImp;

    public EscuelaBusiness() throws ExceptionConnection {
        this.escuelaDaoImp = new EscuelaDaoImp();
    }
    
    public List<EscuelaBean> ListarEscuelas() throws ExceptionGeneral {
        List<EscuelaBean> listaEscuelas = null;
        try {
            listaEscuelas = this.escuelaDaoImp.ListarEscuelas();
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "escuelaBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar los registros ...", ex.toString(), 1, false, 1, "escuelaBusiness", 1);
        }
        return listaEscuelas;
    }
}
