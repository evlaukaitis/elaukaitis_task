package seb.task.customer;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import seb.task.generic.GenericEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER")
public class CustomerEntity extends GenericEntity {

    @Column(length = 31)
    @NonNull
    private String name;

    @Column(length = 31)
    @NonNull
    private String surname;

    @Column(unique = true)
    @NonNull
    private String email;

    private String phone;

    @NonNull
    private LocalDate birthDate;
}
