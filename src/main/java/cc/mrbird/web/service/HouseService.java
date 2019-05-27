package cc.mrbird.web.service;

import cc.mrbird.common.service.ImpalaService;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public abstract class HouseService implements ImpalaService {


    private String getNewHouseSql(String starDate, String endDate, String sqlType, String deviceId) {

        StringBuilder sql = new StringBuilder();
        sql.append("select count(*),data_date from " + sqlType + " where 1=1");
        if (StringUtils.isNotEmpty(starDate) && StringUtils.isNotEmpty(endDate)) {
            sql.append(" and data_date between '" + starDate + "' and '" + endDate + "'");
        }

        if (StringUtils.isNotEmpty(deviceId)) {
            sql.append(" and device_id='" + deviceId + "'");
        }
        return sql.toString();
    }


    @Override
    public List queryCountList(String startDate, String endDate, String deviceId) {
        return null;
    }

}
