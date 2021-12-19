package seb.task.customer;

public interface CustomerService {
    void createCustomer(CustomerDTO customerDTO);

    CustomerSearchResultDTO search(String query, Long pageNo, Long pageSize);

    CustomerDTO getCustomerDetails(Long id);
}
