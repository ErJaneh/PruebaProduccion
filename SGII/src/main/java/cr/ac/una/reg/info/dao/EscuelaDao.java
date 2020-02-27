/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.EscuelaBean;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.util.ArrayList;

/**
 *
 * @author MSI
 */
public interface EscuelaDao {
    
    ArrayList<EscuelaBean>ListarEscuelas( ) throws ExceptionConnection;
}
