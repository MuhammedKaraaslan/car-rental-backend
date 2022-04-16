package recap.carRental.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import recap.carRental.business.abstracts.CarCheckService;
import recap.carRental.core.utilities.results.ErrorResult;
import recap.carRental.core.utilities.results.Result;
import recap.carRental.core.utilities.results.SuccessResult;
import recap.carRental.dataAccess.abstracts.BrandDao;
import recap.carRental.dataAccess.abstracts.ColorDao;
import recap.carRental.entities.concretes.Car;


@Service
public class CarCheckManager implements CarCheckService{
	
	private BrandDao brandDao;
	private ColorDao colorDao;
	
	@Autowired
	public CarCheckManager(BrandDao brandDao, ColorDao colorDao) {
		super();
		this.brandDao = brandDao;
		this.colorDao = colorDao;
	}

	@Override
	public Result checkBrand(Car car) {
		if(this.brandDao.getById(car.getBrand().getId()) == null) {
			return new ErrorResult("Invalid brand");
		}
		return new SuccessResult();
	}

	@Override
	public Result checkColor(Car car) {
		if(this.colorDao.getById(car.getBrand().getId()) == null) {
			return new ErrorResult("Invalid brand");
		}
		return new SuccessResult();
	}

	@Override
	public Result checkModelYear(Car car) {
		if(car.getModelYear() < 0) {
			return new ErrorResult("Invalid model year");
		}
		return new SuccessResult();
	}

	@Override
	public Result checkDailyPrice(Car car) {
		if(car.getDailyPrice() < 0) {
			return new ErrorResult("Invalid daily price");
		}
		return new SuccessResult();
	}

	@Override
	public Result checkDescription(Car car) {
		if(car.getDescription() == null) {
			return new ErrorResult("Description can not be empty");
		}
		return new SuccessResult();
	}

	@Override
	public Result checkAll(Car car) {
		if(! checkBrand(car).isSuccess()) {
			return new ErrorResult(this.checkBrand(car).getMessage());
		}
		else if(! checkColor(car).isSuccess()) {
			return new ErrorResult(this.checkColor(car).getMessage());
		}
		else if(! checkModelYear(car).isSuccess()) {
			return new ErrorResult(this.checkModelYear(car).getMessage());
		}
		else if(! checkDailyPrice(car).isSuccess()) {
			return new ErrorResult(this.checkDailyPrice(car).getMessage());
		}
		else if(! checkDescription(car).isSuccess()) {
			return new ErrorResult(this.checkDescription(car).getMessage());
		}
		return new SuccessResult();
	}

}
