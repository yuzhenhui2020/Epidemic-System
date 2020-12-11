package epidemicinfosystem.backend.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import epidemicinfosystem.backend.bean.Answer;
import epidemicinfosystem.backend.bean.Question;
import epidemicinfosystem.backend.mapper.AnswerMapper;
import epidemicinfosystem.backend.mapper.QuestionMapper;
import epidemicinfosystem.backend.util.JWTUtils;
import epidemicinfosystem.backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class QuestionAnswerService {
    @Autowired
    private AnswerMapper answerMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public Result askQuestion(String content, String description, HttpServletRequest request)
    {
        Result result=new Result();
        result.setMsg(null);
        result.setSuccess(false);
        result.setDetail(null);

        String token = request.getHeader("token");
        DecodedJWT verify = JWTUtils.decode(token);
        String userName = verify.getClaim("userName").asString();

        try{
            questionMapper.askQuestion(userName,content,description);
            result.setSuccess(true);
        }
        catch(Exception e)
        {
            result.setMsg(e.getMessage());
        }
        return result;
    }
    public Result getQuestions()
    {
        Result result=new Result();
        result.setMsg(null);
        result.setSuccess(false);
        result.setDetail(null);

        try{
            List<Question> questions=questionMapper.getQuestions();
            result.setSuccess(true);
            result.setDetail(questions);
        }
        catch(Exception e)
        {
            result.setMsg(e.getMessage());
        }
        return result;
    }
    public Result answer(int questionId,String content,HttpServletRequest request)
    {
        Result result =new Result();
        result.setDetail(null);
        result.setSuccess(false);
        result.setMsg(null);

        String token = request.getHeader("token");
        DecodedJWT verify = JWTUtils.decode(token);
        String userName = verify.getClaim("userName").asString();
        try{
            answerMapper.answer(userName,content,questionId);
            result.setSuccess(true);
        }
        catch(Exception e)
        {
            result.setMsg(e.getMessage());
        }
        return result;
    }
    public Result like(int questionId,String userName)
    {
        Result result =new Result();
        result.setDetail(null);
        result.setSuccess(false);
        result.setMsg(null);

        try{
            answerMapper.like(questionId,userName);
            result.setSuccess(true);
        }
        catch(Exception e)
        {
            result.setMsg(e.getMessage());
        }
        return result;
    }
    public Result dislike(int questionId,String userName)
    {
        Result result =new Result();
        result.setDetail(null);
        result.setSuccess(false);
        result.setMsg(null);

        try{
            answerMapper.dislike(questionId,userName);
            result.setSuccess(true);
        }
        catch(Exception e)
        {
            result.setMsg(e.getMessage());
        }
        return result;
    }
    public Result getAnswers(int questionId)
    {
        Result result=new Result();
        result.setMsg(null);
        result.setSuccess(false);
        result.setDetail(null);

        try{
            List<Answer> answers=answerMapper.getAnswers(questionId);
            result.setSuccess(true);
            result.setDetail(answers);
        }
        catch(Exception e)
        {
            result.setMsg(e.getMessage());
        }
        return result;
    }
}

