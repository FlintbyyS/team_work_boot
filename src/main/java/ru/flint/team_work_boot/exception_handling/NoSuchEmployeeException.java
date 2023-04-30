package ru.flint.team_work_boot.exception_handling;

public class NoSuchEmployeeException extends  RuntimeException{

    public NoSuchEmployeeException(String message) {
        super(message);
    }
}
