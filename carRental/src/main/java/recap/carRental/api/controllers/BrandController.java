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

import recap.carRental.business.abstracts.BrandService;
import recap.carRental.core.utilities.results.DataResult;
import recap.carRental.core.utilities.results.Result;
import recap.carRental.entities.concretes.Brand;

@RestController
@RequestMapping("/api/brands/")
@CrossOrigin
public class BrandController {
	
	private BrandService brandService;

	@Autowired 
	public BrandController(BrandService brandService) {
		super();
		this.brandService = brandService;
	}
	
	@PostMapping("add")
	public Result add(@RequestBody Brand brand) {
		return this.brandService.add(brand);
	}
	
	@PostMapping("update")
	public Result update(@RequestBody Brand brand) {
		return this.brandService.update(brand);
	}
	
	@PostMapping("delete")
	public Result delete(@RequestParam int id) {
		return this.brandService.delete(id);
	}
	
	@GetMapping("getById")
	public DataResult<Brand> getById(@RequestParam int id){
		return this.brandService.getById(id);
	}
	
	@GetMapping("getAll")
	public DataResult<List<Brand>> getAll(){
		return this.brandService.getAll();
	}

	
}
