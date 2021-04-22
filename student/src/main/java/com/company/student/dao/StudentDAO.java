package com.company.student.dao;

import com.company.student.entities.Student;

import java.util.List;

public interface StudentDAO {
    void create(Student student);
    void edit(Student student);
    void remove(Student student);

    Student find(Object id);
    List<Student> findAll();

    List<Student> getTop3();

    List<Student> searchByName(String name);

}
