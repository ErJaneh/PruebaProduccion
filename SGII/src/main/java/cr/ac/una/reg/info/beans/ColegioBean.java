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
public class ColegioBean implements Serializable{
    String codigo;
    String descripcion;
    String codigoBanner;

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCodigoBanner(String codigoBanner) {
        this.codigoBanner = codigoBanner;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCodigoBanner() {
        return codigoBanner;
    }
    
}
