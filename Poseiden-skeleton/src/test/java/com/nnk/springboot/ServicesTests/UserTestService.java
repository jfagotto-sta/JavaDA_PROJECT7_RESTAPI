package com.nnk.springboot.ServicesTests;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class UserTestService {

    @InjectMocks
    public UserService userService;

    @Mock
    public UserRepository userRepoMock;

    @Test
    public void complexTest() {
        User u = new User();
        u.setRole("admin");
        when(userRepoMock.getById(anyInt())).thenReturn(u);


        assertEquals(userService.complex(1).getRole(),"coucou");
    }

    @Test
    public void getUserByUsername() {
        User u = new User();
        u.setRole("admin");
        when(userRepoMock.findByUsername(anyString())).thenReturn(u);
        assertNotNull(userService.getUserByUsername("jeff"));
    }

    @Test
    public void getUserById() {
        User u = new User();
        u.setRole("admin");
        when(userRepoMock.getById(anyInt())).thenReturn(u);
        assertNotNull(userService.getUserById(1));
    }

    @Test
    public void saveUser(){
        User u = new User();
        u.setUsername("fagotto");
        u.setPassword("abc");

        when(userRepoMock.save(u)).thenReturn(u);
        userService.saveNewUser(u);
        System.out.println(u.getPassword());
        assertTrue((BCrypt.checkpw("abc",u.getPassword())));
    }

    @Test
    public void getAllUsers(){

        User u = new User();
        u.setFullname("fag");
        u.setPassword("abc");

        User u2 = new User();
        u.setFullname("ken");
        u.setPassword("abc");

        List<User> userList = new ArrayList<>();
        userList.add(u);
        userList.add((u2));

        when(userRepoMock.findAll()).thenReturn(userList);

        assertTrue(userService.getAllUsers().size() == 2);
    }

//     @Test
//    public void deleteUser(){
//
//         User u = new User();
//         u.setFullname("fag");
//         u.setPassword("abc");
//
//        when(userRepoMock.delete(u)).;
//     }



}
