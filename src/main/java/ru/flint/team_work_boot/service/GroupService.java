package ru.flint.team_work_boot.service;





import ru.flint.team_work_boot.entity.Group;

import java.util.List;

public interface GroupService {
     List<Group> getAllGroups();

     Group getGroupByName(String name);
}
