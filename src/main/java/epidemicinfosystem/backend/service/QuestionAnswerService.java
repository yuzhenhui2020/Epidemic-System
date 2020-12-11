package epidemicinfosystem.backend.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import epidemicinfosystem.backend.mapper.AnswerMapper;
import epidemicinfosystem.backend.mapper.QuestionMapper;
import epidemicinfosystem.backend.util.JWTUtils;
import epidemicinfosystem.backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

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
}

