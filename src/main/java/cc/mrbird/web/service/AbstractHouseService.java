package cc.mrbird.web.service;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.ImpalaService;
import cc.mrbird.common.util.DateUtil;
import cc.mrbird.web.domain.CountDate;
import com.sun.tools.corba.se.idl.StringGen;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public abstract class AbstractHouseService<T> implements ImpalaService<T> {

    protected String getHouseCountSql(String startDate, String endDate, String sqlTable, String deviceId) {
        StringBuilder sql = new StringBuilder();
        sql.append("select count(*) as count ,data_date from " + sqlTable + " where 1=1");
        if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
            sql.append(" and data_date between '" + startDate + "' and '" + endDate + "'");
        }

        if (StringUtils.isNotEmpty(deviceId)) {
            sql.append(" and device_id='" + deviceId + "'");
        }
        sql.append(" group by data_date order by data_date");
        return sql.toString();
    }


    protected String getHouseDetailSql(String startDate, String endDate, String sqlTable, String deviceId, QueryRequest queryRequest) {
        StringBuilder sql = new StringBuilder();

        if (sqlTable.equalsIgnoreCase("newhouselog")) {

            sql.append("select device_id, city_name, prj_itemname,price_show,pic_hx_totalprice,pic_area from newhouselog where 1=1");

        } else if (sqlTable.equalsIgnoreCase("secondhouselog")) {

            sql.append("SELECT device_id,city,blockshowname, averprice_x,price,buildarea from secondhouselog where 1=1");

        }

        if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
            sql.append(" and data_date between '" + startDate + "' and '" + endDate + "'");
        }

        if (StringUtils.isNotEmpty(deviceId)) {
            sql.append(" and device_id='" + deviceId + "'");
        }
        sql.append(" order by device_id limit " + queryRequest.getPageSize() + " offset " + queryRequest.getPageSize() * (queryRequest.getPageNum()-1));

        return sql.toString();
    }


    protected String getHouseDetailCountSql(String startDate, String endDate, String sqlTable, String deviceId) {
        StringBuilder sql = new StringBuilder();

        if (sqlTable.equalsIgnoreCase("newhouselog")) {

            sql.append("select count(*) from newhouselog where 1=1");

        } else if (sqlTable.equalsIgnoreCase("secondhouselog")) {

            sql.append("SELECT count(*) from secondhouselog where 1=1");

        }

        if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
            sql.append(" and data_date between '" + startDate + "' and '" + endDate + "'");
        }

        if (StringUtils.isNotEmpty(deviceId)) {
            sql.append(" and device_id='" + deviceId + "'");
        }

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
