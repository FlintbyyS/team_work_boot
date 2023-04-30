package ru.flint.team_work_boot.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.flint.team_work_boot.entity.Employee;
import ru.flint.team_work_boot.exception_handling.NoSuchEmployeeException;
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

    @PostMapping()
    public Employee addNewEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping()
    public Employee updateEmployee(@RequestBody Employee employee){

        employeeService.saveEmployee(employee);

        return employee;
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id){

        Employee employee = employeeService.getEmployee(id);
        if (employee == null){
            throw new NoSuchEmployeeException("There is no employee with ID = " + id + " in Database");
        }

        employeeService.deleteEmployee(id);

        return "Employee with ID = " + id + " was deleted";
    }

}
