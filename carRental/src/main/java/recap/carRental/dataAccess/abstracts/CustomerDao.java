package recap.carRental.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import recap.carRental.entities.concretes.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer>{

}
