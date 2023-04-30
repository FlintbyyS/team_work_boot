package ru.flint.team_work_boot.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.flint.team_work_boot.entity.Employee;


import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Employee> getAllEmployees() {

        return entityManager.createQuery("from Employee",Employee.class)
                .getResultList();
    }

    @Override
    public void saveEmployee(Employee employee) {


        entityManager.merge(employee);

    }

    @Override
    public Employee getEmployee(int id) {

        return entityManager.find(Employee.class,id);
    }

    @Override
    public void deleteEmployee(int id) {
        entityManager.createQuery("delete from Employee where id =:employeeId")
                .setParameter("employeeId",id)
                .executeUpdate();
    }
}
