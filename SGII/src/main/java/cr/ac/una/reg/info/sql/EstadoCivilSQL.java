package cr.ac.una.reg.info.sql;

/**

* @author Sheng Yuan Lin
* @version 1.0
* @since 1.0

**/
public class EstadoCivilSQL {
    public synchronized static String listarEstadoCivil() {
        String sql = "SELECT CODIGO, DESCRIPCION FROM REG_GENERAL_ESTADO_CIVIL";
        return sql;
    }
}
