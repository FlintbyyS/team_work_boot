package ru.flint.team_work_boot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.List;

@Entity
@Table(name = "student_group")
@Getter
@Setter
public class Group {
    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "students")
    private int students_number;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name="LECTURER_GROUPS",
            inverseJoinColumns=@JoinColumn(name="LECTURER_ID", referencedColumnName="ID"),
            joinColumns=@JoinColumn(name="GROUP_NAME", referencedColumnName="NAME"))
    private List<Employee> employees;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name="GROUP_SUBJECT",
            inverseJoinColumns=@JoinColumn(name="SUBJECT_ID", referencedColumnName="ID"),
            joinColumns=@JoinColumn(name="GROUP_NAME", referencedColumnName="NAME"))
    private List<Subject> subjects;

    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    public Group(String name, int students_number) {
        this.name = name;
        this.students_number = students_number;
    }

}
