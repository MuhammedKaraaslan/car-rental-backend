package recap.carRental.core.utilities.abstracts;

import recap.carRental.core.utilities.results.Result;
import recap.carRental.entities.concretes.User;

public interface SignUpCheckService {
	Result checkEmailFormat(User user);
	Result checkUniqueEmail(User user);
	Result checkPassword(User user, String passwordAgain);
}
