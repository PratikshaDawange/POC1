package com.poc.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.poc.entities.UserAccount;
import com.poc.repository.UserRepository;
import com.poc.service.UserServiceI;

@SpringBootTest
public class UserServiceImplTest
{
	@Autowired
    private UserServiceI userServiceI;

    @MockBean
    private UserRepository userRepository;
	@BeforeEach
    void setUp()
	{
       UserAccount user = UserAccount.builder()
                .userId(1)
                .fname("Pratiksha")
                .lname("Pawar")
                .phno("9604278841")
                .dob(LocalDate.of(1995,07,27))
                .doj(LocalDate.of(2021,12,14))
                .gender("Female")
                .userPincode("422215")
                .activeSw(true)
                .build();
    

       List<UserAccount> userList = new ArrayList<>();
       userList.add(user);
       
       
       
       Mockito.when(userServiceI.getUsers()).thenReturn(userList);
       Mockito.when(userServiceI.getUsersOrderByDoj()).thenReturn(userList);
       Mockito.when(userServiceI.findUserByFirstName("Prati")).thenReturn(userList);
       Mockito.when(userServiceI.findUserByPinCode("422215")).thenReturn(userList);
       
       
      // Mockito.when(userServiceI.deleteUserByIdHard(1)).thenReturn(isNull());
    }
	//@Disabled
	@Test
	void testResgisterUser()
	{
		UserAccount user = UserAccount.builder()
			    .userId(1)
                .fname("Pratiksha")
                .lname("Pawar")
                .phno("9604278841")
                .dob(LocalDate.of(1995,07,27))
                .doj(LocalDate.of(2021,12,14))
                .gender("Female")
                .userPincode("422215")
                .activeSw(true)
                .build();
		 Mockito.when(userServiceI.registerUser(user)).thenReturn(user);
		UserAccount savedUser = userRepository.save(user);
        assertNotNull(savedUser);
       assertEquals(user.getFname(),savedUser.getFname());
	
	}
	@Test
	void testGetUsers()
	{
		List<UserAccount> usersByName = userRepository.getAll();
        assertEquals(1, usersByName.size());
		
	}
	@Test
    void testSortByDobAndDoj()
	{
		List<UserAccount> usersByName =  userRepository.getUsersOrderByDoj();
        assertEquals(1, usersByName.size());
    }

	@Test
    void testFindUserByFirstName()
	{
		List<UserAccount> userlist =  userRepository.findByFname("Prati");
        assertEquals(1, userlist.size());
    }
	@Test
    void testFindUserByPinCode1()
	{
		List<UserAccount> userlist =  userRepository.findByUserPincode("422215");
        assertEquals(1, userlist.size());
    }
	@Test
    void testFindUserByPinCode2()
	{
		List<UserAccount> userlist =  userRepository.findByUserPincode("422111");
        assertNotEquals(1, userlist.size());
    }
	
	/*@Test
	void deleteUserByIdSoft()
	{
		
		Mockito.when(userServiceI.deleteUserByIdSoft(1)).thenReturn(true);
		
		boolean user = userRepository.findById(1);
		
		int b=userRepository.deleteUserSoft(user);
	}*/
	
	/*@Test
	void deleteUserByIdHard()
	{
		UserAccount user=userRepository.findById(1).get();
		userRepository.delete(user);
		assertThat(user.getUserId()).isNull(); 
	}*/
}
