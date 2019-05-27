package cc.mrbird.common.impala;

import jdk.nashorn.internal.runtime.options.Option;
import org.apache.poi.ss.formula.functions.T;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class ImpalaJDBCUtilTest {

//    @Autowired
//    private ImpalaJDBCUtil impalaJDBCUtil;
//
//    @Autowired
//    private ImpalaUtil impalaUtil;
////
////    @Test
////    public void testJDBC() {
////        List<Object> objects = impalaJDBCUtil.excuteQuery("select * from customers;", null);
////        objects.forEach(System.out::println);
////    }
//
//
//    @Test
//    public void testImpala() throws Exception {
//
//        Function<? super ResultSet, String> function = resultSet -> {
//            try {
//                return resultSet.getString("name");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            return null;
//        };
//     impalaUtil.executeQuery("select * from customers;", function);
//    }


}





