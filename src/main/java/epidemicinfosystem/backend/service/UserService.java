package epidemicinfosystem.backend.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import epidemicinfosystem.backend.bean.User;
import epidemicinfosystem.backend.mapper.UserMapper;
import epidemicinfosystem.backend.util.JWTUtils;
import epidemicinfosystem.backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;


@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Result regist(User user) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            User existUser = userMapper.findUserByName(user.getUserName());
            if(existUser != null){
                //如果用户名已存在
                result.setMsg("用户名已存在");

            }else{
                userMapper.regist(user);
                //System.out.println(user.getId());
                result.setMsg("注册成功");
                result.setSuccess(true);
                result.setDetail(user);
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /***
     * 进行登陆
     * @param user 输入的用户信息
     * @return 是否成功
     */
    public Result login(User user) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        String token;
        try {
            User userT= userMapper.login(user);
            if(userT == null){
                result.setMsg("用户名或密码错误");
            }else{


                token= JWTUtils.getToke(user.getUserName());

                result.setMsg("登录成功");
                result.setSuccess(true);
                result.setDetail(token);
                return result;
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Result getSecureQuestion(User user)
    {
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try{
            User usr=userMapper.findUserByName(user.getUserName());
            if(usr!=null) {
                String secureQuestion;
                secureQuestion = userMapper.getSecureQuestion(user);
                result.setDetail(secureQuestion);
                result.setSuccess(true);
            }
            else
            {
                result.setSuccess(false);
                result.setMsg("不存在该用户");
            }
        }catch(Exception e)
        {
            result.setMsg(e.getMessage());
        }
        return result;
    }

    public Result changePassword(String userName,String secureAnswerI,String password)
    {
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);

            String secureAnswer;
            secureAnswer=userMapper.getSecureAnswer(userName);
            if(secureAnswerI.equals(secureAnswer)){
                userMapper.changePassword(userName,password);
                result.setMsg("成功修改密码");
                result.setSuccess(true);
            }
            else
            {
                result.setMsg("安全答案错误");
            }
            return result;

    }

    /**
     *
     * @param request
     * @return
     */
    /*public Result getSecureQuestion(HttpServletRequest request)
    {

        Result result=new Result();
        String token=request.getHeader("token");

        result.setDetail(token);
        DecodedJWT verify=JWTUtils.decode(token);

        String userName=verify.getClaim("userName").asString();

        //result.setDetail(userName);
        result.setDetail(userMapper.getSecureQuestion(userName));
        return result;
    }
    */

}


