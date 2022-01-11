package com.appsdeveloperblog.app.ws.ui.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.app.ws.exceptions.UserServiceException;
import com.appsdeveloperblog.app.ws.service.AddressService;
import com.appsdeveloperblog.app.ws.service.SubscriptionService;
import com.appsdeveloperblog.app.ws.service.UserService;
import com.appsdeveloperblog.app.ws.shared.dto.AddressDTO;
import com.appsdeveloperblog.app.ws.shared.dto.UserDto;
import com.appsdeveloperblog.app.ws.ui.model.request.PasswordResetModel;
import com.appsdeveloperblog.app.ws.ui.model.request.PasswordResetRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.response.AddressesRest;
import com.appsdeveloperblog.app.ws.ui.model.response.ErrorMessages;
import com.appsdeveloperblog.app.ws.ui.model.response.OperationStatusModel;
import com.appsdeveloperblog.app.ws.ui.model.response.RequestOperationStatus;
import com.appsdeveloperblog.app.ws.ui.model.response.UserRest;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import com.appsdeveloperblog.app.ws.service.SubscriptionService;
import com.appsdeveloperblog.app.ws.shared.dto.SubscriptionDto;
import org.apache.log4j.Logger;

@CrossOrigin(origins = "http://localhost:8046")
@RestController
@RequestMapping("users") // http:localhost:8080/users
public class UserController {

	final static Logger logger = Logger.getLogger(UserController.class);


//	private SubscriptionService subscriptionService;
	
	@Autowired
	UserService userService;

	@Autowired
	AddressService addressService;
	
	@Autowired
	AddressService addressesService;
	
	@ApiOperation(value="The Get User Details Web Service Endpoint",
			notes="${userController.GetUser.ApiOperation.Notes}")
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest getUser(@PathVariable String id) {
		UserRest returnValue = new UserRest();

		UserDto userDto = userService.getUserByUserId(id);
		BeanUtils.copyProperties(userDto, returnValue);

		return returnValue;
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {
		
	
		
		UserRest returnValue = new UserRest();

//		if(userDetails.getFirstName().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		if (userDetails.getFirstName().isEmpty())
			throw new NullPointerException("The object is null");

//		UserDto userDto = new UserDto();
//		BeanUtils.copyProperties(userDetails, userDto);

		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);

		UserDto createdUser = userService.createUser(userDto);
//		BeanUtils.copyProperties(createdUser, returnValue);
		returnValue = modelMapper.map(createdUser, UserRest.class);
		
		return returnValue;
	}

	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) {

		UserRest returnValue = new UserRest();

//		if(userDetails.getFirstName().isEmpty()) throw new NullPointerException("The object is null");

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);

		UserDto updateUser = userService.updateUser(id, userDto);
		BeanUtils.copyProperties(updateUser, returnValue);

		return returnValue;

	}
	
//	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('DELETE_AUTHORITY')")
	@DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel deleteUser(@PathVariable String id) {

		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETE.name());

		userService.deleteUser(id);

		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());

		return returnValue;
	}

	@ApiImplicitParams({
		@ApiImplicitParam(name="authorization", value="${userController.authorizationHeader.description}", paramType="header")
	})
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<UserRest> getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "25") int limit) {

		List<UserRest> returnValue = new ArrayList<>();

		List<UserDto> users = userService.getUsers(page, limit);

		for (UserDto userDto : users) {
			UserRest userModel = new UserRest();
			BeanUtils.copyProperties(userDto, userModel);
			returnValue.add(userModel);
		}

		return returnValue;

	}
	
