package recap.carRental.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import recap.carRental.entities.concretes.Color;

public interface ColorDao extends JpaRepository<Color, Integer>{

}
