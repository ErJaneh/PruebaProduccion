package cr.ac.una.reg.info.beans;

import java.io.Serializable;

public class NoCalificaBean implements Serializable{
    private String identificacion;
    private String motivo;

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
    
}
