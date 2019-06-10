package cc.mrbird.common.service;

import java.util.List;
import java.util.Map;

public interface ImpalaService<T> {

    String queryCountList(String startDate, String endDate, String deviceId);


}
