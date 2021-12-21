package com.poc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.poc.entities.UserAccount;
import com.poc.exceptionhandling.ResourceNotFoundException;
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
	public Integer updateUser(Integer userId, String fname)
	{
		Integer userAccount= userRepo.updateUser(userId, fname);
		return userAccount;
	}

	@Override
	public List<UserAccount> findUserByFirstNameOrLastNameOrUserPincode(String fname, String lname,
			Integer userPincode) {
		
		List<UserAccount> userlist= userRepo.findByFnameOrLnameOrUserPincode(fname, lname, userPincode);
		return userlist;
	}

	@Override
	public List<UserAccount> sortByDobAndDoj()
	{
	List<UserAccount> userList= userRepo.findAllOrderByDobAscAndDojAsc();
		return userList;
	}

	@Override
	public Boolean deleteUserByIdSoft(Integer userId) throws ResourceNotFoundException
	{
		Optional<UserAccount> optional =userRepo.findById(userId);
		if(optional.isPresent())
		{
			UserAccount user= optional.get();
			user.setActiveSw(false);
			userRepo.save(user);
			return true;
		}
		else
		{
		throw new ResourceNotFoundException(null);
		}
	}

	@Override
	public List<UserAccount> deleteUserByIdHard(Integer userId) throws ResourceNotFoundException
	{
		Optional<UserAccount> optional =userRepo.findById(userId);
		if(optional.isPresent())
		{
			userRepo.deleteById(userId);
			List<UserAccount>  list=userRepo.findAll();
			return list;
		}
		else
		{
		throw new ResourceNotFoundException(null);
		}
		
	}
}
