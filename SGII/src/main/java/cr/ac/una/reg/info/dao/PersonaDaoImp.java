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
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.sql.PersonaSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PersonaDaoImp extends Connector implements PersonaDao {

    public PersonaDaoImp() throws ExceptionConnection {
        this.inicializarDataSource();
    }

    @Override
    public ArrayList<PersonaBean> ListarPersona(PersonaBean personaBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        PreparedStatement prepareStatementTitulo = null;
        PreparedStatement prepareStatementCategoria = null;

        ArrayList<PersonaBean> arrayPersonaBean = new ArrayList<PersonaBean>();
        try {
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(PersonaSQL.listarPersona());
                ResultSet resultSet = prepareStatement.executeQuery();

                while (resultSet.next()) {
                    PersonaBean per = new PersonaBean();
                    per.setIdentificacion(resultSet.getString("IDENTIFICACION"));
                    per.setCodigoTipoIdentificacion(resultSet.getString("CODIGO_TIPO_IDENTIFICACION"));
                    per.setNombre(resultSet.getString("NOMBRE"));
                    per.setPrimerApellido(resultSet.getString("PRIMER_APELLIDO"));
                    per.setSegundoApellido(resultSet.getString("SEGUNDO_APELLIDO"));
                    per.setConocidoComo(resultSet.getString("CONOCIDO_COMO"));
                    per.setFecha(resultSet.getDate("FECHA_NACIMIENTO"));
                    per.setCodigoColegio(resultSet.getString("CODIGO_COLEGIO"));
                    per.setAnioGraduacionColegio(resultSet.getInt("ANIO_GRADUACION_COLEGIO"));
                    per.setNotaColegio(resultSet.getInt("NOTA_COLEGIO"));
                    per.setIndigena(resultSet.getString("INDIGENA").charAt(0));
                    per.setCodigoTerritorio(resultSet.getString("CODIGO_TERRITORIO"));
                    per.setCodigoGrupoIndigena(resultSet.getString("CODIGO_GRUPO_INDIGENA"));
                    per.setTelefono(resultSet.getInt("TELEFONO"));
                    per.setCorreoElectronico(resultSet.getString("CORREO_ELECTRONICO"));
                    per.setEstadoEnSistema(resultSet.getString("ESTADO_EN_SISTEMA"));
                    arrayPersonaBean.add(per);
                }//
            }//
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al consultar persona " + sqlex.toString(), sqlex.toString(), 1, true, 3, "PersonaDaoImpl");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al consultar persona " + ex.toString(), ex.toString(), 1, true, 3, "PersonaDaoImpl");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1020:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "ConsultarEstudianteDaoImpl");
                }//
            }//
        }//
        return arrayPersonaBean;
    }

    @Override
    public PersonaBean ConsultarPersona(String personaBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        PersonaBean per = new PersonaBean();

        try {
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(PersonaSQL.detallePersona());
                prepareStatement.setString(1, personaBean);
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    per.setIdentificacion(resultSet.getString("IDENTIFICACION"));
                    per.setCodigoTipoIdentificacion(resultSet.getString("CODIGO_TIPO_IDENTIFICACION"));
                    per.setNombre(resultSet.getString("NOMBRE"));
                    per.setPrimerApellido(resultSet.getString("PRIMER_APELLIDO"));
                    per.setSegundoApellido(resultSet.getString("SEGUNDO_APELLIDO"));
                    per.setConocidoComo(resultSet.getString("CONOCIDO_COMO"));
                    per.setFecha(resultSet.getDate("FECHA_NACIMIENTO"));
                    per.setCodigoColegio(resultSet.getString("CODIGO_COLEGIO"));
                    per.setAnioGraduacionColegio(resultSet.getInt("ANIO_GRADUACION_COLEGIO"));
                    per.setNotaColegio(resultSet.getInt("NOTA_COLEGIO"));
                    per.setIndigena(resultSet.getString("INDIGENA").charAt(0));
                    per.setCodigoTerritorio(resultSet.getString("CODIGO_TERRITORIO"));
                    per.setTelefono(resultSet.getInt("TELEFONO"));
                    per.setCorreoElectronico(resultSet.getString("CORREO_ELECTRONICO"));
                    per.setCodigoGrupoIndigena(resultSet.getString("CODIGO_GRUPO_INDIGENA"));
                    per.setEstadoEnSistema(resultSet.getString("ESTADO_EN_SISTEMA"));

                }
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al consultar persona " + sqlex.toString(), sqlex.toString(), 1, true, 3, "PersonaDaoImpl");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al consultar persona " + ex.toString(), ex.toString(), 1, true, 3, "PersonaDaoImpl");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1020:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "ConsultaDetallePersona");
                }//
            }//
        }//
        return per;
    }

    @Override
    public void RegistrarPersona(PersonaBean personaBean) throws ExceptionConnection {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ModificarConsultaPersona(PersonaBean personaBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        PreparedStatement comm = null;
        try {
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(PersonaSQL.modificarPersonaConsulta());
                prepareStatement.setString(1, personaBean.getNombre());
                prepareStatement.setString(2, personaBean.getPrimerApellido());
                prepareStatement.setString(3, personaBean.getSegundoApellido());
                prepareStatement.setString(4, personaBean.getConocidoComo());
                prepareStatement.setInt(5, personaBean.getAnioGraduacionColegio());
                prepareStatement.setInt(6, personaBean.getTelefono());
                prepareStatement.setString(7, personaBean.getCorreoElectronico());
                prepareStatement.setString(8, personaBean.getEstadoEnSistema());
                prepareStatement.setString(9, personaBean.getIdentificacion());
                prepareStatement.execute();
                comm = this.getConexion().prepareStatement("commit");
                comm.execute();
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1006:" + "Error al modificar Persona", sqlex.toString(), 1, true, 3, "PersonaDaoImpl");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:" + "Error al modificar Persona", ex.toString(), 1, true, 3, "PersonaDaoImpl");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "PersonaDaoImpl");
                }
            }
        }
    }

    /**
     * Sheng Yuan Lin *
     */
    @Override
    public void ingresarPersona(PersonaBean p) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(PersonaSQL.ingresarPersona());
                prepareStatement.setString(1, p.getIdentificacion());
                prepareStatement.setString(2, p.getCodigoTipoIdentificacion());
                prepareStatement.setString(3, p.getNombre());
                prepareStatement.setString(4, p.getPrimerApellido());
                prepareStatement.setString(5, p.getSegundoApellido());
                prepareStatement.setString(6, formatter.format(p.getFecha()));
                prepareStatement.setString(7, p.getCodigoColegio());
                prepareStatement.setInt(8, p.getAnioGraduacionColegio());
                prepareStatement.setInt(9, p.getNotaColegio());
                prepareStatement.setString(10, String.valueOf(p.getIndigena()));
                prepareStatement.setString(11, p.getCodigoTerritorio());
                prepareStatement.setString(12, p.getCodigoGrupoIndigena());
                prepareStatement.setInt(13, p.getTelefono());
                prepareStatement.setString(14, p.getCorreoElectronico());
                prepareStatement.setString(15, p.getEstadoEnSistema());
                prepareStatement.executeQuery();
                this.commit();
            }//
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al ingresar " + sqlex.toString(), sqlex.toString(), 1, true, 3, "persona");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al ingresar " + ex.toString(), ex.toString(), 1, true, 3, "persona");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1020:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "ingresar persona");
                }//
            }//
        }//
    }

}
