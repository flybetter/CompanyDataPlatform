package cc.mrbird.web.service;

import cc.mrbird.common.service.ImpalaService;

public interface SecondHouseService<T> extends ImpalaService<T> {
    String querySecondHouseDetail(String startDate, String endDate, String deviceId, String sqlTable);
}
