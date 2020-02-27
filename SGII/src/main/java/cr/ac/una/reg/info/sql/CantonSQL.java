/**
 *
 * @author Sheng Yuan Lin
 * @version 1.0
 * @since 1.0
 *
 *
 */
package cr.ac.una.reg.info.sql;

public class CantonSQL {

    public synchronized static String listarCantonPorProvincia(String provincia) {
        String sql = "select GTVZIPC_CODE, GTVZIPC_CITY, GTVZIPC_CNTY_CODE from GTVZIPC where "
                + "GTVZIPC_CITY like '%%(cant%%' and GTVZIPC_CODE like '%s%%'";
        sql = String.format(sql, provincia);
        return sql;
    }

    public synchronized static String provinciaPorCodigo() {
        String sql = "select GTVZIPC_CODE, GTVZIPC_CITY, GTVZIPC_CNTY_CODE from GTVZIPC where "
                + "GTVZIPC_CITY like '%%(cant%%' and GTVZIPC_CODE =? ";
        return sql;
    }
}
