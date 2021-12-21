package com.poc.service;

import java.util.List;

import com.poc.entities.UserAccount;
import com.poc.exceptionhandling.ResourceNotFoundException;

public interface UserServiceI
{
	public UserAccount registerUser(UserAccount userAccount);
	public Integer updateUser(Integer userId, String fname);

	public List<UserAccount> findUserByFirstNameOrLastNameOrUserPincode(String fname, String lname, Integer userPincode);
	public List<UserAccount> sortByDobAndDoj();
	public Boolean deleteUserByIdSoft(Integer userId) throws ResourceNotFoundException;
	public List<UserAccount> deleteUserByIdHard(Integer userId) throws ResourceNotFoundException;
}
