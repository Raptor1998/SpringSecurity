package com.raptor.springboot_security_jsp.advice;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author raptor
 * @version V1.0
 * @Package com.raptor.springboot_security_jsp.advice
 * @date 2020/11/4 19:50
 */
@ControllerAdvice
public class HandleControllerException {

    @ExceptionHandler(RuntimeException.class)
    public String handleException(RuntimeException e){
        if(e instanceof AccessDeniedException){
            return "redirect:/403.jsp";
        }else {
            return "redirect:/500.jsp";
        }

    }
}
