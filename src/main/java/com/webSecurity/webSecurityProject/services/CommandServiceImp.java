package com.webSecurity.webSecurityProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webSecurity.webSecurityProject.model.Command;
import com.webSecurity.webSecurityProject.repositories.CommandRepository;
import com.webSecurity.webSecurityProject.repositories.UserRepository;

@Service
public class CommandServiceImp implements CommandService{

	@Autowired
	CommandRepository commandRepository;
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Command addCommand(Command command) {
		return commandRepository.save(command);
	}

	@Override
	public List<Command> getAllCommands() {
		// TODO Auto-generated method stub
		return commandRepository.findAll();
	}

	@Override
	public Command updateCommand(Long id_cmd, Command command) {
		Command c=commandRepository.findById(id_cmd).get();
		//u.setId(id);
		c.setDate(command.getDate());
		c.setClient(command.getClient());;
		return commandRepository.save(c);
	}

	@Override
	public void deleteCommand(Command command) {
		commandRepository.delete(command);
	}

	@Override
	public Command SearchCommandById(Long id) {
		// TODO Auto-generated method stub
		return commandRepository.findById(id).get();
	}

}
