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

import recap.carRental.business.abstracts.CustomerService;
import recap.carRental.core.utilities.results.DataResult;
import recap.carRental.core.utilities.results.Result;
import recap.carRental.entities.concretes.Customer;

@RestController
@RequestMapping("/api/customers/")
@CrossOrigin
public class CustomerController {
	
	private CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	@PostMapping("add")
	public Result add(@RequestBody Customer customer, @RequestParam String passwordAgain) {
		return this.customerService.add(customer, passwordAgain);
	}
	
	@PostMapping("update")
	public Result update(@RequestBody Customer customer, @RequestParam String passwordAgain) {
		return this.customerService.update(customer, passwordAgain);
	}
	
	
	@PostMapping("delete")
	public Result delete(@RequestParam int id) {
		return this.customerService.delete(id);
	}
	
	@GetMapping("getById")
	public DataResult<Customer> getById(int id){
		return this.customerService.getById(id);
	}
	
	@GetMapping("getAll")
	public DataResult<List<Customer>> getAll(){
		return this.customerService.getAll();
	}
}