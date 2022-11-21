package com.example.demo.service;

import com.example.demo.essence.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestion(int amount);
}
