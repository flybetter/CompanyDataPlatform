package cc.mrbird.web.service.impl;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.web.domain.CountDate;
import cc.mrbird.web.domain.NewHouseDetail;
import cc.mrbird.web.service.AbstractHouseService;
import cc.mrbird.web.service.NewHouseService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
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


    protected String getNewHouseDetailSql(String startDate, String endDate, String deviceId) {
        StringBuilder sql = new StringBuilder();
        sql.append("select device_id, city_name, prj_itemname,price_show,pic_hx_totalprice  from newhouselog where 1=1");
        if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
            sql.append(" and data_date between '" + startDate + "' and '" + endDate + "'");
        }

        if (StringUtils.isNotEmpty(deviceId)) {
            sql.append(" and device_id='" + deviceId + "'");
        }
        sql.append(" group by data_date order by data_date");
        return sql.toString();
    }

    @Override
    public String queryCountList(String startDate, String endDate, String deviceId) {
        String sql = super.getHouseCountSql(startDate, endDate, "newhouselog", deviceId);
        List<T> objects = (List<T>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CountDate.class));
        long[][] result = super.getHouseResult(objects);
        return JSON.toJSONString(result);
    }

    @Override
    public List<T> queryNewHouseDetail(String startDate, String endDate, String deviceId, QueryRequest queryRequest) {
        String sql = super.getHouseDetailSql(startDate, endDate, "newhouselog", deviceId, queryRequest);
        logger.info("sql:" + sql);
        List<T> objects = (List<T>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(NewHouseDetail.class));
        return objects;
    }

}
