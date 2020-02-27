/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.CarreraBean;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.sql.CarreraSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author MSI
 */
public class CarreraDaoImp extends Connector implements CarreraDao{

    public CarreraDaoImp() throws ExceptionConnection {
        this.inicializarDataSource();
    }
    
    @Override
    public ArrayList<CarreraBean> ListarCarreras() throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        ArrayList<CarreraBean> arrayCarreraBean = new ArrayList();
        try{
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(CarreraSQL.listarCarreras());
                ResultSet resultSet = prepareStatement.executeQuery();                
                while (resultSet.next()) {
                    CarreraBean carrera = new CarreraBean();
                    carrera.setCodigo(resultSet.getString("CODIGO"));
                    carrera.setDescripcion(resultSet.getString("DESCRIPCION"));
                    arrayCarreraBean.add(carrera);
                }//
            }//
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al consultar " + sqlex.toString(), sqlex.toString(), 1, true, 3, "carreras");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al consultar " + ex.toString(), ex.toString(), 1, true, 3, "carreras");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1020:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "consultar carreras");
                }//
            }//
        }//
        return arrayCarreraBean;
    }

    @Override
    public String getDescripcionCarrera(String codigo) throws ExceptionConnection {
        PreparedStatement prepareStatement = null;
        String descripcionCarrera = "";
        try{
            if (this.openConnection()) {
                prepareStatement = this.getConexion().prepareStatement(CarreraSQL.getDescripcionCarrera(codigo));
                ResultSet resultSet = prepareStatement.executeQuery();                
                while (resultSet.next()) {
                    descripcionCarrera = resultSet.getString("DESCRIPCION");
                }//
            }//
        } catch (ExceptionConnection exc) {
            throw exc;
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al consultar " + sqlex.toString(), sqlex.toString(), 1, true, 3, "carrera");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al consultar " + ex.toString(), ex.toString(), 1, true, 3, "carrera");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1020:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "consultar carrera");
                }//
            }//
        }//
        return descripcionCarrera;
    }
    
    
  
}
