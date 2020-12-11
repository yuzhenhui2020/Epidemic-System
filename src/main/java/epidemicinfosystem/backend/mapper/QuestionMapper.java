package epidemicinfosystem.backend.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface QuestionMapper {

    @Insert("insert into question values(null,#{userName},#{content},#{description})")
    void askQuestion(String userName,String content,String description);
}
