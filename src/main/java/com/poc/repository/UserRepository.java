package com.poc.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.poc.entities.UserAccount;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, Serializable>
{
	public List<UserAccount> findByFnameOrLnameOrUserPincode(String fname, String lname, Integer userPincode);
	   
	@Transactional
	@Modifying
	@Query(value="UPDATE  USER_ACCOUNT  SET FIRST_NAME =:fname WHERE USER_ID =:userId", nativeQuery = true)
	public Integer updateUser(Integer userId, String fname);
	@Transactional
	@Modifying
	@Query(value="SELECT * FROM USER_ACCOUNT ORDER BY dob ASC, doj ASC", nativeQuery= true)
	List<UserAccount> findAllOrderByDobAscAndDojAsc();	
	
	
	/*@Transactional
	@Query(value="UPDATE USER_ACCOUNT SET ACTIVE_SW =:false WHERE USER_ID =:userId",nativeQuery = true)
	@Modifying
	public Integer softDelete(Integer userId);*/
	
	/*@Query(value="SELECT * FROM USER_ACCOUNT WHERE ACTIVE_SW =1",nativeQuery = true)
	@Modifying
	public void softFindAll(UserAccount userAccount);*/

}
