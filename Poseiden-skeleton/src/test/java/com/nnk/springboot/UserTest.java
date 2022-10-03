package com.nnk.springboot;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

	@BeforeAll
	public void deleteall() {
		userService.deleteAll();
	}

    @Test
	public void userTest() {
		User u = new User();
		u.setUsername("a");
		u.setFullname("b");
        u.setPassword("ab");

		// Save
       userService.saveNewUser(u);
	   User u2 = userService.getUserById(2);
       assertNotNull(u2);

		// Update
		u.setFullname("test");
		userService.saveNewUser(u);
		assertTrue(u.getFullname().equals("test"));

		// Find
		List<User> listResult = userService.getAllUsers();
		assertTrue(listResult.size() > 0);

		// Delete
	    userService.deleteUser(u.getId());
		assertTrue(userService.getAllUsers().size()==0);	}
}

