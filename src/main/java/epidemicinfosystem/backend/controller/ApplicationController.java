package epidemicinfosystem.backend.controller;

import epidemicinfosystem.backend.service.ApplicationService;
import epidemicinfosystem.backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/application")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @RequestMapping("submitApplication")
    public Result submitApplication(HttpServletRequest request)
    {
        return applicationService.submitApplication(request);
    }

}
