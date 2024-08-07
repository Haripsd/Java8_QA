package com.example.CompletableFutureExample.async;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private Boolean newJoiner;
    private Boolean learningPending;
    private Long salary;
    private Integer rating;


    public Employee(String firstName, Long salary) {
        this.firstName = firstName;
        this.salary = salary;
    }
}
