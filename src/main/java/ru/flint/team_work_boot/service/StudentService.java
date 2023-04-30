package ru.flint.team_work_boot.service;



import ru.flint.team_work_boot.entity.Student;

import java.util.List;

public interface StudentService {

     List<Student> getAllByGroup(String group);

     String saveStudent(List<Student> students,String group,String subject);
     String saveStudent(Student student,String group);

     Student getStudentById(long id);


     void deleteStudent(int id);

     List<Student> getAllStudentsByGroupAndSubject(String group,String subject);


    void updateStudents(List<Student> students,String group, String subject);
}
