package cc.mrbird.job.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
public class ImpalaSqlServiceTest {

    @Autowired
    public ImpalaSqlService impalaSqlService;



    @Test
    public void customSecondHouseProfile() {
        List<Map<String, Object>> lists = impalaSqlService.customSecondHouseProfile();
        System.out.println(lists.toString());
    }
}