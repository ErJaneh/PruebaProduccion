/**
 *
 * @author Janel Garces Castillo
 * @version 1.0
 * @since 1.0
 *
 *
 */
package cr.ac.una.reg.info.controllers;

import cr.ac.una.reg.info.beans.ColegioBean;
import cr.ac.una.reg.info.beans.GrupoIndigenaBean;
import cr.ac.una.reg.info.beans.PersonaBean;
import cr.ac.una.reg.info.beans.SolicitudBean;
import cr.ac.una.reg.info.beans.TerritorioIndigenaBean;
import cr.ac.una.reg.info.beans.WarningBean;
import cr.ac.una.reg.info.business.ColegioBusiness;
import cr.ac.una.reg.info.business.GrupoIndigenaBusiness;
import cr.ac.una.reg.info.business.PersonaBusiness;
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
import org.icefaces.ace.event.SelectEvent;

@ManagedBean()
@SessionScoped
public class ConsultaPostulantesController implements Serializable {

    private List<PersonaBean> listaPersona;
    private List<ColegioBean> listaColegio;
    private List<TerritorioIndigenaBean> listaTerritorio;
    private List<GrupoIndigenaBean> listaGrupo;
    private PersonaBusiness personaBusiness;
    private TerritorioIndigenaBusiness territorioBusiness;
    private GrupoIndigenaBusiness grupoBusiness;
    private ColegioBusiness colegioBusiness;
    private TerritorioIndigenaBean territorioIndigenaSelected;
    private GrupoIndigenaBean grupoIndigenaSelected;
    private PersonaBean personaBean;
    private PersonaBean personaBeanSelected;
    private ColegioBean colegio;

    private boolean Editar = false;
    private boolean MostrarModal = true;
    private boolean colorEditar = true;

    private boolean tieneAdecuacion = false;
    private boolean esIndigena = false;
    private boolean noCalifica = true;

    private ManejoFacesContext manejoFacesContext;
    private static final String REDIRECCION_PAG_WARNING = "";

    // INICIALIZACION DE VARIABLES
    public ConsultaPostulantesController() throws ExceptionConnection, ExceptionGeneral {
        this.listaPersona = new ArrayList<>();
        this.listaColegio = new ArrayList<>();
        this.listaTerritorio = new ArrayList<>();
        this.listaGrupo = new ArrayList<>();
        this.personaBusiness = new PersonaBusiness();
        this.territorioBusiness = new TerritorioIndigenaBusiness();
        this.grupoBusiness = new GrupoIndigenaBusiness();
        this.colegioBusiness = new ColegioBusiness();
        this.personaBean = new PersonaBean();
        this.personaBeanSelected = new PersonaBean();
        this.territorioIndigenaSelected = new TerritorioIndigenaBean();
        this.grupoIndigenaSelected = new GrupoIndigenaBean();
        this.colegio = new ColegioBean();

        consultarListaPersonas();
    }

    // METODO QUE CARGA LA LISTA DE PERSONAS
    public void consultarListaPersonas() {
        try {
            this.listaPersona = this.personaBusiness.ListarPersona(personaBean);
            this.listaColegio = this.colegioBusiness.ListarColegios();
            this.listaGrupo = this.grupoBusiness.ListarGrupoIndigena();
            this.listaTerritorio = this.territorioBusiness.ListarTerritorio();
        } catch (ExceptionGeneral exg) {
            WarningBean warningBean = new WarningBean();
            warningBean.setMensajeSimple(exg.getMensajeError() + exg.toString());
            this.manejoFacesContext.incluirObjetoSession(warningBean, "warningBean");
            this.manejoFacesContext.redireccionarFlujoWeb(REDIRECCION_PAG_WARNING);
        }
    }

    // METODO QUE TRAE TODOS LOS DATOS DE LA BASE DE DATOS A MOSTRAR COMO EL COLEGIO, TERRITORIO, DIRECCION, GRUPO INDIGENA, Y LA SOLICITUD ASOCIADA A ESA PERSONA
    public void mostrarDetalle() {

        if (this.personaBeanSelected.getEstadoEnSistema().equals("H")) {
            this.noCalifica = true;
        } else {
            this.noCalifica = false;
        }
        SolicitudBean solicitudAuxiliar = new SolicitudBean();
        TerritorioIndigenaBean territorioAuxiliar = new TerritorioIndigenaBean();
        GrupoIndigenaBean grupoAuxiliar = new GrupoIndigenaBean();

        solicitudAuxiliar.setIdentificacionPersona(personaBeanSelected.getIdentificacion());
        territorioAuxiliar.setCodigo(personaBeanSelected.getCodigoTerritorio());
        grupoAuxiliar.setCodigo(personaBeanSelected.getCodigoGrupoIndigena());

        try {
            territorioIndigenaSelected = this.territorioBusiness.getTerritorioPorCodigo(territorioAuxiliar);
            grupoIndigenaSelected = this.grupoBusiness.getGrupoPorCodigo(grupoAuxiliar);
            this.colegio = this.colegioBusiness.colegioPorSolicitud(this.personaBeanSelected.getCodigoColegio());
            persona_Indigena(personaBeanSelected);
            persona_Habilitada(this.noCalifica);
        } catch (ExceptionGeneral ex) {
            WarningBean warningBean = new WarningBean();
            warningBean.setMensajeSimple("error no capturado..." + ex.toString());
        }
    }

