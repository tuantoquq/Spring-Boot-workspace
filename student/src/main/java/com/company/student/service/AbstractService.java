package com.company.student.service;

import com.company.student.adapter.EntityAdapter;
import com.company.student.entities.Student;
import com.company.student.model.response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractService {
    @Autowired
    protected EntityAdapter<Student, StudentResponse> entityAdapter;
}
