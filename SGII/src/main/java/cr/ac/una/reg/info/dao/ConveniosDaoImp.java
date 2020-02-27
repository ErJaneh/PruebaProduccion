/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.ConvenioBean;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.sql.ConvenioSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author MSI
 */
public class ConveniosDaoImp extends Connector implements ConveniosDao{
    
    public ConveniosDaoImp() throws ExceptionConnection {
        this.inicializarDataSource();
    }

    @Override
    public ArrayList<ConvenioBean> ListarConvenios() throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<ConvenioBean> arrayConvenioBean = new ArrayList();
        try{
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(ConvenioSQL.listarConvenios());
                ResultSet resultSet = prepareStatement.executeQuery();                
                while (resultSet.next()) {
                    ConvenioBean convenio = new ConvenioBean();
                    convenio.setCodigo(resultSet.getString("STVATTS_CODE"));
                    convenio.setDescripcion(resultSet.getString("STVATTS_DESC"));                   
                    arrayConvenioBean.add(convenio);
                }//
            }//
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al consultar " + sqlex.toString(), sqlex.toString(), 1, true, 3, "convenios");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al consultar " + ex.toString(), ex.toString(), 1, true, 3, "convenios");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1020:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "consultar convenios");
                }//
            }//
        }//
        return arrayConvenioBean;          
    }
    
    
}
