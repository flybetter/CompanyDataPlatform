package cc.mrbird.common.impala;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class ImpalaJDBCUtilTest {

    @Autowired
    private ImpalaJDBCUtil impalaJDBCUtil;

    @Test
    public void testJDBC() {

        List<Object> objects = impalaJDBCUtil.excuteQuery("select * from customers;", null);
        objects.forEach(System.out::println);
    }
}





