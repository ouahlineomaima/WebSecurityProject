package com.webSecurity.webSecurityProject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.webSecurity.webSecurityProject.model.Command;
import com.webSecurity.webSecurityProject.model.User;

public interface CommandRepository extends JpaRepository<Command,Long>{

	List<Command> findAllByClient(User client);
	
	
	@Query(value = "SELECT ID, ID_CLIENT, DATE,LIBELLE FROM COMMAND u WHERE u.ID_CLIENT =:id", nativeQuery = true)
	List<Command> findAllCommandsClient(Long id);
}
