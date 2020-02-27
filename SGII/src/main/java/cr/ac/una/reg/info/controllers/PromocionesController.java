/**
 *
 * @author Gabriel Araya Ruiz
 * @version 1.0
 * @since 1.0
 * 
 */

package cr.ac.una.reg.info.controllers;

import cr.ac.una.reg.info.beans.CarreraBean;
import cr.ac.una.reg.info.beans.ConvenioBean;
import cr.ac.una.reg.info.beans.EscuelaBean;
import cr.ac.una.reg.info.beans.PeriodoBean;
import cr.ac.una.reg.info.beans.PromocionBean;
import cr.ac.una.reg.info.beans.ResponsablePromocionBean;
import cr.ac.una.reg.info.beans.WarningBean;
import cr.ac.una.reg.info.business.CarreraBusiness;
import cr.ac.una.reg.info.business.ConveniosBusiness;
import cr.ac.una.reg.info.business.EscuelaBusiness;
import cr.ac.una.reg.info.business.PeriodoBusiness;
import cr.ac.una.reg.info.business.PromocionBusiness;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.exceptions.ExceptionGeneral;
import cr.ac.una.reg.info.tools.ManejoFacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;

@ManagedBean()
@SessionScoped
public class PromocionesController implements Serializable{
    private transient List<PromocionBean> listaPromociones;  //Lista utilizada para mostrar las promociones en el DataTable principal
    private transient List<ConvenioBean> listaGruposDeInteres; //Lista que contiene todos los grupos de interés
    private transient List<PeriodoBean> listaPeriodos; //Lista que contiene todos los periodos
    private transient List<EscuelaBean> listaEscuelas; //Lista que contiene todas las escuelas
    private transient List<CarreraBean> listaCarreras; //Lista que contiene todas las carreras
    private transient List<CarreraBean> listaCarrerasSeleccionadas = new ArrayList(); //Lista que guarda carreras seleccionadas al ingresar una promoción
    private transient List<ResponsablePromocionBean> listaIngresarResponsablesPromocion= new ArrayList(); //Lista que guarda los responsables al ingresar una promoción
    private transient List<ResponsablePromocionBean> listaEliminarResponsablesPromocion= new ArrayList(); //Lista que guarda los responsables para eiminar de una promoción en BD
    private String codigoCarreraSeleccionada=""; 
    private String codigoEscuelaSeleccionada="";
    private String descripcionResponsablePromocion="";
    private String correoResponsablePromocion="";
    private transient CarreraBean carreraSeleccionada = new CarreraBean();
    private transient ResponsablePromocionBean responsablePromocion = new ResponsablePromocionBean();
    private transient PromocionBean promocionBean = new PromocionBean();
    private transient PromocionBean promocionPorIngresar = new PromocionBean();
    private boolean switchEditar = false;
    private boolean switchMostrar = true;
    private int view = 0;
    private final transient PromocionBusiness promocionBusiness = new PromocionBusiness();
    private final transient ConveniosBusiness conveniosBusiness = new ConveniosBusiness();
    private final transient PeriodoBusiness periodoBusiness = new PeriodoBusiness();
    private final transient EscuelaBusiness escuelaBusiness = new EscuelaBusiness();
    private final transient CarreraBusiness carreraBusiness = new CarreraBusiness();
    private transient final ManejoFacesContext manejoFacesContext = new ManejoFacesContext();   
    private static final String REDIRECCION_PAG_WARNING = "";
    
    /**
     * Constructor de la clase PromocionesController
     * Inicializa las lista de Promociones,Grupos de Interés, Periodos, Escuelas y las fechas de la variable promocionPorIngresar
     * @throws cr.ac.una.reg.info.exceptions.ExceptionConnection
     * 
     */
    public PromocionesController() throws ExceptionConnection {  
        consultaListaPromociones();
        consultaListaGruposDeInteres();
        consultaPeriodos();
        consultaEscuelas();
        consultaCarreras();
        promocionPorIngresar.setFechaInicioPromocion(new java.util.Date());
        promocionPorIngresar.setFechaFinPromocion(new java.util.Date());
    }
    
