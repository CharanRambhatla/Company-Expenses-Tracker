package companyexpensestracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import companyexpensestracker.entities.PaymentMode;

public interface PaymentModeRepo extends JpaRepository<PaymentMode, String>{

}
