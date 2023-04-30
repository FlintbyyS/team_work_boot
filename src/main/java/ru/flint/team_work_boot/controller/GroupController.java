package ru.flint.team_work_boot.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.flint.team_work_boot.entity.Group;
import ru.flint.team_work_boot.service.GroupService;

@RestController
@RequestMapping(value = "/api/groups",produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/{name}")
    public Group getGroupWithSubjectsAndTeachers(@PathVariable String name){
        return groupService.getGroupByName(name);
    }
}
