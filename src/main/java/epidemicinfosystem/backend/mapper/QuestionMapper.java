package epidemicinfosystem.backend.mapper;

import epidemicinfosystem.backend.bean.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface QuestionMapper {

    @Insert("insert into question values(null,#{userName},#{content},#{description})")
    void askQuestion(String userName,String content,String description);

    @Select("select * from question")
    List<Question> getQuestions();
}
