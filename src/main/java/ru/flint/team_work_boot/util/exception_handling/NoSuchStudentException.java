package ru.flint.team_work_boot.util.exception_handling;

public class NoSuchStudentException extends  RuntimeException{

    public NoSuchStudentException(String message) {
        super(message);
    }
}
