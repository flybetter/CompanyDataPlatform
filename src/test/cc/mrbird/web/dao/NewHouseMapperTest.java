package cc.mrbird.web.dao;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.web.domain.CountDate;
import cc.mrbird.web.domain.NewHouse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@EnableAutoConfiguration
@SpringBootTest
@RunWith(SpringRunner.class)
public class NewHouseMapperTest {


    @Autowired
    @Qualifier("impalaSqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private NewHouseMapper newHouseMapper;

    @Test
    public void count() {
        Long count = newHouseMapper.queryCountByStartDateAndEndDateAndDeviceIds("2019-06-01", "2019-06-11", null);
        System.out.println(count);
    }

    @Test
    public void query() {
        QueryRequest queryRequest = new QueryRequest();
        queryRequest.setPageNum(2);
        queryRequest.setPageSize(100);
        List<NewHouse> newHouseList = newHouseMapper.queryByStartDateAndEndDateAndDeviceIds("2019-06-01", "2019-06-11", null, queryRequest);
        newHouseList.stream().forEach(newHouse -> {
            System.out.println(newHouse.getDevice_id());
        });
    }

    @Test
    public void query2() {
        List<CountDate> countDates = newHouseMapper.queryCountAndDataDateByStartDateAndEndDate(null, null, null);
        countDates.stream().forEach(countDate -> {
            System.out.println(countDate.getUTC());
        });
    }
}