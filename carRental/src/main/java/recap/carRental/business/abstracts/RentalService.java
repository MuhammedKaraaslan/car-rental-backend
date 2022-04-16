package recap.carRental.business.abstracts;

import java.util.List;

import recap.carRental.core.utilities.results.DataResult;
import recap.carRental.core.utilities.results.Result;
import recap.carRental.entities.concretes.Rental;

public interface RentalService {
	Result checkCar(Rental rental);
	Result checkCustomer(Rental rental);
	Result checkRentDate(Rental rental);
	Result checkReturn(Rental rental);
	Result checkAll(Rental rental);
	Result add(Rental rental);
	Result returnCar(int id);
	DataResult<Rental> getById(int id);
	DataResult<List<Rental>> getAll();
	Result delete(int id);
}
