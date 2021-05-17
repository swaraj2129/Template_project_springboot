package com.example.myapp.controller;

import com.example.myapp.entity.Response;
import com.example.myapp.entity.User;
import com.example.myapp.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    private UserService userService;

    @ApiOperation(value = "Find all users")
    @ApiResponses(value =
            {@ApiResponse(code = 200, message = "Successfully retrieved the list of users"),
                    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
                    @ApiResponse(code = 401, message = "You are not authorized to perform this action on the resource")})
    @GetMapping
    public Response<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    @ApiOperation(value = "Get an User by USER_ID", produces = "application/json")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved the user details"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 401, message = "You are not authorized to perform this action on the resource")})

	@GetMapping("/{userid}")
    public Response<User> getUserDtoById(@PathVariable("userid") long userid) {
        return userService.getUserDtoById(userid);
    }

    @ApiOperation(value = "Create an User", produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "User created successfully"),
			@ApiResponse(code = 401, message = "You are not authorized to perform this action on the resource") })
    @PostMapping
    public Response<User> createUser(@RequestBody @Valid User user) {
        return userService.createUser(user);

    }


    @ApiOperation(value = "Update an User", produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User updated successfully"),
			@ApiResponse(code = 401, message = "You are not authorized to perform this action on the resource"),
			@ApiResponse(code = 404, message = "The resource you were trying to delete is not found") })
    @PutMapping
    public Response<User> updateUserDto(@RequestBody User user) {
        return userService.updateUser(user);

    }

    @ApiOperation(value = "Delete an User", produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User deleted successfully"),
			@ApiResponse(code = 401, message = "You are not authorized to perform this action on the resource") })
	@DeleteMapping("/{userid}")
    public String deleteUser(@PathVariable("userid") long userid) {
        return userService.delete(userid);
    }
}
