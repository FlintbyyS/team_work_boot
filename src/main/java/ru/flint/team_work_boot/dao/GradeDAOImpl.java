package ru.flint.team_work_boot.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.flint.team_work_boot.entity.Grade;
import ru.flint.team_work_boot.entity.Group;
import ru.flint.team_work_boot.entity.Student;
import ru.flint.team_work_boot.entity.Subject;


import java.util.List;

@Repository
public class GradeDAOImpl implements GradeDAO{
    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public Long getGradeIdByUniqueKey(String group, String subject, int student_id) {

        List<Long> idlist = entityManager.createQuery("select id from Grade where group like: group and subject like: subject and student_id=: student_id")
                .setParameter("group",group)
                .setParameter("subject",subject)
                .setParameter("student_id",student_id)
                .getResultList();
        if (idlist.size() == 0)
            return 0L;
        else
            return idlist.get(0);
    }

    @Override
    @Transactional
    public void saveGrade(Student student, Group group) {
        for (Subject subject : group.getSubjects()){
            Grade grade = new Grade(subject.getName(), (int) student.getId(),group.getName());
            entityManager.persist(grade);
        }
    }

    @Override
    public void deleteStudentGrade(int id,Group group) {
        for (Subject subject : group.getSubjects()){
            entityManager.createQuery("delete from Grade where student_id=:id and subject like: subject")
                    .setParameter("id",id)
                    .setParameter("subject",subject.getName())
                    .executeUpdate();
        }
    }
}
