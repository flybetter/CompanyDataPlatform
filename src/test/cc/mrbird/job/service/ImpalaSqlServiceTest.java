package cc.mrbird.job.service;

import cc.mrbird.web.domain.CRMSecondHouseStatistics;
import cc.mrbird.web.service.ImpalaSqlService;
import com.alibaba.fastjson.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
public class ImpalaSqlServiceTest {

    @Autowired
    public ImpalaSqlService impalaSqlService;


    @Test
    public void customSecondHouseProfile() {
        List<CRMSecondHouseStatistics> lists = impalaSqlService.customSecondHouseProfile("2019-07-08", "2019-07-12");
        System.out.println(JSONArray.toJSON(lists));
    }
}