package com.webSecurity.webSecurityProject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.webSecurity.webSecurityProject.model.Command;
import com.webSecurity.webSecurityProject.model.User;
import com.webSecurity.webSecurityProject.repositories.CommandRepository;
import com.webSecurity.webSecurityProject.repositories.UserRepository;
import com.webSecurity.webSecurityProject.services.CommandServiceImp;
import com.webSecurity.webSecurityProject.services.PingService;
import com.webSecurity.webSecurityProject.services.UploadService;
import com.webSecurity.webSecurityProject.services.UserServiceImp;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api")
public class UserController {

	@Autowired
	  UserRepository userRepository;
	@Autowired
	  CommandRepository commandRepository;
	@Autowired
	  UserServiceImp userServiceImp;
	@Autowired
	  CommandServiceImp commandServiceImp;
	@Autowired
	  UploadService uploadService;
	@Autowired
	  PingService pingService;
    
//----------------user controller----------------------
	
    @PostMapping("/users")
    public ResponseEntity<?> AddUser(@RequestBody User user) {
      userServiceImp.addUser(user);
      return ResponseEntity.ok().body(user);
    }
    
    @GetMapping("/users")
    public ResponseEntity<?> GetAllUsers() {
    	userServiceImp.getAllUsers();
      return ResponseEntity.ok().body(userServiceImp.getAllUsers());
    }
    
    @PatchMapping("/users/{id}")
    public ResponseEntity<?> UpdateUser(@PathVariable("id") Long id,@RequestBody User user) {
    	//userServiceImp.updateUser(user);
      return ResponseEntity.ok().body(userServiceImp.updateUser(id,user));
    }
    
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> DeleteUser(@PathVariable("id") Long id) {
       userServiceImp.deleteUser(userRepository.findById(id).get());
      return ResponseEntity.ok().body("user successfully deleted");
    }
    
    @GetMapping("/users/{id}")
    public ResponseEntity<?> GetUserById(@PathVariable("id") Long id) {
       
      return ResponseEntity.ok().body(userServiceImp.SearchUserById(id));
    }
    
  //----------------command controller----------------------
    
    @PostMapping("/commands")
    public ResponseEntity<?> AddCommand(@RequestBody Command command) {
        commandServiceImp.addCommand(command);
        return ResponseEntity.ok().body(command);
      }
      
      @GetMapping("/commands")
      public ResponseEntity<?> GetAllCommands() {
      	commandServiceImp.getAllCommands();
        return ResponseEntity.ok().body(commandServiceImp.getAllCommands());
      }
      
      @PatchMapping("/commands/{id}")
      public ResponseEntity<?> UpdateCommand(@PathVariable("id") Long id,@RequestBody Command command) {
      	//userServiceImp.updateUser(user);
        return ResponseEntity.ok().body(commandServiceImp.updateCommand(id,command));
      }
      
      @DeleteMapping("/commands/{id}")
      public ResponseEntity<?> DeleteCommand(@PathVariable("id") Long id) {
         commandServiceImp.deleteCommand(commandRepository.findById(id).get());
        return ResponseEntity.ok().body("command successfully deleted");
      }
      
      @GetMapping("/commands/{id}")
      public ResponseEntity<?> GetCommandById(@PathVariable("id") Long id) {
         
        return ResponseEntity.ok().body(commandServiceImp.SearchCommandById(id));
      }
      
    //----------------recherche command par client controller----------------------
      @GetMapping("/users/{id}/commands")
      public ResponseEntity<?> GetCommandsByUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok()
        		.body(commandRepository.findAllByClient(userRepository.findById(id).get()));
      }
      
      //-------------------------------File upload----------------------------------
      @PostMapping("/upload")
      public ResponseEntity<?> UploadImage(@RequestParam("file") MultipartFile file)  {
    	  return uploadService.uploadFile(file);
        }
      
      //-------------------------------command execution----------------------------------
      @PostMapping("/ping/{ip}")
      public ResponseEntity<?> CommandExecution(@PathVariable("ip") String ip)  {
    	  return pingService.pingIP(ip);
        }
      
      //--------------------------------reception-----------------------------------------
      @GetMapping("/reception/{id}")
      public ResponseEntity<?> Reception(@PathVariable("id") Long id) {
        return ResponseEntity.ok()
        		.body(commandRepository.findAllCommandsClient(id));
      }
	
}
