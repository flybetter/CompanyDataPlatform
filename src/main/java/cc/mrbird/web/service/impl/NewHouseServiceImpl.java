package cc.mrbird.web.service.impl;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.web.dao.NewHouseMapper;
import cc.mrbird.web.domain.CountDate;
import cc.mrbird.web.domain.NewHouse;
import cc.mrbird.web.domain.NewHouseDetail;
import cc.mrbird.web.service.AbstractHouseService;
import cc.mrbird.web.service.NewHouseService;
import com.alibaba.fastjson.JSON;
import net.sf.saxon.functions.Count;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("NewHouseService")
public class NewHouseServiceImpl<T> extends AbstractHouseService<T> implements NewHouseService<T> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private NewHouseMapper newHouseMapper;

    @Override
    public String queryCountList(String startDate, String endDate, String deviceId) {
        List<CountDate> countDates = newHouseMapper.queryCountAndDataDateByStartDateAndEndDate(startDate, endDate, deviceId);
        long[][] result = super.getHouseResult(countDates);
        return JSON.toJSONString(result);
    }

    @Override
    public List<T> queryNewHouseDetail(String startDate, String endDate, String deviceId, QueryRequest queryRequest) {
        List<T> newHouses = (List<T>) newHouseMapper.queryByStartDateAndEndDateAndDeviceIds(startDate, endDate, deviceId, queryRequest);
        return newHouses;
    }

    @Override
    public Long queryNewHouseCountDetail(String startDate, String endDate, String deviceId) {
        return newHouseMapper.queryCountByStartDateAndEndDateAndDeviceIds(startDate, endDate, deviceId);
    }


    @Override
    public List<T> queryCountAndDataDateByStartDateAndEndDateAndItemName(String startDate, String endDate, String itemName) {
        return (List<T>) newHouseMapper.queryCountAndDataDateByStartDateAndEndDateAndItemName(startDate, endDate, itemName);
    }


    @Override
    public String queryCountAndDataDateUTCByStartDateAndEndDateAndItemName(String startDate, String endDate, String itemName) {
        List<CountDate> countDates = newHouseMapper.queryCountAndDataDateByStartDateAndEndDateAndItemName(startDate, endDate, itemName);
        long[][] result = super.getHouseResult(countDates);
        return JSON.toJSONString(result);
    }
}
