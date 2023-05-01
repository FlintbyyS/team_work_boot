package ru.flint.team_work_boot.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import ru.flint.team_work_boot.util.annotation.NoHtml;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    @NoHtml
    @NotBlank(message = "Name must not be empty")
    private String name;

    @Column(name = "surname")
    @NoHtml
    @NotBlank(message = "Surname must not be empty")
    private String surname;

    @Column(name = "school")
    @NoHtml
    @NotBlank(message = "School must not be empty")
    private String school;
    @Column(name = "direction")
    @NoHtml
    @NotBlank(message = "Direction must not be empty")
    private String direction;
    @Column(name = "specialization")
    @NoHtml
    @NotBlank(message = "Specialization must not be empty")
    private String specialization;
    @Column(name = "course")
    @Positive(message = "Course must be positive")
    @Max(value = 6)
    private int course;
    @Column(name = "group_name")
    @NoHtml
    @NotBlank(message = "Group must not be empty")
    private String group;

    @Column(name = "omissions")
    @Positive(message = "Omissions must be positive")
    @Max(value = 256)
    private int omissions;

    @Column(name = "scores")
    @Positive(message = "scores must be positive")
    @Max(value = 100)
    private int scores;

    @Column(name = "user_id")
    @Positive(message = "User_id must be positive")
    private Integer user_id;
}
