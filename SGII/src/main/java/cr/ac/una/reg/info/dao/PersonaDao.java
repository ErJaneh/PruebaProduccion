/**
 *
 * @author Janel Garces Castillo
 * @version 1.0
 * @since 1.0
 *
 *
 */
package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.PersonaBean;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.util.ArrayList;

public interface PersonaDao {

    /**
     *
     * @param personaBean
     * @return
     * @throws ExceptionConnection
     */
    ArrayList<PersonaBean> ListarPersona(PersonaBean personaBean) throws ExceptionConnection;

    PersonaBean ConsultarPersona(String personaBean) throws ExceptionConnection;

    void RegistrarPersona(PersonaBean personaBean) throws ExceptionConnection;

    void ModificarConsultaPersona(PersonaBean personaBean) throws ExceptionConnection;

    void ingresarPersona(PersonaBean p) throws ExceptionConnection;
    
}
