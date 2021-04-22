package com.company.student.service;

import com.company.student.adapter.StudentAdapter;
import com.company.student.builder.Response;
import com.company.student.dao.StudentDAO;
import com.company.student.entities.Student;
import com.company.student.exception.StudentException;
import com.company.student.model.request.CreateForm;
import com.company.student.model.request.ModifyForm;
import com.company.student.model.response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl extends AbstractService implements StudentService{

    private final StudentDAO studentDAO;

    @Autowired
    public StudentServiceImpl(StudentDAO studentDAO){
        this.studentDAO = studentDAO;
    }


    @Override
    public Response findById(Object id) throws Exception {
        Student student = studentDAO.find(id);
        if(student == null) throw new StudentException("Student not found!", HttpStatus.NOT_FOUND.value());

        StudentResponse sr = entityAdapter.toMapper(student);
        return new Response.Builder(1, HttpStatus.OK.value())
                .buildMessage("Success")
                .buildData(sr)
                .build();
    }

    @Override
    public Response findAll() throws Exception {
        List<StudentResponse> result = studentDAO.findAll().stream().map(
                e ->entityAdapter.toMapper(e)).collect(Collectors.toList());

        return new Response.Builder(1,HttpStatus.OK.value())
                .buildMessage("Success")
                .buildData(result)
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Response createStudent(CreateForm createForm) throws Exception {
        Student student = new Student();
        student.setAddress(createForm.getAddress());
        student.setName(createForm.getName());
        student.setScore(createForm.getScore());
        studentDAO.create(student);
        return new Response.Builder(1, HttpStatus.OK.value())
                .buildMessage("create student successfully")
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public Response modifyStudent(Object id, ModifyForm modifyForm) throws Exception {
        Student student = studentDAO.find(id);
        if(student == null) throw new StudentException("Could not find this id!",HttpStatus.NOT_FOUND.value());
        student.setName(modifyForm.getName());
        student.setAddress(modifyForm.getAddress());
        student.setScore(modifyForm.getScore());
        studentDAO.edit(student);
        return new Response.Builder(1,HttpStatus.OK.value())
                .buildMessage("edit student successfully!")
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Response deleteStudent(Object id) throws Exception {
        Student student = studentDAO.find(id);
        if(student == null) throw new StudentException("Student is not exist!",HttpStatus.NOT_FOUND.value());
        studentDAO.remove(student);
        return new Response.Builder(1,HttpStatus.OK.value())
                .buildMessage("Delete student successfully!")
                .build();
    }

    @Override
    public Response getTop3Student() throws Exception {
        List<StudentResponse> rs = studentDAO.getTop3().stream().map(
                s -> entityAdapter.toMapper(s)).collect(Collectors.toList());
        return new Response.Builder(1, HttpStatus.OK.value())
                .buildData(rs)
                .buildMessage("Success")
                .build();
    }

    @Override
    public Response searchStudentByName(String name) throws Exception {
        List<StudentResponse> rs = studentDAO.searchByName(name).stream().map(
                s -> entityAdapter.toMapper(s)).collect(Collectors.toList());
        return new Response.Builder(1, HttpStatus.OK.value())
                .buildData(rs)
                .buildMessage("Success")
                .build();
    }

}
