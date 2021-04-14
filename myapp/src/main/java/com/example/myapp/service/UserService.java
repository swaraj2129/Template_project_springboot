package com.example.myapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myapp.dto.UserDto;

@Service
public class UserService {
	
	@Autowired  
	UserRepository userRepository;  
	
	
	public List<UserDto> getAllUserDtos()   
	{  
	List<UserDto> users = new ArrayList<UserDto>();  
	userRepository.findAll().forEach(userdto1 -> users.add(userdto1));  
	return users;  
	}
	
	public UserDto getUserDtoById(long id)   
	{  
	return userRepository.findById(id).get();  
	}  
	
	public void createUser(UserDto user)   
	{  
	userRepository.save(user);  
	}  
	public void delete(long id)   
	{  
	userRepository.deleteById(id);  
	}  
	//updating a record  
	public void update(UserDto user)   
	{  
	userRepository.save(user);  
	}
}
