package cr.ac.una.reg.info.beans;

import java.io.Serializable;

public class ResponsablePromocionBean implements Serializable{
    private int index_responsable_promocion;
    private int numeroPromocion;
    private String correo;
    private String descripcion;

    public int getIndex_responsable_promocion() {
        return index_responsable_promocion;
    }

    public void setIndex_responsable_promocion(int index_responsable_promocion) {
        this.index_responsable_promocion = index_responsable_promocion;
    }
    
    public int getNumeroPromocion() {
        return numeroPromocion;
    }

    public void setNumeroPromocion(int numeroPromocion) {
        this.numeroPromocion = numeroPromocion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
