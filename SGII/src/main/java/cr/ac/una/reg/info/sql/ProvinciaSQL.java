/**
 *
 * @author Sheng Yuan Lin
 * @version 1.0
 * @since 1.0
 *
 *
 */
package cr.ac.una.reg.info.sql;

public class ProvinciaSQL {

    public synchronized static String listarProvincias() {
        String sql = "select distinct(substr(GTVZIPC_CODE,1,1)) as CODIGOPROV, GTVZIPC_STAT_CODE from GTVZIPC where "
                + "GTVZIPC_CODE not like '9%%' order by CODIGOPROV asc ";
        return sql;
    }

    public synchronized static String provinciaPorCodigo() {        
        String sql = "select distinct(substr(GTVZIPC_CODE,1,1)) as CODIGOPROV, GTVZIPC_STAT_CODE from GTVZIPC "
                + "where GTVZIPC_CODE =? ";
        return sql;
    }
}
