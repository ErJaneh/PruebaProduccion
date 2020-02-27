/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.business;

import cr.ac.una.reg.info.beans.CarreraBean;
import cr.ac.una.reg.info.dao.CarreraDaoImp;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.exceptions.ExceptionGeneral;
import java.util.List;

/**
 *
 * @author MSI
 */
public class CarreraBusiness{
    private final CarreraDaoImp carreraDaoImp;

    public CarreraBusiness() throws ExceptionConnection {
        this.carreraDaoImp = new CarreraDaoImp();
    }
    
    public List<CarreraBean> ListarCarreras() throws ExceptionGeneral {
        List<CarreraBean> listaCarreras = null;
        try {
            listaCarreras = this.carreraDaoImp.ListarCarreras();
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "carreraBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar los registros ...", ex.toString(), 1, false, 1, "carreraBusiness", 1);
        }
        return listaCarreras;
    }
    
}
