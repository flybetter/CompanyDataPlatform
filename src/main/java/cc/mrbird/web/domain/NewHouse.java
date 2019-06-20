package cc.mrbird.web.domain;

import org.apache.ibatis.type.Alias;

@Alias("newhouse")
public class NewHouse {
    private String device_id;
    private String city_name;
    private String prj_itemname;
    private String price_show;
    private String pic_hx_totalprice;
    private String pic_area;

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getPrj_itemname() {
        return prj_itemname;
    }

    public void setPrj_itemname(String prj_itemname) {
        this.prj_itemname = prj_itemname;
    }

    public String getPrice_show() {
        return price_show;
    }

    public void setPrice_show(String price_show) {
        this.price_show = price_show;
    }

    public String getPic_hx_totalprice() {
        return pic_hx_totalprice;
    }

    public void setPic_hx_totalprice(String pic_hx_totalprice) {
        this.pic_hx_totalprice = pic_hx_totalprice;
    }

    public String getPic_area() {
        return pic_area;
    }

    public void setPic_area(String pic_area) {
        this.pic_area = pic_area;
    }
}
