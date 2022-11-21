package com.example.demo.service;


import com.example.demo.essence.Question;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ExaminerServiceImpl implements ExaminerService {
private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestion(int amount){
        List<Question> result = new ArrayList<>();
        for (int i = 0; i <= amount; i++){
            Question question = questionService.getRandomQuestion();
            result.add(question);
        }
        return result;
    }
}
