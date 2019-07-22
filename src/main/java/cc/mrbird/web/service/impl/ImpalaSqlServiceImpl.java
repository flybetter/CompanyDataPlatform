package cc.mrbird.web.service.impl;

import cc.mrbird.web.domain.CRMSecondHouseStatistics;
import cc.mrbird.web.service.ImpalaSqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("ImpalaSqlService")
public class ImpalaSqlServiceImpl implements ImpalaSqlService {

    @Autowired
    public JdbcTemplate jdbcTemplate;

//    public static String sql = "select aa.login_account as mobile, aa.city as city_name, aa.count as vist_num, aa.total_price,aa.avg_price,aa.build_area,bb.district  from (select count(*) as count,city,login_account,round(avg(price),2) as total_price, round( avg(averprice_x),2) as avg_price ,round(avg(buildarea),2) as build_area from user_track.secondhouselog where data_date between '2019-07-08' and '2019-07-12' and length(login_account) >0 group by login_account,city) aa,(select aa.login_account,aa.district_x as district ,aa.rn from (select cc.login_account, cc.district_x, row_number() over(partition by cc.login_account order by cc.count desc ) rn from(SELECT login_account,district_x ,count(*) as count,round(avg(price),2), round( avg(averprice_x),2), round(avg(buildarea),2)  from user_track.secondhouselog where data_date between '2019-07-08' and '2019-07-12' and length(login_account)>0 GROUP BY login_account,district_x  ORDER BY count desc) cc) aa where aa.rn<=1) bb where aa.login_account=bb.login_account order by count desc";

    @Override
    public List<CRMSecondHouseStatistics> customSecondHouseProfile(String startDate, String endDate) {

        String sql = "select aa.login_account as mobile, aa.city as city_name, aa.count as visit_num, aa.total_price,aa.avg_price,aa.build_area,bb.district  from (select count(*) as count,city,login_account,round(avg(price),2) as total_price, round( avg(averprice_x),2) as avg_price ,round(avg(buildarea),2) as build_area from user_track.secondhouselog where data_date between '" + startDate + "'and '" + endDate + "' and length(login_account) >0 group by login_account,city) aa,(select aa.login_account,aa.district_x as district ,aa.rn from (select cc.login_account, cc.district_x, row_number() over(partition by cc.login_account order by cc.count desc ) rn from(SELECT login_account,district_x ,count(*) as count,round(avg(price),2), round( avg(averprice_x),2), round(avg(buildarea),2)  from user_track.secondhouselog where data_date between '" + startDate + "' and '" + endDate + "' and length(login_account)>0 GROUP BY login_account,district_x  ORDER BY count desc) cc) aa where aa.rn<=1) bb where aa.login_account=bb.login_account order by count desc limit 300";
        List<CRMSecondHouseStatistics> lists = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CRMSecondHouseStatistics.class));
        return lists;
    }
}
