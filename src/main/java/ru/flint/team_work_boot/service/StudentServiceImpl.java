package ru.flint.team_work_boot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.flint.team_work_boot.dao.GradeDAO;
import ru.flint.team_work_boot.dao.GroupDAO;
import ru.flint.team_work_boot.dao.StudentDAO;
import ru.flint.team_work_boot.entity.*;


import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
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
        log.info("Get all students by group: {}",group);
        return studentDAO.getAllByGroup(group);
    }

    @Override
    public List<Student> saveStudent(List<Student> students,String group,String subject) {
        log.info("Update students: {} from group: {} with subject: {}",students,group,subject);
        if (groupDAO.getGroupByName(group) == null) return null;
        for (Student student: students){
            studentDAO.saveStudent(student,group,subject);
        }
        return students;
    }
    @Override
    @Transactional
    public Student saveStudent(Student student,String group) {
        log.info("Save student: {} for group: {} ",student,group);
        if (groupDAO.getGroupByName(student.getGroup()) == null) return null;

        studentDAO.saveStudent(student);

        Group studentGroup = groupDAO.getGroupByName(group);
        studentGroup.setStudents_number(studentGroup.getStudents_number()+1);
        groupDAO.saveGroup(studentGroup);

        gradeDAO.saveGrade(student,studentGroup);

        return student;
    }

    @Override
    public Student getStudentById(long id) {
        log.info("Get student by id: {}",id);
        return studentDAO.getStudentById(id);
    }

    @Override
    public Student getStudentByUserId(int user_id) {
        log.info("Get student by user_id: {}",user_id);
        return studentDAO.getStudentByUserId(user_id);
    }


    @Override
    @Transactional
    public void deleteStudent(int id) {
        log.info("Delete student with id: {}",id);
        Group studentGroup = groupDAO.getGroupByName(getStudentById(id).getGroup());
        studentGroup.setStudents_number(studentGroup.getStudents_number()-1);
        groupDAO.saveGroup(studentGroup);

        gradeDAO.deleteStudentGrade(id,studentGroup);

        studentDAO.deleteStudent(id);
    }

    @Override
    @Transactional
    public List<Student> getAllStudentsByGroupAndSubject(String group, String subject) {
        log.info("Get students from group: {} with subject: {}",group,subject);
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
    public List<Student> updateStudents(List<Student> students,String group, String subject) {
         return saveStudent(students,group,subject);
    }

    @Override
    @Transactional
    public List<Performance> getPerformance(int id) {
        log.info("Get student performance with id: {}",id);
        List<Performance> performances = new ArrayList<>();

        Student student = studentDAO.getStudentById(id);
        if (student == null) return null;

        Group group = groupDAO.getGroupByName(student.getGroup());
        if (group == null) return null;

        List<Employee> employees = group.getEmployees();
        List<Subject> subjects = group.getSubjects();
        for (Subject subject : subjects){
            subject.setEmployees(subject.getEmployees()
                    .stream().filter(employees::contains).toList());
            Performance performance = new Performance(subject.getName()
                    ,studentDAO.getScoreForSubjectByStudentId(subject.getName(),id)
                    ,studentDAO.getOmissionForSubjectByStudentId(subject.getName(),id)
                    ,subject.getEmployees());
            performances.add(performance);
        }

        return performances;
    }
}
