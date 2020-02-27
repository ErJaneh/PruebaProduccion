/**
 *
 * @author Janel Garces Castillo
 * @version 1.0
 * @since 1.0
 *
 *
 */
package cr.ac.una.reg.info.business;

import cr.ac.una.reg.info.beans.SolicitudBean;
import cr.ac.una.reg.info.dao.SolicitudDaoImp;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.exceptions.ExceptionGeneral;
import java.util.List;

public class SolicitudBusiness {

    private SolicitudDaoImp solicitudDaoImp;

    public List<SolicitudBean> ListarSolicitud() throws ExceptionGeneral {
        List<SolicitudBean> listaSolicitud = null;
        try {
            listaSolicitud = this.solicitudDaoImp.ListarSolicitud();
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "personaBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar Registros de personas ...", ex.toString(), 1, false, 1, "personaBusiness", 1);
        }
        return listaSolicitud;
    }

    public SolicitudBusiness() throws ExceptionConnection, ExceptionConnection {
        this.solicitudDaoImp = new SolicitudDaoImp();
    }

    public SolicitudBean getSolicitudPorPersona(SolicitudBean id) throws ExceptionGeneral {
        SolicitudBean solicitudID = null;
        try {
            solicitudID = this.solicitudDaoImp.ConsultarSolicitudPorIdentificacion(id);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar una solicitud ...", ex.toString(), 1, false, 1, "SolicitudBusiness", 1);
        }
        return solicitudID;
    }

    public void actualizarSolicitudConsultaPersona(SolicitudBean id) throws ExceptionGeneral {
        try {
            this.solicitudDaoImp.ModificarSolicitudConsultaPersona(id);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2004:" + "Problemas al modificar solictud...", ex.toString(), 1, false, 1, "SolicitudBusiness", 1);
        }
    }
    
    public SolicitudBean getSolicitudPorCodigo(SolicitudBean id) throws ExceptionGeneral {
        SolicitudBean solicitudID = null;
        try {
            solicitudID = this.solicitudDaoImp.ConsultarSolicitudPorCodigo(id);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar una solicitud ...", ex.toString(), 1, false, 1, "solicitudBusiness", 1);
        }
        return solicitudID;
    }

    public List<SolicitudBean> getSolicitudPorPromocion(String codigo) throws ExceptionGeneral {
        List<SolicitudBean> listaSolicitud = null;
        try {
            listaSolicitud = this.solicitudDaoImp.ListarSolicitudPorPromocion(codigo);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar una solicitud ...", ex.toString(), 1, false, 1, "solicitudBusiness", 1);
        }
        return listaSolicitud;
    }
    
    public void actualizarSolicitud(SolicitudBean sol)throws ExceptionGeneral{
        try{
            this.solicitudDaoImp.ModificarConsultaSolicitud(sol);
        }catch (Exception ex) {
            System.err.printf(String.format("EXCEPCION:'%s'%n",ex.getMessage()));
            throw new ExceptionGeneral("2004:" + "Problemas al modificar solictud...", ex.toString(), 1, false, 1, "SolicitudBusiness", 1);
        }
    }   
    
    public void ingresarSolicitudes(SolicitudBean p) throws ExceptionGeneral {
        try {
            this.solicitudDaoImp.ingresarSolicitud(p);
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "solicitudBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar los registros ...", ex.toString(), 1, false, 1, "solicitudBusiness", 1);
        }
    }
    
    public int recuperarConsecutivo(String identificacionSolicitud) throws ExceptionGeneral{
        try {
            return this.solicitudDaoImp.recuperarConsecutivo(identificacionSolicitud);
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "solicitudBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar los registros ...", ex.toString(), 1, false, 1, "solicitudBusiness", 1);
        }
    }
}
