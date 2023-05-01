package ru.flint.team_work_boot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import ru.flint.team_work_boot.util.annotation.NoHtml;


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
    @Column(name = "department")
    @NoHtml
    @NotBlank(message = "Department must not be empty")
    private String department;
    @Column(name = "post")
    @NoHtml
    @NotBlank(message = "Post must not be empty")
    private String post;
    @Column(name = "workload")
    @Positive(message = "Workload must be positive")
    private int workload;
    @Column(name = "salary")
    @Positive(message = "Salary must be positive")
    private int salary;
    @Column(name = "email")
    @Email(message = "Enter valid e-mail")
    @NoHtml
    @Size(max = 128)
    private String email;

    @Column(name = "user_id")
    @NotNull
    private Integer user_id;

    public boolean isNew(){
        return this.id == 0;
    }
}
