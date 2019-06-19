package cc.mrbird.web.dao;

import org.apache.ibatis.annotations.Select;

public interface NewHouseMapper {

    @Select("select count(*) from newhouselog ")
    Long count();

}
