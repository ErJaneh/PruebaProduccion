/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.reg.info.beans;

import java.io.Serializable;

/**
 *
 * @author sylin
 */
public class CantonBean implements Serializable{
    String codigo;
    String descripcion;
    String identificador;

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    
    
}
