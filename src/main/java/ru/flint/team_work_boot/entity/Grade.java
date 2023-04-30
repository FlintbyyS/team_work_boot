package ru.flint.team_work_boot.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "grade")
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "student_id")
    private int student_id;

    @Column(name = "group_name")
    private String group;

    @Column(name = "omissions")
    private int omissions;

    @Column(name = "scores")
    private int scores;

    public Grade() {
    }

    public Grade(String subject, int student_id, String group, int omissions, int scores) {
        this.subject = subject;
        this.student_id = student_id;
        this.group = group;
        this.omissions = omissions;
        this.scores = scores;
    }

    public Grade(String subject, int student_id, String group) {
        this.subject = subject;
        this.student_id = student_id;
        this.group = group;
    }
}
