package com.example.myapp.controller;
import com.example.myapp.entity.Response;
import com.example.myapp.entity.User;
import com.example.myapp.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "User CRUD API")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {


	@Autowired
	UserService userService;

	@ApiOperation(value = "Find all users")
	@GetMapping
	public Response<List<User>> getAllUsers() {
		return userService.getAllUserDtos();
	}
	@ApiOperation(value = "Get an User by USER_ID", produces = "application/json")
	@GetMapping("/{userid}")
	public Response<User> getUserDtoById(@PathVariable("userid") long userid)
	{
	return userService.getUserDtoById(userid);
	}

	@ApiOperation(value = "Create an User", produces = "application/json")
	@PostMapping
	public Response<User> createUser(@RequestBody @Valid User user)
	{
	  return userService.createUser(user);

	}

	@ApiOperation(value = "Update an User", produces = "application/json")
	@PutMapping
	public Response<User> updateUserDto(@RequestBody User user)
	{
		return userService.updateUser(user);
		//return user;
	}

	@ApiOperation(value = "Delete an User", produces = "application/json")
	@DeleteMapping("/{userid}")
	public String deleteUser(@PathVariable("userid") long userid) {
		return userService.delete(userid);
	}
}
