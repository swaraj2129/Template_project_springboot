package com.example.myapp.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.myapp.entity.Course;
import com.example.myapp.entity.Response;
import com.example.myapp.entity.User;
import com.example.myapp.filter.BasicAuthFilter;
import com.example.myapp.filter.JwtAuthenticationFilter;
import com.example.myapp.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = UserController.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                classes = {BasicAuthFilter.class, JwtAuthenticationFilter.class}))
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void testListUsers() throws Exception {

        List<User> listUsers = new ArrayList<>();
        listUsers.add(new User(1L,"Swaraj","Kumar"));
        listUsers.add(new User(2L,"Satyam","Roy"));
        Response<List<User>> response = new Response(listUsers,200);
        Mockito.when(userService.getAllUsers()).thenReturn(response);
        String url =  "/api/v1/user";
        MvcResult mvcResult =  mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andReturn();
        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        JsonNode node = new ObjectMapper().readTree(actualJsonResponse);
        String entityValue = node.path("entity").toString();
        String expectedJsonResponse = objectMapper.writeValueAsString(listUsers);
        System.out.println("printing expected:" + expectedJsonResponse);
        assertThat(entityValue).isEqualTo(expectedJsonResponse);
    }



    @Test
    public void testSaveUsers() throws Exception {
        User newUser = new User(null,"Swaraj","Kumar");
        User savedUser = new User(1L,"Swaraj","Kumar");
        Response<User> response1 = new Response(savedUser,201);
        Mockito.when(userService.createUser(newUser)).thenReturn(response1);
        String url =  "/api/v1/user";
        MvcResult mvcResult =  mockMvc.perform(post(url)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isOk())
                .andReturn();
        String expectedJsonResponse = objectMapper.writeValueAsString(savedUser);

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        JsonNode node = new ObjectMapper().readTree(actualJsonResponse);
        String entityValue = node.path("entity").toString();

        assertThat(entityValue).isEqualTo(expectedJsonResponse);

    }
    @Test
    public void testFirstNameNotBlank() throws Exception {
        User user = new User();
        String url =  "/api/v1/user";
        mockMvc.perform(post(url)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest());
        Mockito.verify(userService,Mockito.times(0)).createUser(user);
    }




}
