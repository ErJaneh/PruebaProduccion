/**
 *
 * @author Janel Garces Castillo
 * @version 1.0
 * @since 1.0
 *
 *
 */
package cr.ac.una.reg.info.business;

import cr.ac.una.reg.info.beans.PersonaBean;
import cr.ac.una.reg.info.dao.PersonaDaoImp;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.exceptions.ExceptionGeneral;
import java.util.List;

public class PersonaBusiness {

    private PersonaDaoImp personaDaoImp;

    public PersonaBusiness() throws ExceptionConnection, ExceptionConnection {
        this.personaDaoImp = new PersonaDaoImp();
    }

    public List<PersonaBean> ListarPersona(PersonaBean personaBean) throws ExceptionGeneral {
        List<PersonaBean> listaPersona = null;
        try {
            listaPersona = this.personaDaoImp.ListarPersona(personaBean);
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "personaBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar Registros de personas ...", ex.toString(), 1, false, 1, "personaBusiness", 1);
        }
        return listaPersona;
    }

    public PersonaBean getDetallePersona(PersonaBean id) throws ExceptionGeneral {
        PersonaBean personaID = null;
        try {
            personaID = this.personaDaoImp.ConsultarPersona(id.getIdentificacion());
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "personaBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar una persona ...", ex.toString(), 1, false, 1, "personaBusiness", 1);
        }
        return personaID;
    }

    public void actualizarPersonaConsulta(PersonaBean id) throws ExceptionGeneral {
        try {
            this.personaDaoImp.ModificarConsultaPersona(id);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2004:" + "Problemas al modificar solictud...", ex.toString(), 1, false, 1, "SolicitudBusiness", 1);
        }
    }

    public void ingresarPersona(PersonaBean p) throws ExceptionGeneral {
        try {
            this.personaDaoImp.ingresarPersona(p);
        } catch (ExceptionConnection exc) {
            throw new ExceptionGeneral(exc.getMensajeError(), exc.getMensajeTecnico(), 1, false, 1, "personaBusiness", 1);
        } catch (Exception ex) {
            throw new ExceptionGeneral("2006:" + "Problemas a consultar los registros ...", ex.toString(), 1, false, 1, "personaBusiness", 1);
        }
    }
}
