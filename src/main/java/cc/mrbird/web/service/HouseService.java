package cc.mrbird.web.service;

import cc.mrbird.common.service.ImpalaService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public abstract class HouseService<T> implements ImpalaService<T> {

    protected String getHouseCountSql(String starDate, String endDate, String sqlTable, String deviceId) {
        StringBuilder sql = new StringBuilder();
        sql.append("select count(*) as count ,data_date from " + sqlTable + " where 1=1");
        if (StringUtils.isNotEmpty(starDate) && StringUtils.isNotEmpty(endDate)) {
            sql.append(" and data_date between '" + starDate + "' and '" + endDate + "'");
        }

        if (StringUtils.isNotEmpty(deviceId)) {
            sql.append(" and device_id='" + deviceId + "'");
        }
        sql.append(" group by data_date order by data_date desc");
        return sql.toString();
    }


    @Override
    public List queryCountList(String startDate, String endDate, String deviceId) {
        return null;
    }

}
