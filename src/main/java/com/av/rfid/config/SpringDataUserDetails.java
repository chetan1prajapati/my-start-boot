package com.av.rfid.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.av.rfid.data.entity.User;
import com.av.rfid.data.entity.UserRole;

public class SpringDataUserDetails implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(SpringDataUserDetails.class);

	private List<UserRole> userRoles;
	private User user;

	public SpringDataUserDetails(User user, List<UserRole> userRoles) {
		this.user = user;
		this.userRoles = userRoles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<String> roleString = new ArrayList<>();
		for (UserRole userRole : userRoles) {
			roleString.add(userRole.getRole().toString());
		}
		String roles = StringUtils.collectionToCommaDelimitedString(roleString);
		log.info(roles);
		return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.getEnabled();
	}

}
