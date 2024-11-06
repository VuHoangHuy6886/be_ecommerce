package web.beecommerce.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import web.beecommerce.dto.request.create_request.CreateCustomerRequestDTO;
import web.beecommerce.dto.request.update_request.UpdateCustomerRequestDTO;
import web.beecommerce.dto.response.CustomerResponseDTO;
import web.beecommerce.entity.Cart;
import web.beecommerce.entity.Customer;
import web.beecommerce.exception.NotFound;
import web.beecommerce.mapper.CustomerMapper;
import web.beecommerce.repository.CustomerRepository;
import web.beecommerce.service.CartService;
import web.beecommerce.service.CustomerService;
import web.beecommerce.util.BaseStatus;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CartService cartService;

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new NotFound("Customer not found"));
    }

    @Override
    public CustomerResponseDTO findByUserId(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new NotFound("Customer not found"));
        return customerMapper.mapCustomerToCustomerResponseDTO(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerResponseDTO save(CreateCustomerRequestDTO requestDTO) {
        Customer customer = customerMapper.createRequestToCustomer(requestDTO);
        customerRepository.save(customer);
        // create cart for customer
        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setDescription("Cart of customer id : " + customer.getId());
        cart.setStatus(BaseStatus.ACTIVE.getValue());
        cartService.save(cart);
        log.info("create cart successfully");
        // ..............................
        return customerMapper.mapCustomerToCustomerResponseDTO(customer);
    }

    @Override
    public CustomerResponseDTO update(UpdateCustomerRequestDTO requestDTO) {
        Customer customer = customerMapper.updateRequestToCustomer(requestDTO);
        customerRepository.save(customer);
        return customerMapper.mapCustomerToCustomerResponseDTO(customer);
    }

    @Override
    public String delete(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new NotFound("Customer not found"));
        if (customer.getId() != null) {
            Cart cart = cartService.findCartByCustomerID(customer.getId());
            customer.setStatus(BaseStatus.INACTIVE.getValue());
            customerRepository.save(customer);
            cart.setStatus(BaseStatus.INACTIVE.getValue());
            cartService.save(cart);
            return "Delete user id : " + id + " successfully";
        } else {
            return "Delete user id : " + id + " Failed";
        }
    }

    @Override
    public Page<CustomerResponseDTO> findAll(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Customer> customers = customerRepository.findAll(pageable);
        return customerMapper.mapCustomerPageToCustomerResponseDTOPage(customers);
    }
}
