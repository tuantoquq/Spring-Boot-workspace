package com.company.student.controller;

import com.company.student.builder.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {
    protected static final Logger requestLogger = LogManager.getLogger("RequestLog");
    protected static final Logger eLogger = LogManager.getLogger("ErrorLog");

    protected Gson gson;

    public BaseController(){
        GsonBuilder gsonBuilder =new GsonBuilder();
        gson = gsonBuilder
                .excludeFieldsWithoutExposeAnnotation()
                .serializeNulls()
                .create();
    }

    protected String getRequestParams(HttpServletRequest request){
        StringBuilder sb = new StringBuilder();
        request.getParameterMap().keySet().stream().sorted().forEach(
                s ->{
                    String p = request.getParameter(s);
                    if(sb.length() > 0) sb.append("&");
                    sb.append(s).append("=").append(p);
                }
        );
        return sb.toString();
    }

    protected String buildFailureResponse(int code, String message){
        Response failureResponse = new Response.Builder(0,code)
                .buildMessage(message)
                .build();
        return gson.toJson(failureResponse);
    }
}
