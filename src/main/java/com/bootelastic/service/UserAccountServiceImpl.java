package com.bootelastic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootelastic.model.UserAccount;
import com.bootelastic.repository.UserAccountRepository;

@Service
public class UserAccountServiceImpl implements IUserAccountService{

	@Autowired
	private UserAccountRepository userRepository;
	
	public void save(UserAccount userAccount) {
		userRepository.save(userAccount);
	}
}
