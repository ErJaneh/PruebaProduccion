/**
 *
 * @author Janel Garces Castillo
 * @version 1.0
 * @since 1.0
 *
 *
 */
package cr.ac.una.reg.info.sql;

public class SolicitudSQL {

    public synchronized static String modificarSolicitudPersonaConsulta() {
        String sql = "update DR_SGII_SOLICITUD set DIRECCION_EXACTA =? WHERE identificacion_solicitud =?";
        return sql;
    }

    public synchronized static String modificarSolicitudSolicitudesAdmitidos() {
        String sql = "update DR_SGII_SOLICITUD set ESTADO_SOLICITUD_1 =? WHERE identificacion_solicitud =? ";
        return sql;
    }

    public synchronized static String getSolicitudPorIdentificacion() {
        String sql = "select IDENTIFICACION_SOLICITUD, IDENTIFICACION_PERSONA, GENERO, ESTADO_SOLICITUD_1, ESTADO_SOLICITUD_2, CODIGO_UBICACION, DIRECCION_EXACTA, CODIGO_ESTADO_CIVIL, ADECUACION, CODIGO_NACIONALIDAD, OBSERVACIONES, CODIGO_PROMOCION_1, CODIGO_PROMOCION_2 "
                + "from DR_SGII_SOLICITUD  "
                + "where IDENTIFICACION_PERSONA =? ";
        return sql;
    }

    public synchronized static String listarSolicitud() {
        String sql = "SELECT IDENTIFICACION_SOLICITUD, IDENTIFICACION_PERSONA, GENERO, ESTADO_SOLICITUD_1, ESTADO_SOLICITUD_2, CODIGO_UBICACION, DIRECCION_EXACTA, CODIGO_ESTADO_CIVIL, ADECUACION, CODIGO_NACIONALIDAD, OBSERVACIONES, NUMERO_PROMOCION, CODIGO_PROMOCION_1, CODIGO_PROMOCION_2 FROM DR_SGII_SOLICITUD";
        return sql;
    }

    public synchronized static String detalleSolicitud() {
        String sql = "SELECT IDENTIFICACION_SOLICITUD, IDENTIFICACION_PERSONA, GENERO, ESTADO_SOLICITUD_1, ESTADO_SOLICITUD_2, CODIGO_UBICACION, DIRECCION_EXACTA, CODIGO_ESTADO_CIVIL, TELEFONO, ADECUACION, CORREO_ELECTRONICO, CODIGO_NACIONALIDAD, OBSERVACIONES, CODIGO_PROMOCION_1, CODIGO_PROMOCION_2 FROM DR_SGII_SOLICITUD WHERE IDENTIFICACION_SOLICITUD=?";
        return sql;
    }

    public synchronized static String getSolicitudPorCodigo() {
        String sql = "select s.identificacion_solicitud, s.identificacion_persona, s.genero, s.estado_solicitud_1, s.estado_solicitud_2, s.codgo_ubicacion, s.direccion_exacta, s.codigo_estado_civil, s.adecuacion, s.codigo_nacionalidad, s.observaciones, s.codigo_promocion_1, s.codigo_promocion_1 "
                + "from DR_SGII_SOLICITUD s "
                + "where s.IDENTIFICACION_SOLICITUD =?";
        return sql;
    }

    public synchronized static String listarSolicitudPorPromocion() {
        String sql = "SELECT IDENTIFICACION_SOLICITUD, IDENTIFICACION_PERSONA, GENERO, ESTADO_SOLICITUD_1, ESTADO_SOLICITUD_2, CODIGO_UBICACION, DIRECCION_EXACTA, CODIGO_ESTADO_CIVIL, ADECUACION, CODIGO_NACIONALIDAD, OBSERVACIONES, CODIGO_PROMOCION_1, CODIGO_PROMOCION_2 FROM DR_SGII_SOLICITUD WHERE NUMERO_PROMOCION=? ";
        return sql;
    }

    public synchronized static String modificarSolicitudCodigoConsulta() {
       String sql = "UPDATE DR_SGII_SOLICITUD SET IDENTIFICACION_PERSONA =?, GENERO =?, ESTADO_SOLICITUD_1 =?, ESTADO_SOLICITUD_2 =?, "
                + "CODIGO_UBICACION =?, DIRECCION_EXACTA =?, CODIGO_ESTADO_CIVIL =?, ADECUACION =?, "
                + "CODIGO_NACIONALIDAD =?, OBSERVACIONES =?, CODIGO_PROMOCION_1 =?, CODIGO_PROMOCION_2 =? WHERE IDENTIFICACION_SOLICITUD =?";
        return sql;
    }

    public synchronized static String ingresarSolicitud() {
        String sql = "insert into DR_SGII_SOLICITUD(IDENTIFICACION_SOLICITUD,IDENTIFICACION_PERSONA,GENERO,"
                + "ESTADO_SOLICITUD_1, CODIGO_UBICACION, DIRECCION_EXACTA, CODIGO_ESTADO_CIVIL,ADECUACION,"
                + "CODIGO_NACIONALIDAD, OBSERVACIONES,CODIGO_PROMOCION_1,NUMERO_PROMOCION,ESTADO_SOLICITUD_2,CODIGO_PROMOCION_2) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return sql;
    }
    
    public synchronized static String recuperarConsecutivo(String identificacionSolicitud){
        String sql= "select coalesce(max(to_number(regexp_substr(IDENTIFICACION_SOLICITUD, '[0-9]*$'))), 0) as "
                + "CONSECUTIVO from DR_SGII_SOLICITUD where regexp_like(IDENTIFICACION_SOLICITUD,'%s'||'[0-9]*')";
        sql=String.format(sql,identificacionSolicitud);
        return sql;
    }
}
