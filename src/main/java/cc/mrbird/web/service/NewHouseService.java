package cc.mrbird.web.service;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.ImpalaService;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public interface NewHouseService<T> extends ImpalaService<T> {
    List<T> queryNewHouseDetail(String startDate, String endDate, String deviceId, QueryRequest queryRequest);

    Long queryNewHouseCountDetail(String startDate, String endDate, String deviceId);

    List<T> queryCountAndDataDateByStartDateAndEndDateAndItemName(String startDate, String endDate, String itemName);

    String queryCountAndDataDateUTCByStartDateAndEndDateAndItemName(String startDate, String endDate, String itemName);

}
