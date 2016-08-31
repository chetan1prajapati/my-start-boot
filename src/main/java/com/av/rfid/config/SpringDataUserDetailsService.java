package com.av.rfid.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.av.rfid.data.entity.User;
import com.av.rfid.data.entity.UserRole;
import com.av.rfid.data.repo.UserRepo;
import com.av.rfid.data.repo.UserRoleRepo;

public class SpringDataUserDetailsService implements UserDetailsService {
	private static final Logger log = LoggerFactory.getLogger(SpringDataUserDetailsService.class);

	@Autowired
	private UserRepo userRepository;
	@Autowired
	private UserRoleRepo userRolesRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		if (null == user) {
			throw new UsernameNotFoundException("No user present with username: " + username);
		} else {
			log.info(user.getUsername());
			List<UserRole> userRoles = userRolesRepository.findRoleByUsername(username);
			for (UserRole role : userRoles) {
				log.info(role.getRole().toString());
			}

			return new SpringDataUserDetails(user, userRoles);
		}
	}

}
