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

import recap.carRental.business.abstracts.RentalService;
import recap.carRental.core.utilities.results.DataResult;
import recap.carRental.core.utilities.results.Result;
import recap.carRental.entities.concretes.Rental;

@RestController
@RequestMapping("/api/rentals/")
@CrossOrigin
public class RentalController {

	private RentalService rentalService;
	
	@Autowired	
	public RentalController(RentalService rentalService) {
		super();
		this.rentalService = rentalService;
	}

	@PostMapping("rent")
	public Result add(@RequestBody Rental rental) {
		return this.rentalService.add(rental);
	}
	
	@PostMapping("returnCar")
	public Result returnCar(@RequestParam int id) {
		return this.rentalService.returnCar(id);
	}
	
	@GetMapping("getById")
	public DataResult<Rental> getById(@RequestParam int id){
		return this.rentalService.getById(id);
	}
	
	@GetMapping("getAll")
	public DataResult<List<Rental>> getAll(){
		return this.rentalService.getAll();
	}
	
	@PostMapping("delete")
	public Result delete(@RequestParam int id) {
		return this.rentalService.delete(id);
	}
}