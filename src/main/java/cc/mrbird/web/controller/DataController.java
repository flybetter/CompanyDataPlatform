package cc.mrbird.web.controller;

import cc.mrbird.common.annotation.Log;
import com.sun.tools.corba.se.idl.StringGen;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class DataController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Log("获取所有数据")
    @RequestMapping("data")
    @RequiresPermissions("data:list")
    public String index() {
        return "web/data/data";
    }

    @RequestMapping("data/newHouseList")
    @ResponseBody
    public String newHouseList(String startDate, String endDate, String deviceId) {

        return null;
    }


    @RequestMapping("data/secondHouseList")
    @ResponseBody
    public String secondHouseList(String startDate, String endDate, String deviceId) {

        return null;
    }





}
