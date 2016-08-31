package com.av.rfid.data.repo;

import org.springframework.data.repository.CrudRepository;

import com.av.rfid.data.entity.User;

public interface UserRepo extends CrudRepository<User, Long> {

	public User findByUsername(String username);

	public User findByEmail(String email);

}
