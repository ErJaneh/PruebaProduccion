/**

* @author Sheng Yuan Lin
* @version 1.0
* @since 1.0

**/
package cr.ac.una.reg.info.beans;

import java.io.Serializable;

public class ProvinciaBean implements Serializable {
    String codigo;
    String descripcion;

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    
    
}
