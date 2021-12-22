package com.poc.repositoryTest;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import com.poc.entities.UserAccount;
import com.poc.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=validate"})
public class UserRepositoryTest 
{
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    
   @BeforeEach
    void setUp()
	{
       UserAccount user = UserAccount.builder()
                .userId(1)
                .fname("Pratiksha")
                .lname("Pawar")
                .phno(9604278841L)
                .dob(LocalDate.of(1995,07,27))
                .doj(LocalDate.of(2021,12,14))
                .gender("Female")
                .userPincode(422215)
                .activeSw(true)
                .build();
    testEntityManager.persist(user);
        
    }
    @Test
    void testFindByFname()
    {
    	
    	List<UserAccount> useracc= userRepository.findByFname("Pratiksha");
    	assertThat(useracc).isNotNull();
    	
    	
    }

   
}
