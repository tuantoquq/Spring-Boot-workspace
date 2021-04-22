package com.company.student.controller;

import com.company.student.builder.Response;
import com.company.student.exception.StudentException;
import com.company.student.model.request.CreateForm;
import com.company.student.model.request.ModifyForm;
import com.company.student.service.StudentService;
import com.ecyrd.speed4j.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping(path = "/students")
public class StudentController extends BaseController{

    @Autowired
    private StudentService studentService;


    @GetMapping(path = "/lists",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getListStudent(HttpServletRequest request, HttpServletResponse response){
        StopWatch sw = new StopWatch();
        String requestUri = request.getRequestURI()+ "?" + getRequestParams(request);
        String svResponse;
        Response serverResponse;
        try {
            serverResponse = studentService.findAll();
            svResponse = gson.toJson(serverResponse);
            requestLogger.info("finish process request {} in {}", requestUri, sw.stop());
            return new ResponseEntity<>(svResponse,HttpStatus.OK);
        }catch (StudentException se){
            se.printStackTrace();
            eLogger.error(se.getMessage());
            svResponse = buildFailureResponse(se.getCode(),se.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            eLogger.error("find all student error: {}",e.getMessage());
            svResponse = buildFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "an error occurred");
        }
        return new ResponseEntity<>(svResponse,HttpStatus.OK);
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getStudentById(@PathVariable("id") int id,
                                                 HttpServletRequest request,
                                                 HttpServletResponse response){
        StopWatch sw = new StopWatch();
        String requestUri = request.getRequestURI() + "?" +getRequestParams(request);
        String svResponse;
        Response serverResponse;
        try {
            serverResponse = studentService.findById(id);
            svResponse = gson.toJson(serverResponse);
            requestLogger.info("finish process request {} in {}", requestUri, sw.stop());
            return new ResponseEntity<>(svResponse,HttpStatus.OK);
        }catch (StudentException se){
            se.printStackTrace();
            eLogger.error(se.getMessage());
            svResponse = buildFailureResponse(se.getCode(),se.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            eLogger.error("find student by id error: {}",e.getMessage());
            svResponse = buildFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "an error occurred");
        }
        return new ResponseEntity<>(svResponse,HttpStatus.OK);
    }

    @PostMapping(path = "/add",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> addStudent(@RequestBody @Valid CreateForm createForm,
                                             HttpServletRequest request,
                                             HttpServletResponse response){
        StopWatch sw = new StopWatch();
        String requestUri = request.getRequestURI()+ "?" + getRequestParams(request);
        String svResponse;
        Response serverResponse;
        try {
            serverResponse = studentService.createStudent(createForm);
            svResponse = gson.toJson(serverResponse);
            requestLogger.info("finish process request {} in {}", requestUri, sw.stop());
            return new ResponseEntity<>(svResponse,HttpStatus.OK);
        }catch (StudentException se){
            se.printStackTrace();
            eLogger.error(se.getMessage());
            svResponse = buildFailureResponse(se.getCode(),se.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            eLogger.error("create student error: {}",e.getMessage());
            svResponse = buildFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "an error occurred");
        }
        return new ResponseEntity<>(svResponse,HttpStatus.OK);
    }

    @PutMapping(path = "/update/{id}",produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> updateStudent(@PathVariable("id") int id, @RequestBody @Valid ModifyForm modifyForm,
                                                HttpServletRequest request,
                                                HttpServletResponse response){
        StopWatch sw = new StopWatch();
        String requestUri = request.getRequestURI()+ "?" + getRequestParams(request);
        String svResponse;
        Response serverResponse;
        try {

            serverResponse = studentService.modifyStudent(id,modifyForm);
            svResponse = gson.toJson(serverResponse);
            requestLogger.info("finish process request {} in {}", requestUri, sw.stop());
            return new ResponseEntity<>(svResponse,HttpStatus.OK);
        }catch (StudentException se){
            se.printStackTrace();
            eLogger.error(se.getMessage());
            svResponse = buildFailureResponse(se.getCode(),se.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            eLogger.error("modify student error: {}",e.getMessage());
            svResponse = buildFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "an error occurred");
        }
        return new ResponseEntity<>(svResponse,HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> deleteStudentById(@PathVariable("id") int id,
                                                    HttpServletRequest request,
                                                    HttpServletResponse response){
        StopWatch sw = new StopWatch();
        String requestUri = request.getRequestURI()+ "?" + getRequestParams(request);
        String svResponse;
        Response serverResponse;
        try {

            serverResponse = studentService.deleteStudent(id);
            svResponse = gson.toJson(serverResponse);
            requestLogger.info("finish process request {} in {}", requestUri, sw.stop());
            return new ResponseEntity<>(svResponse,HttpStatus.OK);
        }catch (StudentException se){
            se.printStackTrace();
            eLogger.error(se.getMessage());
            svResponse = buildFailureResponse(se.getCode(),se.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            eLogger.error("delete student error: {}",e.getMessage());
            svResponse = buildFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "an error occurred");
        }
        return new ResponseEntity<>(svResponse,HttpStatus.OK);
    }

    @GetMapping(path = "/top3")
    public ResponseEntity<String> getTop3(HttpServletRequest request, HttpServletResponse response){
        StopWatch sw = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        String svResponse;
        Response serverResponse;
        try{
            serverResponse = studentService.getTop3Student();
            svResponse = gson.toJson(serverResponse);
            requestLogger.info("finish process request {} in {}", requestUri,sw.stop());
            return new ResponseEntity<>(svResponse,HttpStatus.OK);
        }catch(StudentException se){
            se.printStackTrace();
            eLogger.error(se.getMessage());
            svResponse = buildFailureResponse(se.getCode(),se.getMessage());
        }catch(Exception e){
            e.printStackTrace();
            eLogger.error("get top 3 student error: {}",e.getMessage());
            svResponse = buildFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "an error occurred");
        }
        return new ResponseEntity<>(svResponse,HttpStatus.OK);
    }

    @GetMapping(path = "/search_by_name",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> searchStudentByName(@RequestParam(value = "name",defaultValue = "",required = false)
                                                      String name,
                                                      HttpServletRequest request,
                                                      HttpServletResponse response){
        StopWatch sw = new StopWatch();
        String requestUri = request.getRequestURI() + "?" + getRequestParams(request);
        String svResponse;
        Response serverResponse;
        try{
            serverResponse = studentService.searchStudentByName(name);
            svResponse = gson.toJson(serverResponse);
            requestLogger.info("finish process request {} in {}", requestUri,sw.stop());
            return new ResponseEntity<>(svResponse,HttpStatus.OK);
        }catch(StudentException se){
            se.printStackTrace();
            eLogger.error(se.getMessage());
            svResponse = buildFailureResponse(se.getCode(),se.getMessage());
        }catch(Exception e){
            e.printStackTrace();
            eLogger.error("search student by name error: {}",e.getMessage());
            svResponse = buildFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "an error occurred");
        }
        return new ResponseEntity<>(svResponse,HttpStatus.OK);
    }
    
}
