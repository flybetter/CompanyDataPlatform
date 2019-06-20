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
