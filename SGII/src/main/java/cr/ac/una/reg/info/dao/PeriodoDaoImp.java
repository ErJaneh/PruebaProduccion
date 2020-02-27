/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.PeriodoBean;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.sql.PeriodoSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author MSI
 */
public class PeriodoDaoImp extends Connector implements PeriodoDao {

    public PeriodoDaoImp() throws ExceptionConnection {
        this.inicializarDataSource();
    }
      
    @Override
    public ArrayList<PeriodoBean> ListarPeriodos() throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<PeriodoBean> arrayPeriodoBean = new ArrayList();
        try{
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(PeriodoSQL.listarPeriodos());
                ResultSet resultSet = prepareStatement.executeQuery();                
                while (resultSet.next()) {
                    PeriodoBean periodo = new PeriodoBean();
                    periodo.setCodigo(resultSet.getString("STVTERM_CODE"));
                    periodo.setDescripcion(resultSet.getString("STVTERM_DESC"));
                    arrayPeriodoBean.add(periodo);
                }//
            }//
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al consultar " + sqlex.toString(), sqlex.toString(), 1, true, 3, "periodos");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al consultar " + ex.toString(), ex.toString(), 1, true, 3, "periodos");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1020:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "consultar periodos");
                }//
            }//
        }//
        return arrayPeriodoBean;  
    }
    
}
