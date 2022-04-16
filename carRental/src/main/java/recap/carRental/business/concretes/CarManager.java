package recap.carRental.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import recap.carRental.business.abstracts.CarCheckService;
import recap.carRental.business.abstracts.CarService;
import recap.carRental.core.utilities.results.DataResult;
import recap.carRental.core.utilities.results.ErrorResult;
import recap.carRental.core.utilities.results.Result;
import recap.carRental.core.utilities.results.SuccessDataResult;
import recap.carRental.core.utilities.results.SuccessResult;
import recap.carRental.dataAccess.abstracts.CarDao;
import recap.carRental.entities.concretes.Car;
import recap.carRental.entities.dtos.CarWithDetailDto;

@Service
public class CarManager implements CarService{

	private CarCheckService carCheckService;
	private CarDao carDao;
	
	@Autowired
	public CarManager(CarCheckService carCheckService, CarDao carDao) {
		super();
		this.carCheckService = carCheckService;
		this.carDao = carDao;
	}

	@Override
	public Result add(Car car) {
		if(! (this.carCheckService.checkAll(car).isSuccess())) {
			return new ErrorResult(this.carCheckService.checkAll(car).getMessage());
		}
		this.carDao.save(car);
		return new SuccessResult("Car added successfully");
	}

	@Override
	public DataResult<Car> getById(int id) {
		return new SuccessDataResult<Car>(this.carDao.findById(id).get(), "Car listed");
	}

	@Override
	public DataResult<List<Car>> getAll() {
		return new SuccessDataResult<List<Car>>(this.carDao.findAll(), "Cars listed");
	}
	
	@Override
	public Result update(Car car) {
		if(! this.carCheckService.checkAll(car).isSuccess()) {
			return new ErrorResult(this.carCheckService.checkAll(car).getMessage());
		}
		this.carDao.getById(car.getId()).setBrand(car.getBrand());
		this.carDao.getById(car.getId()).setColor(car.getColor());
		this.carDao.getById(car.getId()).setModelYear(car.getModelYear());
		this.carDao.getById(car.getId()).setDailyPrice(car.getDailyPrice());
		this.carDao.getById(car.getId()).setDescription(car.getDescription());
		this.carDao.save(this.carDao.getById(car.getId()));
		return new SuccessResult("Car updated");
	}

	@Override
	public Result delete(int id) {
		this.carDao.delete(this.carDao.getById(id));
		return new SuccessResult("Car deleted successfully");
	}

	@Override
	public DataResult<List<CarWithDetailDto>> getByBrandId(int brandId) {
		return new SuccessDataResult<List<CarWithDetailDto>>(this.carDao.getByBrandId(brandId), "Car listed");
	}
	
	@Override
	public DataResult<List<CarWithDetailDto>> getByColorId(int colorId) {
		return new SuccessDataResult<List<CarWithDetailDto>>(this.carDao.getByColorId(colorId), "Car listed");
	}

	
}
