package recap.carRental.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import recap.carRental.business.abstracts.UserCheckService;
import recap.carRental.core.utilities.abstracts.SignUpCheckService;
import recap.carRental.core.utilities.results.ErrorResult;
import recap.carRental.core.utilities.results.Result;
import recap.carRental.core.utilities.results.SuccessResult;
import recap.carRental.entities.concretes.User;

@Service
public class UserCheckManager implements UserCheckService{
	
	private SignUpCheckService signUpCheckService;
	
	@Autowired
	public UserCheckManager(SignUpCheckService signUpCheckService) {
		super();
		this.signUpCheckService = signUpCheckService;
	}

	@Override
	public Result checkFirstName(User user) {
		if(user.getFirstName().isEmpty()) {
			return new ErrorResult("First name can not be empty");
		}
		else if(user.getFirstName().length() < 2) {
			return new ErrorResult("First name can not be less than 2 characters");
		}
		return new SuccessResult();
	}

	@Override
	public Result checkLastName(User user) {
		if(user.getLastName().isEmpty()) {
			return new ErrorResult("Last name can not be empty");
		}
		else if(user.getLastName().length() < 2) {
			return new ErrorResult("Last name can not be less than 2 characters");
		}
		return new SuccessResult();
	}

	@Override
	public Result checkEmailAdress(User user) {
		if(! this.signUpCheckService.checkEmailFormat(user).isSuccess()) {
			return new ErrorResult("Wrong email adress");
		}
		else if(! this.signUpCheckService.checkUniqueEmail(user).isSuccess()) {
			return new ErrorResult("That email adress is used");
		}
		
		return new SuccessResult();
	}

	@Override
	public Result checkPassword(User user, String passwordAgain) {
		if(! (this.signUpCheckService.checkPassword(user, passwordAgain).isSuccess())) {
			return new ErrorResult(this.signUpCheckService.checkPassword(user, passwordAgain).getMessage());
		}
		return new SuccessResult();
	}

	@Override
	public Result checkAll(User user, String passwordAgain) {
		if(! checkFirstName(user).isSuccess()) {
			return new ErrorResult(checkFirstName(user).getMessage());
		}
		else if(! checkLastName(user).isSuccess()) {
			return new ErrorResult(checkLastName(user).getMessage());
		}
		else if(! checkEmailAdress(user).isSuccess()) {
			return new ErrorResult(checkEmailAdress(user).getMessage());
		}
		else if(! checkPassword(user, passwordAgain).isSuccess()) {
			return new ErrorResult(checkPassword(user, passwordAgain).getMessage());
		}
		return new SuccessResult();
	}

}
