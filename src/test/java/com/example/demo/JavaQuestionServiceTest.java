package com.example.demo;

import com.example.demo.essence.Question;
import com.example.demo.exception.QuestionAlreadyExistException;
import com.example.demo.exception.QuestionNotFoundException;
import com.example.demo.service.JavaQuestionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {
    private final JavaQuestionService javaQuestionService = new JavaQuestionService();

    @AfterEach
    public void AfterEach() {
        javaQuestionService.getAll().forEach(question -> javaQuestionService.remove(question));
    }

    @Test
    public void addTest(){
     javaQuestionService.add(new Question("q", "a"));
        assertThatExceptionOfType(QuestionAlreadyExistException.class)
                .isThrownBy(() -> javaQuestionService.add(new Question("q", "a")));
        assertThat(javaQuestionService.getAll()).containsExactlyInAnyOrder(new Question("q", "a"));
    }

    @Test
    public void add2Test(){
        String question = "q";
        String answer = "a";
        Question q = new Question(question, answer);
        javaQuestionService.add(question, answer);
        assertThatExceptionOfType(QuestionAlreadyExistException.class)
                .isThrownBy(() -> javaQuestionService.add(question, answer));
        assertThat(javaQuestionService.getAll()).containsExactlyInAnyOrder(q);
    }

    @Test
    public void removeTest(){
        javaQuestionService.add(new Question("q", "a"));
        javaQuestionService.remove(new Question("q", "a"));
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> javaQuestionService.remove(new Question("q", "a")));
    }

    @Test
    public void findTest(){
        javaQuestionService.find(new Question("q", "a"));
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> javaQuestionService.find(new Question("q", "a")));

    }

    @ParameterizedTest
    @MethodSource("questions")
    public void getRandomQuestionTest(Set<Question> questions){
        questions.forEach(javaQuestionService::add);
        assertThat(javaQuestionService.getRandomQuestion()).isIn(javaQuestionService.getAll());
    }

    public static Stream<Arguments> questions(){
        return Stream.of(
                Arguments.of(
                        Set.of(
                                new Question("Question1","Answer1"),
                                new Question("Question2","Answer2"),
                                new Question("Question3","Answer3"),
                                new Question("Question4","Answer4"),
                                new Question("Question5","Answer5")
                        )
                )
        );
    }
}
