/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.ColegioBean;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.sql.ColegioSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Janel
 */
public class ColegioDaoImp extends Connector implements ColegioDao {

    public ColegioDaoImp() throws ExceptionConnection {
        this.inicializarDataSource();
    }

     @Override
    public ArrayList<ColegioBean> ListarColegios() throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<ColegioBean> arrayColegioBean = new ArrayList();
        try{
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(ColegioSQL.listarColegios());
                ResultSet resultSet = prepareStatement.executeQuery();                
                while (resultSet.next()) {
                    ColegioBean colegio = new ColegioBean();
                    colegio.setCodigo(resultSet.getString("CODIGO"));
                    colegio.setDescripcion(resultSet.getString("DESCRIPCION"));
                    colegio.setCodigoBanner(resultSet.getString("CODIGO_BANNER"));
                    arrayColegioBean.add(colegio);
                }//
            }//
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al consultar " + sqlex.toString(), sqlex.toString(), 1, true, 3, "colegio");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al consultar " + ex.toString(), ex.toString(), 1, true, 3, "colegio");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1020:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "consultar colegio");
                }//
            }//
        }//
        return arrayColegioBean; 
    }
    
    @Override
    public ColegioBean colegioPorSolicitud(String codigoColegio) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ColegioBean aux = new ColegioBean();
        try {
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(ColegioSQL.colegioPorCodigo());
                prepareStatement.setString(1, codigoColegio);
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    aux.setCodigo(resultSet.getString("CODIGO"));
                    aux.setDescripcion(resultSet.getString("DESCRIPCION"));
                    aux.setCodigoBanner(resultSet.getString("CODIGO_BANNER"));
                }
            }
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al consultar " + sqlex.toString(), sqlex.toString(), 1, true, 3, "colegio");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al consultar " + ex.toString(), ex.toString(), 1, true, 3, "colegio");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1020:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "consultar colegios");
                }
            }
        }
        return aux;
    }
}
