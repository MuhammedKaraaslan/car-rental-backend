package recap.carRental.business.abstracts;

import java.util.List;

import recap.carRental.core.utilities.results.DataResult;
import recap.carRental.core.utilities.results.Result;
import recap.carRental.entities.concretes.Car;
import recap.carRental.entities.dtos.CarWithDetailDto;

public interface CarService {
	Result add(Car car);
	DataResult<Car> getById(int id);
	DataResult<List<Car>> getAll();
	Result update(Car car);
	Result delete(int id);
	DataResult<List<CarWithDetailDto>> getByBrandId(int brandId);
	DataResult<List<CarWithDetailDto>> getByColorId(int colorId);
}
