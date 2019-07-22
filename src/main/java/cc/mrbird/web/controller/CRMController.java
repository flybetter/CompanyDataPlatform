package cc.mrbird.web.controller;

import cc.mrbird.web.domain.CRMSecondHouseStatistics;
import cc.mrbird.web.service.ImpalaSqlService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("CRM")
public class CRMController {

    @Autowired
    public ImpalaSqlService impalaSqlService;

    @GetMapping("secondhousestatistic")
    public List<CRMSecondHouseStatistics> secondhousestatistic(@RequestParam String startDate, @RequestParam String endDate) {
        List<CRMSecondHouseStatistics> lists = impalaSqlService.customSecondHouseProfile(startDate, endDate);
        return lists;
    }
}
