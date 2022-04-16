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

import recap.carRental.business.abstracts.ColorService;
import recap.carRental.core.utilities.results.DataResult;
import recap.carRental.core.utilities.results.Result;
import recap.carRental.entities.concretes.Color;

@RestController
@RequestMapping("/api/colors/")
@CrossOrigin
public class ColorController {
	
	private ColorService colorService;

	@Autowired
	public ColorController(ColorService colorService) {
		super();
		this.colorService = colorService;
	}
		
	@PostMapping("add")
	public Result add(@RequestBody Color color) {
		return this.colorService.add(color);
	}
	
	@PostMapping("update")
	public Result update(@RequestBody Color color) {
		return this.colorService.update(color);
	}
	
	@PostMapping("delete")
	public Result delete(@RequestParam int id) {
		return this.colorService.delete(id);
	}
	
	@GetMapping("getById")
	public DataResult<Color> getById(@RequestParam int id){
		return this.colorService.getById(id);
	}
	
	@GetMapping("getAll")
	public DataResult<List<Color>> getAll(){
		return this.colorService.getAll();
	}
}
