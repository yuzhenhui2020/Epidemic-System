package epidemicinfosystem.backend.mapper;

import epidemicinfosystem.backend.bean.DailyCondition;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Mapper
@Repository
public interface DailyConditionMapper {

    @Insert("insert into dailycondition values(#{userId},#{date},#{position},#{state})")
    void setCondition(int userId, Date date,int position,String state);

    @Select("select * from dailycondition where usr_id=#{userId}")
    List<DailyCondition> getCondition(int userId);
}
