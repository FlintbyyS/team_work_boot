package ru.flint.team_work_boot.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.flint.team_work_boot.dao.GroupDAO;
import ru.flint.team_work_boot.entity.Group;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService{

    @Autowired
    private GroupDAO groupDAO;

    @Override
    public List<Group> getAllGroups() {
        return groupDAO.getAllGroups();
    }

    @Override
    public Group getGroupByName(String name) {
        return groupDAO.getGroupByName(name);
    }
}
