package recap.carRental.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import recap.carRental.business.abstracts.BrandService;
import recap.carRental.core.utilities.results.DataResult;
import recap.carRental.core.utilities.results.ErrorResult;
import recap.carRental.core.utilities.results.Result;
import recap.carRental.core.utilities.results.SuccessDataResult;
import recap.carRental.core.utilities.results.SuccessResult;
import recap.carRental.dataAccess.abstracts.BrandDao;
import recap.carRental.entities.concretes.Brand;

@Service
public class BrandManager implements BrandService{

	private BrandDao brandDao;
	
	@Autowired
	public BrandManager(BrandDao brandDao) {
		super();
		this.brandDao = brandDao;
	}

	@Override
	public Result checkName(Brand brand) {
		if(brand.getName().isEmpty()) {
			return new ErrorResult("Brand name can not be empty");
		}
		return new SuccessResult();
	}

	@Override
	public Result add(Brand brand) {
		if(! checkName(brand).isSuccess()) {
			return new ErrorResult(checkName(brand).getMessage());
		}
		this.brandDao.save(brand);
		return new SuccessResult("Brand added successfully");
	}
	
	@Override
	public Result update(Brand brand) {
		if(! this.checkName(brand).isSuccess()) {
			return new ErrorResult(this.checkName(brand).getMessage());
		}
		this.brandDao.getById(brand.getId()).setName(brand.getName());
		this.brandDao.save(this.brandDao.getById(brand.getId()));
		return new SuccessResult("Brand updated");
	}

	@Override
	public Result delete(int id) {
		this.brandDao.delete(this.brandDao.getById(id));
		return new SuccessResult("Brand deleted");
	}

	@Override
	public DataResult<Brand> getById(int id) {
		return new SuccessDataResult<Brand>(this.brandDao.getById(id));
	}

	@Override
	public DataResult<List<Brand>> getAll() {
		return new SuccessDataResult<List<Brand>>(this.brandDao.findAll(), "Brands listed");
	}

}
