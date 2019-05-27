package cc.mrbird.web.service.impl;

import cc.mrbird.web.service.HouseService;
import cc.mrbird.web.service.NewHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("NewHouseService")
public class NewHouseServiceImpl extends HouseService implements NewHouseService {

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public List queryCountList(String startDate, String endDate, String deviceId) {
//        jdbcTemplate.queryForObject("",CountV);
        return null;
    }
}
