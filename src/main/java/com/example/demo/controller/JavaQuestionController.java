package com.example.demo.controller;

import com.example.demo.essence.Question;
import com.example.demo.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/java")
@RequiredArgsConstructor
public class JavaQuestionController {

    private final QuestionService questionService;

    @GetMapping
    public String getQuestion(){

        return questionService.getAll().toString();
    }

@GetMapping("/add")
    public Question addQuestion(@RequestParam (value = "question") String question,
                                @RequestParam (value = "answer")String answer){
    return questionService.add(question,answer);
}

    @GetMapping("/remove")
    public Question removeQuestion(@RequestParam (value = "question") String question,
                                   @RequestParam (value = "answer")String answer){
    return questionService.remove(new Question(question, answer));
    }

    @GetMapping("/find")
    public Question findQuestion(@RequestParam (value = "question") String question,
                                @RequestParam (value = "answer")String answer){
        return questionService.find(new Question(question, answer));
    }


}
