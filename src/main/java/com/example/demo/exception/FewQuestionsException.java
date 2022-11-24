package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class FewQuestionsException extends RuntimeException{
    public FewQuestionsException() {
    }

    public FewQuestionsException(String message) {
        super(message);
    }

    public FewQuestionsException(String message, Throwable cause) {
        super(message, cause);
    }

    public FewQuestionsException(Throwable cause) {
        super(cause);
    }

    public FewQuestionsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
