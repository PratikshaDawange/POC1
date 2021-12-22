package com.poc.controllerTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.poc.controller.UserController;
import com.poc.entities.UserAccount;
import com.poc.service.UserServiceI;




@WebMvcTest(UserController.class)
public class UserControllerTest
{
	@MockBean
	private UserServiceI userServiceI;
	
	@Autowired
	private MockMvc mockMvc;
	
	private UserAccount user;
    private List<UserAccount> userList;
	
	@BeforeEach
    void setUp()
	{
        user = UserAccount.builder()
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
    }
//	@Disabled
	@Test
	public void testRegisterUser() throws Exception
	{
		UserAccount inputUser = UserAccount.builder()
				    //.userId(101)
	                .fname("Pratiksha")
	                .lname("Pawar")
	                .phno(9604278841L)
	                .dob(LocalDate.of(1995,07,27))
	                .doj(LocalDate.of(2021,12,14))
	                .gender("Female")
	                .userPincode(422215)
	                .activeSw(true)
	                .build();
		
		
		userList = new ArrayList<>();
        userList.add(user);
        Mockito.when(userServiceI.registerUser(inputUser)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/registerUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                	    "  \"fname\": \"Pratiksha\",\n" +
                        "  \"lname\": \"Pawar\",\n" +
                        "  \"phno\": 9604278841,\n" +
                        "  \"dob\": \"1995-07-27\",\n" +
                        "  \"doj\": \"2021-12-14\",\n" +
                        "  \"gender\": \"Female\",\n" +
                        "  \"userPincode\": 422215,\n" +
                        "  \"activeSw\": true\n" +
                        "        }"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
	@Test
	public void testUpdateUser() throws Exception
	{
		UserAccount input = UserAccount.builder()
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
		
		 Mockito.when(userServiceI.registerUser(input))
         .thenReturn(user);
		 
		 mockMvc.perform(MockMvcRequestBuilders
                 .put("/updateUser")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content("{\n" +
                		 "  \"id\": 1,\n" +
                         "  \"fname\": \"Priya\",\n" +
                         "  \"lname\": \"Patil\",\n" +
                         "  \"phno\": 9604278841,\n" +
                         "  \"dob\": \"1995-07-27\",\n" +
                         "  \"doj\": \"2021-12-14\",\n" +
                         "  \"gender\": \"Female\",\n" +
                         "  \"userPincode\": 422222,\n" +
                         "  \"activeSw\": true\n" +
                         "  }"))
         .andExpect(MockMvcResultMatchers.status().isCreated());

		
	}
	
	
	
	 @Test
	    void getUsers() throws Exception {
	        Mockito.when(userServiceI.getUsers())
	                .thenReturn(userList);

	        mockMvc.perform(MockMvcRequestBuilders
	                        .get("/getUsers")
	                        .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.status().isOk());
	    }

	    @Test
	    void getUsersByName() throws Exception {
	        Mockito.when(userServiceI.findUserByFirstName("Pratiksha"))
	                .thenReturn(userList);

	        mockMvc.perform(MockMvcRequestBuilders
	                .get("/findByFname/Pratiksha")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.status().isOk());
	    }

	    @Test
	    void getUsersByPinCode() throws Exception {
	        Mockito.when(userServiceI.findUserByPinCode(100001)).thenReturn(userList);

	        mockMvc.perform(MockMvcRequestBuilders
	                        .get("/findByPincode/422215")
	                        .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.status().isOk());
	    }

	    @Test
	    void getUsersOrderByDoj() throws Exception {
	        Mockito.when(userServiceI.getUsersOrderByDoj())
	                .thenReturn(userList);

	        mockMvc.perform(MockMvcRequestBuilders
	                        .get("/sortByDoj")
	                        .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.status().isOk());
	    }
	    @Test
	    void testDeleteUserSoft() throws Exception
	    {
	    	Mockito.when(userServiceI.deleteUserByIdSoft(1)).thenReturn(true);
	    	 mockMvc.perform(MockMvcRequestBuilders
                     .delete("/deleteUserSoft/1")
                     .contentType(MediaType.APPLICATION_JSON))
             .andExpect(MockMvcResultMatchers.status().isOk());
	    }
	    @Test
	    void testDeleteUserHard() throws Exception
	    {
	    	Mockito.when(userServiceI.deleteUserByIdHard(1)).thenReturn(userList);
	    	 mockMvc.perform(MockMvcRequestBuilders
                     .delete("/deleteUserHard/1")
                     .contentType(MediaType.APPLICATION_JSON))
             .andExpect(MockMvcResultMatchers.status().isOk());
	    }
	    
	
}
