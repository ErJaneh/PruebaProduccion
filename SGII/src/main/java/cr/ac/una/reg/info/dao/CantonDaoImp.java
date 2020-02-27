/**
 *
 * @author Sheng Yuan Lin
 * @version 1.0
 * @since 1.0
 *
 *
 */
package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.CantonBean;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.sql.CantonSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CantonDaoImp extends Connector implements CantonDao {

    public CantonDaoImp() throws ExceptionConnection {
        this.inicializarDataSource();
    }

    @Override
    public ArrayList<CantonBean> ListarCantonPorProvincia(String provincia) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<CantonBean> arrayCantonBean = new ArrayList();
        try {
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(CantonSQL.listarCantonPorProvincia(provincia));
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    CantonBean canton = new CantonBean();
                    canton.setCodigo(resultSet.getString("GTVZIPC_CODE"));
                    canton.setDescripcion(resultSet.getString("GTVZIPC_CITY"));
                    canton.setIdentificador(resultSet.getString("GTVZIPC_CNTY_CODE"));
                    arrayCantonBean.add(canton);
                }//
            }//
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al consultar " + sqlex.toString(), sqlex.toString(), 1, true, 3, "canton");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al consultar " + ex.toString(), ex.toString(), 1, true, 3, "canton");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1020:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "consultar contones");
                }//
            }//
        }//
        return arrayCantonBean;
    }

    @Override
    public CantonBean CantonPorCodigo(String canton) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        CantonBean aux = new CantonBean();
        try {
            if (this.openConnection()) {
                String x = CantonSQL.provinciaPorCodigo();
                prepareStatement = this.getConexion().prepareStatement(CantonSQL.provinciaPorCodigo());
                prepareStatement.setString(1, canton);
                ResultSet resultSet = prepareStatement.executeQuery();

                while (resultSet.next()) {
                    aux.setCodigo(resultSet.getString("GTVZIPC_CODE"));
                    aux.setDescripcion(resultSet.getString("GTVZIPC_CITY"));
                    aux.setIdentificador(resultSet.getString("GTVZIPC_CNTY_CODE"));
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
