package epidemicinfosystem.backend.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Mapper
@Repository
public interface DailyConditionMapper {

    @Insert("insert into dailycondition values(#{userId},#{date},#{position},#{state})")
    void setCondition(int userId, Date date,int position,String state);
}
