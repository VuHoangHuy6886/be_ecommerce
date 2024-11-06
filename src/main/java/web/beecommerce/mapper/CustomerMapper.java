package web.beecommerce.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import web.beecommerce.dto.request.create_request.CreateCustomerRequestDTO;
import web.beecommerce.dto.request.update_request.UpdateCustomerRequestDTO;
import web.beecommerce.dto.response.CustomerResponseDTO;
import web.beecommerce.entity.Customer;

import java.util.List;

@Component
public interface CustomerMapper {
    // map create request to entity
    Customer createRequestToCustomer(CreateCustomerRequestDTO requestDTO);

    // map update request to entity
    Customer updateRequestToCustomer(UpdateCustomerRequestDTO requestDTO);

    // map entity to  response
    CustomerResponseDTO mapCustomerToCustomerResponseDTO(Customer customer);

    // map list entity to list response
    List<CustomerResponseDTO> mapCustomerListToCustomerResponseDTOList(List<Customer> customers);

    // map page entity to page response
    Page<CustomerResponseDTO> mapCustomerPageToCustomerResponseDTOPage(Page<Customer> customers);
}
