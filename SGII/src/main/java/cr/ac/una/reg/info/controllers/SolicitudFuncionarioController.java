/**
 *
 * @author Sheng Yuan Lin
 * @version 1.0
 * @since 1.0
 *
 *
 */
package cr.ac.una.reg.info.controllers;

import cr.ac.una.reg.info.beans.CantonBean;
import cr.ac.una.reg.info.beans.CarreraBean;
import cr.ac.una.reg.info.beans.ColegioBean;
import cr.ac.una.reg.info.beans.DistritoBean;
import cr.ac.una.reg.info.beans.EstadoCivilBean;
import cr.ac.una.reg.info.beans.GrupoIndigenaBean;
import cr.ac.una.reg.info.beans.NacionalidadBean;
import cr.ac.una.reg.info.beans.PersonaBean;
import cr.ac.una.reg.info.beans.PromocionBean;
import cr.ac.una.reg.info.beans.ProvinciaBean;
import cr.ac.una.reg.info.beans.SolicitudBean;
import cr.ac.una.reg.info.beans.TerritorioIndigenaBean;
import cr.ac.una.reg.info.beans.WarningBean;
import cr.ac.una.reg.info.business.CantonBusiness;
import cr.ac.una.reg.info.business.ColegioBusiness;
import cr.ac.una.reg.info.business.DistritoBusiness;
import cr.ac.una.reg.info.business.EstadoCivilBusiness;
import cr.ac.una.reg.info.business.GrupoIndigenaBusiness;
import cr.ac.una.reg.info.business.NacionalidadBusiness;
import cr.ac.una.reg.info.business.PersonaBusiness;
import cr.ac.una.reg.info.business.PromocionBusiness;
import cr.ac.una.reg.info.business.ProvinciaBusiness;
import cr.ac.una.reg.info.business.SolicitudBusiness;
import cr.ac.una.reg.info.business.TerritorioIndigenaBusiness;
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

@ManagedBean()
@SessionScoped
public class SolicitudFuncionarioController implements Serializable {

    private PersonaBean personaBean;
    private PersonaBean personaBeanSelected;
    private PersonaBusiness personaBusiness = new PersonaBusiness();
    private SolicitudBean solicitudBean;
    private SolicitudBusiness solicitudBusiness = new SolicitudBusiness();
    private boolean tieneAdecuacion = false;
    private List<ProvinciaBean> listaProvincias;
    private List<CantonBean> listaCantones;
    private List<DistritoBean> listaDistritos;
    private List<GrupoIndigenaBean> listaGrupoIndigena;
    private List<TerritorioIndigenaBean> listaTerritorioIndigena;
    private List<EstadoCivilBean> listaEstadoCivil;
    private List<NacionalidadBean> listaNacionalidad;
    private List<ColegioBean> listaColegios;
    private List<PromocionBean> listaPromociones;
    private final ProvinciaBusiness provinciasBusiness = new ProvinciaBusiness();
    private final CantonBusiness cantonBusiness = new CantonBusiness();
    private final DistritoBusiness distritoBusiness = new DistritoBusiness();
    private final TerritorioIndigenaBusiness territorioIndigenaBusiness = new TerritorioIndigenaBusiness();
    private final GrupoIndigenaBusiness grupoIndigenaBusiness = new GrupoIndigenaBusiness();
    private final EstadoCivilBusiness estadoCivilBusiness = new EstadoCivilBusiness();
    private final NacionalidadBusiness nacionalidadBusiness = new NacionalidadBusiness();
    private final ColegioBusiness colegioBusiness = new ColegioBusiness();
    private final PromocionBusiness promocionBusiness = new PromocionBusiness();
    private ManejoFacesContext manejoFacesContext;
    private static final String REDIRECCION_PAG_WARNING = "";
    private boolean esIndigena;
    private boolean adecuacion;
    private PersonaBean personaBuscar;
    private ColegioBean colegio;
    private TerritorioIndigenaBean territorioIndigena;
    private GrupoIndigenaBean grupoIndigena;
    private Boolean ocultarForm;
    private String identPersonaBuscada;
    private Boolean ingresado;
    private int promocion;
    private List<CarreraBean> carrerasPorPromocion;
    private Boolean testCarrera2;

