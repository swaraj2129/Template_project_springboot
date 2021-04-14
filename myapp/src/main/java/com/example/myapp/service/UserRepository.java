package com.example.myapp.service;
import com.example.myapp.dto.UserDto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository 
        extends PagingAndSortingRepository<UserDto, Long> {
 
}