package seb.task.customer;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomerSearchResultDTO {
    private List<CustomerListDTO> searchResult;
    private Long numFound;
}
