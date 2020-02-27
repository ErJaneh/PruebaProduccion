/**
 *
 * @author Sheng Yuan Lin
 * @version 1.0
 * @since 1.0
 *
 *
 */
package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.ProvinciaBean;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.sql.ProvinciaSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProvinciaDaoImp extends Connector implements ProvinciaDao {

    public ProvinciaDaoImp() throws ExceptionConnection {
        this.inicializarDataSource();
    }

    @Override
    public ArrayList<ProvinciaBean> ListarProvincias() throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<ProvinciaBean> arrayProvinciaBean = new ArrayList();
        try {
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(ProvinciaSQL.listarProvincias());
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    ProvinciaBean provincia = new ProvinciaBean();
                    provincia.setCodigo(resultSet.getString("CODIGOPROV"));
                    provincia.setDescripcion(resultSet.getString("GTVZIPC_STAT_CODE"));
                    arrayProvinciaBean.add(provincia);
                }//
            }//
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al consultar " + sqlex.toString(), sqlex.toString(), 1, true, 3, "provincias");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al consultar " + ex.toString(), ex.toString(), 1, true, 3, "provincias");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1020:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "consultar provincias");
                }//
            }//
        }//
        return arrayProvinciaBean;
    }

    @Override
    public ProvinciaBean ProvinciaPorCodigo(String codigo) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ProvinciaBean aux = new ProvinciaBean();
        try {
            if (this.openConnection()) {
                String x = ProvinciaSQL.provinciaPorCodigo();
                prepareStatement = this.getConexion().prepareStatement(ProvinciaSQL.provinciaPorCodigo());
                prepareStatement.setString(1, codigo);
                ResultSet resultSet = prepareStatement.executeQuery();

                while (resultSet.next()) {
                    aux.setCodigo(resultSet.getString("CODIGOPROV"));
                    aux.setDescripcion(resultSet.getString("GTVZIPC_STAT_CODE"));
                }

            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al consultar " + sqlex.toString(), sqlex.toString(), 1, true, 3, "provincias");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al consultar " + ex.toString(), ex.toString(), 1, true, 3, "provincias");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1020:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "consultar provincias");
                }
            }
        }
        return aux;
    }
}
