package cc.mrbird.web.service.impl;

import cc.mrbird.web.domain.CountDate;
import cc.mrbird.web.service.AbstractHouseService;
import cc.mrbird.web.service.NewHouseService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("NewHouseService")
public class NewHouseServiceImpl<T> extends AbstractHouseService<T> implements NewHouseService<T> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public String queryCountList(String startDate, String endDate, String deviceId) {
        String sql = super.getHouseCountSql(startDate, endDate, "newhouselog", deviceId);
        List<T> objects = (List<T>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(CountDate.class));
        long[][] result = super.getHouseResult(objects);
        return JSON.toJSONString(result);
    }

    @Override
    public String queryNewHouseDetail(String startDate, String endDate, String deviceId, String sqlTable) {
        return null;
    }



}
