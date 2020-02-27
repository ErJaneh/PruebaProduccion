package cr.ac.una.reg.info.business;

import cr.ac.una.reg.info.beans.TerritorioIndigenaBean;
import cr.ac.una.reg.info.dao.TerritorioIndigenaDaoImp;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.exceptions.ExceptionGeneral;
import java.util.List;

public class TerritorioIndigenaBusiness {

    private TerritorioIndigenaDaoImp territorioIndigenaDaoImp;

    public TerritorioIndigenaBusiness() throws ExceptionConnection, ExceptionConnection {
        this.territorioIndigenaDaoImp = new TerritorioIndigenaDaoImp();
    }

    public TerritorioIndigenaBean getTerritorioPorCodigo(TerritorioIndigenaBean id) throws ExceptionGeneral {
        TerritorioIndigenaBean territorio = null;
        try {
            territorio = this.territorioIndigenaDaoImp.buscarTerritorioPorCodigo(id);
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "territorioBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar un territorio ...", ex.toString(), 1, false, 1, "territorioBusiness", 1);
        }
        return territorio;
    }

    /**
     * Sheng Yuan Lin *
     */
    public List<TerritorioIndigenaBean> ListarTerritorio() throws ExceptionGeneral {
        List<TerritorioIndigenaBean> listaTerritorio = null;
        try {
            listaTerritorio = this.territorioIndigenaDaoImp.ListarTerritoriosIndigenas();
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "TerritorioIndigenaBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar los registros ...", ex.toString(), 1, false, 1, "TerritorioIndigenaBusiness", 1);
        }
        return listaTerritorio;
    }
}
