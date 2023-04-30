package ru.flint.team_work_boot.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.flint.team_work_boot.entity.Performance;
import ru.flint.team_work_boot.entity.Student;
import ru.flint.team_work_boot.exception_handling.NoSuchStudentException;
import ru.flint.team_work_boot.service.StudentService;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping(value = "/api/students",produces = MediaType.APPLICATION_JSON_VALUE)
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
    public Student createStudent(@RequestBody Student student){
        return studentService.saveStudent(student,student.getGroup()) == null
                ? null : studentService.saveStudent(student,student.getGroup());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public List<Student> updateStudents(@RequestBody String jsonStudents
            , @RequestParam("group") String group, @RequestParam("subject") String subject) throws JsonProcessingException {
        Collection<Student> studentsFromJson = new ObjectMapper().readValue(
                jsonStudents, new TypeReference<>() {}
        );
        return studentService.updateStudents((List<Student>) studentsFromJson,group,subject) == null
                ? null : studentService.updateStudents((List<Student>) studentsFromJson,group,subject);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteStudent(@PathVariable int id){
        studentService.deleteStudent(id);
        return "Student with id = " + id + " was deleted";
    }

    @GetMapping("performance/{id}")
    public List<Performance> getPerformance(@PathVariable int id){
        if (studentService.getStudentById(id) == null){
            throw new NoSuchStudentException("There is no student with ID = " + id + " in Database");
        }
        return studentService.getPerformance(id);
    }

}
