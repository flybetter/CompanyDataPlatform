package cc.mrbird.common.impala;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtils {
    public static void disconnect(Connection connection, ResultSet res, PreparedStatement pre) {

        try {
            if (res != null) {
                res.close();
            }
            if (pre != null) {
                pre.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
