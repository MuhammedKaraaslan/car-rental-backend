package recap.carRental.business.abstracts;

import java.util.List;

import recap.carRental.core.utilities.results.DataResult;
import recap.carRental.core.utilities.results.Result;
import recap.carRental.entities.concretes.User;

public interface UserService {
	Result add(User user, String passwordAgain);
	DataResult<List<User>> getAll();
	DataResult<User> getById(int id);
	Result update(User user, String passwordAgain);
	Result delete(int id);
}
