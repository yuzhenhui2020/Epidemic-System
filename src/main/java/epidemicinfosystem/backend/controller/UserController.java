package epidemicinfosystem.backend.controller;

import epidemicinfosystem.backend.bean.User;
import epidemicinfosystem.backend.service.UserService;
import epidemicinfosystem.backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/regist")
    public Result regist(User user)
    {
        return userService.regist(user);
    }

    @RequestMapping("/login")
    public Result login(User user)
    {
        return userService.login(user);
    }

    @RequestMapping("/getSecureQuestion")
    public Result getSecureQuestion(User user)
    {
        return userService.getSecureQuestion(user);
    }

    @RequestMapping("/changePassword")
    public Result changePassword(String userName,String secureAnswer,String password)
    {
        return userService.changePassword(userName,secureAnswer,password);
    }
    //@RequestMapping("/getSecureQuestion")
    //public Result getSecureQuestion(HttpServletRequest request){
    //    return userService.getSecureQuestion(request);
    //}

}
