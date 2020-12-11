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

    @RequestMapping("/answer")
    public Result answer(int questionId,String content,HttpServletRequest request)
    {
        return questionAnswerService.answer(questionId,content,request);
    }

    @RequestMapping("/like")
    public Result like(int questionId,String userName)
    {
        return questionAnswerService.like(questionId,userName);
    }
    @RequestMapping("/dislike")
    public Result dislike(int questionId ,String userName)
    {
        return questionAnswerService.dislike(questionId,userName);
    }

    @RequestMapping("/getAnswers")
    public Result getAnswers(int questionId)
    {
        return questionAnswerService.getAnswers(questionId);
    }
}
