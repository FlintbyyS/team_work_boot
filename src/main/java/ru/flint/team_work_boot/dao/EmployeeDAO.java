package ru.flint.team_work_boot.dao;




import ru.flint.team_work_boot.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
     List<Employee> getAllEmployees();

     Employee saveEmployee(Employee employee);

     Employee getEmployee(int id);

     Employee getEmployeeByUserId(int user_id);

     void deleteEmployee(int id);
}
