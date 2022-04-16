package recap.carRental.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import recap.carRental.entities.concretes.Brand;

public interface BrandDao extends JpaRepository<Brand, Integer>{

}
