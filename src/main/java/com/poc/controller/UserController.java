package com.poc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc.entities.UserAccount;
import com.poc.service.UserServiceI;


@RestController
public class UserController
{
	@Autowired
	private UserServiceI userServiceI;
	
	@PostMapping(value="/registerUser")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserAccount userAccount, BindingResult result)
	{
		if (result.hasErrors()) 
		{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(result.getAllErrors());
        }
		
		UserAccount saveuser=userServiceI.registerUser(userAccount);
        System.err.println(saveuser);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveuser);
        
	}
	@PutMapping(value="/updateUser")
	public ResponseEntity<?> updateUser(@Valid @RequestBody UserAccount userAccount, BindingResult result) 
	{
		if (result.hasErrors())
		{
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(result.getAllErrors());
	    }
	    UserAccount savedUser = userServiceI.registerUser(userAccount);
	    System.err.println(userAccount);
	    return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
		
	}
	@GetMapping("/getUsers")
	public ResponseEntity<List<UserAccount>> getUsers()
	{
	        List<UserAccount> userList = userServiceI.getUsers();
	        System.err.println(userList);
	        return ResponseEntity.status(HttpStatus.OK).body(userList);
	}
	@GetMapping("/findByFname/{fname}")
	public ResponseEntity<List<UserAccount>> getUserByFname(@PathVariable String fname)
	{
		List<UserAccount> userlist= userServiceI.findUserByFirstName(fname);
		 
		  System.err.println(userlist);
		return ResponseEntity.status(HttpStatus.OK).body(userlist);
		
	}
	 @GetMapping("/findByPincode/{userPincode}")
	    public ResponseEntity<?> getUsersByPinCode(@PathVariable Integer userPincode) 
	 {
	        List<UserAccount> userList = userServiceI.findUserByPinCode(userPincode);
	        System.err.println(userList);
	        return ResponseEntity.status(HttpStatus.OK).body(userList);
	 }

	
	 @GetMapping("/sortByDoj")
	public ResponseEntity<?> sortByDobDoj()
	{
		List<UserAccount> userlist= userServiceI.getUsersOrderByDoj();
		System.err.println(userlist);
		return ResponseEntity.status(HttpStatus.OK).body(userlist);
		
	}
	
	@DeleteMapping("/deleteUserSoft/{userId}")
	public ResponseEntity<?> deleteUserSoft(@PathVariable Integer userId)
	{
		Boolean user=userServiceI.deleteUserByIdSoft(userId);
		return ResponseEntity.ok(user);
	}
	
	
	@DeleteMapping("/deleteUserHard/{userId}")
	public ResponseEntity<?> deleteUserHard(@PathVariable Integer userId) 
	{
		List<UserAccount> userlist= userServiceI.deleteUserByIdHard(userId);
		return ResponseEntity.status(HttpStatus.OK).body(userlist);
	}
}
