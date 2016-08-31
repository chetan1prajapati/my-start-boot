package com.av.rfid.data.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.av.rfid.data.entity.UserRole;

public interface UserRoleRepo extends CrudRepository<UserRole, Long> {

	List<UserRole> findRoleByUsername(String username);

}
