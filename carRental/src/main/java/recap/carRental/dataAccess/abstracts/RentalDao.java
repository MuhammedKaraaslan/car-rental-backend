package recap.carRental.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import recap.carRental.entities.concretes.Rental;

public interface RentalDao extends JpaRepository<Rental, Integer>{

	List<Rental> getByCarId(int carId);
}
