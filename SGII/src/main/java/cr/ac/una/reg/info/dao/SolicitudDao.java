/**
 *
 * @author Janel Garces Castillo
 * @version 1.0
 * @since 1.0
 *
 *
 */
package cr.ac.una.reg.info.dao;

import cr.ac.una.reg.info.beans.SolicitudBean;
import cr.ac.una.reg.info.exceptions.ExceptionConnection;
import java.util.ArrayList;
import java.util.List;

public interface SolicitudDao {

    SolicitudBean ConsultarSolicitudPorIdentificacion(SolicitudBean solicitudBean) throws ExceptionConnection;

    void ModificarSolicitudConsultaPersona(SolicitudBean solicitudBean) throws ExceptionConnection;

    SolicitudBean ConsultarSolicitudPorCodigo(SolicitudBean solicitudBean) throws ExceptionConnection;

    public ArrayList<SolicitudBean> ListarSolicitud() throws ExceptionConnection;

    public ArrayList<SolicitudBean> ListarSolicitudPorPromocion(String codigoPromocion) throws ExceptionConnection;

    void ModificarConsultaSolicitud(SolicitudBean solicitudBean) throws ExceptionConnection;

    void ingresarSolicitud(SolicitudBean p) throws ExceptionConnection;
    
    int recuperarConsecutivo(String identificacionSolicitud) throws ExceptionConnection;

}
