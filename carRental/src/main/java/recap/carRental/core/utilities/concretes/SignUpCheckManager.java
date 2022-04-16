package recap.carRental.core.utilities.concretes;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import recap.carRental.core.utilities.abstracts.SignUpCheckService;
import recap.carRental.core.utilities.results.ErrorResult;
import recap.carRental.core.utilities.results.Result;
import recap.carRental.core.utilities.results.SuccessResult;
import recap.carRental.dataAccess.abstracts.UserDao;
import recap.carRental.entities.concretes.User;

@Service
public class SignUpCheckManager implements SignUpCheckService{
	
	private UserDao userDao;
	
	@Autowired
	public SignUpCheckManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public Result checkEmailFormat(User user) {
		String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+.(com|org|net|edu|gov|mil|biz|info|mobi)(.[A-Z]{2})?$";
		Pattern pattern = Pattern.compile(regex,
		Pattern.CASE_INSENSITIVE);
		if(pattern.matcher(user.getEmail()).find()) {
			return new SuccessResult();
		}
		else {
			return new ErrorResult("Wrong email format");
		}
	}
		
	@Override
	public Result checkUniqueEmail(User user) {
		for (User userFromDao : this.userDao.findAll()) {
			if(userFromDao.getEmail().equals(user.getEmail())) {
				return new ErrorResult("This email adress is used");
			}
		}
		return new SuccessResult();
	}
	
	@Override
	public Result checkPassword(User user, String passwordAgain) {
		if(user.getPassword().length() < 2 || passwordAgain.length() < 2) {
			return new ErrorResult("Password can not be less than 2 characters");
		}
		else if(! user.getPassword().equals(passwordAgain)) {
			return new ErrorResult("Passwords should be the same");
		}
		return new SuccessResult();
	}
}
