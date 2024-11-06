package web.beecommerce.mapper.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import web.beecommerce.dto.request.create_request.CreateCustomerRequestDTO;
import web.beecommerce.dto.request.update_request.UpdateCustomerRequestDTO;
import web.beecommerce.dto.response.CustomerResponseDTO;
import web.beecommerce.entity.Customer;
import web.beecommerce.mapper.CustomerMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapperImpl implements CustomerMapper {
    @Override
    public Customer createRequestToCustomer(CreateCustomerRequestDTO requestDTO) {
        Customer customer = Customer.builder()
                .name(requestDTO.getName())
                .email(requestDTO.getEmail())
                .address(requestDTO.getAddress())
                .password(requestDTO.getPassword())
                .phoneNumber(requestDTO.getPhoneNumber())
                .status(requestDTO.getStatus())
                .build();
        return customer;
    }

    @Override
    public Customer updateRequestToCustomer(UpdateCustomerRequestDTO requestDTO) {

        Customer customer = new Customer();
        customer.setId(requestDTO.getId());
        customer.setName(requestDTO.getName());
        customer.setEmail(requestDTO.getEmail());
        customer.setAddress(requestDTO.getAddress());
        customer.setPassword(requestDTO.getPassword());
        customer.setPhoneNumber(requestDTO.getPhoneNumber());
        customer.setStatus(requestDTO.getStatus());
        return customer;
    }

    @Override
    public CustomerResponseDTO mapCustomerToCustomerResponseDTO(Customer customer) {
        CustomerResponseDTO responseDTO = CustomerResponseDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .password(customer.getPassword())
                .phoneNumber(customer.getPhoneNumber())
                .status(customer.getStatus())
                .build();
        return responseDTO;
    }

    @Override
    public List<CustomerResponseDTO> mapCustomerListToCustomerResponseDTOList(List<Customer> customers) {
        return customers.stream().map(this::mapCustomerToCustomerResponseDTO).collect(Collectors.toList());
    }

    @Override
    public Page<CustomerResponseDTO> mapCustomerPageToCustomerResponseDTOPage(Page<Customer> customers) {
        return customers.map(this::mapCustomerToCustomerResponseDTO);
    }
}
