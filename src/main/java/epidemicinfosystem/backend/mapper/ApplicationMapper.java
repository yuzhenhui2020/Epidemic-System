package epidemicinfosystem.backend.mapper;

import epidemicinfosystem.backend.bean.Application;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Mapper
@Repository
public interface ApplicationMapper {

    @Insert("insert into application values(#{userName},#{date},0)")
    void submitApplication(String userName, Date date);

    @Select("select * from application where date=#{date} and state=0")
    List<Application> getApplications(Date date);

    @Update("update application set state=1 where userName=#{userName} and date=#{date}")
    void approveApplication(Date date,String userName);
}
