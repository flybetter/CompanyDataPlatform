package cc.mrbird.job.task;

import cc.mrbird.common.annotation.CronTag;
import cc.mrbird.web.dao.NewHouseMapper;
import org.springframework.beans.factory.annotation.Autowired;

@CronTag("bigData")
public class BigDataTask {

    @Autowired
    private NewHouseMapper newHouseMapper;

    public void saveProjectItemNameCount(){

    }

}
