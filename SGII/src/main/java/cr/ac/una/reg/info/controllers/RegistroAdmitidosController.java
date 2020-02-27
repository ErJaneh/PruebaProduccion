package cr.ac.una.reg.info.controllers;

import cr.ac.una.reg.info.beans.ConvenioBean;
import cr.ac.una.reg.info.beans.PromocionBean;
import cr.ac.una.reg.info.beans.SolicitudBean;
import cr.ac.una.reg.info.beans.WarningBean;
import cr.ac.una.reg.info.business.ConveniosBusiness;
import cr.ac.una.reg.info.business.PromocionBusiness;
import cr.ac.una.reg.info.business.SolicitudBusiness;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.exceptions.ExceptionGeneral;
import cr.ac.una.reg.info.tools.ManejoFacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author Janel
 */
@ManagedBean()
@SessionScoped
public class RegistroAdmitidosController implements Serializable {

    private List<ConvenioBean> listaConvenios;
    private List<PromocionBean> listaPromociones;
    private List<SolicitudBean> listaSolicitudesPorPromocion;

    private SolicitudBean solicitudSelected;
    private String numeroPromocionSelected;
    private String codigoConvenioSelected = "";
    private final ConveniosBusiness conveniosBusiness;
    private final PromocionBusiness promocionBusiness;
    private final SolicitudBusiness solicitudBusiness;

    private boolean dobleCarrera = false;

    private ManejoFacesContext manejoFacesContext;
    private static final String REDIRECCION_PAG_WARNING = "";

    //INICIALIZACION DE VARIABLES
    public RegistroAdmitidosController() throws ExceptionConnection {
        this.listaSolicitudesPorPromocion = null;
        this.conveniosBusiness = new ConveniosBusiness();
        this.promocionBusiness = new PromocionBusiness();
        this.solicitudBusiness = new SolicitudBusiness();
        cargarGruposDeInteres();
    }

    //METODO QUE CARGA TODOS LOS GRUPOS DE INTERES EXISTENTES
    public void cargarGruposDeInteres() {
        try {
            listaConvenios = this.conveniosBusiness.ListarConvenios();

        } catch (ExceptionGeneral exg) {
            WarningBean warningBean = new WarningBean();
            warningBean.setMensajeSimple(exg.getMensajeError() + exg.toString());
            this.manejoFacesContext.incluirObjetoSession(warningBean, "warningBean");
            this.manejoFacesContext.redireccionarFlujoWeb(REDIRECCION_PAG_WARNING);
        } catch (Exception ex) {
            WarningBean warningBean = new WarningBean();
            warningBean.setMensajeSimple("error no capturado..." + ex.toString());
        }
    }
    
        // METODO QUE DETECTA CUAL CONVENIO FUE ELEGIDO Y CARGA LAS PROMOCIONES ASOCIADAS A ESE CONVENIO
    public void promocionChange(ValueChangeEvent event) throws ExceptionGeneral {
        Object value = event.getNewValue();
        if (value != null) {
            listaPromociones = promocionBusiness.ListarPromocionesPorConvenio(value.toString());
        }
    }

    //METODO QUE BUSCA LAS SOLICITUDES ASOCIADAS A ESA PROMOCION Y ADEMAS TRAE LAS PERSONAS ASOCIADAS A ESAS PROMOCIONES
    public void buscarSolicitudesPorPromocion(ActionEvent event) throws ExceptionGeneral {
        String numeroDePromocionSeleccionada = this.numeroPromocionSelected;
        try {
            if(this.listaSolicitudesPorPromocion!=null)
                this.listaSolicitudesPorPromocion.clear();
            this.listaSolicitudesPorPromocion = this.solicitudBusiness.getSolicitudPorPromocion(numeroDePromocionSeleccionada);
            for(PromocionBean p: listaPromociones)
                if(p.getNumeroPromocion() == Integer.valueOf(this.numeroPromocionSelected))
                    this.dobleCarrera = p.getCarrerasPromocion().size()>1;            
        } catch (ExceptionGeneral ex) {
            WarningBean warningBean = new WarningBean();
            warningBean.setMensajeSimple("error no capturado..." + ex.toString());
        }
    }

