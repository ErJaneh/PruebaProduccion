/**
*@autor: Maria Lopez Valverde
*version: 1.0
*since: 1.0
* En esta clase se realiza el manejo de las funcionalidades entre pantalla y base de datos
**/
package cr.ac.una.reg.info.controllers;

import cr.ac.una.reg.info.beans.CarreraBean;
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
import org.icefaces.ace.event.SelectEvent;

/**
 * @author Mary
 */
@ManagedBean()
@SessionScoped

//VARIALES A UTILIZAR PARA LOS DIFERENTES MÉTODOS EN LA CLASE MantenimientoSolicitudController
public class MantenimientoSolicitudController implements Serializable{// inicio clase MantenimientoSolicitudController
    private SolicitudBusiness solicitudBusiness; //variable para invocar metodos del business
    private PromocionBusiness promocionBusiness; //variable para poder llamar los métodos del PromocionBusiness
    private SolicitudBean solicitudBeanSelected = new SolicitudBean(); //variable para la solicitud sobre la que se va atrabajar
    private PromocionBean promocionBean; //variable para obtener la promoción
    private boolean Editar = false; //variable para saber si se va a editar
    private boolean Mostrar=true; //variable para mostrar el modal
    private boolean tieneAdecuacion = false; //variable para saber si tiene adecuacion
    private boolean dobleCarrera = false;
    private List<CarreraBean> carrerasPorPromocion;
    private List<ConvenioBean> listaConvenios;
    private List<PromocionBean> listaPromociones;
    private List<SolicitudBean> listaSolicitudes;
    private List<SolicitudBean> listaSolicitudesPorPromocion;
    private String codigoConvenioSelected = "";
    private PromocionBean promocionSelected;
    private final ConveniosBusiness conveniosBusiness;
    String estadoSolicitud1="";
    String estadoSolicitud2=""; 
    String carrera1="";
    String carrera2="";
    private String numeroPromocionSelected;
    private boolean colorEditar = true;    
    private ManejoFacesContext manejoFacesContext;
    private static final String REDIRECCION_PAG_WARNING = "";
    
    //INICIALIZACION DE LAS VARIABLES EN EL CONTRUCTOR DE LA CLASE
    public MantenimientoSolicitudController() throws ExceptionConnection, ExceptionGeneral {
        this.listaConvenios = new ArrayList();
        this.listaPromociones = new ArrayList();
        this.listaSolicitudes = new ArrayList();
        this.listaSolicitudesPorPromocion=new ArrayList();
        this.solicitudBusiness= new SolicitudBusiness();
        this.promocionBean= new PromocionBean();
        this.promocionBusiness = new PromocionBusiness();
        this.promocionSelected = null;
        this.conveniosBusiness = new ConveniosBusiness();
        cargarGruposDeInteres();
    }
    
    //CARGAR COMBO DE GRUPOS DE INTERES        
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
    
    //BUSCA LAS SOLICITUDES POR LA PROMOCION SELECCIONADA
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
    
    //HANDLER QUE AL SELECCIONAR UN GRUPO DE INTERES BUSCA LAS PROMOCIONES ASOCIADAS    
    public void promocionChange(ValueChangeEvent event) throws ExceptionGeneral {
        Object value = event.getNewValue();
        if (value != null) {
            listaPromociones = promocionBusiness.ListarPromocionesPorConvenio(value.toString());           
        }
    }
    
    public void consultaEstadoSolicitud() throws ExceptionGeneral{
        this.carrerasPorPromocion();
        carrera1 = this.solicitudBeanSelected.getCodigoPromocion1().substring(this.solicitudBeanSelected.getCodigoPromocion1().indexOf('-', 6)+1);
        this.estadoSolicitud1 = getDescripcionEstadoSolicitud(solicitudBeanSelected.getEstadoSolicitud1());
        if(this.solicitudBeanSelected.getCodigoPromocion2()!=null){
            carrera2 = this.solicitudBeanSelected.getCodigoPromocion2().substring(this.solicitudBeanSelected.getCodigoPromocion2().indexOf('-', 6)+1);
            this.estadoSolicitud2 = getDescripcionEstadoSolicitud(solicitudBeanSelected.getEstadoSolicitud2());
        }
    }
    
    public void selectListener(SelectEvent event) {
        solicitudBeanSelected = (SolicitudBean) event.getObject();
    }
    
    //MÉTODO PARA ACTUALIZAR LA SOLICITUD  
    public void ActualizarSolicitudListener(ActionEvent evento) throws ExceptionGeneral {
        String codigoPromocion = this.solicitudBeanSelected.getCodigoPromocion1().substring(0, this.solicitudBeanSelected.getCodigoPromocion1().indexOf('-', 6)); 
        this.solicitudBeanSelected.setCodigoPromocion1(codigoPromocion + '-' + carrera1);
        if(this.solicitudBeanSelected.getCodigoPromocion2()!=null)
            this.solicitudBeanSelected.setCodigoPromocion2(codigoPromocion + '-' + carrera2);     
        solicitudBeanSelected.setAdecuacion(tieneAdecuacion == true ? 'S' : 'N');
        this.solicitudBeanSelected.setDescripcionCarrera1(getDescripcionCarrera(carrera1));
        this.solicitudBeanSelected.setDescripcionCarrera2(getDescripcionCarrera(carrera2));
        this.solicitudBusiness.actualizarSolicitud(this.solicitudBeanSelected);
        this.deshabilitaEdicion(null);
    }
    
