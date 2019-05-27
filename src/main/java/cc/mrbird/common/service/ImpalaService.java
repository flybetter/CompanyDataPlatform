package cc.mrbird.common.service;

import java.util.List;

public interface ImpalaService<T> {

    List<T> queryCountList(String startDate, String endDate, String deviceId);
}
