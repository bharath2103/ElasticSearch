package com.bootelastic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootelastic.model.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

}
