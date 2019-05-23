package cc.mrbird.common.impala;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
public class SpringJdbcTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testDemo() {

        int result = jdbcTemplate.queryForObject("select count(*) from customers;", Integer.class);

        System.out.println(result);


    }

}
