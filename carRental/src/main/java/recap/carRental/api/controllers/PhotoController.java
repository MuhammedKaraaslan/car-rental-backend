package recap.carRental.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import recap.carRental.business.abstracts.PhotoService;
import recap.carRental.core.utilities.results.DataResult;
import recap.carRental.core.utilities.results.Result;
import recap.carRental.entities.concretes.Photo;

@RestController
@RequestMapping("/api/photos/")
@CrossOrigin
public class PhotoController {
	
	private PhotoService photoService;
	
	@Autowired
	public PhotoController(PhotoService photoService) {
		super();
		this.photoService = photoService;
	}
	
	@PostMapping(value = "upload",
				 consumes = {MediaType.APPLICATION_JSON_VALUE,
						 	 MediaType.MULTIPART_FORM_DATA_VALUE})
	public Result upload(@ModelAttribute("photo") Photo photo, @RequestPart("multipartFile") MultipartFile multipartFile) {
		return this.photoService.upload(photo, multipartFile);
	}
	
	@DeleteMapping("delete")
	public Result delete(@RequestParam int id) {
		return this.photoService.delete(id);
	}
	
	@GetMapping("getByCarId")
	public DataResult<List<Photo>> getByCarId(@RequestParam int carId) {
		return this.photoService.getByCarId(carId);
	}
	
	@GetMapping("getAll")
	public DataResult<List<Photo>> getAll(){
		return this.photoService.getAll();
	}
	
}
