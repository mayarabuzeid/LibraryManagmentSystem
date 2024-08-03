package net.javaguides.lms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourseNotAvailable extends RuntimeException{
    public ResourseNotAvailable(String message){
        super(message);
    }
}
