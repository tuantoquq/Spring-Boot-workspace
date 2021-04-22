package com.company.student.dao;

import com.company.student.entities.Student;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Transactional
public abstract class AbstractDAO<T> {
    private Class<T> entityClass;

    public AbstractDAO(Class<T> entityClass){this.entityClass = entityClass;}
    protected abstract EntityManager getEntityManager();

    public void create(T entity){getEntityManager().persist(entity);}
    public void edit(T entity){getEntityManager().merge(entity);}
    public void remove(T entity){getEntityManager().remove(entity);}

    public T find(Object id){
        return getEntityManager().find(entityClass,id);
    }

    public List<T> findAll(){
        CriteriaQuery query = getEntityManager().getCriteriaBuilder().createQuery();
        query.select(query.from(entityClass));
        return getEntityManager().createQuery(query).getResultList();
    }
}