//	http://localhost:8080/mobile-app-ws-users/jfhdjeufhahaj/addresses
	@GetMapping(path = "/{id}/addresses", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public CollectionModel<AddressesRest> getUserAddresses(@PathVariable String id) {
		
		List<AddressesRest> returnValue = new ArrayList<>();
		List<AddressDTO> addressesDTO = addressesService.getAddresses(id);
		
		if(addressesDTO != null && !addressesDTO.isEmpty()){
			
			java.lang.reflect.Type listType = new TypeToken<List<AddressesRest>>() {}.getType();
			returnValue = new ModelMapper().map(addressesDTO,listType);
			
			for (AddressesRest addressesRest : returnValue) {
				Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
						.getUserAddress(id,addressesRest.getAddressId()))
						.withSelfRel();
				addressesRest.add(selfLink);
			}
		}
		
		
		Link userLink = WebMvcLinkBuilder.linkTo(UserController.class)
				.slash(id)
				.withRel("user");

		Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
				.getUserAddresses(id))
				.withSelfRel();
		
		return CollectionModel.of(returnValue,userLink,selfLink);
	}
	
	
	
	@GetMapping(path = "/{userId}/addresses/{addressId}", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public EntityModel<AddressesRest> getUserAddress(@PathVariable String userId ,@PathVariable String addressId) {
		
		AddressDTO addressesDto = addressService.getAddress(addressId);
		
		ModelMapper modelMapper = new ModelMapper();
		AddressesRest returnValue = modelMapper.map(addressesDto, AddressesRest.class);
		
		
//		http://localhost:8080/mobile-app-ws-users/<userId>/addresses/{addressId}
		Link userLink = WebMvcLinkBuilder.linkTo(UserController.class)
				.slash(userId)
				.withRel("user");

		Link userAddressesLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
				.getUserAddresses(userId))
//				.slash(userId)
//				.slash("addresses")
				.withRel("addresses");

		Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
				.getUserAddress(userId,addressId))
//				.slash(userId)
//				.slash("addresses")
//				.slash(addressId)
				.withSelfRel();
				
//		returnValue.add(userLink);
//		returnValue.add(userAddressesLink);
//		returnValue.add(selfLink);
		
//		return  returnValue;
		return EntityModel.of(returnValue,Arrays.asList(userLink,userAddressesLink,selfLink));
				}
	
	
	
	/*
    * http://localhost:8080/mobile-app-ws/users/email-verification?token=sdfsdf
    * */
   @GetMapping(path = "/email-verification", produces = { MediaType.APPLICATION_JSON_VALUE,
           MediaType.APPLICATION_XML_VALUE })
   public OperationStatusModel verifyEmailToken(@RequestParam(value = "token") String token) {

       OperationStatusModel returnValue = new OperationStatusModel();
       returnValue.setOperationName(RequestOperationName.VERIFY_EMAIL.name());
       
       boolean isVerified = userService.verifyEmailToken(token);
       
       if(isVerified)
       {
           returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
       } else {
           returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
       }

       return returnValue;
   }
	
	
	
   
	 /*
    * http://localhost:8080/mobile-app-ws/users/password-reset-request
    * */
   @PostMapping(path = "/password-reset-request", 
           produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
           consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
   )
   public OperationStatusModel requestReset(@RequestBody PasswordResetRequestModel passwordResetRequestModel) {
   	OperationStatusModel returnValue = new OperationStatusModel();

       boolean operationResult = userService.requestPasswordReset(passwordResetRequestModel.getEmail());
       
       returnValue.setOperationName(RequestOperationName.REQUEST_PASSWORD_RESET.name());
       returnValue.setOperationResult(RequestOperationStatus.ERROR.name());

       if(operationResult)
       {
           returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
       }

       return returnValue;
   }
   
   
//   @CrossOrigin(origins = "http://localhost:8046")
   @PostMapping(path = "/password-reset",
           consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
   )
   public OperationStatusModel resetPassword(@RequestBody PasswordResetModel passwordResetModel) {
   	OperationStatusModel returnValue = new OperationStatusModel();

       boolean operationResult = userService.resetPassword(
               passwordResetModel.getToken(),
               passwordResetModel.getPassword());
       
       returnValue.setOperationName(RequestOperationName.PASSWORD_RESET.name());
       returnValue.setOperationResult(RequestOperationStatus.ERROR.name());

       if(operationResult)
       {
           returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
       }

       return returnValue;
   }

	
	
	
	
	
	
	
	

}
