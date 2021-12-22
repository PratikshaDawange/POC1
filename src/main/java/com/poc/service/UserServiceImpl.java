package com.poc.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.entities.UserAccount;
import com.poc.repository.UserRepository;

@Service
public class UserServiceImpl implements UserServiceI
{
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserAccount registerUser(UserAccount userAccount)
	{
		userAccount.setActiveSw(true);

		UserAccount newUser= userRepo.save(userAccount);
		return newUser;
	}
	@Override
	public List<UserAccount> getUsers() 
	{
		List<UserAccount> userlist= userRepo.getAll();
		return userlist;
	}
	@Override
	public List<UserAccount> findUserByFirstName(String fname)
	{
		
		List<UserAccount> userlist= userRepo.findByFname(fname);
		return userlist;
	}
	@Override
	public List<UserAccount> findUserByPinCode(Integer userPincode) 
	{

		List<UserAccount> userlist= userRepo.findByUserPincode(userPincode);
		return userlist;
	}

	@Override
	public List<UserAccount> getUsersOrderByDoj()
	{
		List<UserAccount> userList= userRepo.getUsersOrderByDoj();
		return userList;
	}

	@Override
	public Boolean deleteUserByIdSoft(Integer userId)
	{
		UserAccount user = userRepo.findById(userId).get();
		user.setActiveSw(false);
		userRepo.save(user);
		return true;
	}

	@Override
	public List<UserAccount> deleteUserByIdHard(Integer userId) 
	{
		 UserAccount user = userRepo.findById(userId).get();
		 userRepo.delete(user);
		 List<UserAccount>  list=userRepo.findAll();
		 return list;
		
	}

	
}
