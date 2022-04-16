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

import recap.carRental.business.abstracts.UserService;
import recap.carRental.core.utilities.results.DataResult;
import recap.carRental.core.utilities.results.Result;
import recap.carRental.entities.concretes.User;

@RestController
@RequestMapping("/api/users/")
@CrossOrigin
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("add")
	public Result add(@RequestBody User user, @RequestParam String passwordAgain) {
		return this.userService.add(user, passwordAgain);
	}
	
	@GetMapping("getAll")
	public DataResult<List<User>> getAll(){
		return this.userService.getAll();
	}
	
	@GetMapping("getById")
	public DataResult<User> getById(@RequestParam int id){
		return this.userService.getById(id);
	}
	
	@PostMapping("update")
	public Result update(User user, String passwordAgain) {
		return this.userService.update(user, passwordAgain);
	}
	
	@PostMapping("delete")
	public Result delete(int id) {
		return this.userService.delete(id);
	}
}