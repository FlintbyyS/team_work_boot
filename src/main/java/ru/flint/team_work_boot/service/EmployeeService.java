package ru.flint.team_work_boot.service;



import ru.flint.team_work_boot.entity.Employee;

import java.util.List;

public interface EmployeeService {

     List<Employee> getAllEmployees();

     Employee saveEmployee(Employee employee);

     Employee getEmployee(int id);

     void deleteEmployee(int id);
}
