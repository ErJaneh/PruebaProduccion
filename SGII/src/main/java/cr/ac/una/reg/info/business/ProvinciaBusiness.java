/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.business;

import cr.ac.una.reg.info.beans.ProvinciaBean;
import cr.ac.una.reg.info.dao.ProvinciaDaoImp;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.exceptions.ExceptionGeneral;
import java.util.List;

/**
 *
 * @author sylin
 */
public class ProvinciaBusiness {

    private final ProvinciaDaoImp provinciaDaoImp;

    public ProvinciaBusiness() throws ExceptionConnection {
        this.provinciaDaoImp = new ProvinciaDaoImp();
    }

    public List<ProvinciaBean> ListarProvincias() throws ExceptionGeneral {
        List<ProvinciaBean> listaProvincias = null;
        try {
            listaProvincias = this.provinciaDaoImp.ListarProvincias();
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "provinciaBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar los registros ...", ex.toString(), 1, false, 1, "provinciaBusiness", 1);
        }
        return listaProvincias;
    }

    public ProvinciaBean provinciaPorCodigo(String provincia) throws ExceptionGeneral {
        ProvinciaBean aux = null;
        try {
            aux = this.provinciaDaoImp.ProvinciaPorCodigo(provincia);
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "provinciaBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar los registros ...", ex.toString(), 1, false, 1, "provinciaBusiness", 1);
        }
        return aux;
    }

}
