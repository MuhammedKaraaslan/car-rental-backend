package recap.carRental.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import recap.carRental.entities.concretes.User;

public interface UserDao extends JpaRepository<User, Integer>{

}
