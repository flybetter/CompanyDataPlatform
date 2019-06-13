package cc.mrbird.web.service;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.ImpalaService;

import java.util.List;

public interface SecondHouseService<T> extends ImpalaService<T> {

    List<T> querySecondHouseDetail(String startDate, String endDate, String deviceId, QueryRequest queryRequest);

    Long querySecondHouseCountDetail(String startDate, String endDate, String deviceId);

}
