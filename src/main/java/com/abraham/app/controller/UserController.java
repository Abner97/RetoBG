package com.abraham.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abraham.app.entity.User;
import com.abraham.app.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
  @Autowired
  private UserService userService;
  
  //Create a new user
  @PostMapping
  public ResponseEntity<?> create(@RequestBody User user){
	  return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
  }
  
  // Read an user
  @GetMapping("/{id}")
  public ResponseEntity<?> read(@PathVariable(value="id") Long userId){
	  Optional<User> oUser = userService.findById(userId);
	  System.out.println(oUser);
	  if(!oUser.isPresent()) {
		  return ResponseEntity.notFound().build();
	  }
	  return ResponseEntity.ok(oUser);
  }
  
  //Update user
  @PutMapping("/{id}")
  public ResponseEntity<?> update(@RequestBody User userDetails, @PathVariable(value="id") Long userId){
	Optional<User> oUser = userService.findById(userId);
	if(!oUser.isPresent()) {
		return ResponseEntity.notFound().build();
	}
	
	oUser.get().setName(userDetails.getName());
	
	return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(oUser.get()));
	  
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable(value="id") Long userId){
	  if(!userService.findById(userId).isPresent()) {
		  return ResponseEntity.notFound().build();
	  }
	  
	  userService.deleteById(userId);
	  return ResponseEntity.ok().build();
  }
  
  @GetMapping
  public List<User> readAll(){
	  List<User> user = StreamSupport
			  .stream(userService.findAll().spliterator(), false)
			  .collect(Collectors.toList());
	  return user;
  }
}


