/**
 *
 * @author Sheng Yuan Lin
 * @version 1.0
 * @since 1.0
 *
 *
 */
package cr.ac.una.reg.info.sql;

public class DistritoSQL {

    public synchronized static String listarDistritoPorCanton(String canton) {
        String sql = "select GTVZIPC_CODE, GTVZIPC_CITY from GTVZIPC where GTVZIPC_CODE like "
                + "(select GTVZIPC_CNTY_CODE from GTVZIPC where GTVZIPC_CITY= '%s')||'%%' and GTVZIPC_CITY not "
                + "like '%%(cant%%'";
        sql = String.format(sql, canton);
        return sql;
    }

    public synchronized static String distritoPorCodigo() {
        String sql = "select GTVZIPC_CODE, GTVZIPC_CITY, GTVZIPC_CNTY_CODE from GTVZIPC where GTVZIPC_CODE =? ";
        return sql;
    }
}
