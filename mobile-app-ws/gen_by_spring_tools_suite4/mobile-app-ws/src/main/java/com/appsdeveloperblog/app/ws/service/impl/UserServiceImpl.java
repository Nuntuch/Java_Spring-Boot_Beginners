package com.appsdeveloperblog.app.ws.service.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.appsdeveloperblog.app.ws.exceptions.UserServiceException;
import com.appsdeveloperblog.app.ws.io.entity.UserEntity;
import com.appsdeveloperblog.app.ws.io.repositories.UserRepository;
import com.appsdeveloperblog.app.ws.service.UserService;
import com.appsdeveloperblog.app.ws.shared.Utils;
import com.appsdeveloperblog.app.ws.shared.dto.UserDto;
import com.appsdeveloperblog.app.ws.ui.model.response.ErrorMessage;
import com.appsdeveloperblog.app.ws.ui.model.response.ErrorMessages;

import ch.qos.logback.classic.pattern.Util;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDto createUser(UserDto user) {
		
//		if(userRepository.findByEmail(user.getEmail()) != null) throw new RuntimeException("Record already exits");
		if(userRepository.findByEmail(user.getEmail()) != null) {
			System.err.print("............................throw new RuntimeException(\"Record already exits\"); is working............................");
			throw new RuntimeException("Record already exits");
		}
		
		
		
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		

		String publicUserId = utils.generateUserId(30);
		
		userEntity.setUserId(publicUserId);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		UserEntity storedUserDetails = userRepository.save(userEntity);
		
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnValue);
		
		return returnValue;
	}


	@Override
	public UserDto getUser(String email) {
		
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if(userEntity == null ) throw new UsernameNotFoundException(email);
		
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);
		
		return returnValue;
	}

	
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		
		UserEntity userEntity =  userRepository.findByEmail(email);
		
		if(userEntity == null ) throw new UsernameNotFoundException(email);
		
		
		
		return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(),new ArrayList<>());
	}


	@Override
	public UserDto getUserByUserId(String userId) {
		
		UserDto returnvalue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		if(userEntity == null) throw new UsernameNotFoundException(userId);
		
		BeanUtils.copyProperties(userEntity, returnvalue);
		
		return returnvalue;
	}


	@Override
	public UserDto updateUser(String userId, UserDto user) {
		
		UserDto returnvalue = new UserDto();
		
		UserEntity userEntity = userRepository.findByUserId(userId);
		
//		if(userEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		
		if(userEntity == null) {
			System.out.println("[DEBUGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG] userEntity == null on updateUser");
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		}
		
		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());
		
		UserEntity updatedUserDetails = userRepository.save(userEntity);
		
		BeanUtils.copyProperties(updatedUserDetails, returnvalue);
		
		System.out.println("[DEBUGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG] updateUser is working");
		
		
		return returnvalue;
	}






}
