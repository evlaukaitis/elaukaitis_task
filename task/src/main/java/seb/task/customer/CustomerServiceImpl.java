package seb.task.customer;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final ModelMapper modelMapper;

    private final CustomerDAO customerDAO;

    public void createCustomer(CustomerDTO customerDTO) {

        customerRepository.save(modelMapper.map(customerDTO, CustomerEntity.class));
    }

    public CustomerSearchResultDTO search(String query, Long pageNo, Long pageSize) {
        return customerDAO.search(query, pageNo, pageSize);
    }

    public CustomerDTO getCustomerDetails(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow();
        return modelMapper.map(customerEntity, CustomerDTO.class);
    }
}