    public void cambiarEstadoSolicitud1(SolicitudBean selected){
        if(dobleCarrera){
            if(selected.getEstadoSolicitud1() == 2){
                selected.setEstadoSolicitud1(3);
                if(selected.getEstadoSolicitud2() == 5)
                    selected.setEstadoSolicitud2(3);
            }
            else if(selected.getEstadoSolicitud1() == 1 || selected.getEstadoSolicitud1() == 3 || selected.getEstadoSolicitud1()==5){
                    selected.setEstadoSolicitud1(2);
                    selected.setEstadoSolicitud2(5);             
            }
        }
        else
            if(selected.getEstadoSolicitud1()==2)
                selected.setEstadoSolicitud1(3); 
            else if(selected.getEstadoSolicitud1()==1 || selected.getEstadoSolicitud1()==3)
                    selected.setEstadoSolicitud1(2);     
    }
    
    public void cambiarEstadoSolicitud2(SolicitudBean selected){
        if(selected.getEstadoSolicitud2() == 2){
            selected.setEstadoSolicitud2(3);
            if(selected.getEstadoSolicitud1() == 5)
                    selected.setEstadoSolicitud1(3);
        }
        else if(selected.getEstadoSolicitud2() == 1 || selected.getEstadoSolicitud2() == 3 || selected.getEstadoSolicitud2()==5){
            selected.setEstadoSolicitud2(2);
            selected.setEstadoSolicitud1(5);
        }
    }
    
    public void modificarListado() throws ExceptionGeneral{
        for(SolicitudBean s : this.listaSolicitudesPorPromocion){
            if(s.getEstadoSolicitud1() == 1)
                s.setEstadoSolicitud1(3);
            if(dobleCarrera)
                if(s.getEstadoSolicitud2() == 1)
                    s.setEstadoSolicitud2(3);
            this.solicitudBusiness.actualizarSolicitud(s);        
        }       
    }

    public String getDescripcionEstadoSolicitud(Integer estado){
        switch(estado){
            case 1: return "En proceso"; 
            case 2: return "Admitido";
            case 3: return "Lista de espera";
            case 4: return "No cumple requisitos";
            case 5: return "No admitido";
            case 6: return "Desactivada";
            case 7: return "Renuncia";
            default: return "Desconocido";
        }
    }

    //METODOS GET DE VARIABLES

    public SolicitudBean getSolicitudSelected() {
        return solicitudSelected;
    }

    public String getCodigoConvenioSelected() {
        return this.codigoConvenioSelected;
    }

    public String getNumeroPromocionSelected() {
        return this.numeroPromocionSelected;
    }

    public List<ConvenioBean> getListaConvenios() {
        return this.listaConvenios;
    }

    public List<PromocionBean> getListaPromociones() {
        return this.listaPromociones;
    }

    public List<SolicitudBean> getListaSolicitudesPorPromocion() {
        return this.listaSolicitudesPorPromocion;
    }

    public boolean isDobleCarrera() {
        return dobleCarrera;
    }

    //METODOS SET DE VARIABLES

    public void setSolicitudSelected(SolicitudBean solicitudSelected) {
        this.solicitudSelected = solicitudSelected;
    }

    public void setCodigoConvenioSelected(String codigoConvenio) {
        this.codigoConvenioSelected = codigoConvenio;
    }

    public void setNumeroPromocionSelected(String numeroPromocion) {
        this.numeroPromocionSelected = numeroPromocion;
    }

    public void setListaConvenios(List<ConvenioBean> listaConvenios) {
        this.listaConvenios = listaConvenios;
    }

    public void setListaPromociones(List<PromocionBean> listaPromo) {
        this.listaPromociones = listaPromo;
    }

    public void setListaSolicitudesPorPromocion(List<SolicitudBean> listaPromocionesPorSolicitud) {
        this.listaSolicitudesPorPromocion = listaPromocionesPorSolicitud;
    }

    public void setDobleCarrera(boolean dobleCarrera) {
        this.dobleCarrera = dobleCarrera;
    }

}
