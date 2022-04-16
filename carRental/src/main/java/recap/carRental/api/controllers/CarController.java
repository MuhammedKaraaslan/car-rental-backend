package recap.carRental.api.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import recap.carRental.business.abstracts.CarService;
import recap.carRental.core.utilities.results.DataResult;
import recap.carRental.core.utilities.results.Result;
import recap.carRental.entities.concretes.Car;
import recap.carRental.entities.dtos.CarWithDetailDto;

@RestController
@RequestMapping("/api/cars/")
@CrossOrigin
public class CarController {

	private CarService carService;

	@Autowired
	public CarController(CarService carService) {
		super();
		this.carService = carService;
	}
	
	@PostMapping("add")
	public Result add(@RequestBody Car car) {
		return this.carService.add(car);
	}
	
	@GetMapping("getById")
	public DataResult<Car> getById(@RequestParam int id){
		return this.carService.getById(id);
	}
	
	@GetMapping("getAll")
	public DataResult<List<Car>> getAll(){
		return this.carService.getAll();
	}
	
	@PostMapping("update")
	public Result update(@RequestBody Car car) {
		return this.carService.update(car);
	}
	
	@PostMapping("delete")
	public Result delete(@RequestParam int id) {
		return this.carService.delete(id);
	}
	
	@GetMapping("getByBrandId")
	public DataResult<List<CarWithDetailDto>> getByBrandId(int brandId){
		return this.carService.getByBrandId(brandId);
	}
	
	@GetMapping("getByColorId")
	public DataResult<List<CarWithDetailDto>> getByColorId(int colorId){
		return this.carService.getByColorId(colorId);
	}
	
}
