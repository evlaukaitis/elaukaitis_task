package seb.task.customer;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerListDTO {
    private Long id;
    private String name;
    private String surname;
    private LocalDate birthDate;

    public CustomerListDTO(Long id, String name, String surname, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }
}
