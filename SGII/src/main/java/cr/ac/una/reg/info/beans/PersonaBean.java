package cr.ac.una.reg.info.beans;

import java.io.Serializable;
import java.util.Date;

public class PersonaBean implements Serializable {

    private String identificacion;
    private String codigoTipoIdentificacion;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String conocidoComo;
    private Date fecha;
    private String codigoColegio;
    private Integer anioGraduacionColegio;
    private Integer notaColegio;
    private char indigena;
    private String codigoTerritorio;
    private String codigoGrupoIndigena;
    private Integer telefono;
    private String correoElectronico;
    private boolean selected;
    private String estadoEnSistema;

//    public PersonaBean(String identificacion, String codigoTipoIdentificacion, String nombre, String primerApellido, String segundoApellido, String conocidoComo, Date fecha, String codigoColegio, Integer anioGraduacionColegio, Integer notaColegio, char indigena, String codigoTerritorio, String codigoGrupoIndigena) {
//        this.identificacion = identificacion;
//        this.codigoTipoIdentificacion = codigoTipoIdentificacion;
//        this.nombre = nombre;
//        this.primerApellido = primerApellido;
//        this.segundoApellido = segundoApellido;
//        this.conocidoComo = conocidoComo;
//        this.fecha = fecha;
//        this.codigoColegio = codigoColegio;
//        this.anioGraduacionColegio = anioGraduacionColegio;
//        this.notaColegio = notaColegio;
//        this.indigena = indigena;
//        this.codigoTerritorio = codigoTerritorio;
//        this.codigoGrupoIndigena = codigoGrupoIndigena;
//    }

    public String getEstadoEnSistema() {
        return estadoEnSistema;
    }

    public void setEstadoEnSistema(String estadoEnSistema) {
        this.estadoEnSistema = estadoEnSistema;
    }
    
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    
    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getCodigoTipoIdentificacion() {
        return codigoTipoIdentificacion;
    }

    public void setCodigoTipoIdentificacion(String codigoTipoIdentificacion) {
        this.codigoTipoIdentificacion = codigoTipoIdentificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getConocidoComo() {
        return conocidoComo;
    }

    public void setConocidoComo(String conocidoComo) {
        this.conocidoComo = conocidoComo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCodigoColegio() {
        return codigoColegio;
    }

    public void setCodigoColegio(String codigoColegio) {
        this.codigoColegio = codigoColegio;
    }

    public Integer getAnioGraduacionColegio() {
        return anioGraduacionColegio;
    }

    public void setAnioGraduacionColegio(Integer anioGraduacionColegio) {
        this.anioGraduacionColegio = anioGraduacionColegio;
    }

    public Integer getNotaColegio() {
        return notaColegio;
    }

    public void setNotaColegio(Integer notaColegio) {
        this.notaColegio = notaColegio;
    }

    public char getIndigena() {
        return indigena;
    }

    public void setIndigena(char indigena) {
        this.indigena = indigena;
    }

    public String getCodigoTerritorio() {
        return codigoTerritorio;
    }

    public void setCodigoTerritorio(String codigoTerritorio) {
        this.codigoTerritorio = codigoTerritorio;
    }

    public String getCodigoGrupoIndigena() {
        return codigoGrupoIndigena;
    }

    public void setCodigoGrupoIndigena(String codigoGrupoIndigena) {
        this.codigoGrupoIndigena = codigoGrupoIndigena;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
    
    

}
