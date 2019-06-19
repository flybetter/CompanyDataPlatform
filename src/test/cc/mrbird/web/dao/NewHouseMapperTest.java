package cc.mrbird.web.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@EnableAutoConfiguration
@SpringBootTest
@RunWith(SpringRunner.class)
public class NewHouseMapperTest {


    @Autowired
    @Qualifier("impalaSqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;


    @Test
    public void count() {

        System.out.println(sqlSessionTemplate.toString());
    }
}