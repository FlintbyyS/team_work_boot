package ru.flint.team_work_boot.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.flint.team_work_boot.entity.Student;
import ru.flint.team_work_boot.service.StudentService;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping(value = "api/students",produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudentsByGroupAndSubject(@RequestParam String group, @RequestParam String subject){
        return studentService.getAllStudentsByGroupAndSubject(group,subject);
    }

    @GetMapping("/{id}")
    public Student get(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudent(@RequestBody Student student){
        studentService.saveStudent(student,student.getGroup());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStudents(@RequestBody String jsonStudents
            , @RequestParam("group") String group, @RequestParam("subject") String subject) throws JsonProcessingException {
        Collection<Student> studentsFromJson = new ObjectMapper().readValue(
                jsonStudents, new TypeReference<>() { }
        );
        studentService.updateStudents((List<Student>) studentsFromJson,group,subject);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable int id){
        studentService.deleteStudent(id);
    }

}
