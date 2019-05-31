package cc.mrbird.web.domain;

import cc.mrbird.common.util.DateUtil;

import java.util.Date;

public class CountDate {

    private Integer count;
    private String data_date;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getData_date() {
        return data_date;
    }

    public void setData_date(String data_date) {
        this.data_date = data_date;
    }

    public long getUTC() {
        Date date = DateUtil.getDateParse(this.getData_date(), "yyyy-MM-dd");
        return date.getTime();
    }
}
