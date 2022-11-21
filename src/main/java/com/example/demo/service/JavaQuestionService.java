package com.example.demo.service;

import com.example.demo.essence.Question;
import com.example.demo.exception.QuestionAlreadyExistException;
import com.example.demo.exception.QuestionNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final Random random;
    private final List<Question> questions;

    public JavaQuestionService() {
        this.random = new Random();
        this.questions = new ArrayList<>();
    }
    @Override
    public Question add(String question, String answer){
        return add(new Question(question,answer));
    }

    @Override
    public Question add(Question question){
        if(questions.contains(question)){
            throw new QuestionAlreadyExistException();
        }
        return question;
    }

    @Override
    public Question remove(Question question){
        if(!questions.contains(question)){
            throw new QuestionNotFoundException();
        }
        return question;
    }

    @Override
    public Question find(Question question){
        if(!questions.contains(question)) {
            throw new QuestionNotFoundException();
        }
        return question;
    }

    @Override
    public Collection<Question> getAll(){
        return new ArrayList<>(questions);
    }

    @Override
    public Question getRandomQuestion(){
        return questions.get(random.nextInt(questions.size()));
    }
}
