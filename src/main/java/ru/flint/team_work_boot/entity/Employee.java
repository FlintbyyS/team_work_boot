package ru.flint.team_work_boot.entity;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "school")
    private String school;
    @Column(name = "department")
    private String department;
    @Column(name = "post")
    private String post;
    @Column(name = "workload")
    private int workload;
    @Column(name = "salary")
    private int salary;
    @Column(name = "email")
    private String email;



    public boolean isNew(){
        return this.id == 0;
    }
}
