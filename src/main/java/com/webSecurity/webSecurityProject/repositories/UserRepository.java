package com.webSecurity.webSecurityProject.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.webSecurity.webSecurityProject.model.User;

public interface UserRepository extends JpaRepository<User,Long>{

}
