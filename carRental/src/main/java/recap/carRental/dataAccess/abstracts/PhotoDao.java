package recap.carRental.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import recap.carRental.entities.concretes.Photo;

public interface PhotoDao extends JpaRepository<Photo, Integer>{
	List<Photo> getByCarId(int carId);
}
