package epidemicinfosystem.backend.service;


import com.auth0.jwt.interfaces.DecodedJWT;
import epidemicinfosystem.backend.bean.Application;
import epidemicinfosystem.backend.bean.User;
import epidemicinfosystem.backend.mapper.ApplicationMapper;
import epidemicinfosystem.backend.mapper.UserMapper;
import epidemicinfosystem.backend.util.JWTUtils;
import epidemicinfosystem.backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class ApplicationService {

    @Autowired
    private ApplicationMapper applicationMapper;

    @Autowired
    private UserMapper userMapper;

    public Result submitApplication(HttpServletRequest request) {
        Result result = new Result();
        result.setSuccess(false);
        result.setMsg(null);
        result.setDetail(null);
        String token = request.getHeader("token");
        DecodedJWT verify = JWTUtils.decode(token);
        String userName = verify.getClaim("userName").asString();

        User user = userMapper.findUserByName(userName);
        if (user.getPermission() == 1) {
            result.setSuccess(false);
            result.setMsg("you already had permission");
        }
        else {
            try {
                java.util.Date datetime = new java.util.Date();
                Date date = new Date(datetime.getTime());
                applicationMapper.submitApplication(userName, date);
                result.setSuccess(true);
            } catch (Exception e) {
                result.setSuccess(false);
                result.setMsg(e.getMessage());
            }
        }
            return result;


    }
    public Result getApplications(HttpServletRequest request)
    {
        Result result=new Result();
        result.setSuccess(false);
        result.setMsg(null);
        result.setDetail(null);

        String token = request.getHeader("token");
        DecodedJWT verify = JWTUtils.decode(token);
        String userName = verify.getClaim("userName").asString();

        User user=userMapper.findUserByName(userName);
        if(user.getType()==0)
        {
            result.setMsg("you cant access to this");
        }
        else{
        try {
            java.util.Date datetime = new java.util.Date();
            Date date = new Date(datetime.getTime());
            List<Application> applications=applicationMapper.getApplications(date);
            result.setSuccess(true);
            result.setDetail(applications);
        }catch(Exception e)
        {
         result.setMsg(e.getMessage());
        }
        }
        return result;
    }
    public Result approveApplication(String userName,HttpServletRequest request)
    {

        Result result=new Result();
        result.setDetail(null);
        result.setSuccess(false);
        result.setMsg(null);

        String token = request.getHeader("token");
        DecodedJWT verify = JWTUtils.decode(token);
        String adminName = verify.getClaim("userName").asString();

        User admin=userMapper.findUserByName(adminName);
        if(admin.getType()==0)
        {
            result.setMsg("you cant access to this");
        }
        else{
            try {
                java.util.Date datetime = new java.util.Date();
                Date date = new Date(datetime.getTime());
                userMapper.getPermission(userName);
                applicationMapper.approveApplication(date,userName);
                result.setSuccess(true);

            }catch(Exception e)
            {
                result.setMsg(e.getMessage());
            }
        }
        return result;
    }
}
