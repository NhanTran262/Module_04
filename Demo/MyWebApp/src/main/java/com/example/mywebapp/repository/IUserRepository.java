package com.example.mywebapp.repository;

import com.example.mywebapp.model.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<User, Integer> {
    public Long countById(Integer id);
}
