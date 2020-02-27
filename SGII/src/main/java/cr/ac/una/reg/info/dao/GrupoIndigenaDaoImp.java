/**
 *
 * @author Janel Garces Castillo
 * @version 1.0
 * @since 1.0
 *
 *
 */
package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.GrupoIndigenaBean;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.sql.GrupoIndigenaSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GrupoIndigenaDaoImp extends Connector implements GrupoIndigenaDao {

    public GrupoIndigenaDaoImp() throws ExceptionConnection {
        this.inicializarDataSource();
    }

    public GrupoIndigenaBean buscarGrupoPorCodigo(GrupoIndigenaBean grupo) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        PreparedStatement prepareStatementTitulo = null;
        PreparedStatement prepareStatementCategoria = null;
        GrupoIndigenaBean gru = new GrupoIndigenaBean();

        try {
            if (this.openConnection()) {
                GrupoIndigenaBean auxiliar = new GrupoIndigenaBean();
                prepareStatement = this.getConexion().prepareStatement(GrupoIndigenaSQL.consultaGrupoPorCodigo());
                prepareStatement.setString(1, grupo.getCodigo());
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    auxiliar.setCodigo(resultSet.getString("CODIGO"));
                    auxiliar.setDescripcion(resultSet.getString("DESCRIPCION"));
                    gru = auxiliar;
                }
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al consultar Grupo Indigena " + sqlex.toString(), sqlex.toString(), 1, true, 3, "GrupoIndigenaDaoImpl");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al consultar Grupo Indigena " + ex.toString(), ex.toString(), 1, true, 3, "GrupoIndigenaDaoImpl");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1020:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "ConsultaDetalleTerritorio");
                }
            }
        }

        return gru;
    }

    
    /** Sheng Yuan Lin **/
    @Override
    public ArrayList<GrupoIndigenaBean> ListarGruposIndigenas() throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<GrupoIndigenaBean> arrayGruposIndigenas = new ArrayList();
        try{
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(GrupoIndigenaSQL.listarGruposIndigenas());
                ResultSet resultSet = prepareStatement.executeQuery();                
                while (resultSet.next()) {
                    GrupoIndigenaBean grupoIndigena = new GrupoIndigenaBean();
                    grupoIndigena.setCodigo(resultSet.getString("CODIGO"));
                    grupoIndigena.setDescripcion(resultSet.getString("DESCRIPCION"));
                    arrayGruposIndigenas.add(grupoIndigena);
                }//
            }//
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al consultar " + sqlex.toString(), sqlex.toString(), 1, true, 3, "gruposIndigenas");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al consultar " + ex.toString(), ex.toString(), 1, true, 3, "gruposIndigenas");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1020:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "consultar gruposIndigenas");
                }//
            }//
        }//
        return arrayGruposIndigenas; 
    }
}
