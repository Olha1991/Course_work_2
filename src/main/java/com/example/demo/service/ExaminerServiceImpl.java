package com.example.demo.service;


import com.example.demo.essence.Question;
import com.example.demo.exception.FewQuestionsException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
private final QuestionService questionService;

    public ExaminerServiceImpl(JavaQuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount){
        Collection<Question> questions = questionService.getAll();
        if(amount > questions.size() || amount < 1){
            throw new FewQuestionsException();
        }
        Set<Question> result = new HashSet<>();
        while (result.size() < amount){
            result.add(questionService.getRandomQuestion());
        }
        return result;
    }
}