    // RETORNA TRUE SI LA PERSONA ESTA DESACTIVADA 
    public void persona_Habilitada(boolean noCal) {
        if (this.noCalifica == true) {
            this.personaBeanSelected.setEstadoEnSistema("H");
        } else {
            this.personaBeanSelected.setEstadoEnSistema("D");
        }
    }

    // RETORNA TRUE SI ES INDIGENA, FALSE SINO
    public boolean persona_Indigena(PersonaBean persona) {
        if (persona.getIndigena() == 'S') {
            this.esIndigena = true;
        } else {
            this.esIndigena = false;
        }
        return this.esIndigena;
    }

    // METODO QUE ACTUALIZA LA PERSONA 
    public void ActualizarPersonaSolicitudListener(ActionEvent evento) throws ExceptionGeneral {
        persona_Habilitada(this.noCalifica);
        PersonaBean personaAux = this.personaBeanSelected;
        if (!personaAux.getNombre().isEmpty()) {
            if (!personaAux.getPrimerApellido().isEmpty()) {
                if (!personaAux.getSegundoApellido().isEmpty()) {
                    if (!personaAux.getConocidoComo().isEmpty()) {
                        if (personaAux.getAnioGraduacionColegio() != null) {
                            if (personaAux.getTelefono() != null) {
                                if (!personaAux.getCorreoElectronico().isEmpty()) {
                                    this.personaBusiness.actualizarPersonaConsulta(personaAux);
                                }
                            }
                        }
                    }
                }
            }   
            this.mostrarDetalle();
            this.Editar = false;
            this.colorEditar = true;
            this.MostrarModal = true;
        }
    }
    // METODO QUE CARGA LA PERSONA SELECCIONADA

    public void selectListener(SelectEvent event) {
        personaBeanSelected = (PersonaBean) event.getObject();
    }

    // PERMITA LA EDICION SI ESTA TRUE, NO LA PERMITE SI ESTA FALSE
    public void permiteEdicion(ActionEvent event) {
        if (!this.Editar) {
            this.Editar = true;
            this.colorEditar = false;
        } else {
            this.Editar = false;
            this.colorEditar = true;
        }

        if (this.MostrarModal) {
            this.MostrarModal = false;
        } else {
            this.MostrarModal = true;
        }
    }

    public void deshabilitaEdicion(ActionEvent event) {
        this.Editar = false;
        this.colorEditar = true;
        this.MostrarModal = true;
    }

    // MUESTRA EL MODAL
    public boolean isMostrarModal() {
        return this.MostrarModal;
    }

    // RETORNA EL BOOLEAN DE EDITAR
    public boolean isEditar() {
        return Editar;
    }

    // SET DE EDITAR
    public void setEditar(boolean EditarS) {
        this.Editar = EditarS;
    }

    // METODOS GET

    public List<TerritorioIndigenaBean> getListaTerritorio() {
        return listaTerritorio;
    }

    public List<GrupoIndigenaBean> getListaGrupo() {
        return listaGrupo;
    }

    public boolean getColorEditar() {
        return colorEditar;
    }

    public List<PersonaBean> getListaPersona() {
        return listaPersona;
    }

    public List<ColegioBean> getListaColegio() {
        return listaColegio;
    }

    public PersonaBean getPersonaBeanSelected() {
        return personaBeanSelected;
    }

    public PersonaBean getPersonaBeanSelectedBackUp() {
        return personaBeanSelected;
    }

    public TerritorioIndigenaBean getTerritorioIndigenaSelected() {
        return territorioIndigenaSelected;
    }

    public GrupoIndigenaBean getGrupoIndigenaSelected() {
        return grupoIndigenaSelected;
    }

    public ColegioBean getColegio() {
        return this.colegio;
    }

    public boolean getAdecuacion() {
        return this.tieneAdecuacion;
    }

    public boolean getIndigena() {
        return this.esIndigena;
    }

    public boolean getNoCalifica() {
        return this.noCalifica;
    }

    // METODOS SET 

    public void setListaPersona(List<PersonaBean> listaPersona) {
        this.listaPersona = listaPersona;
    }

    public void setListaGrupo(List<GrupoIndigenaBean> listaGrupo) {
        this.listaGrupo = listaGrupo;
    }

    public void setListaColegio(List<ColegioBean> listaColegios) {
        this.listaColegio = listaColegios;
    }

    public void setColorEditar(boolean colorEditar) {
        this.colorEditar = colorEditar;
    }

    public void setPersonaBeanSelected(PersonaBean personaSelected) {
        this.personaBeanSelected = personaSelected;
    }

    public void setColegio(ColegioBean cole) {
        this.colegio = cole;
    }

    public void setAdecuacion(boolean adecuacion) {
        this.tieneAdecuacion = adecuacion;
    }

    public void setIndigena(boolean indigena) {
        this.esIndigena = indigena;
    }

    public void setNoCalifica(boolean noCalifica) {
        this.noCalifica = noCalifica;
    }
}
