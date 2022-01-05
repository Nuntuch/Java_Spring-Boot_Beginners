package com.appsdeveloperblog.app.ws.io.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.appsdeveloperblog.app.ws.io.entity.AddressEntity;
import com.appsdeveloperblog.app.ws.io.entity.UserEntity;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class Test1 {
	
	@Autowired
	UserRepository userRepository;
	
	static boolean recordsCreated = false;
	
	@BeforeEach
	void setUp() throws Exception {
		createRecords();
	}

	@Test
	void test() {
		assertTrue("123".equals("123"));
	}
	
	private void createRecords()
	{
		// Prepare User Entity
	     UserEntity userEntity = new UserEntity();
	     userEntity.setFirstName("Sergey");
	     userEntity.setLastName("Kargopolov");
	     userEntity.setUserId("1a2b3c");
	     userEntity.setEncryptedPassword("xxx");
	     
	     userEntity.setEmail("test666@test.com");
	     userEntity.setEmailVerificationStatus(true);
	     
	     // Prepare User Addresses
	     AddressEntity addressEntity = new AddressEntity();
	     addressEntity.setType("shipping");
	     addressEntity.setAddressId("ahgyt74hfy");
	     addressEntity.setCity("Vancouver");
	     addressEntity.setCountry("Canada");
	     addressEntity.setPostalCode("ABCCDA");
	     addressEntity.setStreetName("123 Street Address");

	     List<AddressEntity> addresses = new ArrayList<>();
	     addresses.add(addressEntity);
	     
	     userEntity.setAddresses(addresses);
	     
	     userRepository.save(userEntity);
	}

}
