package ru.flint.team_work_boot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.flint.team_work_boot.util.annotation.NoHtml;

import java.util.List;

@Entity
@Table(name = "subjects")
@Getter
@Setter
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    @NoHtml
    @NotBlank(message = "Name must not be empty")
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name="LECTURER_SUBJECT",
            inverseJoinColumns=@JoinColumn(name="LECTURER_ID", referencedColumnName="ID"),
            joinColumns=@JoinColumn(name="subject_id", referencedColumnName="id"))
    private List<Employee> employees;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(
            name="GROUP_SUBJECT",
            joinColumns=@JoinColumn(name="SUBJECT_ID", referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="GROUP_NAME", referencedColumnName="NAME"))
    private List<Group> groups;

    public Subject() {
    }

    public Subject(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
