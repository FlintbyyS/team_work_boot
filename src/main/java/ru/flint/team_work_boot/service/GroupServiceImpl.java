package ru.flint.team_work_boot.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.flint.team_work_boot.dao.GroupDAO;
import ru.flint.team_work_boot.entity.Employee;
import ru.flint.team_work_boot.entity.Group;
import ru.flint.team_work_boot.entity.Subject;

import java.util.List;

@Service
@Slf4j
public class GroupServiceImpl implements GroupService{

    @Autowired
    private GroupDAO groupDAO;

    @Override
    public List<Group> getAllGroups() {
        log.info("Get groups");
        return groupDAO.getAllGroups();
    }

    @Override
    @Transactional
    public Group getGroupByName(String name) {
        log.info("Get group with name: {} and filter employees for subjects",name);
         Group group = groupDAO.getGroupByName(name);
         List<Employee> employees = group.getEmployees();
         for (Subject subject : group.getSubjects()) {
             subject.setEmployees(subject.getEmployees()
                     .stream().filter(employees::contains).toList());
         }
         return group;
    }
}
