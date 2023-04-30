package ru.flint.team_work_boot.exception_handling;

public class NoSuchStudentException extends  RuntimeException{

    public NoSuchStudentException(String message) {
        super(message);
    }
}
