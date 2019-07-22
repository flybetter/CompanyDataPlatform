package cc.mrbird.web.service;

import cc.mrbird.web.domain.CRMSecondHouseStatistics;

import java.util.List;
import java.util.Map;

public interface ImpalaSqlService {
    public List<CRMSecondHouseStatistics> customSecondHouseProfile(String startDate, String endDate);
}
