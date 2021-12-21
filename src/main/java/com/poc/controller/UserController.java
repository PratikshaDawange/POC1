package com.poc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc.entities.UserAccount;
import com.poc.exceptionhandling.ResourceNotFoundException;
import com.poc.service.UserServiceI;

@Validated
@RestController
public class UserController
{
	@Autowired
	private UserServiceI userServiceI;
	
	@PostMapping(value="/registerUser")
	public ResponseEntity<UserAccount> registerUser(@Valid @RequestBody UserAccount userAccount)
	{
		
		UserAccount user=userServiceI.registerUser(userAccount);
		if(user!=null)
		{
			return new ResponseEntity<UserAccount> (user,HttpStatus.CREATED);
		}
		else
		{
			return new ResponseEntity<UserAccount> (user,HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping(value="/updateUser/{userId}")
	public ResponseEntity<Integer> updateUser(@PathVariable Integer userId, @RequestBody UserAccount userAccount) throws ResourceNotFoundException
	{
		Integer user= userServiceI.updateUser(userId, userAccount.getFname());
		
		if(user==null)
		{
			throw new ResourceNotFoundException("No User Found For this Id: " +userId);
		}
		return new ResponseEntity<Integer>(user,HttpStatus.CREATED);
	}
	@GetMapping("/findByFnameLnamePincod/{fname}/{lname}/{userPincode}")
	public ResponseEntity<List<UserAccount>> getUserByFnameOrLnameOrPincode(@PathVariable String fname, @PathVariable String lname,
			@PathVariable Integer userPincode, @RequestBody UserAccount userAccount) throws ResourceNotFoundException
	{
		List<UserAccount> userlist= userServiceI.findUserByFirstNameOrLastNameOrUserPincode(fname, lname, userPincode);
		  if (userlist.isEmpty()) {
		      throw new ResourceNotFoundException("No user found: " + fname+" "+lname+" "+userPincode);
		    }
		return new ResponseEntity<List<UserAccount>>(userlist,HttpStatus.OK);
		
	}
	@GetMapping("/sortByDobDoj")
	public ResponseEntity<List<UserAccount>> sortByDobDoj()
	{
		List<UserAccount> userlist= userServiceI.sortByDobAndDoj();
		
		return new ResponseEntity<List<UserAccount>>(userlist,HttpStatus.OK);
		
	}
	
	@DeleteMapping(value="/deleteUserSoft/{userId}")
	public ResponseEntity<Boolean> deleteUserSoft(@PathVariable Integer userId) throws ResourceNotFoundException
	{
		
		Boolean user=userServiceI.deleteUserByIdSoft(userId);
		return new ResponseEntity<Boolean> (user,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUserHard/{userId}")
	public ResponseEntity<List<UserAccount>> deleteUserHard(@PathVariable Integer userId) throws ResourceNotFoundException
	{
		List<UserAccount> userlist= userServiceI.deleteUserByIdHard(userId);
									
		
		return new ResponseEntity<List<UserAccount>>(userlist,HttpStatus.OK);
		
	}
	

}
