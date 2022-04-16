package recap.carRental.business.abstracts;

import recap.carRental.core.utilities.results.Result;
import recap.carRental.entities.concretes.User;

public interface UserCheckService {
	Result checkFirstName(User user);
	Result checkLastName(User user);
	Result checkEmailAdress(User user);
	Result checkPassword(User user, String passwordAgain);
	Result checkAll(User user, String passwordAgain);
}
