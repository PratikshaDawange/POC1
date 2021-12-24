package com.poc.repositoryTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.poc.entities.UserAccount;
import com.poc.repository.UserRepository;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
public class UserRepositoryTest 
{
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;
    
    
	  private UserAccount user1;
	  private UserAccount user2; 
	  private List<UserAccount> allusers;
	 
	 
	 
   @BeforeEach
    void setUp()
	{
       UserAccount user1 = UserAccount.builder()
               //  .userId(1)
                 .fname("Pratiksha")
                 .lname("Pawar")
                 .phno("9604278841")
                 .dob(LocalDate.of(1995,07,27))
                 .doj(LocalDate.of(2021,12,14))
                 .gender("Female")
                 .userPincode("422215")
                 .activeSw(true)
                 .build();
     
        		testEntityManager.persist(user1);
        		testEntityManager.flush();

        	
        	UserAccount user2 = UserAccount.builder()
           //  .userId(2)
             .fname("Priya")
             .lname("Patil")
             .phno("9604278841")
             .dob(LocalDate.of(1995,07,27))
             .doj(LocalDate.of(2021,12,14))
             .gender("Female")
             .userPincode("422222")
             .activeSw(true)
             .build();
        		
        	testEntityManager.persist(user2);
        	testEntityManager.flush();
        	
        	List<UserAccount> alluser=new ArrayList<>();
        	alluser.add(user1);
        	alluser.add(user2);
        
    }
    
    @Test
    public void whenGetAll()
    {  
    	 
        //when
        List<UserAccount> allusers = userRepository.getAll();

        //then
	     assertThat(allusers.size()).isEqualTo(2);
	    // assertThat(allusers.get(0)).isEqualTo(user1);
	     //assertThat(allusers.get(1)).isEqualTo(user2);
        //assertEquals(2,allusers.size());
    }
    
    @Test
    void testFindByFname()
    {
    	UserAccount user1 = UserAccount.builder()
                 //  .userId(1)
                   .fname("Pratiksha")
                   .lname("Pawar")
                   .phno("9604278841")
                   .dob(LocalDate.of(1995,07,27))
                   .doj(LocalDate.of(2021,12,14))
                   .gender("Female")
                   .userPincode("422215")
                   .activeSw(true)
                   .build();
       
          		testEntityManager.persist(user1);
          		testEntityManager.flush();
         
        //when
    	List<UserAccount> allusers= userRepository.findByFname(user1.getFname());
    	
    	//then
    	assertEquals(allusers.get(0).getFname(),"Pratiksha");
    }
    
    @Test
    void testFindByUserPincode()
    {
    	UserAccount user1 = UserAccount.builder()
                 //  .userId(1)
                   .fname("Pratiksha")
                   .lname("Pawar")
                   .phno("9604278841")
                   .dob(LocalDate.of(1995,07,27))
                   .doj(LocalDate.of(2021,12,14))
                   .gender("Female")
                   .userPincode("422215")
                   .activeSw(true)
                   .build();
       
          		testEntityManager.persist(user1);
          		testEntityManager.flush();
         
        //when
    	List<UserAccount> allusers= userRepository.findByUserPincode(user1.getUserPincode());
    	
    	//then
    	assertThat(allusers.get(0).getUserPincode()).isEqualTo("422215");
    }
    @Test
    void testDeleteUserSoft()
    {
    	UserAccount user1 = UserAccount.builder()
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
      
         		testEntityManager.merge(user1);
          		
         int i=userRepository.deleteUserSoft(user1.getUserId());
         	assertEquals(0,i);
         
         
       
    }
    
    
    
    @Test
    void testgetUsersOrderByDoj() {
        List<UserAccount> allusers = userRepository.getUsersOrderByDoj();
        assertEquals(2,allusers.size());
    }
    
    
    

   
}
