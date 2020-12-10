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

    @Insert("insert into dailyCondition values(#{userName},#{date},#{position},#{state})")
    void setCondition(String userName, Date date,int position,String state);

    @Select("select * from dailyCondition where userName=#{userName}")
    List<DailyCondition> getCondition(String userName);
}
