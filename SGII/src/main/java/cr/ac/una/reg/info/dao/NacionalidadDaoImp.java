/**
*@autor: Maria Lopez Valverde
*version: 1.0
*since: 1.0
* En esta clase se listan las nacionalidades con una invocación a la clase NacionalidadSQL.consultaCodigosNacionalidad()
**/
package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.NacionalidadBean;
import cr.ac.una.reg.info.connection.Connector;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import cr.ac.una.reg.info.sql.NacionalidadSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NacionalidadDaoImp  extends Connector implements NacionalidadDao{
    
    public NacionalidadDaoImp() throws ExceptionConnection{
        this.inicializarDataSource();
    }
    
    //METODO PARA LISTAR LAS NACIONALIDADES
    @Override
    public ArrayList<NacionalidadBean> ListarNacionalidad() throws ExceptionConnection {
        
      PreparedStatement prepareStatement = null;      
      ArrayList<NacionalidadBean> arrayNacionalidadBean = new ArrayList();
      try{
          if(this.openConnection()){
              prepareStatement = this.getConexion().prepareStatement(NacionalidadSQL.consultaCodigosNacionalidad());
              ResultSet resultSet = prepareStatement.executeQuery();
              
              while(resultSet.next()){
                    NacionalidadBean nacionalidad=new NacionalidadBean();
                    nacionalidad.setCodigo(resultSet.getString("STVNATN_CODE"));
                    nacionalidad.setDescripcion(resultSet.getString("STVNATN_NATION"));
                    arrayNacionalidadBean.add(nacionalidad);
                }
          }
      }catch (ExceptionConnection exc) {
            throw exc;
            
        } catch (SQLException sqlex) {
            throw new ExceptionConnection("1018:" + "Error al consultar " + sqlex.toString(), sqlex.toString(), 1, true, 3, "nacionalidades");
        } catch (Exception ex) {
            throw new ExceptionConnection("1019:" + "Error al consultar " + ex.toString(), ex.toString(), 1, true, 3, "nacionalidades");
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                    this.closeConnection();
                } catch (SQLException sqlex) {
                    throw new ExceptionConnection("1020:" + "Error al cerrar statement", sqlex.toString(), 1, true, 3, "consultar nacionalidades");
                }//
            }//
        }//
        return arrayNacionalidadBean;
    }
    
}
