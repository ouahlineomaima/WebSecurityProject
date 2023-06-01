package com.webSecurity.webSecurityProject.services;

import java.util.List;

import com.webSecurity.webSecurityProject.model.Command;

public interface CommandService {

	public Command addCommand(Command command);
	public List<Command> getAllCommands();
	public Command updateCommand(Long id_cmd,Command command);
	public void deleteCommand(Command command);
	public Command SearchCommandById(Long id);
}
