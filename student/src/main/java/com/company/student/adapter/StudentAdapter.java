package com.company.student.adapter;

import com.company.student.entities.Student;
import com.company.student.model.response.StudentResponse;
import org.springframework.stereotype.Component;

@Component("EntityAdapter")
public class StudentAdapter implements EntityAdapter<Student, StudentResponse> {
    @Override
    public StudentResponse toMapper(Student entity) {
        StudentResponse sr = new StudentResponse();
        sr.setId(entity.getId());
        sr.setName(entity.getName());
        sr.setAddress(entity.getAddress());
        sr.setScore(entity.getScore());
        return sr;
    }
}
