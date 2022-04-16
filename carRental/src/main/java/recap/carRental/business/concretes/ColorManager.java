package recap.carRental.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import recap.carRental.business.abstracts.ColorService;
import recap.carRental.core.utilities.results.DataResult;
import recap.carRental.core.utilities.results.ErrorResult;
import recap.carRental.core.utilities.results.Result;
import recap.carRental.core.utilities.results.SuccessDataResult;
import recap.carRental.core.utilities.results.SuccessResult;
import recap.carRental.dataAccess.abstracts.ColorDao;
import recap.carRental.entities.concretes.Color;

@Service
public class ColorManager implements ColorService{

	private ColorDao colorDao;
	
	@Autowired
	public ColorManager(ColorDao colorDao) {
		super();
		this.colorDao = colorDao;
	}

	@Override
	public Result checkName(Color color) {
		if(color.getName().isEmpty()) {
			return new ErrorResult("Color name can not be empty");
		}
		return new SuccessResult();
	}

	@Override
	public Result add(Color color) {
		if(! checkName(color).isSuccess()) {
			return new ErrorResult(checkName(color).getMessage());
		}
		this.colorDao.save(color);
		return new SuccessResult("Color added successfully");
	}

	@Override
	public DataResult<List<Color>> getAll() {
		return new SuccessDataResult<List<Color>>(this.colorDao.findAll(), "Colors listed");
	}

	@Override
	public Result update(Color color) {
		if(! this.checkName(color).isSuccess()) {
			return new ErrorResult(this.checkName(color).getMessage());
		}
		this.colorDao.getById(color.getId()).setName(color.getName());
		this.colorDao.save(this.colorDao.getById(color.getId()));
		return new SuccessResult("Color updated");
	}

	@Override
	public Result delete(int id) {
		this.colorDao.delete(this.colorDao.getById(id));
		return new SuccessResult("Color deleted");
	}

	@Override
	public DataResult<Color> getById(int id) {
		return new SuccessDataResult<Color>(this.colorDao.getById(id));
	}

}
