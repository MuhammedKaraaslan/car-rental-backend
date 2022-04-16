package recap.carRental.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import recap.carRental.entities.concretes.Car;
import recap.carRental.entities.dtos.CarWithDetailDto;

public interface CarDao extends JpaRepository<Car, Integer>{
	
	@Query("Select new recap.carRental.entities.dtos.CarWithDetailDto(c.modelYear, c.dailyPrice, c.description, c.brand.name, c.color.name)"
			+ " From Car c where c.brand.id=:brandId")
	List<CarWithDetailDto> getByBrandId(int brandId);
	
	@Query("Select new recap.carRental.entities.dtos.CarWithDetailDto(c.modelYear, c.dailyPrice, c.description, c.brand.name, c.color.name)"
			+ " From Car c where c.color.id=:colorId")
	List<CarWithDetailDto> getByColorId(int colorId);
	
}

