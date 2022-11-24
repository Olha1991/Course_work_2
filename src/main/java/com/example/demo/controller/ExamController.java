package com.example.demo.controller;


import com.example.demo.essence.Question;
import com.example.demo.service.ExaminerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class ExamController {
    private final ExaminerService examinerService;

    @GetMapping("/get/{amount}")
    public Collection<Question> getQuestions(@RequestParam int amount){

        return examinerService.getQuestions(amount);
    }


}
