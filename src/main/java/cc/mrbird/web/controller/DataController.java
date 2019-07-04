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
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.assertj.core.api.UrlAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

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


    @Log("设备号查询页面")
    @RequestMapping("devicesPage")
    @RequiresPermissions("data:devicesPage")
    public String devicesPage() {
        return "web/data/devicesPage";
    }


    @Log("设备号查询页面")
    @RequestMapping("devices")
    @ResponseBody
    public Map<String, Object> devices(QueryRequest queryRequest, String city_name, Integer shi, Integer ting, Integer wei, String item, Integer flat,
                                       String total, String price, String area) {
        try {
            Map<String, Object> rspData = new HashMap<>();

            String totalUrl = DataController.url_split(total, "total[]=");
            String priceUrl = DataController.url_split(price, "price[]=");
            String areaUrl = DataController.url_split(area, "area[]=");

            item = URLEncoder.encode(item, "UTF-8");
            city_name = URLEncoder.encode(city_name, "UTF-8");
            StringBuilder url = new StringBuilder();

            if (totalUrl!=null){
                url.append(totalUrl + "&");
            }

            if (priceUrl!=null){
                url.append(priceUrl + "&");
            }

            if (areaUrl!=null){
                url.append(areaUrl + "&");
            }

            if (shi!=null){
                url.append("shi=" + shi + "&");
            }

            if (ting!=null){
                url.append("ting=" + ting + "&");
            }

            if (city_name!=null){
                url.append("city_name=" + city_name + "&");
            }

            if (wei!=null){
                url.append("wei=" + wei + "&");
            }

            if (item!=null){
                url.append("item=" + item + "&");
            }

            if (flat!=null){
                url.append("flat=" + flat + "&");
            }

            String data2 = HttpUtils.sendGet(FebsConstant.CUSTOM_DEVICES_COUNT_URL, null);
            JSONArray array = JSONObject.parseArray(data2);
            url.append("limit=" + queryRequest.getPageSize() + "&offset=" + ((queryRequest.getPageNum() - 1) * queryRequest.getPageSize()));
            String data = HttpUtils.sendGet(FebsConstant.CUSTOM_DEVICES_URL, url.toString());
            List<Device> devices = JSONObject.parseArray(data, Device.class);
            rspData.put("rows", devices);
            rspData.put("total", ((JSONObject) array.get(0)).get("counting"));
            return rspData;
        } catch (Exception e) {
            logger.error("查询失败!", e);
            return ResponseBo.error("查询失败!");
        }

    }


    public static String url_split(String url, String pattern) {
        if (url.isEmpty()) {
            return null;
        }
        String[] urls = url.split("~");
        for (int i = 0; i < urls.length; i++) {
            urls[i] = new String(pattern + urls[i]);
        }

        return StringUtils.join(urls, "&");
    }

    public static void main(String[] args) {

        String demo = "200~300";

        String result = DataController.url_split(demo, "total[]=");
        System.out.println(result);

    }


}
