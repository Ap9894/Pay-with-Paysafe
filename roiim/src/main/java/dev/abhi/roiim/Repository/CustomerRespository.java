package dev.abhi.roiim.Repository;

import dev.abhi.roiim.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRespository extends JpaRepository<Customer,Long> {

    public Customer findCustomerByEmail(String email);
}
