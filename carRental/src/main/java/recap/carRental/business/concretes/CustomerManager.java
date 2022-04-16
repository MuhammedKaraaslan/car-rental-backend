package recap.carRental.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import recap.carRental.business.abstracts.CustomerService;
import recap.carRental.business.abstracts.UserCheckService;
import recap.carRental.business.abstracts.UserService;
import recap.carRental.core.utilities.results.DataResult;
import recap.carRental.core.utilities.results.ErrorResult;
import recap.carRental.core.utilities.results.Result;
import recap.carRental.core.utilities.results.SuccessDataResult;
import recap.carRental.core.utilities.results.SuccessResult;
import recap.carRental.dataAccess.abstracts.CustomerDao;
import recap.carRental.entities.concretes.Customer;

@Service
public class CustomerManager implements CustomerService{

	private UserCheckService userCheckService;
	private CustomerDao customerDao;
	private UserService userService;
	@Autowired
	public CustomerManager(UserCheckService userCheckService, CustomerDao customerDao, UserService userService) {
		super();
		this.userCheckService = userCheckService;
		this.customerDao = customerDao;
		this.userService = userService;
	}

	@Override
	public Result checkUserConditions(Customer customer, String passwordAgain) {
		if(! this.userCheckService.checkAll(customer, passwordAgain).isSuccess()) {
			return new ErrorResult(this.userCheckService.checkAll(customer, passwordAgain).getMessage());
		}
		return new SuccessResult();
	}
	
	@Override
	public Result checkCompanyName(Customer customer) {
		if(customer.getCompanyName().isEmpty()) {
			return new ErrorResult("Company name can not be empty");
		}
		else if(customer.getCompanyName().isBlank()) {
			return new ErrorResult("Company name can not be blank");
		}
		else if(customer.getCompanyName().length() < 2) {
			return new ErrorResult("Company name can not less then 2");
		} 
		return new SuccessResult();
	}

	@Override
	public Result add(Customer customer, String passwordAgain) {
		if(! checkUserConditions(customer, passwordAgain).isSuccess()) {
			return new ErrorResult(checkUserConditions(customer, passwordAgain).getMessage());
		}
		else if(! checkCompanyName(customer).isSuccess()) {
			return new ErrorResult(checkCompanyName(customer).getMessage());
		}
		this.customerDao.save(customer);
		return new SuccessResult("Customer added");
	}

	@Override
	public Result update(Customer customer, String passwordAgain) {
		if(! checkUserConditions(customer, passwordAgain).isSuccess()) {
			return new ErrorResult(checkUserConditions(customer, passwordAgain).getMessage());
		}
		this.userService.update(customer, passwordAgain);
		this.customerDao.getById(customer.getId()).setCompanyName(customer.getCompanyName());
		this.customerDao.save(this.customerDao.getById(customer.getId()));
		return new SuccessResult("Customer updated");
	}

	@Override
	public Result delete(int id) {
		this.customerDao.delete(this.customerDao.getById(id));
		return new SuccessResult("Customer deleted successfully");
	}

	@Override
	public DataResult<Customer> getById(int id) {
		return new SuccessDataResult<Customer>(this.customerDao.getById(id), "Customer listed");
	}

	@Override
	public DataResult<List<Customer>> getAll() {
		return new SuccessDataResult<List<Customer>>(this.customerDao.findAll(), "Customers listed");
	}



}
