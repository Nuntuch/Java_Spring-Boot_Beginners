package com.appsdeveloperblog.app.ws.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
//import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.appsdeveloperblog.app.ws.exceptions.UserServiceException;
import com.appsdeveloperblog.app.ws.io.entity.UserEntity;
import com.appsdeveloperblog.app.ws.io.entity.PasswordResetTokenEntity;
import com.appsdeveloperblog.app.ws.io.repositories.UserRepository;
import com.appsdeveloperblog.app.ws.io.repositories.PasswordResetTokenRepository;
import com.appsdeveloperblog.app.ws.service.EmailService;
import com.appsdeveloperblog.app.ws.service.SubscriptionService;
import com.appsdeveloperblog.app.ws.service.UserService;
import com.appsdeveloperblog.app.ws.shared.Utils;
import com.appsdeveloperblog.app.ws.shared.dto.AddressDTO;
import com.appsdeveloperblog.app.ws.shared.dto.UserDto;
import com.appsdeveloperblog.app.ws.ui.controller.SubscriptionController;
//import com.appsdeveloperblog.app.ws.ui.model.response.ErrorMessage;
import com.appsdeveloperblog.app.ws.ui.model.response.ErrorMessages;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired 
	PasswordResetTokenRepository passwordResetTokenRepository;
	
	
	private EmailService emailService;
	
	private SubscriptionService subscriptionService;
	
    public UserServiceImpl(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }
	
	@Override
	public UserDto createUser(UserDto user) {
		
//		if(userRepository.findByEmail(user.getEmail()) != null) throw new RuntimeException("Record already exits");
		if(userRepository.findByEmail(user.getEmail()) != null) {
			System.err.print("............................throw new RuntimeException(\"Record already exits\"); is working............................");
			throw new RuntimeException("Record already exits");
		}
		
		
		for(int i = 0; i < user.getAddresses().size(); i++) {
			
			AddressDTO address = user.getAddresses().get(i);
			System.out.println("DEBUG : " + "user.getAddresses().get(i) => " + user.getAddresses().get(i));
			address.setUserDetails(user);
			address.setAddressId(utils.generateAddressId(30));
			user.getAddresses().set(i, address);
			System.out.println("DEBUG : " + "address.getType() => " + address.getType());
			
		}
		
		
//		UserEntity userEntity = new UserEntity();
//		BeanUtils.copyProperties(user, userEntity);
		UserEntity userEntity = new UserEntity();
		ModelMapper modelMapper = new ModelMapper();
		userEntity = modelMapper.map(user, UserEntity.class);
		
		

		String publicUserId = utils.generateUserId(30);
		
		userEntity.setUserId(publicUserId);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setEmailVerificationToken(utils.generateEmailVerificationToken(publicUserId));
		userEntity.setEmailVerificationStatus(false);
		
		UserEntity storedUserDetails = userRepository.save(userEntity);
		
//		UserDto returnValue = new UserDto();
//		BeanUtils.copyProperties(storedUserDetails, returnValue);
		
		UserDto returnValue = modelMapper.map(storedUserDetails, UserDto.class);
		
		System.err.println("DEBUG : "+ returnValue.getEmail());
		System.err.println("DEBUG : "+ returnValue.getEmailVerificationToken());
					
//		Send an email message to user to verify their email address

//		SubscriptionController subscriptionController = new SubscriptionController(null)
		subscriptionService.doSubscript_new(returnValue.getEmail(), returnValue.getEmailVerificationToken() );
//		subscriptionService.doSubscript(returnValue.getEmail(), "test" , new ArrayList<>() );
//		5555555555555555
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
		
		
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), 
				userEntity.getEmailVerificationStatus(),
				true, true,
				true, new ArrayList<>());
		
