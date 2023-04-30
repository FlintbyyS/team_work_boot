package ru.flint.team_work_boot.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Performance {

    private String subject;

    private int score;

    private int omission;

    private List<Employee> employees;

}
