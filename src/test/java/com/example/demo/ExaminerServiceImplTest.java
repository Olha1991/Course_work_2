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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService javaQuestionService;
    @InjectMocks
    private ExaminerServiceImpl examinerService;
    private final List<Question> javaQuestions = new ArrayList<>();


    @BeforeEach
    public void beforeEach(){
        javaQuestions.clear();
        javaQuestions.addAll(
                Stream.of(
                        new Question("Question - 1", "Answer - 1"),
                        new Question("Question - 2", "Answer - 2"),
                        new Question("Question - 3", "Answer - 3"),
                        new Question("Question - 4", "Answer - 4"),
                        new Question("Question - 5", "Answer - 5")
                ).collect(Collectors.toSet())
        );
        when(javaQuestionService.getAll()).thenReturn(javaQuestions);
    }

    @Test
    public void getQuestionsNegativeTest(){
        assertThatExceptionOfType(FewQuestionsException.class)
                .isThrownBy(() -> examinerService.getQuestions(-1));
    }

    @Test
    public void getQuestionsPositiveTest(){
        when(javaQuestionService.getRandomQuestion()).thenReturn(
                new Question("Question - 2", "Answer - 2"),
                new Question("Question - 1", "Answer - 1"),
                new Question("Question - 5", "Answer - 5"),
                new Question("Question - 4", "Answer - 4"),
                new Question("Question - 3", "Answer - 3")
        );
        assertThat(examinerService.getQuestions(1))
                .hasSize(5)
                .containsExactly(new Question("Question - 1", "Answer - 1"),
                        new Question("Question - 2", "Answer - 2"),
                        new Question("Question - 3", "Answer - 3"),
                        new Question("Question - 4", "Answer - 4"),
                        new Question("Question - 5", "Answer - 5")
                );
    }
}
