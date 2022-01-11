package com.appsdeveloperblog.app.ws;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.appsdeveloperblog.app.ws.io.entity.AuthorityEntity;
import com.appsdeveloperblog.app.ws.io.entity.RoleEntity;
import com.appsdeveloperblog.app.ws.io.entity.UserEntity;
import com.appsdeveloperblog.app.ws.io.repositories.AuthorityRepository;
import com.appsdeveloperblog.app.ws.io.repositories.RoleRepository;
import com.appsdeveloperblog.app.ws.io.repositories.UserRepository;
//import com.appsdeveloperblog.app.ws.shared.Roles;
import com.appsdeveloperblog.app.ws.shared.Utils;

@Component
public class InitialUsersSetup {

	@EventListener
	public void onApplicationEvent(ApplicationReadyEvent event) {
		System.out.println("From Application ready event...");
		
		
    }
	
}
