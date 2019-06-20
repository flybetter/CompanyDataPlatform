package cc.mrbird.web.service.impl;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.web.dao.SecondHouseMapper;
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
    private SecondHouseMapper secondHouseMapper;

    @Override
    public String queryCountList(String startDate, String endDate, String deviceId) {
        List<CountDate> countDates = secondHouseMapper.queryCountAndDataDateByStartDateAndEndDate(startDate, endDate, deviceId);
        long[][] result = super.getHouseResult(countDates);
        return JSON.toJSONString(result);
    }

    @Override
    public List<T> querySecondHouseDetail(String startDate, String endDate, String deviceId, QueryRequest queryRequest) {
        List<T> newHouses = (List<T>) secondHouseMapper.queryByStartDateAndEndDateAndDeviceIds(startDate, endDate, deviceId, queryRequest);
        return newHouses;
    }

    @Override
    public Long querySecondHouseCountDetail(String startDate, String endDate, String deviceId) {
        return secondHouseMapper.queryCountByStartDateAndEndDateAndDeviceIds(startDate, endDate, deviceId);
    }
}
