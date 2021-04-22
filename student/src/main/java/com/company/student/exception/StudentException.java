package com.company.student.exception;

public class StudentException extends Exception{
    private int code;

    public StudentException(String message, int code){
        super(message);
        this.code= code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
