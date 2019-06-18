package cc.mrbird.web.controller;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.web.domain.NewHouseDetail;
import cc.mrbird.web.domain.SecondHouseDetail;
import cc.mrbird.web.service.NewHouseService;
import cc.mrbird.web.service.SecondHouseService;
import org.apache.ibatis.session.SqlSession;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("data")
public class DataController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private NewHouseService newHouseService;

    @Autowired
    private SecondHouseService secondHouseService;

    @Log("获取所有数据")
    @RequestMapping("")
    @RequiresPermissions("data:list")
    public String index() {
        return "web/data/data";
    }

    @RequestMapping("newHouseList")
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

    @RequestMapping("secondHouseList")
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


    @Log("获取新房所有数据页面")
    @RequestMapping("newHouseDetailPage")
    @RequiresPermissions("data:newHouseDetailPage")
    public String newHouseDetailPage() {
        return "web/data/newHouseDetailPage";
    }


    @Log("获取二手房所有数据页面")
    @RequestMapping("secondHouseDetailPage")
    @RequiresPermissions("data:secondHouseDetailPage")
    public String secondHouseDetailPage() {
        return "web/data/secondHouseDetailPage";
    }

    @Log("获取新房数据")
    @RequestMapping("newHouseDetail")
    @ResponseBody
    public Map<String, Object> newHouseDetail(QueryRequest queryRequest, String startDate, String endDate, String deviceId) {
        List<NewHouseDetail> newHouseDetails = newHouseService.queryNewHouseDetail(startDate, endDate, deviceId, queryRequest);
        Long count = newHouseService.queryNewHouseCountDetail(startDate, endDate, deviceId);
        Map<String, Object> rspData = new HashMap<>();
        rspData.put("rows", newHouseDetails);
        rspData.put("total", count);
        return rspData;
    }


    @Log("获取二手房数据")
    @RequestMapping("secondHouseDetail")
    @ResponseBody
    public Map<String, Object> secondHouseDetail(QueryRequest queryRequest, String startDate, String endDate, String deviceId) {
        List<SecondHouseDetail> secondHouseDetails = secondHouseService.querySecondHouseDetail(startDate, endDate, deviceId, queryRequest);
        Long count = secondHouseService.querySecondHouseCountDetail(startDate, endDate, deviceId);
        Map<String, Object> rspData = new HashMap<>();
        rspData.put("rows", secondHouseDetails);
        rspData.put("total", count);
        return rspData;
    }

}
