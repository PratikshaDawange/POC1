package com.poc.repository;

import java.io.Serializable;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.poc.entities.UserAccount;
//@Transactional
@Repository
public interface UserRepository extends JpaRepository<UserAccount, Serializable>
{
	@Query("from UserAccount where activeSw = 1")
	List<UserAccount> getAll();
	
	@Query("from UserAccount where activeSw = 1 and fname = ?1")
	List<UserAccount> findByFname(String fname);
	
	@Query("from UserAccount where activeSw = 1 and userPincode = ?1")
	List<UserAccount> findByUserPincode(String userPincode);
		

	
	@Query("from UserAccount where activeSw = 1 order by doj desc")
	List<UserAccount> getUsersOrderByDoj();
	
	@Modifying
	@Query("update UserAccount set activeSw = 1 where id = ?1")
	int deleteUserSoft(Integer userId);
	

	

}
