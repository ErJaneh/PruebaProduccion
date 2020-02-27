/**

* @author Janel Garces Castillo
* @version 1.0
* @since 1.0

**/

package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.PersonaBean;
import cr.ac.una.reg.info.beans.TerritorioIndigenaBean;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.sql.PersonaSQL;
import cr.ac.una.reg.info.sql.TerritorioIndigenaSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TerritorioIndigenaDaoImp extends Connector implements TerritorioIndigenaDao {

    public TerritorioIndigenaDaoImp() throws ExceptionConnection {
        this.inicializarDataSource();
    }

    public TerritorioIndigenaBean buscarTerritorioPorCodigo(TerritorioIndigenaBean territorio) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        PreparedStatement prepareStatementTitulo = null;
        PreparedStatement prepareStatementCategoria = null;
        TerritorioIndigenaBean terr = new TerritorioIndigenaBean();

        try {
            if (this.openConnection()) {
                TerritorioIndigenaBean auxiliar = new TerritorioIndigenaBean();
                prepareStatement = this.getConexion().prepareStatement(TerritorioIndigenaSQL.consultaTerritorioPorCodigo());
                prepareStatement.setString(1, territorio.getCodigo());
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    auxiliar.setCodigo(resultSet.getString("CODIGO"));
                    auxiliar.setDescripcion(resultSet.getString("DESCRIPCION"));
                    terr = auxiliar;
                }
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al consultar Territorio " + sqlex.toString(), sqlex.toString(), 1, true, 3, "TerritorioDaoImpl");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al consultar Territorio " + ex.toString(), ex.toString(), 1, true, 3, "TerritorioDaoImpl");
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

        return terr;
    }
    
    
    /** Sheng Yuan Lin **/
    @Override
    public ArrayList<TerritorioIndigenaBean> ListarTerritoriosIndigenas() throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<TerritorioIndigenaBean> arrayTerritoriosIndigenas = new ArrayList();
        try{
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(TerritorioIndigenaSQL.listarTerritoriosIndigenas());
                ResultSet resultSet = prepareStatement.executeQuery();                
                while (resultSet.next()) {
                    TerritorioIndigenaBean territorioIndigena = new TerritorioIndigenaBean();
                    territorioIndigena.setCodigo(resultSet.getString("CODIGO"));
                    territorioIndigena.setDescripcion(resultSet.getString("DESCRIPCION"));
                    arrayTerritoriosIndigenas.add(territorioIndigena);
                }//
            }//
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al consultar " + sqlex.toString(), sqlex.toString(), 1, true, 3, "territorios");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al consultar " + ex.toString(), ex.toString(), 1, true, 3, "territorios");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1020:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "consultar territorios");
                }//
            }//
        }//
        return arrayTerritoriosIndigenas; 
    }

}
