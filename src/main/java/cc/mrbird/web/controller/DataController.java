package cc.mrbird.web.controller;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.web.service.NewHouseService;
import cc.mrbird.web.service.SecondHouseService;
import com.sun.tools.corba.se.idl.StringGen;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class DataController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private NewHouseService newHouseService;

    @Autowired
    private SecondHouseService secondHouseService;

    @Log("获取所有数据")
    @RequestMapping("data")
    @RequiresPermissions("data:list")
    public String index() {
        return "web/data/data";
    }

    @RequestMapping("data/newHouseList")
    @ResponseBody
    public ResponseBo newHouseList(String startDate, String endDate, String deviceId) {
        try {
            Object object = newHouseService.queryCountList(startDate, endDate, deviceId);
            return ResponseBo.ok(object);
        } catch (Exception e) {
            logger.error("统计新房浏览量失败", e);
            return ResponseBo.error("统计新房浏览量失败");
        }
    }

    @RequestMapping("data/secondHouseList")
    @ResponseBody
    public ResponseBo secondHouseList(String startDate, String endDate, String deviceId) {

        try {
            Object object = secondHouseService.queryCountList(startDate, endDate, deviceId);
            return ResponseBo.ok(object);
        } catch (Exception e) {
            logger.error("统计二手房浏览量失败", e);
            return ResponseBo.error("统计二手房浏览量失败");
        }
    }


}
