package cr.ac.una.reg.info.beans;

import java.io.Serializable;

public class SolicitudBean implements Serializable{
    private String identificacionSolicitud;
    private String identificacionPersona;
    private String genero;
    private Integer estadoSolicitud1;
    private Integer estadoSolicitud2;
    private String codigoUbicacion;
    private String direccionExacta;
    private char codigoEstadoCivil;
    private char adecuacion;
    private String codigoNacionalidad;
    private String observaciones;
    private int numeroPromocion;
    private String codigoPromocion1;
    private String descripcionCarrera1;
    private String codigoPromocion2;
    private String descripcionCarrera2;
    private PersonaBean postulanteAsociado;

    public String getIdentificacionSolicitud() {
        return identificacionSolicitud;
    }

    public void setIdentificacionSolicitud(String identificacionSolicitud) {
        this.identificacionSolicitud = identificacionSolicitud;
    }

    public String getIdentificacionPersona() {
        return identificacionPersona;
    }

    public void setIdentificacionPersona(String identificacionPersona) {
        this.identificacionPersona = identificacionPersona;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getEstadoSolicitud1() {
        return estadoSolicitud1;
    }
     public Integer getEstadoSolicitud2() {
        return estadoSolicitud2;
    }

    public void setEstadoSolicitud1(Integer estadoSolicitud1) {
        this.estadoSolicitud1 = estadoSolicitud1;
    }
     public void setEstadoSolicitud2(Integer estadoSolicitud2) {
        this.estadoSolicitud2 = estadoSolicitud2;
    }

    public String getCodigoUbicacion() {
        return codigoUbicacion;
    }

    public void setCodigoUbicacion(String codigoUbicacion) {
        this.codigoUbicacion = codigoUbicacion;
    }

    public String getDireccionExacta() {
        return direccionExacta;
    }

    public void setDireccionExacta(String direccionExacta) {
        this.direccionExacta = direccionExacta;
    }

    public char getCodigoEstadoCivil() {
        return codigoEstadoCivil;
    }

    public void setCodigoEstadoCivil(char codigoEstadoCivil) {
        this.codigoEstadoCivil = codigoEstadoCivil;
    }

    public char getAdecuacion() {
        return adecuacion;
    }

    public void setAdecuacion(char adecuacion) {
        this.adecuacion = adecuacion;
    }

    public String getCodigoNacionalidad() {
        return codigoNacionalidad;
    }

    public void setCodigoNacionalidad(String codigoNacionalidad) {
        this.codigoNacionalidad = codigoNacionalidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getNumeroPromocion() {
        return numeroPromocion;
    }

    public void setNumeroPromocion(int numeroPromocion) {
        this.numeroPromocion = numeroPromocion;
    }

    public String getCodigoPromocion1() {
        return codigoPromocion1;
    }

    public void setCodigoPromocion1(String codigoPromocion1) {
        this.codigoPromocion1 = codigoPromocion1;
    }

    public String getCodigoPromocion2() {
        return codigoPromocion2;
    }

    public void setCodigoPromocion2(String codigoPromocion2) {
        this.codigoPromocion2 = codigoPromocion2;
    }

    public PersonaBean getPostulanteAsociado() {
        return postulanteAsociado;
    }

    public void setPostulanteAsociado(PersonaBean postulanteAsociado) {
        this.postulanteAsociado = postulanteAsociado;
    }

    public String getDescripcionCarrera1() {
        return descripcionCarrera1;
    }

    public void setDescripcionCarrera1(String descripcionCarrera1) {
        this.descripcionCarrera1 = descripcionCarrera1;
    }

    public String getDescripcionCarrera2() {
        return descripcionCarrera2;
    }

    public void setDescripcionCarrera2(String descripcionCarrera2) {
        this.descripcionCarrera2 = descripcionCarrera2;
    }

}
