package com.appsdeveloperblog.app.ws.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.appsdeveloperblog.app.ws.io.entity.UserEntity;

public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;
	private UserEntity userEntity;

	public UserPrincipal(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> auth = new ArrayList<>();
		
		userEntity.getRoles()
					.forEach(r->{
						auth.add(new SimpleGrantedAuthority(r.getName()));
						r.getAuthorities().forEach(a-> auth.add(new SimpleGrantedAuthority(a.getName())));
					}
					);
	return auth;
	}

	@Override
	public String getPassword() {
		return userEntity.getEncryptedPassword();
	}

	@Override
	public String getUsername() {
		return userEntity.getEmail();
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
		return userEntity.getEmailVerificationStatus();
	}

	public String getUserId() {
		return userEntity.getUserId();
	}
}
