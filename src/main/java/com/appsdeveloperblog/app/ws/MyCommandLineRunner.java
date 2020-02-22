package com.appsdeveloperblog.app.ws;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.appsdeveloperblog.app.ws.io.entity.AddressEntity;
import com.appsdeveloperblog.app.ws.io.entity.UserEntity;
import com.appsdeveloperblog.app.ws.io.repository.UserRepository;
import com.appsdeveloperblog.app.ws.service.UserService;
import com.appsdeveloperblog.app.ws.shared.Utils;
import com.appsdeveloperblog.app.ws.shared.dto.AddressDTO;
import com.appsdeveloperblog.app.ws.shared.dto.UserDto;

@Component
public class MyCommandLineRunner implements CommandLineRunner {
	
	@Autowired
	Utils utils;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void run(String... args) throws Exception {
		
		if(!userRepository.findAll().iterator().hasNext()) {
			
			
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
			userRepository.save(userEntity);
		}
		
	}

}
