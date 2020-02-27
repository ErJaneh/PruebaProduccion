/**

* @author Sheng Yuan Lin 
* @version 1.0
* @since 1.0

**/
package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.EstadoCivilBean;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.sql.EstadoCivilSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EstadoCivilDaoImp extends Connector implements EstadoCivilDao{
    
    public EstadoCivilDaoImp() throws ExceptionConnection{
        this.inicializarDataSource();
    }
    
    @Override
    public ArrayList<EstadoCivilBean> ListarEstadoCivil() throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<EstadoCivilBean> arrayGruposEstadoCivil = new ArrayList();
        try{
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(EstadoCivilSQL.listarEstadoCivil());
                ResultSet resultSet = prepareStatement.executeQuery();                
                while (resultSet.next()) {
                    EstadoCivilBean estadoCivil = new EstadoCivilBean();
                    estadoCivil.setCodigo(resultSet.getString("CODIGO"));
                    estadoCivil.setDescripcion(resultSet.getString("DESCRIPCION"));
                    arrayGruposEstadoCivil.add(estadoCivil);
                }//
            }//
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al consultar " + sqlex.toString(), sqlex.toString(), 1, true, 3, "estadoCivil");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al consultar " + ex.toString(), ex.toString(), 1, true, 3, "estadoCivil");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1020:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "consultar estadoCivil");
                }//
            }//
        }//
        return arrayGruposEstadoCivil; 
    }
}
