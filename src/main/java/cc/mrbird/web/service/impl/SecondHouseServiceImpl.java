package cc.mrbird.web.service.impl;

import cc.mrbird.web.domain.CountDate;
import cc.mrbird.web.service.AbstractHouseService;
import cc.mrbird.web.service.SecondHouseService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SecondHouseService")
public class SecondHouseServiceImpl<T> extends AbstractHouseService<T> implements SecondHouseService<T> {

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public String queryCountList(String startDate, String endDate, String deviceId) {
        String sql = super.getHouseCountSql(startDate, endDate, "secondhouselog", deviceId);
        List<CountDate> objects = (List<CountDate>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<CountDate>(CountDate.class));
        long[][] result = super.getHouseResult(objects);
        return JSON.toJSONString(result);
    }


    @Override
    public String querySecondHouseDetail(String startDate, String endDate, String deviceId, String sqlTable) {
        return null;
    }
}
