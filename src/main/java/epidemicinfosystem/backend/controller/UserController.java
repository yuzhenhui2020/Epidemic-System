package epidemicinfosystem.backend.controller;

import epidemicinfosystem.backend.bean.User;
import epidemicinfosystem.backend.service.UserService;
import epidemicinfosystem.backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
