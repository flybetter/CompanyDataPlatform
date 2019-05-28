package cc.mrbird.web.service.impl;

import cc.mrbird.web.service.HouseService;
import cc.mrbird.web.service.NewHouseService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("NewHouseService")
public class NewHouseServiceImpl<T> extends HouseService<T> implements NewHouseService<T> {

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<T> queryCountList(String startDate, String endDate, String deviceId) {
        String sql = super.getHouseCountSql(startDate, endDate, "newhouselog", deviceId);
        List<T> objects = (List<T>) jdbcTemplate.queryForList(sql);
        return objects;
    }

}
