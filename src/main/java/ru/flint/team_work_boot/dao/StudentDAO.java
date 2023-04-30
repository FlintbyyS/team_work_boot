package ru.flint.team_work_boot.dao;




import ru.flint.team_work_boot.entity.Student;

import java.util.List;

public interface StudentDAO {

     List<Student> getAllByGroup(String group);

     void saveStudent(Student student,String group,String subject);

     void saveStudent(Student student);

     Student getStudentById(long id);

     void deleteStudent(long id);

     List<Integer> getOmissionsForSubject(String subject,String group);
     Integer getOmissionForSubjectByStudentId(String subject,int id);

     List<Integer> getScoresForSubject(String subject,String group);
     Integer getScoreForSubjectByStudentId(String subject,int id);

}
