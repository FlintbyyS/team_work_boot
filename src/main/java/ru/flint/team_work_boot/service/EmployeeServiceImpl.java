package ru.flint.team_work_boot.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.flint.team_work_boot.dao.EmployeeDAO;
import ru.flint.team_work_boot.entity.Employee;


import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        log.info("Get employees");
        return employeeDAO.getAllEmployees();
    }

    @Override
    @Transactional
    public Employee saveEmployee(Employee employee) {
        log.info("Save employee: {}",employee);
        return employeeDAO.saveEmployee(employee);
    }

    @Override
    @Transactional
    public Employee getEmployee(int id) {
        log.info("Get employee with id: {}",id);
        return employeeDAO.getEmployee(id);
    }

    @Override
    public Employee getEmployeeByUserId(int user_id) {
        log.info("Get employee with user_id: {}",user_id);
        return employeeDAO.getEmployeeByUserId(user_id);
    }

    @Override
    @Transactional
    public void deleteEmployee(int id) {
        log.info("Delete employee with id: {}",id);
        employeeDAO.deleteEmployee(id);
    }
}
