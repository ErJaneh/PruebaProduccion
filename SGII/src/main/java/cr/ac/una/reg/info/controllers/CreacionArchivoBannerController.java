/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author MSI
 */
@ManagedBean()
@SessionScoped
public class CreacionArchivoBannerController implements Serializable{
    private List<ConvenioBean> listaConvenios;
    private List<PromocionBean> listaPromociones;
    private List<SolicitudBean> listaSolicitudes;
    private String numeroPromocionSelected;
    private String codigoConvenioSelected = "";
    private PromocionBean promocionSelected;
    private int postulantesAdmitidos = 0;
    private int postulantesNoAdmitidos = 0;
    private int totalPostulantes = 0;
    private boolean dobleCarrera = false;
    private final ConveniosBusiness conveniosBusiness;
    private final PromocionBusiness promocionBusiness;
    private final SolicitudBusiness solicitudBusiness;
    private ManejoFacesContext manejoFacesContext;
    private static final String REDIRECCION_PAG_WARNING = "";

    public CreacionArchivoBannerController() throws ExceptionConnection {
        this.conveniosBusiness = new ConveniosBusiness();
        this.promocionBusiness = new PromocionBusiness();
        this.solicitudBusiness = new SolicitudBusiness();
        this.listaConvenios = new ArrayList();
        this.listaPromociones = new ArrayList();
        this.listaSolicitudes = new ArrayList();
        this.promocionSelected = null;
        cargarGruposDeInteres();
    }

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
    
    public void promocionChange(ValueChangeEvent event) throws ExceptionGeneral {
        Object value = event.getNewValue();
        if (value != null) {
            listaPromociones = promocionBusiness.ListarPromocionesPorConvenio(value.toString());           
        }
    }
    
    public void actualizarListadoSolicitudes() throws ExceptionGeneral {
        try {
            this.listaSolicitudes.clear();
            this.listaSolicitudes = this.solicitudBusiness.getSolicitudPorPromocion(this.numeroPromocionSelected);
        } catch (ExceptionGeneral ex) {
            WarningBean warningBean = new WarningBean();
            warningBean.setMensajeSimple("error no capturado..." + ex.toString());
        }
    }
    
    public void actualizaDatosPorMostrar() throws ExceptionGeneral{
        this.actualizarListadoSolicitudes();
        for(PromocionBean p: listaPromociones)
            if(p.getNumeroPromocion() == Integer.valueOf(this.numeroPromocionSelected))
                this.promocionSelected = p;
        this.dobleCarrera = this.promocionSelected.getCarrerasPromocion().size()>1; 
        this.datosSolicitudesPromocion();
    }
              
    public void datosSolicitudesPromocion(){
        this.totalPostulantes = this.listaSolicitudes.size();
        if(dobleCarrera)
            for(SolicitudBean s1: this.listaSolicitudes)
                if(s1.getEstadoSolicitud1() == 2 || s1.getEstadoSolicitud2() == 2)
                    this.postulantesAdmitidos++;           
        else
            for(SolicitudBean s2: this.listaSolicitudes)
                if(s2.getEstadoSolicitud1() == 2)
                    this.postulantesAdmitidos++;                  
        this.postulantesNoAdmitidos = this.totalPostulantes - this.postulantesAdmitidos;
    }
    

    public List<ConvenioBean> getListaConvenios() {
        return listaConvenios;
    }

    public void setListaConvenios(List<ConvenioBean> listaConvenios) {
        this.listaConvenios = listaConvenios;
    }

    public List<PromocionBean> getListaPromociones() {
        return listaPromociones;
    }

    public void setListaPromociones(List<PromocionBean> listaPromociones) {
        this.listaPromociones = listaPromociones;
    }

    public List<SolicitudBean> getListaSolicitudes() {
        return listaSolicitudes;
    }

    public void setListaSolicitudes(List<SolicitudBean> listaSolicitudes) {
        this.listaSolicitudes = listaSolicitudes;
    }

    public String getNumeroPromocionSelected() {
        return numeroPromocionSelected;
    }

    public void setNumeroPromocionSelected(String numeroPromocionSelected) {
        this.numeroPromocionSelected = numeroPromocionSelected;
    }

    public String getCodigoConvenioSelected() {
        return codigoConvenioSelected;
    }

    public void setCodigoConvenioSelected(String codigoConvenioSelected) {
        this.codigoConvenioSelected = codigoConvenioSelected;
    }

    public PromocionBean getPromocionSelected() {
        return promocionSelected;
    }

    public void setPromocionSelected(PromocionBean promocionSelected) {
        this.promocionSelected = promocionSelected;
    }

    public int getPostulantesAdmitidos() {
        return postulantesAdmitidos;
    }

    public void setPostulantesAdmitidos(int postulantesAdmitidos) {
        this.postulantesAdmitidos = postulantesAdmitidos;
    }

    public int getPostulantesNoAdmitidos() {
        return postulantesNoAdmitidos;
    }

    public void setPostulantesNoAdmitidos(int postulantesNoAdmitidos) {
        this.postulantesNoAdmitidos = postulantesNoAdmitidos;
    }

    public int getTotalPostulantes() {
        return totalPostulantes;
    }

    public void setTotalPostulantes(int totalPostulantes) {
        this.totalPostulantes = totalPostulantes;
    }

}
