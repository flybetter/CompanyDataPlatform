package cc.mrbird.web.controller;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.common.util.FebsConstant;
import cc.mrbird.common.util.HttpUtils;
import cc.mrbird.web.domain.*;
import cc.mrbird.web.service.NewHouseService;
import cc.mrbird.web.service.SecondHouseService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.assertj.core.api.UrlAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLEncoder;
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


    @Log("楼盘查询页面")
    @RequestMapping("customItemNamePage")
    @RequiresPermissions("data:customItemNamePage")
    public String customItemNamePage() {
        return "web/data/customItemNamePage";
    }


    @Log("手机号查询页面")
    @RequestMapping("customMobilePage")
    @RequiresPermissions("data:customMobilePage")
    public String customMobilePage() {
        return "web/data/customMobilePage";
    }

    @Log("楼盘查询")
    @RequestMapping("customItemName")
    @ResponseBody
    public Map<String, Object> customItemName(QueryRequest queryRequest, String startDate, String endDate, String city, String itemName) {
        try {
            Map<String, Object> rspData = new HashMap<>();
            itemName = URLEncoder.encode(itemName, "UTF-8");
            city = URLEncoder.encode(city, "UTF-8");
            String data = HttpUtils.sendGet(FebsConstant.CUSTOM_ITEMNAME_URL + itemName, "from=" + startDate + "&to=" + endDate + "&city_name=" + city + "&limit=" + queryRequest.getPageSize() + "&offset=" + ((queryRequest.getPageNum() - 1) * queryRequest.getPageSize()));
            List<CustomItemName> customItemNames = JSONObject.parseArray(data, CustomItemName.class);
            String data2 = HttpUtils.sendGet(FebsConstant.CUSTOM_ITEMNAME_URL + itemName + "/count", "from=" + startDate + "&to=" + endDate + "&city_name=" + city);
            JSONArray array = JSONObject.parseArray(data2);
            rspData.put("rows", customItemNames);
            rspData.put("total", ((JSONObject) array.get(0)).get("counting"));
            return rspData;
        } catch (Exception e) {
            logger.error("查询失败!", e);
            return ResponseBo.error("查询失败!");
        }
    }


    @Log("手机号查询")
    @RequestMapping("customMobile")
    @ResponseBody
    public Map<String, Object> customMobile(QueryRequest queryRequest, String startDate, String endDate, String phoneNumber, String city) {
        try {
            Map<String, Object> rspData = new HashMap<>();
            city = URLEncoder.encode(city, "UTF-8");
            String data = HttpUtils.sendGet(FebsConstant.CUSTOM_MOBILE_URL + phoneNumber, "from=" + startDate + "&to=" + endDate + "&city_name=" + city);
            List<CustomMobile> customMobiles = JSONObject.parseArray(data, CustomMobile.class);
            PageHelper.startPage(queryRequest.getPageNum(), queryRequest.getPageSize());
            PageInfo pageInfo = new PageInfo<>(customMobiles);
            rspData.put("rows", pageInfo.getList());
            rspData.put("total", pageInfo.getSize());
            return rspData;
        } catch (Exception e) {
            logger.error("查询失败!", e);
            return ResponseBo.error("查询失败!");
        }
    }


    @Log("楼盘点击次数查询页面")
    @RequestMapping("buildingCountPage")
    @RequiresPermissions("data:buildingCountPage")
    public String buildingCountPage() {
        return "web/data/buildingCountPage";
    }


    @Log("楼盘点击次数查询")
    @RequestMapping("buildingCount")
    @ResponseBody
    public Map<String, Object> buildingCount(QueryRequest queryRequest, String startDate, String endDate, String itemName) {
        try {
            Map<String, Object> rspData = new HashMap<>();
            List<CountDate> buildingCountList = newHouseService.queryCountAndDataDateByStartDateAndEndDateAndItemName(startDate, endDate, itemName);
            PageHelper.startPage(queryRequest.getPageNum(), queryRequest.getPageSize());
            PageInfo pageInfo = new PageInfo<>(buildingCountList);
            rspData.put("rows", pageInfo.getList());
            rspData.put("total", pageInfo.getSize());
            return rspData;
        } catch (Exception e) {
            logger.error("查询失败!", e);
            return ResponseBo.error("查询失败!");
        }
    }


}
