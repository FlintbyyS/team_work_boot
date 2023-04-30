package ru.flint.team_work_boot.dao;


import ru.flint.team_work_boot.entity.Group;
import ru.flint.team_work_boot.entity.Student;

public interface GradeDAO {

    Long getGradeIdByUniqueKey(String group,String subject,int student_id);

    void saveGrade(Student student, Group group);

    void deleteStudentGrade(int id,Group group);
}
