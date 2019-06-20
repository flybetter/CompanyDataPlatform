package cc.mrbird.web.dao;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.web.domain.CountDate;
import cc.mrbird.web.domain.NewHouse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SecondHouseMapper {
    Long queryCountByStartDateAndEndDateAndDeviceIds(@Param("startDate") String startDate, @Param("endDate") String endDate,
                                                     @Param("deviceId") String deviceId);

    List<NewHouse> queryByStartDateAndEndDateAndDeviceIds(@Param("startDate") String startDate, @Param("endDate") String endDate,
                                                          @Param("deviceId") String deviceId, @Param("queryRequest") QueryRequest queryRequest);

    List<CountDate> queryCountAndDataDateByStartDateAndEndDate(@Param("startDate") String startDate, @Param("endDate") String endDate,
                                                               @Param("deviceId") String deviceId);
}