//		return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(),new ArrayList<>());
	}


	@Override
	public UserDto getUserByUserId(String userId) {
		
		UserDto returnvalue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		if(userEntity == null) throw new UsernameNotFoundException("User with ID: "+ userId + " not found");
		
		BeanUtils.copyProperties(userEntity, returnvalue);
		
		return returnvalue;
	}


	@Override
	public UserDto updateUser(String userId, UserDto user) {
		
		UserDto returnvalue = new UserDto();
		
		UserEntity userEntity = userRepository.findByUserId(userId);
		
//		if(userEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		
		if(userEntity == null) {
			System.out.println("[DEBUG] userEntity == null on updateUser");
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		}
		
		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());
		
		UserEntity updatedUserDetails = userRepository.save(userEntity);
		
		BeanUtils.copyProperties(updatedUserDetails, returnvalue);
		
		System.out.println("[DEBUG] updateUser is working");
		
		
		return returnvalue;
	}


	@Override
	public void deleteUser(String userId) {

		UserEntity userEntity = userRepository.findByUserId(userId);
		
		if(userEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		
		userRepository.delete(userEntity);
	
	}


	@Override
	public List<UserDto> getUsers(int page, int limit) {
		
		List<UserDto> returnValue = new ArrayList<>();
		
		if(page > 0) page = page - 1;
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<UserEntity> usersPage = userRepository.findAll(pageableRequest);
		List<UserEntity> users = usersPage.getContent();
		
		for (UserEntity userEntity : users) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(userEntity, userDto);
			returnValue.add(userDto);
		}
		
		
		return returnValue;
	}


	@Override
	public boolean verifyEmailToken(String token) {
	    boolean returnValue = false;

        // Find user by token
        UserEntity userEntity = userRepository.findUserByEmailVerificationToken(token);

        if (userEntity != null) {
            boolean hastokenExpired = Utils.hasTokenExpired(token);
            if (!hastokenExpired) {
                userEntity.setEmailVerificationToken(null);
                userEntity.setEmailVerificationStatus(Boolean.TRUE);
                userRepository.save(userEntity);
                returnValue = true;
            }
        }

        return returnValue;
	}



	@Override
	public boolean requestPasswordReset(String email) {
		
        boolean returnValue = false;
        
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) {
            return returnValue;
        }
        
        String token = new Utils().generatePasswordResetToken(userEntity.getUserId());
        
        PasswordResetTokenEntity passwordResetTokenEntity = new PasswordResetTokenEntity();
        passwordResetTokenEntity.setToken(token);
        passwordResetTokenEntity.setUserDetails(userEntity);
        passwordResetTokenRepository.save(passwordResetTokenEntity);
        
//        returnValue = new AmazonSES().sendPasswordResetRequest(
//                userEntity.getFirstName(), 
//                userEntity.getEmail(),
//                token);  // return true / flase
        
        subscriptionService.doResetPassword(userEntity.getEmail(), token );
        
        returnValue = true; // asume can find and sent real mail it have in this word
        
		return returnValue;
	}

	@Override
	public boolean resetPassword(String token, String password) {
        boolean returnValue = false;
        
        if( Utils.hasTokenExpired(token) )
        {
            return returnValue;
        }
 
        PasswordResetTokenEntity passwordResetTokenEntity = passwordResetTokenRepository.findByToken(token);

        if (passwordResetTokenEntity == null) {
            return returnValue;
        }

        // Prepare new password
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        
        // Update User password in database
        UserEntity userEntity = passwordResetTokenEntity.getUserDetails();
        userEntity.setEncryptedPassword(encodedPassword);
        UserEntity savedUserEntity = userRepository.save(userEntity);
 
        // Verify if password was saved successfully
        if (savedUserEntity != null && savedUserEntity.getEncryptedPassword().equalsIgnoreCase(encodedPassword)) {
            returnValue = true;
        }
   
        // Remove Password Reset token from database
        passwordResetTokenRepository.delete(passwordResetTokenEntity);
        
        return returnValue;
	}
	



}
