package ru.testfield.algorithm.controller.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ru.testfield.web.exception.EntityNotFoundException;

/**
 * Created by J.Bgood on 6/3/18.
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = EntityNotFoundException.class)
    protected String notFoundHandler(EntityNotFoundException exception, WebRequest request, Model model) {
        model.addAttribute("message",exception.getMessage());
        exception.printStackTrace();
        return "error";
    }

    @ExceptionHandler(value = Throwable.class)
    protected String defaultHandler(Throwable exception, WebRequest request, Model model) {
        model.addAttribute("message",exception.getMessage());
        exception.printStackTrace();
        return "error";
    }

}