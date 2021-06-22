package com.example.myapp.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.myapp.entity.Course;
import com.example.myapp.entity.Response;
import com.example.myapp.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.myapp.entity.User;

import javax.servlet.http.HttpServletResponse;

@Service
public class UserService {
	
	@Autowired  
	private UserRepository userRepository;
	
	
	public Response<List<User>> getAllUsers()
	{  
	List<User> users = new ArrayList<User>();
	userRepository.findAll().forEach(user -> users.add(user));
		if(users.isEmpty()){
			// throw the exception
			return new Response<>(users, HttpStatus.NOT_FOUND.value());
		}
	return new Response<>(users, HttpStatus.OK.value());
	}
	
	public Response<User> getUserById(long id)
	{
		Optional<User> theUser = userRepository.findById(id);
		if(!theUser.isPresent()){
			throw new UserNotFoundException("User id not found - " + id);

		}
		return new Response<>(theUser.get(),HttpStatus.OK.value());
	}
	
	public Response<User>createUser(User user) throws IOException {
		userRepository.save(user);
		return new Response<>(user,HttpStatus.CREATED.value());

	}
	public Response<User> delete(long id)
	{
		Optional<User> theUser = userRepository.findById(id);
		if(!theUser.isPresent()){
			throw new UserNotFoundException("User with id - " + id + " not found");
		}

		userRepository.delete(theUser.get());
		return new Response<>(theUser.get(),HttpStatus.OK.value());

	}  
	//updating a record  
	public Response<User> updateUser(User theUser)

	{
		Optional<User> user = userRepository.findById(theUser.getUserId());
		if(!user.isPresent()){
			throw new UserNotFoundException("User with id - " + theUser.getUserId() + " not found");
		}
		//User.get().setAuthor(theUser.getAuthor());
		//User.get().setTitle(theUser.getTitle());
		userRepository.save(theUser);
		return new Response<>(user.get(), HttpStatus.OK.value());
	}

}
