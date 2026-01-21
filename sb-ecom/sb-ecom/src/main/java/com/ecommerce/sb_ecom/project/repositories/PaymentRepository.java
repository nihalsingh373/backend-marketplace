package com.ecommerce.sb_ecom.project.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.sb_ecom.project.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

}