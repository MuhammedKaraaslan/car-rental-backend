package recap.carRental.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import recap.carRental.business.abstracts.UserCheckService;
import recap.carRental.business.abstracts.UserService;
import recap.carRental.core.utilities.results.DataResult;
import recap.carRental.core.utilities.results.ErrorResult;
import recap.carRental.core.utilities.results.Result;
import recap.carRental.core.utilities.results.SuccessDataResult;
import recap.carRental.core.utilities.results.SuccessResult;
import recap.carRental.dataAccess.abstracts.UserDao;
import recap.carRental.entities.concretes.User;

@Service
public class UserManager implements UserService{

	private UserDao userDao;
	private UserCheckService userCheckService;
	
	@Autowired
	public UserManager(UserDao userDao, UserCheckService userCheckService) {
		super();
		this.userDao = userDao;
		this.userCheckService = userCheckService;
	}

	@Override
	public Result add(User user, String passwordAgain) {
		if(! this.userCheckService.checkAll(user, passwordAgain).isSuccess()) {
			return new ErrorResult(this.userCheckService.checkAll(user, passwordAgain).getMessage());
		}
		this.userDao.save(user);
		return new SuccessResult("User added");
	}

	@Override
	public DataResult<List<User>> getAll() {
		return new SuccessDataResult<List<User>>(this.userDao.findAll(), "Users listed");
	}

	@Override
	public DataResult<User> getById(int id) {
		return new SuccessDataResult<User>(this.userDao.findById(id).get(), "User listed");
	}

	@Override
	public Result update(User user, String passwordAgain) {
		if(! this.userCheckService.checkAll(user, passwordAgain).isSuccess()) {
			return new ErrorResult(this.userCheckService.checkAll(user, passwordAgain).getMessage());
		}
		this.userDao.getById(user.getId()).setFirstName(user.getFirstName());
		this.userDao.getById(user.getId()).setLastName(user.getLastName());
		this.userDao.getById(user.getId()).setEmail(user.getEmail());
		this.userDao.save(this.userDao.getById(user.getId()));
		return new SuccessResult("Car updated");
	}

	@Override
	public Result delete(int id) {
		this.userDao.delete(this.userDao.getById(id));
		return new SuccessResult("User deleted successfully");
	}

}
