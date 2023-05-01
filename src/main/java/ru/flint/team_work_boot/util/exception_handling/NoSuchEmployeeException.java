package ru.flint.team_work_boot.util.exception_handling;

public class NoSuchEmployeeException extends  RuntimeException{

    public NoSuchEmployeeException(String message) {
        super(message);
    }
}
