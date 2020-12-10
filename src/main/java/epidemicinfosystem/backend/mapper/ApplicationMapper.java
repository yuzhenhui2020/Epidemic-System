package epidemicinfosystem.backend.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Mapper
@Repository
public interface ApplicationMapper {

    @Insert("insert into application values(#{userId},#{date},0)")
    void submitApplication(int userId, Date date);
}
