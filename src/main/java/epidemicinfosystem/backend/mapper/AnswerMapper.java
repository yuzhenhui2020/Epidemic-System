package epidemicinfosystem.backend.mapper;

import epidemicinfosystem.backend.bean.Answer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AnswerMapper {
    @Insert("insert into answer values(#{content},0,0,#{userName},#{questionId})")
    void answer(String userName,String content,int questionId);

    @Update("update answer set likes=likes+1 where questionId=#{questionId} and userName=#{userName}")
    void like(int questionId,String userName);

    @Update("update answer set dislikes=dislikes+1 where questionId=#{questionId} and userName=#{userName}")
    void dislike(int questionId,String userName);

    @Select("select * from answer where questionId=#{questionId}")
    List<Answer> getAnswers(int questionId);
}
