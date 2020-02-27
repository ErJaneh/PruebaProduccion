/**

* @author Janel Garces Castillo
* @version 1.0
* @since 1.0

**/

package cr.ac.una.reg.info.business;

import cr.ac.una.reg.info.beans.GrupoIndigenaBean;
import cr.ac.una.reg.info.dao.GrupoIndigenaDaoImp;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.exceptions.ExceptionGeneral;
import java.util.List;

public class GrupoIndigenaBusiness {

    private GrupoIndigenaDaoImp grupoIndigenaDaoImp;

    public GrupoIndigenaBusiness() throws ExceptionConnection, ExceptionConnection {
        this.grupoIndigenaDaoImp = new GrupoIndigenaDaoImp();
    }

     public GrupoIndigenaBean getGrupoPorCodigo(GrupoIndigenaBean id) throws ExceptionGeneral {
        GrupoIndigenaBean grupo = null;
        try {
            grupo = this.grupoIndigenaDaoImp.buscarGrupoPorCodigo(id);
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "territorioBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar un grupo ...", ex.toString(), 1, false, 1, "grupoBusiness", 1);
        }
        return grupo;
    }
     
     
     /**
     * Sheng Yuan Lin *
     */
    public List<GrupoIndigenaBean> ListarGrupoIndigena() throws ExceptionGeneral {
        List<GrupoIndigenaBean> listaGruposIndigenas = null;
        try {
            listaGruposIndigenas = this.grupoIndigenaDaoImp.ListarGruposIndigenas();
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "GrupoIndigenaBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar los registros ...", ex.toString(), 1, false, 1, "GrupoIndigenaBusiness", 1);
        }
        return listaGruposIndigenas;
    }
}
