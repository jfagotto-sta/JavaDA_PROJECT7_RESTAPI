package com.nnk.springboot.RepositoriesTests;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class USerRepoTest {

    @MockBean
    UserRepository userRepositoryMock;

    @Test
    public void getUserWithId(){

        User u = new User();
        u.setFullname("Azu");
        u.setPassword("ab");

        when(userRepositoryMock.getById(1)).thenReturn(u);

        assertNotNull(userRepositoryMock.getById(1));

    }

    @Test
    public void getAllUsers(){

        User u = new User();
        u.setFullname("Azu");
        u.setPassword("ab");

        List<User> userList = new ArrayList<>();
        userList.add(u);

        when(userRepositoryMock.findAll()).thenReturn(userList);

        assertTrue(userRepositoryMock.findAll().size()==1);

    }

    @Test
    public void deleteUSer(){

        User u = new User();
        u.setFullname("Azu");
        u.setPassword("ab");

        List<User> userList = new ArrayList<>();
        userList.add(u);

        userRepositoryMock.delete(u);

        assertTrue(userRepositoryMock.findAll().size()==0);

    }

    @Test
    public void addUser(){

        User u = new User();
        u.setFullname("Azu");
        u.setPassword("ab");

        when(userRepositoryMock.save(u)).thenReturn(u);

        assertTrue(userRepositoryMock.findAll().size()==0);

    }




}
