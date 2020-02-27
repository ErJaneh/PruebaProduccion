/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.EscuelaBean;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.sql.EscuelaSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author MSI
 */
public class EscuelaDaoImp extends Connector implements EscuelaDao{

    public EscuelaDaoImp() throws ExceptionConnection {
        this.inicializarDataSource();
    }
    
    @Override
    public ArrayList<EscuelaBean> ListarEscuelas() throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<EscuelaBean> arrayEscuelaBean = new ArrayList();
        try{
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(EscuelaSQL.listarEscuelas());
                ResultSet resultSet = prepareStatement.executeQuery();                
                while (resultSet.next()) {
                    EscuelaBean escuela = new EscuelaBean();
                    escuela.setCodigo(resultSet.getString("STVCOLL_CODE"));
                    escuela.setDescripcion(resultSet.getString("STVCOLL_DESC"));
                    arrayEscuelaBean.add(escuela);
                }//
            }//
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al consultar " + sqlex.toString(), sqlex.toString(), 1, true, 3, "escuelas");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al consultar " + ex.toString(), ex.toString(), 1, true, 3, "escuelas");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1020:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "consultar escuelas");
                }//
            }//
        }//
        return arrayEscuelaBean; 
    }
    
}