    //MÉTODO PARA PERMITIR EDITAR LA SOLICITUD
    public void permiteEdicion(ActionEvent event){
        if(!this.Editar){
            this.Editar= true; 
            this.Mostrar=false;
            this.colorEditar = false;
        }
        else{
            this.Editar= false;
            this.Mostrar=true;
            this.colorEditar = true;
        }
    }
  
    //MÉTODO QUE DEVUELVE SI ESTÁ EN MODO MOSTRAR MODAL
    public boolean isMostrar(){
        return this.Mostrar;
    }
    
    //MÉTODO QUE DEVUELVE SI ESTÁ EN MODO EDITAR MODAL
    public boolean isEditar() {
        return Editar;
    }
       
    public void carrerasPorPromocion() throws ExceptionGeneral{
        for(PromocionBean p : listaPromociones)
            if(p.getNumeroPromocion()==solicitudBeanSelected.getNumeroPromocion()){
                this.carrerasPorPromocion = p.getCarrerasPromocion();
                return;
            }    
    }
    
    public String getDescripcionCarrera(String codigoCarrera){
        for(CarreraBean c : this.carrerasPorPromocion)
            if(c.getCodigo().equals(codigoCarrera))
                return c.getDescripcion();
        return "";
    }
    
    public void habilitaEdicion(ActionEvent event){
        this.Editar= true;
        this.Mostrar = false;
        this.colorEditar=false;
    }
    
    public void deshabilitaEdicion(ActionEvent event){
        this.Editar= false;
        this.Mostrar = true;
        this.colorEditar=true;
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

    //-------------------MÉTODOS GET----------------------------------
    
    //MÉTODO GET PARA LA SOLICITUD SELECCIONADA
    public SolicitudBean getSolicitudBeanSelected() {
        return solicitudBeanSelected;
    }
    
    public String getCodigoConvenioSelected() {
        return this.codigoConvenioSelected;
    }
    
    //MÉTODO GET PARA MOSTRAR SI LA PERSONA TIENE ADECUACIÓN
    public boolean getAdecuacion() {
        return solicitudBeanSelected.getAdecuacion()=='S';
    }

    public String getEstadoSolicitud1() {
        return estadoSolicitud1;
    }

    public String getEstadoSolicitud2() {
        return estadoSolicitud2;
    }

    public boolean isColorEditar() {
        return colorEditar;
    }

    public String getNumeroPromocionSelected() {
        return this.numeroPromocionSelected;
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
 
    //---------------------MÉTODOS SET--------------------
    
    //MÉTODO SET PARA LA SOLICITUD SELECCIONADA
    public void setSolicitudBeanSelected(SolicitudBean x) {
        this.solicitudBeanSelected = x;
    } 
    
    public List<ConvenioBean> getListaConvenios() {
        return this.listaConvenios;
    }

    public void setAdecuacion(boolean tieneAdecuacion) {
        this.tieneAdecuacion = tieneAdecuacion;
    }

    public void setEstadoSolicitud1(String estadoSolicitud1) {
        this.estadoSolicitud1 = estadoSolicitud1;
    }

    public void setEstadoSolicitud2(String estadoSolicitud2) {
        this.estadoSolicitud2 = estadoSolicitud2;
    }

    public void setColorEditar(boolean colorEditar) {
        this.colorEditar = colorEditar;
    }
    
    public void setEditar(boolean EditarS) {
        this.Editar = EditarS;
    }

    public List<CarreraBean> getCarrerasPorPromocion() {
        return carrerasPorPromocion;
    }

    public void setCarrerasPorPromocion(List<CarreraBean> carrerasPorPromocion) {
        this.carrerasPorPromocion = carrerasPorPromocion;
    }

    public String getCarrera1() {
        return carrera1;
    }

    public void setCarrera1(String carrera1) {
        this.carrera1 = carrera1;
    }

    public String getCarrera2() {
        return carrera2;
    }

    public void setCarrera2(String carrera2) {
        this.carrera2 = carrera2;
    }
    
    public void setListaConvenios(List<ConvenioBean> listaConvenios) {
        this.listaConvenios = listaConvenios;
    }

    public void setNumeroPromocionSelected(String numeroPromocionSelected) {
        this.numeroPromocionSelected = numeroPromocionSelected;
    }

    public void setListaPromociones(List<PromocionBean> listaPromociones) {
        this.listaPromociones = listaPromociones;
    }

    public void setCodigoConvenioSelected(String codigoConvenioSelected) {
        this.codigoConvenioSelected = codigoConvenioSelected;
    }

    public void setDobleCarrera(boolean dobleCarrera) {
        this.dobleCarrera = dobleCarrera;
    }

  }//final clase MantenimientoSolicitudController