package impala;

import java.util.List;

public class test {
    public static void main(String[] args) {
        String username = null;
        String password = null;
        String driver = "com.cloudera.impala.jdbc41.Driver";
        String url = "jdbc:impala://192.168.10.164:21050/";

        ImpalaJDBCUtil jdbcUtil = new ImpalaJDBCUtil(driver, url, username, password);

        String sql = "select * from customers;";

        List<Object> objects = jdbcUtil.excuteQuery(sql, null);

        objects.forEach(System.out::println);

    }
}
