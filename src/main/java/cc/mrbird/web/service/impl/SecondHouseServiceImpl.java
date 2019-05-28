package cc.mrbird.web.service.impl;

import cc.mrbird.web.service.HouseService;
import cc.mrbird.web.service.SecondHouseService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("SecondHouseService")
public class SecondHouseServiceImpl<T> extends HouseService<T> implements SecondHouseService<T> {

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<T> queryCountList(String startDate, String endDate, String deviceId) {
        String sql = super.getHouseCountSql(startDate, endDate, "secondhouselog", deviceId);
        List<T> objects = (List<T>) jdbcTemplate.queryForList(sql);
        return objects;
    }


}
