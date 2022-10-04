package com.test.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Response {
    private String message;
    private LocalDateTime time=LocalDateTime.now();
    public Response(String message){
        this.message=message;
    }
}
