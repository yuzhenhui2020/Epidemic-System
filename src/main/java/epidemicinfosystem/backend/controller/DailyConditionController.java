package epidemicinfosystem.backend.controller;

import epidemicinfosystem.backend.bean.Position;
import epidemicinfosystem.backend.bean.User;
import epidemicinfosystem.backend.service.DailyConditionService;
import epidemicinfosystem.backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/condition")
public class DailyConditionController {
    @Autowired
    private DailyConditionService dailyConditionService;

    @RequestMapping("/setCondition")
    public Result setCondition(String state, Position position, HttpServletRequest request)
    {
        return dailyConditionService.setCondition(state,position,request);
    }
    @RequestMapping("/getCondition")
    public Result getCondition(String userName,HttpServletRequest request)
    {
        return dailyConditionService.getCondition(userName,request);
    }
}
