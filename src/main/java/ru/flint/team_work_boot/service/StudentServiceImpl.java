package ru.flint.team_work_boot.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.flint.team_work_boot.dao.GradeDAO;
import ru.flint.team_work_boot.dao.GroupDAO;
import ru.flint.team_work_boot.dao.StudentDAO;
import ru.flint.team_work_boot.entity.Group;
import ru.flint.team_work_boot.entity.Student;


import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{


    private final StudentDAO studentDAO;


    private final GroupDAO groupDAO;


    private final GradeDAO gradeDAO;

    public StudentServiceImpl(StudentDAO studentDAO, GroupDAO groupDAO, GradeDAO gradeDAO) {
        this.studentDAO = studentDAO;
        this.groupDAO = groupDAO;
        this.gradeDAO = gradeDAO;
    }

    @Override
    public List<Student> getAllByGroup(String group) {
        return studentDAO.getAllByGroup(group);
    }

    @Override
    public String saveStudent(List<Student> students,String group,String subject) {
        if (groupDAO.getGroupByName(group) == null) return "Error!";
        for (Student student: students){
            studentDAO.saveStudent(student,group,subject);
        }
        return "Success!";
    }
    @Override
    @Transactional
    public String saveStudent(Student student,String group) {
        if (groupDAO.getGroupByName(student.getGroup()) == null) return "Error!";

        studentDAO.saveStudent(student);

        Group studentGroup = groupDAO.getGroupByName(group);
        studentGroup.setStudents_number(studentGroup.getStudents_number()+1);
        groupDAO.saveGroup(studentGroup);

        gradeDAO.saveGrade(student,studentGroup);

        return "Success!";
    }

    @Override
    public Student getStudentById(long id) {
        return studentDAO.getStudentById(id);
    }


    @Override
    @Transactional
    public void deleteStudent(int id) {
        Group studentGroup = groupDAO.getGroupByName(getStudentById(id).getGroup());
        studentGroup.setStudents_number(studentGroup.getStudents_number()-1);
        groupDAO.saveGroup(studentGroup);

        gradeDAO.deleteStudentGrade(id,studentGroup);

        studentDAO.deleteStudent(id);
    }

    @Override
    @Transactional
    public List<Student> getAllStudentsByGroupAndSubject(String group, String subject) {
        List<Student> students = getAllByGroup(group);
        List<Integer> omissions = studentDAO.getOmissionsForSubject(subject,group);
        List<Integer> scores = studentDAO.getScoresForSubject(subject,group);
        for(int i=0;i<students.size();i++){
            students.get(i).setOmissions(omissions.get(i));
            students.get(i).setScores(scores.get(i));
        }
        return students;
    }


    @Override
    public void updateStudents(List<Student> students,String group, String subject) {
        saveStudent(students,group,subject);
    }
}
