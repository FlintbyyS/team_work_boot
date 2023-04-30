package ru.flint.team_work_boot.entity;


import jakarta.persistence.*;
import lombok.*;

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
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "school")
    private String school;
    @Column(name = "direction")
    private String direction;
    @Column(name = "specialization")
    private String specialization;
    @Column(name = "course")
    private int course;
    @Column(name = "group_name")
    private String group;

    @Column(name = "omissions")
    private int omissions;

    @Column(name = "scores")
    private int scores;
}
