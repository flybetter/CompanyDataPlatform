package cc.mrbird.web.service;

import cc.mrbird.common.service.ImpalaService;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public interface NewHouseService<T> extends ImpalaService<T> {
    String queryNewHouseDetail(String startDate, String endDate, String deviceId, String sqlTable);

}
