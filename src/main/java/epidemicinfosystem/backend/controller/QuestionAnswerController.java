package epidemicinfosystem.backend.controller;


import epidemicinfosystem.backend.service.QuestionAnswerService;
import epidemicinfosystem.backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/questionAnswer")
public class QuestionAnswerController {
    @Autowired
    private QuestionAnswerService questionAnswerService;

    @RequestMapping("/askQuestion")
    public Result askQuestion(String content, String description, HttpServletRequest request)
    {
        return questionAnswerService.askQuestion(content,description,request);
    }

    @RequestMapping("/getQuestions")
    public Result getQuestions()
    {
        return questionAnswerService.getQuestions();
    }

}
