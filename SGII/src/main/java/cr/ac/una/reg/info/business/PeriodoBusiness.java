/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.business;

import cr.ac.una.reg.info.beans.PeriodoBean;
import cr.ac.una.reg.info.dao.PeriodoDaoImp;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.exceptions.ExceptionGeneral;
import java.util.List;

/**
 *
 * @author MSI
 */
public class PeriodoBusiness {
    private final PeriodoDaoImp periodosDaoImp;

    public PeriodoBusiness() throws ExceptionConnection {
        this.periodosDaoImp = new PeriodoDaoImp();
    }
    
    public List<PeriodoBean> ListarPeriodos() throws ExceptionGeneral {
        List<PeriodoBean> listaPeriodos = null;
        try {
            listaPeriodos = this.periodosDaoImp.ListarPeriodos();
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "periodoBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar los registros ...", ex.toString(), 1, false, 1, "periodoBusiness", 1);
        }
        return listaPeriodos;
    }
}
