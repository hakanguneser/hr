package com.avinty.hr.model.exception;

import lombok.Data;

@Data
public class SubError {
    private final String errorMessage;
    private final int errorCode;
}
