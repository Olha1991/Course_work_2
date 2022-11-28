package com.example.demo;

import com.example.demo.essence.Question;
import com.example.demo.exception.FewQuestionsException;
import com.example.demo.service.ExaminerServiceImpl;
import com.example.demo.service.JavaQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;
import org.mockito.Mockito;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService javaQuestionService;
    @InjectMocks
    private ExaminerServiceImpl examinerService;


    @BeforeEach
    public void beforeEach(){
        List<Question> javaQuestions = List.of(
                        new Question("Question - 1", "Answer - 1"),
                        new Question("Question - 2", "Answer - 2"),
                        new Question("Question - 3", "Answer - 3"),
                        new Question("Question - 4", "Answer - 4"),
                        new Question("Question - 5", "Answer - 5")
        );
        when(javaQuestionService.getAll()).thenReturn(javaQuestions);
        examinerService = new ExaminerServiceImpl(javaQuestionService);
    }

    @Test
    public void getQuestionsNegativeTest(){
        assertThatExceptionOfType(FewQuestionsException.class)
                .isThrownBy(() -> examinerService.getQuestions(
                        javaQuestionService.getAll().size()+1));
    }

    @Test
    public void getQuestionsPositiveTest(){
        Mockito.when(javaQuestionService.getRandomQuestion()).thenReturn(
                new Question("Question - 2", "Answer - 2"),
                new Question("Question - 1", "Answer - 1"),
                new Question("Question - 5", "Answer - 5"),
                new Question("Question - 4", "Answer - 4"),
                new Question("Question - 3", "Answer - 3")
        );
        assertThat(examinerService.getQuestions(5))
                .hasSize(5)
                .containsExactlyInAnyOrder(
                        new Question("Question - 1", "Answer - 1"),
                        new Question("Question - 2", "Answer - 2"),
                        new Question("Question - 3", "Answer - 3"),
                        new Question("Question - 4", "Answer - 4"),
                        new Question("Question - 5", "Answer - 5")
                );
    }
}
