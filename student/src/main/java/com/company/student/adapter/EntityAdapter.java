package com.company.student.adapter;

public interface EntityAdapter<T, B> {
    public abstract B toMapper(T entity);
}
