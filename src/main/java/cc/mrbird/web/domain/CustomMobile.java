package cc.mrbird.web.domain;

public class CustomMobile {

    private String device_id;
    private String prj_itemname;
    private Long counting;

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getPrj_itemname() {
        return prj_itemname;
    }

    public void setPrj_itemname(String prj_itemname) {
        this.prj_itemname = prj_itemname;
    }

    public Long getCounting() {
        return counting;
    }

    public void setCounting(Long counting) {
        this.counting = counting;
    }
}
