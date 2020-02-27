package cr.ac.una.reg.info.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PromocionBean implements Serializable{

    private String codigoPromocion;
    private int numeroPromocion;
    private String descripcionPromocion;
    private String codigoGrupoDeInteres;
    private List<CarreraBean> carrerasPromocion;
    private List<ResponsablePromocionBean> responsablesPromocion;
    private String codigoPeriodo;
    private String codigoUnidadAcademica;
    private Integer cupos;
    private java.util.Date fechaInicioPromocion;
    private java.util.Date fechaFinPromocion;
    private String estadoPromocion;

    public String getCodigoPromocion() {
        return codigoPromocion;
    }

    public void setCodigoPromocion(String codigoPromocion) {
        this.codigoPromocion = codigoPromocion;
    }

    public void setNumeroPromocion(int numeroPromocion) {
        this.numeroPromocion = numeroPromocion;
    }

    public int getNumeroPromocion() {
        return numeroPromocion;
    }

    public String getDescripcionPromocion() {
        return descripcionPromocion;
    }

    public void setDescripcionPromocion(String descripcionPromocion) {
        this.descripcionPromocion = descripcionPromocion;
    }

    public String getCodigoGrupoDeInteres() {
        return codigoGrupoDeInteres;
    }

    public void setCodigoGrupoDeInteres(String codigoGrupoDeInteres) {
        this.codigoGrupoDeInteres = codigoGrupoDeInteres;
    }

    public List<CarreraBean> getCarrerasPromocion() {
        return carrerasPromocion;
    }

    public void setCarrerasPromocion(List<CarreraBean> carrerasPromocion) {
        this.carrerasPromocion = carrerasPromocion;
    }

    public List<ResponsablePromocionBean> getResponsablesPromocion() {
        return responsablesPromocion;
    }

    public void setResponsablesPromocion(List<ResponsablePromocionBean> responsablesPromocion) {
        this.responsablesPromocion = responsablesPromocion;
    }
 
    public String getCodigoPeriodo() {
        return codigoPeriodo;
    }

    public void setCodigoPeriodo(String codigoPeriodo) {
        this.codigoPeriodo = codigoPeriodo;
    }

    public String getCodigoUnidadAcademica() {
        return codigoUnidadAcademica;
    }

    public void setCodigoUnidadAcademica(String codigoUnidadAcademica) {
        this.codigoUnidadAcademica = codigoUnidadAcademica;
    }

    public Integer getCupos() {
        return cupos;
    }

    public void setCupos(Integer cupos) {
        this.cupos = cupos;
    }

    public Date getFechaInicioPromocion() {
        return fechaInicioPromocion;
    }

    public void setFechaInicioPromocion(Date fechaInicioPromocion) {
        this.fechaInicioPromocion = fechaInicioPromocion;
    }

    public Date getFechaFinPromocion() {
        return fechaFinPromocion;
    }

    public void setFechaFinPromocion(Date fechaFinPromocion) {
        this.fechaFinPromocion = fechaFinPromocion;
    }

    public String getEstadoPromocion() {
        return estadoPromocion;
    }

    public void setEstadoPromocion(String estadoPromocion) {
        this.estadoPromocion = estadoPromocion;
    }
    
}