    /**
     * Método que consulta a Base de datos por todas las promociones
     * Inicializa listaPromociones 
     */
    public void consultaListaPromociones() {
        try {
            listaPromociones = promocionBusiness.ListarPromociones();
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
    
    /**
     * Método que oculta,pone visibles y habilita para edición ciertos componentes en Promociones.xhtml
     * @param event 
     */
    public void habilitaEdicion(ActionEvent event){
        this.switchEditar= true;
        this.switchMostrar = false;
    }
    
    
    public void deshabilitaEdicion(ActionEvent event){
        this.switchEditar= false;
        this.switchMostrar = true;
    }
    
    /**
     * Método que consulta a Base de datos por todos los grupos de Interes
     * Inicializa listaGruposDeInteres 
     */
     public void consultaListaGruposDeInteres() {
        try {
            listaGruposDeInteres = conveniosBusiness.ListarConvenios();
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
    
     /**
      * Método que consulta a Base de datos por todos los periodos
      * Inicializa listaPeriodos 
      */
    public void consultaPeriodos(){
        try {
            listaPeriodos = periodoBusiness.ListarPeriodos();
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
    
    /**
      * Método que consulta a Base de datos por todas las escuelas de la UNA
      * Inicializa listaEscuelas 
      */
    public void consultaEscuelas(){
        try {
            listaEscuelas = escuelaBusiness.ListarEscuelas();
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
    
    
    public void consultaCarreras(){
        try {
            listaCarreras = carreraBusiness.ListarCarreras();
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
    
    /**
     * Método que valida la fecha de inicio contra la fecha final que se coloque al ingresar una promoción
     * @param context
     * @param component
     * @param value
     * @throws ValidatorException 
     */
    public void validaFechaInicio(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        java.util.Date date = (java.util.Date) value;
        if (date.after(this.promocionPorIngresar.getFechaFinPromocion())) {
            ((UIInput) component).setValid(false);
            String message = "ERROR: La fecha de inicio es mayor a la fecha de finalización.";
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
            FacesContext.getCurrentInstance().addMessage(component.getClientId(), facesMessage);
        }
    }

    /**
     * Método que valida la fecha final contra la fecha de inicio que se coloque al ingresar una promoción
     * @param context
     * @param component
     * @param value
     * @throws ValidatorException 
     */
    public void validaFechaFin(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        java.util.Date date = (java.util.Date) value;
        if (this.promocionPorIngresar.getFechaInicioPromocion().after(date)) {
            ((UIInput) component).setValid(false);
            String message = "ERROR: La fecha de finalización es menor a la fecha de inicio.";
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
            FacesContext.getCurrentInstance().addMessage(component.getClientId(), facesMessage);
        }
    }
    
    /**
     * Método que agrega una carrera a listaCarrerasSeleccionadas cuando se esta ingresando una promoción
     * @param event
     * @throws ExceptionGeneral 
     */
    public void agregarCarrera(ActionEvent event) throws ExceptionGeneral { 
        CarreraBean car = null;
        for(CarreraBean carreraTemp : listaCarreras)
            if(carreraTemp.getCodigo().equals(codigoCarreraSeleccionada))
                car = carreraTemp;
        if(car != null){
            this.listaCarrerasSeleccionadas.add(car);
            this.codigoCarreraSeleccionada="";
            this.codigoEscuelaSeleccionada="";
        }
    }
    
    /**
     * Método que elimina una carrera de listaCarrerasSeleccionadas
     * @param event 
     */
    public void elminarCarrera(ActionEvent event){
        this.listaCarrerasSeleccionadas.remove(this.carreraSeleccionada);
    }
    
    /**
     * Método que agrega un responsable a listaResponsablesPromocion cuando se esta ingresando una promoción
     * @param event 
     */
    public void agregarResponsable(ActionEvent event){
        ResponsablePromocionBean resp = new ResponsablePromocionBean();
        resp.setDescripcion(this.descripcionResponsablePromocion);
        resp.setCorreo(this.correoResponsablePromocion);
        this.listaIngresarResponsablesPromocion.add(resp);
        this.correoResponsablePromocion = "";
        this.descripcionResponsablePromocion = "";
    }

    /**
     * Método que elimina un responsable de listaResponsablesPromocion
     * @param event 
     */
    public void eliminarResponsable(ActionEvent event){
        this.listaIngresarResponsablesPromocion.remove(this.responsablePromocion);
    }
    
    public void agregarListaEliminarResponsable(){
        this.listaEliminarResponsablesPromocion.add(responsablePromocion);
        this.promocionBean.getResponsablesPromocion().remove(this.responsablePromocion);
    }
    
    public void agregarResponsableEditar(ActionEvent event){
        ResponsablePromocionBean resp = new ResponsablePromocionBean();
        resp.setNumeroPromocion(this.promocionBean.getNumeroPromocion());
        resp.setDescripcion(this.descripcionResponsablePromocion);
        resp.setCorreo(this.correoResponsablePromocion);
        this.promocionBean.getResponsablesPromocion().add(resp);
        this.listaIngresarResponsablesPromocion.add(resp);
        this.correoResponsablePromocion = "";
        this.descripcionResponsablePromocion = "";
    }
    
    /**
     * Método que inicializa todas las variables y listas utilizadas al ingresar una promoción
     */
    public void clean(){
        this.codigoCarreraSeleccionada="";
        this.codigoEscuelaSeleccionada="";
        this.correoResponsablePromocion="";
        this.descripcionResponsablePromocion="";
        this.listaCarrerasSeleccionadas = new ArrayList<>();
        this.listaIngresarResponsablesPromocion = new ArrayList<>();
        this.listaEliminarResponsablesPromocion= new ArrayList<>();
        this.promocionPorIngresar = new PromocionBean();
        promocionPorIngresar.setFechaInicioPromocion(new java.util.Date());
        promocionPorIngresar.setFechaFinPromocion(new java.util.Date());
    }
    
    /**
     * Método que ingresa las promociones o promoción a la Base de Datos, crea el código y obtiene número de promoción
     * @throws ExceptionGeneral 
     */
    public void ingresaPromocion() throws ExceptionGeneral{
        int index = this.promocionBusiness.getUltimoIndexPromociones()+1;
        String cod;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        for(ResponsablePromocionBean responsable : listaIngresarResponsablesPromocion)
            responsable.setNumeroPromocion(index);
 
        if(listaCarrerasSeleccionadas.size()>1){
            for(CarreraBean carrera : listaCarrerasSeleccionadas){
                promocionPorIngresar.setCarrerasPromocion(new ArrayList());
                promocionPorIngresar.getCarrerasPromocion().add(carrera);
                cod = promocionPorIngresar.getCodigoGrupoDeInteres()+"-"+this.promocionPorIngresar.getCodigoPeriodo()+"-"+carrera.getCodigo();
                promocionPorIngresar.setCodigoPromocion(cod);
                promocionPorIngresar.setNumeroPromocion(index);
                promocionBusiness.ingresarPromociones(promocionPorIngresar);
            }}
        else{
            promocionPorIngresar.setCarrerasPromocion(new ArrayList());
            promocionPorIngresar.getCarrerasPromocion().add(listaCarrerasSeleccionadas.get(0));
            cod = promocionPorIngresar.getCodigoGrupoDeInteres()+"-"+this.promocionPorIngresar.getCodigoPeriodo()+"-"+listaCarrerasSeleccionadas.get(0).getCodigo();
            promocionPorIngresar.setCodigoPromocion(cod);
            promocionPorIngresar.setNumeroPromocion(index);
            promocionBusiness.ingresarPromociones(promocionPorIngresar);
        }
        promocionBusiness.ingresarResponsablesPromocion(listaIngresarResponsablesPromocion);
        this.clean();  
        this.consultaListaPromociones();
    }
    
    
    
    public void actualizarPromocion()throws ExceptionGeneral{
        this.promocionBusiness.actualizarPromocion(promocionBean);
        this.promocionBusiness.ingresarResponsablesPromocion(listaIngresarResponsablesPromocion);
        this.promocionBusiness.eliminarResponsablesPromocion(listaEliminarResponsablesPromocion);
        this.consultaListaPromociones();
        this.clean();
        this.deshabilitaEdicion(null);
    }
    
    public void cambiarView(ActionEvent event){
        if(event.getComponent().getId().equals("informacionPromocion"))
            this.view = 1;
        else
            this.view = 2; 
    }
    
    public void redirect(String url) throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(url);
    }
   
    public List<PromocionBean> getListaPromociones() {
        return listaPromociones;
    }

    public void setListaPromociones(List<PromocionBean> listaPromociones) {
        this.listaPromociones = listaPromociones;
    }

    public PromocionBean getPromocionBean() {
        return promocionBean;
    }

    public void setPromocionBean(PromocionBean promocionBean) {
        this.promocionBean = promocionBean;
    }

    public boolean isSwitchEditar() {
        return switchEditar;
    }

    public void setSwitchEditar(boolean switchEditar) {
        this.switchEditar = switchEditar;
    }

    public boolean isSwitchMostrar() {
        return switchMostrar;
    }

    public void setSwitchMostrar(boolean switchMostrar) {
        this.switchMostrar = switchMostrar;
    }
    
    public List<ConvenioBean> getListaGruposDeInteres() {
        return listaGruposDeInteres;
    }

    public void setListaGruposDeInteres(List<ConvenioBean> listaGruposDeInteres) {
        this.listaGruposDeInteres = listaGruposDeInteres;
    }

    public List<PeriodoBean> getListaPeriodos() {
        return listaPeriodos;
    }

    public void setListaPeriodos(List<PeriodoBean> listaPeriodos) {
        this.listaPeriodos = listaPeriodos;
    }

    public List<EscuelaBean> getListaEscuelas() {
        return listaEscuelas;
    }

    public void setListaEscuelas(List<EscuelaBean> listaEscuelas) {
        this.listaEscuelas = listaEscuelas;
    }

    public List<CarreraBean> getListaCarreras() {
        return listaCarreras;
    }

    public void setListaCarreras(List<CarreraBean> listaCarreras) {
        this.listaCarreras = listaCarreras;
    }

    public List<CarreraBean> getListaCarrerasSeleccionadas() {
        return listaCarrerasSeleccionadas;
    }

    public void setListaCarrerasSeleccionadas(List<CarreraBean> listaCarrerasSeleccionadas) {
        this.listaCarrerasSeleccionadas = listaCarrerasSeleccionadas;
    }
    
    public String getCodigoCarreraSeleccionada() {
        return codigoCarreraSeleccionada;
    }

    public void setCodigoCarreraSeleccionada(String codigoCarreraSeleccionada) {
        this.codigoCarreraSeleccionada = codigoCarreraSeleccionada;
    }

    public String getCodigoEscuelaSeleccionada() {
        return codigoEscuelaSeleccionada;
    }

    public void setCodigoEscuelaSeleccionada(String codigoEscuelaSeleccionada) {
        this.codigoEscuelaSeleccionada = codigoEscuelaSeleccionada;
    }

    public List<ResponsablePromocionBean> getListaIngresarResponsablesPromocion() {
        return listaIngresarResponsablesPromocion;
    }

    public void setListaIngresarResponsablesPromocion(List<ResponsablePromocionBean> listaIngresarResponsablesPromocion) {
        this.listaIngresarResponsablesPromocion = listaIngresarResponsablesPromocion;
    }

    public String getDescripcionResponsablePromocion() {
        return descripcionResponsablePromocion;
    }

    public void setDescripcionResponsablePromocion(String descripcionResponsablePromocion) {
        this.descripcionResponsablePromocion = descripcionResponsablePromocion;
    }

    public String getCorreoResponsablePromocion() {
        return correoResponsablePromocion;
    }

    public void setCorreoResponsablePromocion(String correoResponsablePromocion) {
        this.correoResponsablePromocion = correoResponsablePromocion;
    }

    public PromocionBean getPromocionPorIngresar() {
        return promocionPorIngresar;
    }

    public void setPromocionPorIngresar(PromocionBean promocionPorIngresar) {
        this.promocionPorIngresar = promocionPorIngresar;
    }

    public CarreraBean getCarreraSeleccionada() {
        return carreraSeleccionada;
    }

    public void setCarreraSeleccionada(CarreraBean carreraSeleccionada) {
        this.carreraSeleccionada = carreraSeleccionada;
    }

    public ResponsablePromocionBean getResponsablePromocion() {
        return responsablePromocion;
    }

    public void setResponsablePromocion(ResponsablePromocionBean responsablePromocion) {
        this.responsablePromocion = responsablePromocion;
    }

    public List<ResponsablePromocionBean> getListaEliminarResponsablesPromocion() {
        return listaEliminarResponsablesPromocion;
    }

    public void setListaEliminarResponsablesPromocion(List<ResponsablePromocionBean> listaEliminarResponsablesPromocion) {
        this.listaEliminarResponsablesPromocion = listaEliminarResponsablesPromocion;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }
  
}
