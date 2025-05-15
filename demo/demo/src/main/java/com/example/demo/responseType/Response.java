package com.example.demo.responseType;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Response<T> {
    private String message;
    private T data;
    private HttpStatus statusCode;


}
