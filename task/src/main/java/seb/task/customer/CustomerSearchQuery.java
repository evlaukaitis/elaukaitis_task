package seb.task.customer;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CustomerSearchQuery {
    private String name;
    private String surname;
    private LocalDate birthDate;
}
