/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.business;

import cr.ac.una.reg.info.beans.CantonBean;
import cr.ac.una.reg.info.beans.DistritoBean;
import cr.ac.una.reg.info.dao.DistritoDaoImp;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.exceptions.ExceptionGeneral;
import java.util.List;

/**
 *
 * @author sylin
 */
public class DistritoBusiness {

    private final DistritoDaoImp distritoDaoImp;

    public DistritoBusiness() throws ExceptionConnection {
        this.distritoDaoImp = new DistritoDaoImp();
    }

    public List<DistritoBean> ListarDistritoPorCanton(String canton) throws ExceptionGeneral {
        List<DistritoBean> listaDistritos = null;
        try {
            listaDistritos = this.distritoDaoImp.ListarDistritoPorCanton(canton);
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "distritoBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar los registros ...", ex.toString(), 1, false, 1, "distritoBusiness", 1);
        }
        return listaDistritos;
    }

    public DistritoBean distritoPorCodigo(String distrito) throws ExceptionGeneral {
        DistritoBean aux = null;
        try {
            aux = this.distritoDaoImp.DistritoPorCodigo(distrito);
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "provinciaBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar los registros ...", ex.toString(), 1, false, 1, "provinciaBusiness", 1);
        }
        return aux;
    }

}
