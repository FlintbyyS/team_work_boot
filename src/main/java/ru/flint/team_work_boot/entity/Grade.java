package ru.flint.team_work_boot.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.flint.team_work_boot.util.annotation.NoHtml;

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
    @NoHtml
    @NotBlank(message = "Subject must not be empty")
    private String subject;

    @Column(name = "student_id")
    @Positive(message = "Student_id must be positive")
    private int student_id;

    @Column(name = "group_name")
    @NoHtml
    @NotBlank(message = "Group must not be empty")
    private String group;

    @Column(name = "omissions")
    @Positive(message = "Omissions must be positive")
    @Max(value = 256)
    private int omissions;

    @Column(name = "scores")
    @Positive(message = "Scores must be positive")
    @Max(value = 100)
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
