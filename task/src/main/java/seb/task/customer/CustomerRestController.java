package seb.task.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerRestController {

    private final CustomerService customerService;

    @PostMapping()
    public void createUser(@RequestBody CustomerDTO customerDTO) {
        customerService.createCustomer(customerDTO);
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerSearchResultDTO search(@RequestParam(required = false) String query,
                                        @RequestParam(required = false) Long pageNo,
                                        @RequestParam(required = false) Long pageSize) {
        return customerService.search(query, pageNo, pageSize);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDTO getCustomerDetails(@PathVariable Long id) {
        return customerService.getCustomerDetails(id);
    }
}