    /**
     * Constructor de la clase SolicitudFuncionarioController Inicializa las
     * lista de Provincias, Estado Civil, Nacionalidad, Colegios y Promociones.
     * Inicializa Estado Civil en Soltero y Nacionalidad en Costa Rica
     * @throws cr.ac.una.reg.info.exceptions.ExceptionConnection
     * @throws cr.ac.una.reg.info.exceptions.ExceptionGeneral
     */
    public SolicitudFuncionarioController() throws ExceptionConnection, ExceptionGeneral {
        this.personaBean = new PersonaBean();
        this.personaBeanSelected = new PersonaBean();
        this.personaBuscar = new PersonaBean();
        this.solicitudBean = new SolicitudBean();
        consultarProvincias();
        consultarEstadoCivil();
        consultarNacionalidad();
        consultarColegios();
        consultarPromocion();
        solicitudBean.setCodigoEstadoCivil('S');
        solicitudBean.setCodigoNacionalidad("188");
        colegio = new ColegioBean();
        territorioIndigena = new TerritorioIndigenaBean();
        grupoIndigena = new GrupoIndigenaBean();
        ocultarForm = false;
        ingresado = false;
        testCarrera2=false;
        
    }

    /**
     * Método que consulta a Base de datos por todas las Provincias Inicializa
     * listaProvincias
     */
    public void consultarProvincias() {
        try {
            listaProvincias = provinciasBusiness.ListarProvincias();
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
    
    public void testC(){
        if(carrerasPorPromocion.size()>1){
        testCarrera2=true;
        }else{
        testCarrera2=false;}
    }
    
    /**
     * Método que consulta a Base de datos por todas los Estados Civiles
     * Inicializa listaEstadoCivil
     */
    public void consultarEstadoCivil() {
        try {
            listaEstadoCivil = estadoCivilBusiness.ListarEstadoCivil();
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
     * Método que consulta a Base de datos por la información de carreras 
     * de una promocion
     */
    public void carrerasPorPromocion() throws ExceptionGeneral{
         // BUSCAR LAS CARRERAS DESDE LA LISTA DE PROMOCIONES
         for (PromocionBean promoTemp : listaPromociones) {
                if (promoTemp.getNumeroPromocion()==promocion) {
                    carrerasPorPromocion=promoTemp.getCarrerasPromocion();
                    break;
                }
            }
    }

    
    /**
     * Método que consulta a Base de datos por la información de una Persona,
     * por medio de su Identificación Inicializa personaBeanSelected
     */
    public Boolean buscar() throws ExceptionGeneral {
        if (personaBuscar.getIdentificacion() != null) {
            personaBeanSelected = personaBusiness.getDetallePersona(personaBuscar);
            identPersonaBuscada = personaBeanSelected.getIdentificacion();
            colegio = this.colegioBusiness.colegioPorSolicitud(this.personaBeanSelected.getCodigoColegio());
            TerritorioIndigenaBean territorioAuxiliar = new TerritorioIndigenaBean();
            GrupoIndigenaBean grupoAuxiliar = new GrupoIndigenaBean();
            territorioAuxiliar.setCodigo(personaBeanSelected.getCodigoTerritorio());
            grupoAuxiliar.setCodigo(personaBeanSelected.getCodigoGrupoIndigena());
            territorioIndigena = this.territorioIndigenaBusiness.getTerritorioPorCodigo(territorioAuxiliar);
            grupoIndigena = this.grupoIndigenaBusiness.getGrupoPorCodigo(grupoAuxiliar);

        }
        return true;
    }

    /**
     * Método que consulta a Base de datos por la lista de Nacionalidades
     * Inicializa listaNacionalidad
     */
    public void consultarNacionalidad() {
        try {
            listaNacionalidad = nacionalidadBusiness.ListarNacionalidad();
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
     * Método que consulta a Base de datos por la lista de Promociones
     * Existentes Inicializa listaPromociones
     */
    public void consultarPromocion() {
        try {
            listaPromociones = promocionBusiness.ListarPromociones();
            for(PromocionBean xa: listaPromociones){             
                xa.setCodigoPromocion(xa.getCodigoPromocion().substring(0,xa.getCodigoPromocion().indexOf("-", xa.getCodigoPromocion().indexOf("-") + 1)));
            }
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
     * Método que valida que los campos REQUERIDOS del formulario estén llenos
     * @return 
     */
    public Boolean validar() {
        return personaBean.getIdentificacion() != null && personaBean.getNombre() != null && personaBean.getPrimerApellido() != null
                && personaBean.getSegundoApellido() != null && personaBean.getFecha() != null
                && personaBean.getTelefono() != null && solicitudBean.getGenero() != null
                && personaBean.getCorreoElectronico() != null && solicitudBean.getDireccionExacta() != null
                && personaBean.getCodigoColegio() != null && personaBean.getAnioGraduacionColegio() != null;
  //              && personaBean.getNotaColegio() != null;
    }

    /**
     * Método que valida que ingresa Persona y Solicitud Si la Información de la
     * Persona ya existe, le crea una nueva Solicitud Caso contrario, ingresa la
     * Persona y luego la Solicitud
     *
     * @throws cr.ac.una.reg.info.exceptions.ExceptionGeneral
     */
    public void ingresarSolicitud() throws ExceptionGeneral {
        String codigo;
        int consecutivo;
        String idSolicitud;
        PromocionBean promo = null;
        if (personaBeanSelected.getIdentificacion() != null) {
            for (PromocionBean promoTemp : listaPromociones) {
                if (promoTemp.getNumeroPromocion()==promocion) {
                    promo = promoTemp;
                    break;
                }
            }
            
            
            codigo = promo.getCodigoPromocion() + "-" + promo.getNumeroPromocion() + "-";
            consecutivo = solicitudBusiness.recuperarConsecutivo(codigo);
            consecutivo++;
            idSolicitud = codigo + consecutivo;
            solicitudBean.setIdentificacionSolicitud(idSolicitud); //test
            solicitudBean.setNumeroPromocion(promo.getNumeroPromocion()); //test
            solicitudBean.setIdentificacionPersona(personaBeanSelected.getIdentificacion());
            solicitudBean.setEstadoSolicitud1(1);
            if(carrerasPorPromocion.size()>1){
            solicitudBean.setEstadoSolicitud2(1);
            }
            solicitudBusiness.ingresarSolicitudes(solicitudBean);
            ingresado = true;
            limpiar();
        } else if (solicitudBean.getCodigoPromocion1() != null && validar()) {
            personaBean.setCodigoTipoIdentificacion("1"); //test
            if (esIndigena) {
                personaBean.setIndigena('S');
            }
            personaBean.setEstadoEnSistema("A");
            personaBusiness.ingresarPersona(personaBean);
            for (PromocionBean promoTemp : listaPromociones) {
                if (promoTemp.getNumeroPromocion()==promocion) {
                    promo = promoTemp;
                    break;
                }
            }
           // promo.setCodigoPromocion(promo.getCodigoPromocion().substring(0,promo.getCodigoPromocion().indexOf("-", promo.getCodigoPromocion().indexOf("-") + 1)));
            codigo = promo.getCodigoPromocion() + "-" + promo.getNumeroPromocion() + "-";
            consecutivo = solicitudBusiness.recuperarConsecutivo(codigo);
            consecutivo++;
            idSolicitud = codigo + consecutivo;
            solicitudBean.setIdentificacionSolicitud(idSolicitud); //test
            solicitudBean.setNumeroPromocion(promo.getNumeroPromocion()); //test
            solicitudBean.setIdentificacionPersona(personaBean.getIdentificacion());
            solicitudBean.setEstadoSolicitud1(1);
            if(carrerasPorPromocion.size()>1){
            solicitudBean.setEstadoSolicitud2(1);
            }
            solicitudBusiness.ingresarSolicitudes(solicitudBean);
            ingresado = true;
            limpiar();
        }
    }

    /**
     * Método que consulta a la Base de Datos la lista de Colegios Inicializa
     * listaColegios
     */
    public void consultarColegios() {
        try {
            listaColegios = colegioBusiness.ListarColegios();
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
     * Método que consulta a la Base de Datos la lista de Cantones una vez que
     * se haya escogido la Provincia Inicializa listaCantones
     *
     * @param event
     * @throws cr.ac.una.reg.info.exceptions.ExceptionGeneral
     */
    public void provinciaChange(ValueChangeEvent event) throws ExceptionGeneral {
        Object value = event.getNewValue();
        if (value != null) {
            listaCantones = cantonBusiness.ListarCantonPorProvincia(value.toString());
        }
    }
    
    
    
    public void promocionChange(ValueChangeEvent event) throws ExceptionGeneral {
        Object value = event.getNewValue();
        if (value != null) {
            promocion=(Integer)value;
            carrerasPorPromocion();
           testC();
        }
       
    }

    /**
     * Método que consulta a la Base de Datos la lista de Distritos una vez que
     * se haya escogido el Cantón Inicializa listaDistritos
     *
     * @param event
     * @throws cr.ac.una.reg.info.exceptions.ExceptionGeneral
     */
    public void cantonChange(ValueChangeEvent event) throws ExceptionGeneral {
        Object value = event.getNewValue();
        if (value != null) {
            listaDistritos = distritoBusiness.ListarDistritoPorCanton(value.toString());
        }
    }

    /**
     * Método que verifica si el checkbox de Indigena es marcado Inicializa
     * listaTerritorioIndigena y listaGrupoIndigena
     *
     * @param event
     * @throws cr.ac.una.reg.info.exceptions.ExceptionGeneral
     */
    public void indigenaChange(ValueChangeEvent event) throws ExceptionGeneral {
        if (esIndigena) {
            personaBean.setIndigena('S');
        } else {
            personaBean.setIndigena('N');
        }
        listaTerritorioIndigena = territorioIndigenaBusiness.ListarTerritorio();
        listaGrupoIndigena = grupoIndigenaBusiness.ListarGrupoIndigena();
    }

    public void adecuacionChange(ValueChangeEvent event) throws ExceptionGeneral {
        if (adecuacion) {
            solicitudBean.setAdecuacion('S');
        } else {
            solicitudBean.setAdecuacion('N');
        }
    }

    /**
     * Método que limpia las variables una vez que se ingrese la solicitud
     */
    public void limpiar() throws ExceptionGeneral {
        personaBeanSelected.setIdentificacion(null);
        personaBuscar.setIdentificacion("");
        personaBean.setIdentificacion("");
        personaBean.setNombre("");
        personaBean.setPrimerApellido("");
        personaBean.setSegundoApellido("");
        personaBean.setAnioGraduacionColegio(null);
        personaBean.setNotaColegio(0);
        personaBean.setFecha(new java.util.Date());
        personaBean.setCodigoGrupoIndigena(null);
        esIndigena = false;
        personaBean.setCorreoElectronico("");
        esIndigena = false;
        personaBean.setTelefono(null);
        solicitudBean.setDireccionExacta("");
        solicitudBean.setGenero("");
        personaBean.setCodigoColegio("");
        solicitudBean.setCodigoPromocion1("");
        identPersonaBuscada = "";
        listaProvincias = provinciasBusiness.ListarProvincias();
        solicitudBean.setCodigoUbicacion("");
        solicitudBean.setCodigoPromocion1("");
        solicitudBean.setCodigoPromocion2("");
        solicitudBean.setObservaciones("");
        promocion=0;
    }

    public Boolean personaEncontrada() {
        return personaBeanSelected.getIdentificacion() != null;
    }

    public Boolean personaNoEncontrada() {
        if (personaBeanSelected.getIdentificacion() != null) {
            return false;
        }
        ocultarForm = true;
        return true;
    }

    /**
     * Métodos Set/Get
     */
    public void mostrarPromocion(ActionEvent event) {
    }

    public PersonaBean getPersonaBean() {
        return personaBean;
    }

    public PersonaBean getPersonaBeanSelected() {
        return personaBeanSelected;
    }

    public PersonaBusiness getPersonaBusiness() {
        return personaBusiness;
    }

    public SolicitudBean getSolicitudBean() {
        return solicitudBean;
    }

    public SolicitudBusiness getSolicitudBusiness() {
        return solicitudBusiness;
    }

    public boolean isTieneAdecuacion() {
        return tieneAdecuacion;
    }

    public void setPersonaBean(PersonaBean personaBean) {
        this.personaBean = personaBean;
    }

    public void setPersonaBeanSelected(PersonaBean personaBeanSelected) {
        this.personaBeanSelected = personaBeanSelected;
    }

    public void setPersonaBusiness(PersonaBusiness personaBusiness) {
        this.personaBusiness = personaBusiness;
    }

    public void setSolicitudBean(SolicitudBean solicitudBean) {
        this.solicitudBean = solicitudBean;
    }

    public void setSolicitudBusiness(SolicitudBusiness solicitudBusiness) {
        this.solicitudBusiness = solicitudBusiness;
    }

    public void setTieneAdecuacion(boolean tieneAdecuacion) {
        this.tieneAdecuacion = tieneAdecuacion;
    }

    public List<ProvinciaBean> getListaProvincias() {
        return listaProvincias;
    }

    public void setListaProvincias(List<ProvinciaBean> listaProvincias) {
        this.listaProvincias = listaProvincias;
    }

    public List<CantonBean> getListaCantones() {
        return listaCantones;
    }

    public void setListaCantones(List<CantonBean> listaCantones) {
        this.listaCantones = listaCantones;
    }

    public void setListaDistritos(List<DistritoBean> listaDistritos) {
        this.listaDistritos = listaDistritos;
    }

    public List<DistritoBean> getListaDistritos() {
        return listaDistritos;
    }

    public CantonBusiness getCantonBusiness() {
        return cantonBusiness;
    }

    public DistritoBusiness getDistritoBusiness() {
        return distritoBusiness;
    }

    public boolean isEsIndigena() {
        return esIndigena;
    }

    public void setEsIndigena(boolean esIndigena) {
        this.esIndigena = esIndigena;
    }

    public List<GrupoIndigenaBean> getListaGrupoIndigena() {
        return listaGrupoIndigena;
    }

    public List<TerritorioIndigenaBean> getListaTerritorioIndigena() {
        return listaTerritorioIndigena;
    }

    public List<EstadoCivilBean> getListaEstadoCivil() {
        return listaEstadoCivil;
    }

    public EstadoCivilBusiness getEstadoCivilBusiness() {
        return estadoCivilBusiness;
    }

    public List<NacionalidadBean> getListaNacionalidad() {
        return listaNacionalidad;
    }

    public NacionalidadBusiness getNacionalidadBusiness() {
        return nacionalidadBusiness;
    }

    public List<ColegioBean> getListaColegios() {
        return listaColegios;
    }

    public ProvinciaBusiness getProvinciasBusiness() {
        return provinciasBusiness;
    }

    public ColegioBusiness getColegioBusiness() {
        return colegioBusiness;
    }

    public boolean isAdecuacion() {
        return adecuacion;
    }

    public void setAdecuacion(boolean adecuacion) {
        this.adecuacion = adecuacion;
    }

    public List<PromocionBean> getListaPromociones() {
        return listaPromociones;
    }

    public PromocionBusiness getPromocionBusiness() {
        return promocionBusiness;
    }

    public PersonaBean getPersonaBuscar() {
        return personaBuscar;
    }

    public void setPersonaBuscar(PersonaBean personaBuscar) {
        this.personaBuscar = personaBuscar;
    }

    public ColegioBean getColegio() {
        return colegio;
    }

    public TerritorioIndigenaBean getTerritorioIndigena() {
        return territorioIndigena;
    }

    public GrupoIndigenaBean getGrupoIndigena() {
        return grupoIndigena;
    }

    public Boolean getPersonaExistente() {
        return ocultarForm;
    }

    public void setOcultarForm(Boolean ocultarForm) {
        this.ocultarForm = ocultarForm;
    }

    public Boolean getOcultarForm() {
        return ocultarForm;
    }

    public String getIdentPersonaBuscada() {
        return identPersonaBuscada;
    }

    public void setIdentPersonaBuscada(String identPersonaBuscada) {
        this.identPersonaBuscada = identPersonaBuscada;
    }

    public Boolean getIngresado() {
        return ingresado;
    }

    public void setIngresado(Boolean ingresado) {
        this.ingresado = ingresado;
    }

    public int getPromocion() {
        return promocion;
    }

    public void setPromocion(int promocion) {
        this.promocion = promocion;
    }

    public List<CarreraBean> getCarrerasPorPromocion() {
        return carrerasPorPromocion;
    }

    public void setCarrerasPorPromocion(List<CarreraBean> carrerasPorPromocion) {
        this.carrerasPorPromocion = carrerasPorPromocion;
    }

    public Boolean getTestCarrera2() {
        return testCarrera2;
    }

    public void setTestCarrera2(Boolean testCarrera2) {
        this.testCarrera2 = testCarrera2;
    }

}
