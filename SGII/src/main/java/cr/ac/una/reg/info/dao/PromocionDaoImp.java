/**
 *
 * @author Gabriel Araya Ruiz
 * @version 1.0
 * @since 1.0
 *
 */
package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.CarreraBean;
import cr.ac.una.reg.info.beans.PromocionBean;
import cr.ac.una.reg.info.beans.ResponsablePromocionBean;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.sql.PromocionSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Janel
 */
public class PromocionDaoImp extends Connector implements PromocionDao {

    public PromocionDaoImp() throws ExceptionConnection {
        this.inicializarDataSource();
    }

    @Override
    public ArrayList<PromocionBean> ListarPromociones() throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<PromocionBean> arrayPromocionBean = new ArrayList<>();
        CarreraBean carrera = new CarreraBean();
        PromocionBean promocion = new PromocionBean();
        int numeroPromocion = -1;
        try {
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(PromocionSQL.listarPromociones());
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    if (numeroPromocion != resultSet.getInt("NUMERO_PROMOCION")) {
                        if (numeroPromocion == promocion.getNumeroPromocion()) {
                            promocion.setResponsablesPromocion(this.getResponsablesPromocion(promocion.getNumeroPromocion()));
                            arrayPromocionBean.add(promocion);
                        }
                        numeroPromocion = resultSet.getInt("NUMERO_PROMOCION");
                        promocion = new PromocionBean();
                        promocion.setCodigoPromocion(resultSet.getString("CODIGO_PROMOCION"));
                        promocion.setNumeroPromocion(resultSet.getInt("NUMERO_PROMOCION"));
                        promocion.setDescripcionPromocion(resultSet.getString("DESCRIPCION_PROMOCION"));
                        promocion.setCodigoGrupoDeInteres(resultSet.getString("CODIGO_GRUPO_DE_INTERES"));
                        promocion.setCodigoPeriodo(resultSet.getString("CODIGO_PERIODO"));
                        promocion.setCodigoUnidadAcademica(resultSet.getString("CODIGO_UNIDAD_ACADEMICA"));
                        promocion.setCupos(resultSet.getInt("CUPOS"));
                        promocion.setFechaInicioPromocion(resultSet.getDate("FECHA_INICIO_PROMOCION"));
                        promocion.setFechaFinPromocion(resultSet.getDate("FECHA_FIN_PROMOCION"));
                        promocion.setEstadoPromocion(resultSet.getString("ESTADO_PROMOCION"));
                        promocion.setCarrerasPromocion(new ArrayList<CarreraBean>());
                        carrera = new CarreraBean();
                        carrera.setCodigo(resultSet.getString("CODIGO_CARRERA"));
                        carrera.setDescripcion(resultSet.getString("DESCRIPCION_CARRERA"));
                        promocion.getCarrerasPromocion().add(carrera);
                    } else {
                        carrera = new CarreraBean();
                        carrera.setCodigo(resultSet.getString("CODIGO_CARRERA"));
                        carrera.setDescripcion(resultSet.getString("DESCRIPCION_CARRERA"));
                        promocion.getCarrerasPromocion().add(carrera);
                    }
                }//
                if (promocion.getCarrerasPromocion() != null) {
                    promocion.setResponsablesPromocion(this.getResponsablesPromocion(promocion.getNumeroPromocion()));
                    arrayPromocionBean.add(promocion);
                }
            }//
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al consultar " + sqlex.toString(), sqlex.toString(), 1, true, 3, "promociones");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al consultar " + ex.toString(), ex.toString(), 1, true, 3, "promociones");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1020:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "consultar promociones");
                }//
            }//
        }//
        return arrayPromocionBean;
    }

    @Override
    public ArrayList<PromocionBean> ListarPromocionesPorConvenio(String codigo) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<PromocionBean> arrayPromocionBean = new ArrayList<>();
        CarreraBean carrera = new CarreraBean();
        PromocionBean promocion = new PromocionBean();
        int numeroPromocion = -1;
        try {
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(PromocionSQL.listarPromocionesPorConvenios());
                prepareStatement.setString(1, codigo);
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    if (numeroPromocion != resultSet.getInt("NUMERO_PROMOCION")) {
                        if (numeroPromocion == promocion.getNumeroPromocion()) {
                            promocion.setResponsablesPromocion(this.getResponsablesPromocion(promocion.getNumeroPromocion()));
                            arrayPromocionBean.add(promocion);
                        }
                        numeroPromocion = resultSet.getInt("NUMERO_PROMOCION");
                        promocion = new PromocionBean();
                        promocion.setCodigoPromocion(resultSet.getString("CODIGO_PROMOCION"));
                        promocion.setNumeroPromocion(resultSet.getInt("NUMERO_PROMOCION"));
                        promocion.setDescripcionPromocion(resultSet.getString("DESCRIPCION_PROMOCION"));
                        promocion.setCodigoGrupoDeInteres(resultSet.getString("CODIGO_GRUPO_DE_INTERES"));
                        promocion.setCodigoPeriodo(resultSet.getString("CODIGO_PERIODO"));
                        promocion.setCodigoUnidadAcademica(resultSet.getString("CODIGO_UNIDAD_ACADEMICA"));
                        promocion.setCupos(resultSet.getInt("CUPOS"));
                        promocion.setFechaInicioPromocion(resultSet.getDate("FECHA_INICIO_PROMOCION"));
                        promocion.setFechaFinPromocion(resultSet.getDate("FECHA_FIN_PROMOCION"));
                        promocion.setEstadoPromocion(resultSet.getString("ESTADO_PROMOCION"));
                        promocion.setCarrerasPromocion(new ArrayList<CarreraBean>());
                        carrera = new CarreraBean();
                        carrera.setCodigo(resultSet.getString("CODIGO_CARRERA"));
                        carrera.setDescripcion(resultSet.getString("DESCRIPCION_CARRERA"));
                        promocion.getCarrerasPromocion().add(carrera);
                    } else {
                        carrera = new CarreraBean();
                        carrera.setCodigo(resultSet.getString("CODIGO_CARRERA"));
                        carrera.setDescripcion(resultSet.getString("DESCRIPCION_CARRERA"));
                        promocion.getCarrerasPromocion().add(carrera);
                    }
                }//
                if (promocion.getCarrerasPromocion() != null) {
                    promocion.setResponsablesPromocion(this.getResponsablesPromocion(promocion.getNumeroPromocion()));
                    arrayPromocionBean.add(promocion);
                }
            }//
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al consultar " + sqlex.toString(), sqlex.toString(), 1, true, 3, "promociones");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al consultar " + ex.toString(), ex.toString(), 1, true, 3, "promociones");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1020:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "consultar promociones");
                }//
            }//
        }//
        return arrayPromocionBean;
    }

    @Override
    public void ingresarPromocion(PromocionBean promocion) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(PromocionSQL.ingresaPromocion());
                prepareStatement.setString(1, promocion.getCodigoPromocion());
                prepareStatement.setString(2, promocion.getDescripcionPromocion());
                prepareStatement.setString(3, promocion.getCodigoGrupoDeInteres());
                prepareStatement.setString(4, promocion.getCarrerasPromocion().get(0).getCodigo());
                prepareStatement.setString(5, promocion.getCodigoPeriodo());
                prepareStatement.setString(6, promocion.getCodigoUnidadAcademica());
                prepareStatement.setInt(7, /*p.getCupos()*/ 0);
                prepareStatement.setString(8, formatter.format(promocion.getFechaInicioPromocion()));
                prepareStatement.setString(9, formatter.format(promocion.getFechaFinPromocion()));
                prepareStatement.setString(10, "Disponible para inscripcion");
                prepareStatement.setInt(11, promocion.getNumeroPromocion());
                prepareStatement.executeQuery();
                this.commit();
            }//
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al ingresar " + sqlex.toString(), sqlex.toString(), 1, true, 3, "promociones");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al ingresar " + ex.toString(), ex.toString(), 1, true, 3, "promociones");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1020:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "ingresar promociones");
                }//
            }//
        }//
    }

    @Override
    public int getUltimoIndexPromociones() throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        int index = 0;
        try {
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(PromocionSQL.getUltimoIndexPromociones());
                ResultSet resultSet = prepareStatement.executeQuery();
                if (resultSet.next()) {
                    index = resultSet.getInt("LASTNUMBER");
                }//
            }//
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al consultar " + sqlex.toString(), sqlex.toString(), 1, true, 3, "index");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al consultar " + ex.toString(), ex.toString(), 1, true, 3, "index");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1020:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "index");
                }//
            }//
        }//
        return index;
    }

    @Override
    public void ingresarResponsablesPromocion(List<ResponsablePromocionBean> responsablesPromocion) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        try {
            if (this.openConnection()) {
                for (ResponsablePromocionBean responsable : responsablesPromocion) {
                    prepareStatement = this.getConexion().prepareStatement(PromocionSQL.ingresaResponsablePromocion());
                    prepareStatement.setInt(1, responsable.getNumeroPromocion());
                    prepareStatement.setString(2, responsable.getCorreo());
                    prepareStatement.setString(3, responsable.getDescripcion());
                    prepareStatement.executeQuery();
                    this.commit();
                }
            }//
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al ingresar " + sqlex.toString(), sqlex.toString(), 1, true, 3, "promociones");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al ingresar " + ex.toString(), ex.toString(), 1, true, 3, "promociones");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1020:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "ingresar promociones");
                }//
            }//
        }//  
    }

    @Override
    public ArrayList<ResponsablePromocionBean> getResponsablesPromocion(int numeroPromocion) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<ResponsablePromocionBean> arrayResponsableBean = new ArrayList<>();
        try {
            prepareStatement = this.getConexion().prepareStatement(PromocionSQL.getResponsablesPromocion());
            prepareStatement.setInt(1, numeroPromocion);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                ResponsablePromocionBean responsable = new ResponsablePromocionBean();
                responsable.setIndex_responsable_promocion(resultSet.getInt("INDEX_RESPONSABLES_PROMOCION"));
                responsable.setNumeroPromocion(numeroPromocion);
                responsable.setCorreo(resultSet.getString("CORREO"));
                responsable.setDescripcion(resultSet.getString("DESCRIPCION"));
                arrayResponsableBean.add(responsable);
            }//
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al consultar " + sqlex.toString(), sqlex.toString(), 1, true, 3, "promociones");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al consultar " + ex.toString(), ex.toString(), 1, true, 3, "promociones");
        }
        return arrayResponsableBean;
    }

    @Override
    public void actualizarPromocion(PromocionBean promocion) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(PromocionSQL.actualizarPromocion());
                prepareStatement.setString(1, promocion.getEstadoPromocion());
                prepareStatement.setString(2, promocion.getCodigoPeriodo());
                prepareStatement.setString(3, promocion.getCodigoUnidadAcademica());
                prepareStatement.setString(4, formatter.format(promocion.getFechaInicioPromocion()));
                prepareStatement.setString(5, formatter.format(promocion.getFechaFinPromocion()));
                prepareStatement.setString(6, promocion.getDescripcionPromocion());
                prepareStatement.setInt(7, promocion.getNumeroPromocion());
                prepareStatement.execute();
                this.commit();
            }//
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al consultar " + sqlex.toString(), sqlex.toString(), 1, true, 3, "index");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al consultar " + ex.toString(), ex.toString(), 1, true, 3, "index");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1020:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "index");
                }//
            }//
        }//
    }

    @Override
    public void eliminarResponsablesPromocion(List<ResponsablePromocionBean> responsablesPromocion) throws ExceptionConnection {
     PreparedStatement prepareStatement = null;
        try {
            if (this.openConnection()) {
                for (ResponsablePromocionBean responsable : responsablesPromocion) {
                    prepareStatement = this.getConexion().prepareStatement(PromocionSQL.eliminarResponsablePromocion());
                    prepareStatement.setInt(1, responsable.getIndex_responsable_promocion());
                    prepareStatement.execute();
                    this.commit();
                }
            }//
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al ingresar " + sqlex.toString(), sqlex.toString(), 1, true, 3, "promociones");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al ingresar " + ex.toString(), ex.toString(), 1, true, 3, "promociones");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1020:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "ingresar promociones");
                }//
            }//
        }//  
    }

    @Override
    public ArrayList<CarreraBean> listarCarrerasPorPromocion(int numeroPromocion) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<CarreraBean> carreras = new ArrayList();
        try {
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(PromocionSQL.getCarrerasPorPromocion());
                prepareStatement.setInt(1, numeroPromocion);
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    CarreraBean carrera = new CarreraBean();
                    carrera.setCodigo(resultSet.getString("CODIGO_CARRERA"));
                    carrera.setDescripcion(resultSet.getString("SMRPRLE_PROGRAM_DESC"));
                    carreras.add(carrera);
                }
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al consultar " + sqlex.toString(), sqlex.toString(), 1, true, 3, "promocion");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al consultar " + ex.toString(), ex.toString(), 1, true, 3, "promocion");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1020:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "consultar promocion");
                }//
            }//
        }//
        return carreras;
    }
    
    
    @Override
    public ArrayList<CarreraBean> listarCarrerasPorCodPromocion(String codigoPromocion) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<CarreraBean> carreras = new ArrayList();
        try {
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(PromocionSQL.getCarrerasPorCodPromocion(codigoPromocion));
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    CarreraBean carrera = new CarreraBean();
                    carrera.setCodigo(resultSet.getString("CODIGO_CARRERA"));
                    carrera.setDescripcion(resultSet.getString("SMRPRLE_PROGRAM_DESC"));
                    carreras.add(carrera);
                }
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al consultar " + sqlex.toString(), sqlex.toString(), 1, true, 3, "promocion");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al consultar " + ex.toString(), ex.toString(), 1, true, 3, "promocion");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1020:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "consultar promocion");
                }//
            }//
        }//
        return carreras;
    }
}
