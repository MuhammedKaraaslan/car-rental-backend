package recap.carRental.business.abstracts;

import java.util.List;

import recap.carRental.core.utilities.results.DataResult;
import recap.carRental.core.utilities.results.Result;
import recap.carRental.entities.concretes.Brand;

public interface BrandService {
	Result checkName(Brand brand);
	Result add(Brand brand);
	Result update(Brand brand);
	Result delete(int id);
	DataResult<Brand> getById(int id);
	DataResult<List<Brand>> getAll();
}
