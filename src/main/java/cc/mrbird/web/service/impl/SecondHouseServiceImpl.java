package cc.mrbird.web.service.impl;

import cc.mrbird.web.service.HouseService;
import cc.mrbird.web.service.SecondHouseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SecondHouseService")
public class SecondHouseServiceImpl extends HouseService implements SecondHouseService {
    @Override
    public List queryCountList(String startDate, String endDate, String deviceId) {
        return null;
    }
}
