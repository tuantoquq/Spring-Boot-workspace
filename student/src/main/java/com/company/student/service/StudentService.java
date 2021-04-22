package com.company.student.service;

import com.company.student.builder.Response;
import com.company.student.model.request.CreateForm;
import com.company.student.model.request.ModifyForm;

public interface StudentService {
    public abstract Response findById(Object id) throws Exception;
    public abstract Response findAll() throws Exception;
    public abstract Response createStudent(CreateForm createForm) throws Exception;
    public abstract Response modifyStudent(Object id, ModifyForm modifyForm) throws Exception;
    public abstract Response deleteStudent(Object id) throws Exception;
    public abstract Response getTop3Student() throws Exception;
    public abstract Response searchStudentByName(String name) throws Exception;
}
