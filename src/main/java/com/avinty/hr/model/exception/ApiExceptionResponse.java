package com.avinty.hr.model.exception;

import com.avinty.hr.model.exception.SubError;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
public class ApiExceptionResponse {
    private final String errorMessage;
    private final int errorCode;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timeStamp;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final List<SubError> subErrorList;
}
