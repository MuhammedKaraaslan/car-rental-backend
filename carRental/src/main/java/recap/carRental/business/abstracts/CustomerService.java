package recap.carRental.business.abstracts;

import java.util.List;

import recap.carRental.core.utilities.results.DataResult;
import recap.carRental.core.utilities.results.Result;
import recap.carRental.entities.concretes.Customer;

public interface CustomerService {

	Result checkUserConditions(Customer customer, String passwordAgain);
	Result checkCompanyName(Customer customer);
	Result add(Customer customer, String passwordAgain);
	Result update(Customer customer, String passwordAgain);
	Result delete(int id);
	DataResult<Customer> getById(int id);
	DataResult<List<Customer>> getAll();
}
