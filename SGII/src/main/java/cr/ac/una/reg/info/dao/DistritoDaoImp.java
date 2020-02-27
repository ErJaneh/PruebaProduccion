/**
 *
 * @author Sheng Yuan Lin
 * @version 1.0
 * @since 1.0
 *
 *
 */
package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.DistritoBean;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.sql.DistritoSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DistritoDaoImp extends Connector implements DistritoDao {

    public DistritoDaoImp() throws ExceptionConnection {
        this.inicializarDataSource();
    }

    @Override
    public ArrayList<DistritoBean> ListarDistritoPorCanton(String canton) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<DistritoBean> arrayDistritoBean = new ArrayList();
        try {
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(DistritoSQL.listarDistritoPorCanton(canton));
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    DistritoBean distrito = new DistritoBean();
                    distrito.setCodigo(resultSet.getString("GTVZIPC_CODE"));
                    distrito.setDescripcion(resultSet.getString("GTVZIPC_CITY"));

                    arrayDistritoBean.add(distrito);
                }//
            }//
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al consultar " + sqlex.toString(), sqlex.toString(), 1, true, 3, "distrito");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al consultar " + ex.toString(), ex.toString(), 1, true, 3, "distrito");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1020:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "consultar distritos");
                }//
            }//
        }//
        return arrayDistritoBean;
    }

    @Override
    public DistritoBean DistritoPorCodigo(String codigo) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        DistritoBean aux = new DistritoBean();
        try {
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(DistritoSQL.distritoPorCodigo());
                prepareStatement.setString(1, codigo);
                ResultSet resultSet = prepareStatement.executeQuery();

                while (resultSet.next()) {
                    aux.setCodigo(resultSet.getString("GTVZIPC_CODE"));
                    aux.setDescripcion(resultSet.getString("GTVZIPC_CITY"));
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
