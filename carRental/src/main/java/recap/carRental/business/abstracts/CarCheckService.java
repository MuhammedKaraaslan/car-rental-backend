package recap.carRental.business.abstracts;

import recap.carRental.core.utilities.results.Result;
import recap.carRental.entities.concretes.Car;

public interface CarCheckService {
	Result checkBrand(Car car);
	Result checkColor(Car car);
	Result checkModelYear(Car car);
	Result checkDailyPrice(Car car);
	Result checkDescription(Car car);
	Result checkAll(Car car);
}
