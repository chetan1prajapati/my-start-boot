package com.av.rfid.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.av.rfid.data.entity.PasswordResetToken;
import com.av.rfid.data.entity.User;

@Service
public class UserService {
	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	public void createPasswordResetTokenForUser(User user, String token) {
		log.info("CreatePasswordResetTokenForUser");

	}

	public PasswordResetToken getPasswordResetToken(String token) {
		log.info("getPasswordResetToken");
		return null;
	}

	public void changeUserPassword(User user, String password) {
		log.info("changeUserPassword");
	}

}
