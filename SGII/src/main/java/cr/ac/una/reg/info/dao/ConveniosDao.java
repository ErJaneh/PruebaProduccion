/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.ConvenioBean;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.util.ArrayList;

/**
 *
 * @author MSI
 */
public interface ConveniosDao {
    
    ArrayList<ConvenioBean>ListarConvenios( ) throws ExceptionConnection;
    
}
