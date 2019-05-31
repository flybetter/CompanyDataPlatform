package cc.mrbird.web.service;

import cc.mrbird.common.service.ImpalaService;
import cc.mrbird.common.util.DateUtil;
import cc.mrbird.web.domain.CountDate;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    protected long[][] getHouseResult(List list) {
        long[][] result = new long[list.size()][2];

        try {
            for (int i = 0; i < list.size(); i++) {
                CountDate countDate = (CountDate) list.get(i);
                result[i][0] = countDate.getUTC();
                result[i][1] = countDate.getCount();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public String queryCountList(String startDate, String endDate, String deviceId) {
        return null;
    }

}
