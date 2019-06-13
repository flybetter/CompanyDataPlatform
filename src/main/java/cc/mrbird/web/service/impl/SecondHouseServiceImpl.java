package cc.mrbird.web.service.impl;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.web.domain.CountDate;
import cc.mrbird.web.domain.NewHouseDetail;
import cc.mrbird.web.domain.SecondHouseDetail;
import cc.mrbird.web.service.AbstractHouseService;
import cc.mrbird.web.service.SecondHouseService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("SecondHouseService")
public class SecondHouseServiceImpl<T> extends AbstractHouseService<T> implements SecondHouseService<T> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
    public List<T> querySecondHouseDetail(String startDate, String endDate, String deviceId, QueryRequest queryRequest) {
        String sql = super.getHouseDetailSql(startDate, endDate, "secondhouselog", deviceId, queryRequest);
        logger.info("sql:" + sql);
        List<T> objects = (List<T>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SecondHouseDetail.class));
        return objects;

    }

    @Override
    public Long querySecondHouseCountDetail(String startDate, String endDate, String deviceId) {
        String sql = super.getHouseDetailCountSql(startDate, endDate, "secondhouselog", deviceId);
        logger.info("sql:" + sql);
        Map<String, Object> objectMap = jdbcTemplate.queryForMap(sql);
        return (Long) objectMap.get("expr_0");
    }
}
