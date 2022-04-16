package recap.carRental.business.concretes;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import recap.carRental.business.abstracts.PhotoService;
import recap.carRental.core.adapters.abstracts.CloudinaryService;
import recap.carRental.core.utilities.results.DataResult;
import recap.carRental.core.utilities.results.ErrorResult;
import recap.carRental.core.utilities.results.Result;
import recap.carRental.core.utilities.results.SuccessDataResult;
import recap.carRental.core.utilities.results.SuccessResult;
import recap.carRental.dataAccess.abstracts.PhotoDao;
import recap.carRental.entities.concretes.Photo;

@Service
@Transactional
public class PhotoManager implements PhotoService{

	private PhotoDao photoDao;
	private CloudinaryService cloudinaryService;
	
	@Autowired	
	public PhotoManager(PhotoDao photoDao, CloudinaryService cloudinaryService) {
		super();
		this.photoDao = photoDao;
		this.cloudinaryService = cloudinaryService;
	}
	
	@Override
	public Result checkPhotoCount(Photo photo) {
		if(this.photoDao.getByCarId(photo.getCar().getId()).size() > 5) {
			return new ErrorResult("There is already 5 photos");
		}
		return new SuccessResult();
	}
	
	@Override
	public Result checkFileType(MultipartFile multipartFile) {
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(multipartFile.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(bufferedImage == null) {
			return new ErrorResult("Image not valid");
		}
		return new SuccessResult();
	}
	
	@Override
	public Result checkConditions(Photo photo, MultipartFile multipartFile) {
		if(! checkPhotoCount(photo).isSuccess()) {
			return new ErrorResult(checkPhotoCount(photo).getMessage());
		}
		else if(! checkFileType(multipartFile).isSuccess()) {
			return new ErrorResult(checkFileType(multipartFile).getMessage());
		}
		return new SuccessResult();
	}

	@Override
	public Result upload(Photo photo, MultipartFile multipartFile) {
		if(! checkConditions(photo, multipartFile).isSuccess()) {
			return new ErrorResult(checkConditions(photo, multipartFile).getMessage());
		}
		photo.setPhotoUrl(this.cloudinaryService.upload(multipartFile).get("url").toString()); // For both upload to cloudinary and get the url
		photo.setDate(new Date());
		this.photoDao.save(photo);
		return new SuccessResult("Photo uploaded");
	}
	


	@Override
	public Result delete(int id) {
		this.cloudinaryService.delete(String.valueOf(id));
		this.photoDao.deleteById(id);		
		return new SuccessResult("Photo deleted");
	}

	@Override
	public DataResult<List<Photo>> getByCarId(int carId) {
		return new SuccessDataResult<List<Photo>>(this.photoDao.getByCarId(carId));
	}
	
	@Override
	public DataResult<List<Photo>> getAll(){
		return new SuccessDataResult<List<Photo>>(this.photoDao.findAll(), "Photos listed");
	}

}
