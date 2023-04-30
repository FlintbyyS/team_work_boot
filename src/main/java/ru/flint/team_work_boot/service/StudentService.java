package ru.flint.team_work_boot.service;



import ru.flint.team_work_boot.entity.Performance;
import ru.flint.team_work_boot.entity.Student;

import java.util.List;

public interface StudentService {

     List<Student> getAllByGroup(String group);
     List<Student> saveStudent(List<Student> students,String group,String subject);
     Student saveStudent(Student student,String group);
     Student getStudentById(long id);
     void deleteStudent(int id);
     List<Student> getAllStudentsByGroupAndSubject(String group,String subject);
     List<Student> updateStudents(List<Student> students,String group, String subject);
     List<Performance> getPerformance(int id);
}
