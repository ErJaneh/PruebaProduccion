/**
*@autor: Maria Lopez Valverde
*version: 1.0
*since: 1.0
**/
package cr.ac.una.reg.info.beans;

import java.io.Serializable;

/**
 *
 * @author Mary
 */
public class NacionalidadBean implements Serializable {
    String codigo;
    String descripcion;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
