package seb.task.customer;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CustomerDTO {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private LocalDateTime created;
}
