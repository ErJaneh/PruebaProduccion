/**
 *
 * @author Janel Garces Castillo
 * @version 1.0
 * @since 1.0
 *
 *
 */
package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.SolicitudBean;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.sql.SolicitudSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SolicitudDaoImp extends Connector implements SolicitudDao {

    public SolicitudDaoImp() throws ExceptionConnection {
        this.inicializarDataSource();
    }

    @Override
    public SolicitudBean ConsultarSolicitudPorIdentificacion(SolicitudBean solicitudBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<SolicitudBean> arraySolicitudBean = new ArrayList<>();
        try {
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(SolicitudSQL.getSolicitudPorIdentificacion());
                prepareStatement.setString(1, solicitudBean.getIdentificacionPersona());
                ResultSet resultSet = prepareStatement.executeQuery();

                while (resultSet.next()) {
                    SolicitudBean resultado = new SolicitudBean();
                    resultado.setIdentificacionSolicitud(resultSet.getString("IDENTIFICACION_SOLICITUD"));
                    resultado.setIdentificacionPersona(solicitudBean.getIdentificacionPersona());
                    resultado.setGenero(resultSet.getString("GENERO"));
                    resultado.setEstadoSolicitud1(resultSet.getInt("ESTADO_SOLICITUD_1"));
                    resultado.setEstadoSolicitud2(resultSet.getInt("ESTADO_SOLICITUD_2"));
                    resultado.setCodigoUbicacion(resultSet.getString("CODIGO_UBICACION"));
                    resultado.setDireccionExacta(resultSet.getString("DIRECCION_EXACTA"));
                    resultado.setCodigoEstadoCivil(resultSet.getString("CODIGO_ESTADO_CIVIL").charAt(0));
                    resultado.setAdecuacion(resultSet.getString("ADECUACION").charAt(0));
                    resultado.setCodigoEstadoCivil(resultSet.getString("CODIGO_ESTADO_CIVIL").charAt(0));                   
                    resultado.setAdecuacion(resultSet.getString("ADECUACION").charAt(0));                    
                    resultado.setCodigoNacionalidad(resultSet.getString("CODIGO_NACIONALIDAD"));
                    resultado.setObservaciones(resultSet.getString("OBSERVACIONES"));
                    resultado.setCodigoPromocion1(resultSet.getString("CODIGO_PROMOCION_1"));
                    resultado.setCodigoPromocion2(resultSet.getString("CODIGO_PROMOCION_2"));
                    arraySolicitudBean.add(resultado);
                }
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al consultar solicitud de papel " + sqlex.toString(), sqlex.toString(), 1, true, 3, "SolicitudPapelDaoImpl");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al consultar solicitud de papel " + ex.toString(), ex.toString(), 1, true, 3, "SolicitudPapelDaoImpl");
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

        SolicitudBean max = new SolicitudBean();
        max.setNumeroPromocion(-1);
        for (int i = 0; i < arraySolicitudBean.size(); i++) {
            int auxiliar1 = arraySolicitudBean.get(i).getNumeroPromocion();
            int auxiliar2 = max.getNumeroPromocion();
            if (auxiliar1 > auxiliar2) {
                max = arraySolicitudBean.get(i);
            }
        }
        if (max.getNumeroPromocion()== 0) {
            max.setIdentificacionSolicitud("NA");
        }
        return max;
    }

    @Override
    public void ModificarSolicitudConsultaPersona(SolicitudBean solicitudBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        PreparedStatement comm = null;
        try {
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(SolicitudSQL.modificarSolicitudPersonaConsulta());
                prepareStatement.setString(1, solicitudBean.getDireccionExacta());
                prepareStatement.setString(2, solicitudBean.getIdentificacionSolicitud());
               // prepareStatement.setInt(1, solicitudBean.getTelefono());
               // prepareStatement.setString(2, solicitudBean.getCorreoElectronico());
                prepareStatement.execute();
                comm = this.getConexion().prepareStatement("commit");
                comm.execute();

            }

        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1006:" + "Error al modificar Modificar Solicitud ", sqlex.toString(), 1, true, 3, "SolicitudDaoImpl");
        } catch (Exception ex) {
            throw new ExceptionConnection("1007:" + "Error al modificar Modificar Solicitud ", ex.toString(), 1, true, 3, "SolicitudDaoImpl");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "SolicitudDaoImpl");
                }//
            }//
        }//
    }

    @Override
    public ArrayList<SolicitudBean> ListarSolicitud() throws ExceptionConnection {
        PreparedStatement prepareStatement = null;

        ArrayList<SolicitudBean> arraySolicitudBean = new ArrayList();
        try {
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(SolicitudSQL.listarSolicitud());
                ResultSet resultSet = prepareStatement.executeQuery();

                while (resultSet.next()) {
                    SolicitudBean sol = new SolicitudBean();
                    sol.setIdentificacionSolicitud(resultSet.getString("IDENTIFICACION_SOLICITUD"));
                    sol.setIdentificacionPersona(resultSet.getString("IDENTIFICACION_PERSONA"));
                    sol.setGenero(resultSet.getString("GENERO"));
                    sol.setEstadoSolicitud1(resultSet.getInt("ESTADO_SOLICITUD_1"));
                    sol.setEstadoSolicitud2(resultSet.getInt("ESTADO_SOLICITUD_2"));
                    sol.setCodigoUbicacion(resultSet.getString("CODIGO_UBICACION"));
                    sol.setDireccionExacta(resultSet.getString("DIRECCION_EXACTA"));
                    sol.setCodigoEstadoCivil(resultSet.getString("CODIGO_ESTADO_CIVIL").charAt(0));
                    sol.setCodigoEstadoCivil(resultSet.getString("CODIGO_ESTADO_CIVIL").charAt(0));                   
                    sol.setAdecuacion(resultSet.getString("ADECUACION").charAt(0));
                    sol.setCodigoNacionalidad(resultSet.getString("CODIGO_NACIONALIDAD"));
                    sol.setObservaciones(resultSet.getString("OBSERVACIONES"));
                    sol.setNumeroPromocion(resultSet.getInt("NUMERO_PROMOCION"));
                    sol.setCodigoPromocion1(resultSet.getString("CODIGO_PROMOCION_1"));
                    sol.setCodigoPromocion2(resultSet.getString("CODIGO_PROMOCION_2"));
                    arraySolicitudBean.add(sol);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SolicitudDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arraySolicitudBean;

    }

    @Override
    public SolicitudBean ConsultarSolicitudPorCodigo(SolicitudBean solicitudBean) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        SolicitudBean resultado = new SolicitudBean();
        ArrayList<SolicitudBean> arraySolicitudBean = new ArrayList<SolicitudBean>();
        try {
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(SolicitudSQL.getSolicitudPorCodigo());
                String x = SolicitudSQL.getSolicitudPorCodigo();
                prepareStatement.setString(1, solicitudBean.getIdentificacionSolicitud());
                ResultSet resultSet = prepareStatement.executeQuery();

                while (resultSet.next()) {
                    SolicitudBean sol = new SolicitudBean();
                    sol.setIdentificacionSolicitud(resultSet.getString("IDENTIFICACION_SOLICITUD"));
                    sol.setIdentificacionPersona(resultSet.getString("IDENTIFICACION_PERSONA"));
                    sol.setGenero(resultSet.getString("GENERO"));
                    sol.setEstadoSolicitud1(resultSet.getInt("ESTADO_SOLICITUD_1"));
                    sol.setEstadoSolicitud2(resultSet.getInt("ESTADO_SOLICITUD_2"));
                    sol.setCodigoUbicacion(resultSet.getString("CODIGO_UBICACION"));
                    sol.setDireccionExacta(resultSet.getString("DIRECCION_EXACTA"));
                    sol.setCodigoEstadoCivil(resultSet.getString("CODIGO_ESTADO_CIVIL").charAt(0));
                    sol.setAdecuacion(resultSet.getString("ADECUACION").charAt(0));
                    sol.setCodigoNacionalidad(resultSet.getString("CODIGO_NACIONALIDAD"));
                    sol.setObservaciones(resultSet.getString("OBSERVACIONES"));
                    sol.setCodigoPromocion1(resultSet.getString("CODIGO_PROMOCION_1"));
                    sol.setCodigoPromocion2(resultSet.getString("CODIGO_PROMOCION_2"));
                    arraySolicitudBean.add(sol);
                }
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al consultar solicitud de papel " + sqlex.toString(), sqlex.toString(), 1, true, 3, "SolicitudPapelDaoImpl");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al consultar solicitud de papel " + ex.toString(), ex.toString(), 1, true, 3, "SolicitudPapelDaoImpl");
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

        SolicitudBean max = new SolicitudBean();
        max.setIdentificacionSolicitud("0");
        for (int i = 0; i < arraySolicitudBean.size(); i++) {
            int auxiliar1 = Integer.parseInt(arraySolicitudBean.get(i).getIdentificacionSolicitud());
            int auxiliar2 = Integer.parseInt(max.getIdentificacionSolicitud());
            if (auxiliar1 > auxiliar2) {
                max = arraySolicitudBean.get(i);
            }
        }

        return max;
    }

    @Override
    public ArrayList<SolicitudBean> ListarSolicitudPorPromocion(String codigoPromocion) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        PersonaDaoImp personaDao = new PersonaDaoImp();
        CarreraDaoImp carreraDao = new CarreraDaoImp();
        ArrayList<SolicitudBean> arraySolicitudBean = new ArrayList();
        try {
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(SolicitudSQL.listarSolicitudPorPromocion());
                prepareStatement.setString(1, codigoPromocion);

                ResultSet resultSet = prepareStatement.executeQuery();

                while (resultSet.next()) {
                    SolicitudBean sol = new SolicitudBean();
                    sol.setIdentificacionSolicitud(resultSet.getString("IDENTIFICACION_SOLICITUD"));
                    sol.setIdentificacionPersona(resultSet.getString("IDENTIFICACION_PERSONA"));
                    sol.setGenero(resultSet.getString("GENERO"));
                    sol.setEstadoSolicitud1(resultSet.getInt("ESTADO_SOLICITUD_1"));
                    sol.setEstadoSolicitud2(resultSet.getInt("ESTADO_SOLICITUD_2"));
                    sol.setCodigoUbicacion(resultSet.getString("CODIGO_UBICACION"));
                    sol.setDireccionExacta(resultSet.getString("DIRECCION_EXACTA"));
                    sol.setCodigoEstadoCivil(resultSet.getString("CODIGO_ESTADO_CIVIL").charAt(0));
                    sol.setAdecuacion(resultSet.getString("ADECUACION").charAt(0));
                   // sol.setTelefono(resultSet.getInt("TELEFONO"));
                    sol.setAdecuacion(resultSet.getString("ADECUACION").charAt(0));
                   // sol.setCorreoElectronico(resultSet.getString("CORREO_ELECTRONICO"));
                    sol.setCodigoNacionalidad(resultSet.getString("CODIGO_NACIONALIDAD"));
                    sol.setObservaciones(resultSet.getString("OBSERVACIONES"));
                    sol.setNumeroPromocion(Integer.valueOf(codigoPromocion));
                    sol.setCodigoPromocion1(resultSet.getString("CODIGO_PROMOCION_1"));
                    sol.setCodigoPromocion2(resultSet.getString("CODIGO_PROMOCION_2"));
                    String codCarrera1 = sol.getCodigoPromocion1().substring(sol.getCodigoPromocion1().indexOf("-",6)+1);
                    sol.setDescripcionCarrera1(carreraDao.getDescripcionCarrera(codCarrera1));
                    if(sol.getCodigoPromocion2()!=null){
                       String codCarrera2 = sol.getCodigoPromocion2().substring(sol.getCodigoPromocion2().indexOf("-",6)+1);
                       sol.setDescripcionCarrera2(carreraDao.getDescripcionCarrera(codCarrera2)); 
                    }
                    else
                        sol.setDescripcionCarrera2("");
                    sol.setPostulanteAsociado(personaDao.ConsultarPersona(sol.getIdentificacionPersona()));
                    arraySolicitudBean.add(sol);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SolicitudDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arraySolicitudBean;
    }

    @Override
    public void ModificarConsultaSolicitud(SolicitudBean solicitudBean) throws ExceptionConnection {

        PreparedStatement prepareStatement = null;
        PreparedStatement comm = null;
        try {
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(SolicitudSQL.modificarSolicitudCodigoConsulta());
                prepareStatement.setString(1, solicitudBean.getIdentificacionPersona());
                prepareStatement.setString(2, solicitudBean.getGenero());
                prepareStatement.setInt(3, solicitudBean.getEstadoSolicitud1());
                prepareStatement.setInt(4, solicitudBean.getEstadoSolicitud2());
                prepareStatement.setString(5, solicitudBean.getCodigoUbicacion());
                prepareStatement.setString(6, solicitudBean.getDireccionExacta());
                prepareStatement.setString(7, String.valueOf(solicitudBean.getCodigoEstadoCivil()));
                prepareStatement.setString(8, String.valueOf(solicitudBean.getAdecuacion()));
                prepareStatement.setString(9, solicitudBean.getCodigoNacionalidad());
                prepareStatement.setString(10, solicitudBean.getObservaciones());
                prepareStatement.setString(11, solicitudBean.getCodigoPromocion1());
                prepareStatement.setString(12, solicitudBean.getCodigoPromocion2());
                prepareStatement.setString(13, solicitudBean.getIdentificacionSolicitud());
                prepareStatement.execute();
                comm = this.getConexion().prepareStatement("commit");
                comm.execute();
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            System.err.printf(String.format("***EXCEPCION***:'%s'%n", sqlex.getMessage()));
            throw new ExceptionConnection("1006:" + "Error al modificar Solicitud", sqlex.toString(), 1, true, 3, "SolicitudDaoImpl");
        } catch (Exception ex) {
            System.err.printf(String.format("EXCEPCION:'%s'%n", ex.getMessage()));
            throw new ExceptionConnection("1007:" + "Error al modificar Solicitud", ex.toString(), 1, true, 3, "SolicitudDaoImpl");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1008:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "SolicitudDaoImpl");
                }
            }
        }
    }

    @Override
    public void ingresarSolicitud(SolicitudBean p) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        try {
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(SolicitudSQL.ingresarSolicitud());
                prepareStatement.setString(1, p.getIdentificacionSolicitud());
                prepareStatement.setString(2, p.getIdentificacionPersona());
                prepareStatement.setString(3, p.getGenero());
                prepareStatement.setInt(4, p.getEstadoSolicitud1());
                prepareStatement.setString(5, p.getCodigoUbicacion());
                prepareStatement.setString(6, p.getDireccionExacta());
                prepareStatement.setString(7, String.valueOf(p.getCodigoEstadoCivil()));
                prepareStatement.setString(8, String.valueOf(p.getAdecuacion()));
              //  prepareStatement.setInt(8, p.getTelefono());
              //  prepareStatement.setString(10, p.getCorreoElectronico());
                prepareStatement.setString(9, p.getCodigoNacionalidad());
                prepareStatement.setString(10, p.getObservaciones());
                prepareStatement.setString(11, p.getCodigoPromocion1());
                prepareStatement.setInt(12, p.getNumeroPromocion());
                prepareStatement.setInt(13, p.getEstadoSolicitud2());
                prepareStatement.setString(14, p.getCodigoPromocion2());
                prepareStatement.executeQuery();
                this.commit();
            }//
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al ingresar " + sqlex.toString(), sqlex.toString(), 1, true, 3, "solicitudes");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al ingresar " + ex.toString(), ex.toString(), 1, true, 3, "solicitudes");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1020:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "ingresar solicitudes");
                }//
            }//
        }//
    }
    
    @Override
    public int recuperarConsecutivo(String identificacionSolicitud) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        int index = 0;
        try {
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(SolicitudSQL.recuperarConsecutivo(identificacionSolicitud));
                ResultSet resultSet = prepareStatement.executeQuery();
                if (resultSet.next()) {
                    index = resultSet.getInt("CONSECUTIVO");
                }//
            }//
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al consultar " + sqlex.toString(), sqlex.toString(), 1, true, 3, "consecutivo");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al consultar " + ex.toString(), ex.toString(), 1, true, 3, "consecutivo");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1020:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "consecutivo");
                }//
            }//
        }//
        return index;
    }
}
