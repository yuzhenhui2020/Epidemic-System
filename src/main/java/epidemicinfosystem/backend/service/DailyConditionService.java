package epidemicinfosystem.backend.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import epidemicinfosystem.backend.bean.Position;
import epidemicinfosystem.backend.bean.User;
import epidemicinfosystem.backend.mapper.DailyConditionMapper;
import epidemicinfosystem.backend.mapper.PositionMapper;
import epidemicinfosystem.backend.mapper.UserMapper;
import epidemicinfosystem.backend.util.JWTUtils;
import epidemicinfosystem.backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class DailyConditionService {
    @Autowired
    private DailyConditionMapper dailyConditionMapper;
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private UserMapper userMapper;

    public Result setCondition(String state, Position position, HttpServletRequest request){
        Result result=new Result();
        result.setSuccess(false);
        result.setMsg(null);
        result.setDetail(null);
        try {
            Integer positionI = positionMapper.findPosition(position);

            if (positionI == null) {
                positionMapper.addPosition(position);
                positionI = positionMapper.findPosition(position);
            }
            java.util.Date datetime = new java.util.Date();
            Date date = new Date(datetime.getTime());
            String token = request.getHeader("token");
            DecodedJWT verify = JWTUtils.decode(token);
            String userName = verify.getClaim("userName").asString();

            User user = userMapper.findUserByName(userName);
            dailyConditionMapper.setCondition(user.getUsr_id(), date, positionI, state);
            result.setSuccess(true);
            result.setMsg("健康上报成功");
        }catch(Exception e)
        {
            result.setSuccess(false);
            result.setMsg("您已经进行过健康上报");
        }

        return result;
    }
}
