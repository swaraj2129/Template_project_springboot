package com.example.myapp.service;
import com.example.myapp.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository 
        extends CrudRepository<User, Long> {
 
}