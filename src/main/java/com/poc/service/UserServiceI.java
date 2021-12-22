package com.poc.service;

import java.util.List;

import com.poc.entities.UserAccount;

public interface UserServiceI
{
	UserAccount registerUser(UserAccount userAccount);
	List<UserAccount> getUsers();
	List<UserAccount> findUserByFirstName(String fname);
	List<UserAccount> findUserByPinCode(Integer userPincode);
	List<UserAccount> getUsersOrderByDoj();
	Boolean deleteUserByIdSoft(Integer userId);
	List<UserAccount> deleteUserByIdHard(Integer userId);
	
	 
}
