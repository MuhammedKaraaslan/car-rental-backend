package recap.carRental.business.abstracts;

import java.util.List;

import recap.carRental.core.utilities.results.DataResult;
import recap.carRental.core.utilities.results.Result;
import recap.carRental.entities.concretes.Color;

public interface ColorService {
	Result checkName(Color color);
	Result add(Color color);
	Result update(Color color);
	Result delete(int id);
	DataResult<Color> getById(int id);
	DataResult<List<Color>> getAll();
}
