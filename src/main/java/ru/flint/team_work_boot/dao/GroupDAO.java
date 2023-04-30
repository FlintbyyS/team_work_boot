package ru.flint.team_work_boot.dao;





import ru.flint.team_work_boot.entity.Group;

import java.util.List;

public interface GroupDAO {
     List<Group> getAllGroups();

     Group getGroupByName(String name);

     void saveGroup(Group group);

}
