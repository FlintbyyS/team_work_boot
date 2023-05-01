package ru.flint.team_work_boot.dao;


import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.flint.team_work_boot.entity.Grade;
import ru.flint.team_work_boot.entity.Student;

import java.util.List;

@Repository
@Slf4j
public class StudentDAOImpl implements StudentDAO{


    @Autowired
    private EntityManager entityManager;

    @Autowired
    private GradeDAO gradeDAO;


    @Override
    @Transactional
    public List<Student> getAllByGroup(String group) {

        return entityManager.createQuery("from Student where group like :group_name ",Student.class)
                .setParameter("group_name","%" +group + "%")
                .getResultList();
    }

    @Override
    @Transactional
    public void saveStudent(Student student,String group,String subject) {

        long id = 0L;
        if (student.getId() !=0){
            id = student.getId();
        }
        entityManager.merge(student);

        Integer queryId = Math.toIntExact(student.getId());
        entityManager.createQuery("update Grade set omissions =: omissions where group like: group and subject like: subject and student_id =: id")
                .setParameter("omissions",student.getOmissions())
                .setParameter("group",group)
                .setParameter("subject",subject)
                .setParameter("id",queryId)
                .executeUpdate();
        entityManager.createQuery("update Grade set scores =: scores where group like: group and subject like: subject and student_id =: id")
                .setParameter("scores",student.getScores())
                .setParameter("group",group)
                .setParameter("subject",subject)
                .setParameter("id",queryId)
                .executeUpdate();

        Grade grade = new Grade(subject,Math.toIntExact(student.getId()),group,student.getOmissions(),student.getScores());
        Long gradeId = gradeDAO.getGradeIdByUniqueKey(group,subject, (int) student.getId());
        if (gradeId != null) grade.setId(gradeId);
        if (id == 0)
            entityManager.persist(grade);
        else
            entityManager.merge(grade);
    }
    @Transactional
    @Override
    public void saveStudent(Student student){
        entityManager.persist(student);
    }

    @Override
    @Transactional
    public Student getStudentById(long id) {

        return entityManager.find(Student.class,id);
    }

    @Override
    public Student getStudentByUserId(int user_id) {
        return entityManager.createQuery("from Student where user_id =: user_id", Student.class)
                .setParameter("user_id",user_id)
                .getSingleResult();
    }


    @Override
    @Transactional
    public void deleteStudent(long id) {

        entityManager.createQuery("delete from Student where id =:studentId")
                .setParameter("studentId",id)
                .executeUpdate();

    }

    @Override
    @Transactional
    public List<Integer> getOmissionsForSubject(String subject, String group) {

        return entityManager.createQuery("select omissions from Grade where subject like: subject and group like: group",Integer.class)
                .setParameter("subject","%" +subject + "%")
                .setParameter("group","%" +group + "%")
                .getResultList();
    }

    @Override
    public Integer getOmissionForSubjectByStudentId(String subject,int id) {
        return entityManager.createQuery("select omissions from Grade where subject like: subject and student_id =: id"
                        ,Integer.class)
                .setParameter("subject",subject)
                .setParameter("id",id)
                .getSingleResult();
    }

    @Override
    @Transactional
    public List<Integer> getScoresForSubject(String subject, String group) {

        return entityManager.createQuery("select scores from Grade where subject like: subject and group like: group",Integer.class)
                .setParameter("subject","%" +subject + "%")
                .setParameter("group","%" +group + "%")
                .getResultList();
    }

    @Override
    public Integer getScoreForSubjectByStudentId(String subject,int id) {

        return entityManager.createQuery("select scores from Grade where subject like: subject and student_id =: id"
                        ,Integer.class)
                .setParameter("subject",subject)
                .setParameter("id",id)
                .getSingleResult();
    }
}
