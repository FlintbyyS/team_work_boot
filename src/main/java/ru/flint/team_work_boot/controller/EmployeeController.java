package ru.flint.team_work_boot.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.flint.team_work_boot.entity.Employee;
import ru.flint.team_work_boot.util.exception_handling.NoSuchEmployeeException;
import ru.flint.team_work_boot.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/employees",produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public List<Employee> showAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public  Employee getEmployee(@PathVariable int id){
        Employee employee = employeeService.getEmployee(id);
        if(employee == null){
            throw new NoSuchEmployeeException("There is no employee with ID = " + id + " in Database");
        }
        return employee;
    }
    @GetMapping("/user/{user_id}")
    public  Employee getEmployeeByUserId(@PathVariable int user_id){
        Employee employee = employeeService.getEmployeeByUserId(user_id);
        if(employee == null){
            throw new NoSuchEmployeeException("There is no employee with user_id = " + user_id + " in Database");
        }
        return employee;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addNewEmployee(@RequestBody Employee employee){
         return employeeService.saveEmployee(employee);
    }

    @PutMapping()
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Employee updateEmployee(@RequestBody Employee employee){

        if (employeeService.getEmployee(employee.getId()) == null){
            throw new NoSuchEmployeeException("There is no employee in Database");
        }

          return employeeService.saveEmployee(employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteEmployee(@PathVariable int id){

        Employee employee = employeeService.getEmployee(id);
        if (employee == null){
            throw new NoSuchEmployeeException("There is no employee with ID = " + id + " in Database");
        }

        employeeService.deleteEmployee(id);

        return "Employee with ID = " + id + " was deleted";
    }

}
