package com.company.student.dao;

import com.company.student.entities.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class StudentDAOImpl extends AbstractDAO<Student> implements StudentDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public StudentDAOImpl(){
        super(Student.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public List<Student> getTop3() {
        Query query = getEntityManager().createNativeQuery("select * from student order by score DESC limit 3",
                Student.class);
        return query.getResultList();
    }

    @Override
    public List<Student> searchByName(String name) {
        Query query = getEntityManager().createNativeQuery("select * from student where student.name like '%"+name+"%'",Student.class);
        return query.getResultList();
    }
}
