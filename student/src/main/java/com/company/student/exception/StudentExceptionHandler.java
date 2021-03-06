package com.company.student.exception;

import com.company.student.builder.Response;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class StudentExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    @Qualifier("gsonCustom")
    private Gson gson;


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                HttpStatus status, WebRequest request){
        Response response = new Response.Builder(0,HttpStatus.INTERNAL_SERVER_ERROR.value())
                .buildMessage("Method argument not valid")
                .build();
        return buildResponse(response);
    }


    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers,
                                                                        HttpStatus status, WebRequest request){
        Response response = new Response.Builder(0,HttpStatus.INTERNAL_SERVER_ERROR.value())
                .buildMessage("Missing servlet request parameter")
                .build();
        return buildResponse(response);
    }


    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request){
        Response response = new Response.Builder(0,HttpStatus.INTERNAL_SERVER_ERROR.value())
                .buildMessage("HttpRequest method not supported")
                .build();
        return buildResponse(response);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        Response response = new Response.Builder(0, HttpStatus.INTERNAL_SERVER_ERROR.value())
                .buildMessage("Http message not readable")
                .build();
        return buildResponse(response);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers,
                                                                     HttpStatus status, WebRequest request) {
        Response response = new Response.Builder(0, HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .buildMessage("Unsupported media type")
                .build();
        return buildResponse(response);
    }


    protected ResponseEntity<Object> buildResponse(Response response){
        return new ResponseEntity<>(gson.toJson(response), HttpStatus.BAD_REQUEST);
    }
}
