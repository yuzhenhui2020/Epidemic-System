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
            User existUser = userMapper.findUserByName(user.getUsr_name());
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
            Integer userId= userMapper.login(user);
            if(userId == null){
                result.setMsg("用户名或密码错误");
            }else{


                token= JWTUtils.getToke(user.getUsr_name());

                result.setMsg("登录成功");
                result.setSuccess(true);
                user.setUsr_id(userId);
                result.setDetail(token);
                return result;
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
     *
     * @param request
     * @return
     */
    public Result getSecureQuestion(HttpServletRequest request)
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
}


