package web.beecommerce.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import web.beecommerce.dto.request.create_request.CreateCustomerRequestDTO;
import web.beecommerce.dto.request.update_request.UpdateCustomerRequestDTO;
import web.beecommerce.dto.response.CustomerResponseDTO;
import web.beecommerce.entity.Customer;

import java.util.List;

@Service
public interface CustomerService {
    Customer findById(Long id);

    CustomerResponseDTO findByUserId(Long id);

    List<Customer> findAll();

    CustomerResponseDTO save(CreateCustomerRequestDTO requestDTO);

    CustomerResponseDTO update(UpdateCustomerRequestDTO requestDTO);

    String delete(Long id);

    Page<CustomerResponseDTO> findAll(Integer pageNo, Integer pageSize);
}
