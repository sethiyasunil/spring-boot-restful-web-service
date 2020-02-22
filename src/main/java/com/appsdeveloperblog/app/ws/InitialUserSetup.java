package com.appsdeveloperblog.app.ws;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.appsdeveloperblog.app.ws.io.entity.AddressEntity;
import com.appsdeveloperblog.app.ws.io.entity.AuthorityEntity;
import com.appsdeveloperblog.app.ws.io.entity.RoleEntity;
import com.appsdeveloperblog.app.ws.io.entity.UserEntity;
import com.appsdeveloperblog.app.ws.io.enums.AuthorityTypes;
import com.appsdeveloperblog.app.ws.io.enums.RoleTypes;
import com.appsdeveloperblog.app.ws.io.repository.AuthorityRepository;
import com.appsdeveloperblog.app.ws.io.repository.RoleRepository;
import com.appsdeveloperblog.app.ws.io.repository.UserRepository;
import com.appsdeveloperblog.app.ws.shared.Utils;

@Component
public class InitialUserSetup{
	
	@Autowired
	Utils utils;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthorityRepository authorityRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@EventListener
	@Transactional
	public void onApplicationEvent(ApplicationReadyEvent event) throws Exception {
		
		if(userRepository.findByEmail("sethiya_sunil@yahoo.com")!=null) return;

		AuthorityEntity readAuthority = createAuthority(AuthorityTypes.READ_AUTHORITY.name());
		AuthorityEntity writeAuthority = createAuthority(AuthorityTypes.WRITE_AUTHORITY.name());
		AuthorityEntity deleteAuthority = createAuthority(AuthorityTypes.DELETE_AUTHORITY.name());
		
		RoleEntity roleUser = createRole(RoleTypes.ROLE_USER.name(), Arrays.asList(readAuthority,writeAuthority));
		RoleEntity roleAdmin = createRole(RoleTypes.ROLE_ADMIN.name(), Arrays.asList(readAuthority,writeAuthority,deleteAuthority));
		
		UserEntity userEntity = new UserEntity();
		userEntity.setId(100000l);
		userEntity.setFirstName("admin");
		userEntity.setLastName("admin");
		userEntity.setEmail("sethiya_sunil@yahoo.com");
		userEntity.setUserId( utils.generateUserId(30));
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode("password"));
		userEntity.setEmailVerificationStatus(Boolean.TRUE);
		
		List<AddressEntity> addressess = new ArrayList<>();
		AddressEntity address= new AddressEntity();
		address.setId(100000l);
		address.setAddressId(utils.generateAddressId(30));
		address.setCity("admin-city");
		address.setCountry("admin-country");
		address.setPostalCode("122001");
		address.setStreetName("admin-street");
		address.setType("admin-home");
		address.setUserDetails(userEntity);
		addressess.add(address);
		userEntity.setAddresses(addressess);

		userEntity.setRoles(Arrays.asList(roleAdmin));
		userRepository.save(userEntity);
	}
	
	
	private RoleEntity createRole(String name, List<AuthorityEntity> authorities) {
		RoleEntity role = roleRepository.findByName(name);
		if(role==null) {
			role = new RoleEntity(name);
			role.setAuthorities(authorities);
			role = roleRepository.save(role);
		}
		return role;
	}


	private AuthorityEntity createAuthority(String name) {
		
		AuthorityEntity authority = authorityRepository.findByName(name);
		if(authority==null) {
			authority = authorityRepository.save(new AuthorityEntity(name));
		}
		return authority;
	}

}
