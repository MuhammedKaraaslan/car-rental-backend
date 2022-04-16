package recap.carRental.business.abstracts;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import recap.carRental.core.utilities.results.DataResult;
import recap.carRental.core.utilities.results.Result;
import recap.carRental.entities.concretes.Photo;

public interface PhotoService {
	public Result checkPhotoCount(Photo photo);
	public Result checkFileType(MultipartFile multipartFile);
	public Result checkConditions(Photo photo, MultipartFile multipartFile);
	public Result upload(Photo photo, MultipartFile multipartFile);
	public Result delete(int id);
	public DataResult<List<Photo>> getByCarId(int carId);
	public DataResult<List<Photo>> getAll();
}
