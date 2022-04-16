package recap.carRental.business.concretes;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import recap.carRental.business.abstracts.RentalService;
import recap.carRental.core.utilities.results.DataResult;
import recap.carRental.core.utilities.results.ErrorResult;
import recap.carRental.core.utilities.results.Result;
import recap.carRental.core.utilities.results.SuccessDataResult;
import recap.carRental.core.utilities.results.SuccessResult;
import recap.carRental.dataAccess.abstracts.RentalDao;
import recap.carRental.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService{
	
	private RentalDao rentalDao;
	
	@Autowired
	public RentalManager(RentalDao rentalDao) {
		super();
		this.rentalDao = rentalDao;
	}

	@Override
	public Result checkCar(Rental rental) {
		if(rental.getCar().getId() <= 0) {
			return new ErrorResult("Invalid car");
		}
		return new SuccessResult();
	}

	@Override
	public Result checkCustomer(Rental rental) {
		if(rental.getCustomer().getId() <= 0) {
			return new ErrorResult("Invalid customer");
		}
		return new SuccessResult();
	}

	@Override
	public Result checkRentDate(Rental rental) {
		if(rental.getRentDate() == null) {
			return new ErrorResult("Rent date can not be empty");
		}
		return new SuccessResult();
	}
	
	@Override
	public Result checkReturn(Rental rental) {
		if(this.rentalDao.getByCarId(rental.getCar().getId()).size() > 0) {
			if(this.rentalDao.getByCarId(rental.getCar().getId()).get(this.rentalDao.getByCarId(rental.getCar().getId()).size()-1).getReturnDate() == null) {
				return new ErrorResult("This car is not available now");
			}
			return new SuccessResult();
		}
		return new SuccessResult();
	}
	
	@Override
	public Result checkAll(Rental rental) { 
		if(! checkCar(rental).isSuccess()) {
			return new ErrorResult(checkCar(rental).getMessage());
		}
		else if(! checkCustomer(rental).isSuccess()) {
			return new ErrorResult(checkCustomer(rental).getMessage());
		}
		else if(! checkRentDate(rental).isSuccess()) {
			return new ErrorResult(checkRentDate(rental).getMessage());
		}
		else if(! checkReturn(rental).isSuccess()) {
			return new ErrorResult(checkReturn(rental).getMessage());
		}
		return new SuccessResult();
	}

	@Override
	public Result add(Rental rental) {
		if(! checkAll(rental).isSuccess()) {
			return new ErrorResult(checkAll(rental).getMessage());
		}
		this.rentalDao.save(rental);
		return new SuccessResult("Rental added");
	}

	@Override
	public Result returnCar(int id) {
		Date date = new Date();
		this.rentalDao.findById(id).get().setReturnDate(date);
		this.rentalDao.save(this.rentalDao.getById(id));
		return new SuccessResult("Return date set");
	}

	@Override
	public DataResult<Rental> getById(int id) {
		return new SuccessDataResult<Rental>(this.rentalDao.findById(id).get(), "Rental listed");
	}

	@Override
	public DataResult<List<Rental>> getAll() {
		return new SuccessDataResult<List<Rental>>(this.rentalDao.findAll(), "Rentals listed");
	}

	@Override
	public Result delete(int id) {
		this.rentalDao.delete(this.rentalDao.getById(id));
		return new SuccessResult("Rental deleted");
	}

}
