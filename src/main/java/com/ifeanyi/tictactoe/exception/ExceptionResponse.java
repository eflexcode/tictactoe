package com.ifeanyi.tictactoe.exception;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ExceptionResponse {

    private String message;
    private Integer code;
    private Date timestamp;

}
