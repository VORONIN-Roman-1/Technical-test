package com.voronin.technicaltest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voronin.technicaltest.entity.User;
import com.voronin.technicaltest.service.UserServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {
    User user;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserServiceImp userServiceMock;

    @BeforeEach
    public void init() {
        user = new User("Pierre", LocalDate.of(1934, 8, 16), "France", "+33 1234567890", "MALE");
    }

    @Test
    void addUserWhenValidInput_thenReturns200() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated()).andReturn();

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userServiceMock, times(1)).save(userCaptor.capture());
        assertThat("Pierre").isEqualTo("Pierre");


    }

    @Test
    void addUserWhenInvalidInput_userName_thenReturns400() throws Exception {

        user.setUserName("    ");

        mockMvc.perform(post("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void addUserWhenInvalidInput_birthDate_thenReturns400() throws Exception {

        user.setBirthDate(null);
        mockMvc.perform(post("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void addUserWhenInvalidInput_country_thenReturns400() throws Exception {

        user.setCountry("    ");
        mockMvc.perform(post("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void addUserWhenInvalidInput_phoneNumber_thenReturns400() throws Exception {

        user.setPhoneNumber("112");
        mockMvc.perform(post("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void addUserWhenInvalidInput_userGender_thenReturns400() throws Exception {

        user.setGender("MARTIAN");
        mockMvc.perform(post("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest());
    }


    @Test
    void getUserByUserNameWhenValidInput_thenReturns200() throws Exception {

        mockMvc.perform(get("/api/users")
                .contentType("application/json")
                .param("userName", "Emmanuel"))
                .andExpect(status().isOk());
        verify(userServiceMock, times(1)).findByUserName(ArgumentMatchers.anyString());
    }

    @Test
    void getUserByUserNameWhenInvalidInput_UserNameNull_thenReturns200() throws Exception {

        mockMvc.perform(get("/api/users")
                .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }


}