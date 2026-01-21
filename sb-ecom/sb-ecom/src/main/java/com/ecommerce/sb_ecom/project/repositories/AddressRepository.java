package com.ecommerce.sb_ecom.project.repositories;

import com.ecommerce.sb_ecom.project.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
