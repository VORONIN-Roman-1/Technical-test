package com.voronin.technicaltest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voronin.technicaltest.dao.UserRepository;
import com.voronin.technicaltest.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * {@link UserController} integration tests.
 */
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    private User user;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserRepository userRepositoryMock;

    /**
     * Initialise a test.
     */
    @BeforeEach
    public void init() {
        user = User.builder()
                .userName("   Pierre")
                .birthDate(LocalDate.of(1934, 8, 16))
                .country("France")
                .phoneNumber("+33 1234567890")
                .gender("MALE")
                .build();
    }

    /**
     * Add user when valid input then return 201.
     *
     * @throws Exception the exception
     */
    @Test
    void addUserWhenValidInput_thenReturns201() throws Exception {

        when(userRepositoryMock.save(any(User.class))).thenReturn(user);

        MvcResult mvcResult = mockMvc.perform(post("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated()).andReturn();

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepositoryMock, times(1)).save(userCaptor.capture());
        verify(userRepositoryMock, times(1)).existsById(anyString());
        assertThat(userCaptor.getValue().getUserName()).isEqualTo("Pierre");

        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertThat(actualResponseBody)
                .isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(user));
    }

    /**
     * Add user when invalid input if user exists then return 409.
     *
     * @throws Exception the exception
     */
    @Test
    void addUserWhenInvalidInput_userExists_thenReturns409() throws Exception {

        when(userRepositoryMock.existsById(any())).thenReturn(true);

        MvcResult mvcResult = mockMvc.perform(post("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isConflict())
                .andReturn();
        verify(userRepositoryMock, times(1)).existsById(anyString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("already exists"));
    }

    /**
     * Add user when invalid input if user name null then return 400.
     *
     * @throws Exception the exception
     */
    @Test
    void addUserWhenInvalidInput_userNameNULL_thenReturns400() throws Exception {

        user.setUserName("    ");
        MvcResult mvcResult = mockMvc.perform(post("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Username is mandatory"));
    }

    /**
     * Add user when invalid input if user name too long then return 400.
     *
     * @throws Exception the exception
     */
    @Test
    void addUserWhenInvalidInput_userNameTooLong_thenReturns400() throws Exception {

        user.setUserName("userNameTooLong_userNameTooLong_userNameTooLong_userNameTooLong_userNameTooLong_"
                + "userNameTooLong_userNameTooLong_userNameTooLong_userNameTooLong_");

        MvcResult mvcResult = mockMvc.perform(post("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Username is too long"));
    }

    /**
     * Add user when invalid input if birth date is null then return 400.
     *
     * @throws Exception the exception
     */
    @Test
    void addUserWhenInvalidInput_birthDateNUll_thenReturns400() throws Exception {

        user.setBirthDate(null);
        MvcResult mvcResult = mockMvc.perform(post("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Date of birth is mandatory"));
    }

    /**
     * Add user when invalid input if birth date is in the future then return 400.
     *
     * @throws Exception the exception
     */
    @Test
    void addUserWhenInvalidInput_birthDateFuture_thenReturns400() throws Exception {

        user.setBirthDate(LocalDate.of(2934, 8, 16));
        MvcResult mvcResult = mockMvc.perform(post("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("You have not been born yet."));
    }

    /**
     * Add user when invalid input if user is not adult then return 400.
     *
     * @throws Exception the exception
     */
    @Test
    void addUserWhenInvalidInput_birthDateNotAdult_thenReturns400() throws Exception {

        user.setBirthDate(LocalDate.of(2021, 8, 16));
        MvcResult mvcResult = mockMvc.perform(post("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("User is not adult"));

    }

    /**
     * Add user when invalid input if country is null then return 400.
     *
     * @throws Exception the exception
     */
    @Test
    void addUserWhenInvalidInput_countryNULL_thenReturns400() throws Exception {

        user.setCountry("    ");
        MvcResult mvcResult = mockMvc.perform(post("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Country is mandatory"));
    }

    /**
     * Add user when invalid input if country name is too long then return 400.
     *
     * @throws Exception the exception
     */
    @Test
    void addUserWhenInvalidInput_countryTooLong_thenReturns400() throws Exception {

        user.setCountry("countryTooLong_countryTooLong_countryTooLong_countryTooLong_countryTooLong_"
                + "countryTooLong_countryTooLong_countryTooLong_countryTooLong_countryTooLong_");
        MvcResult mvcResult = mockMvc.perform(post("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Country name is too long"));
    }

    /**
     * Add user when invalid input if country is restricted then return 400.
     *
     * @throws Exception the exception
     */
    @Test
    void addUserWhenInvalidInput_countryRestricted_thenReturns400() throws Exception {

        user.setCountry("ErrorCountry");
        MvcResult mvcResult = mockMvc.perform(post("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Service unavailable in this country"));
    }

    /**
     * Add user when invalid phone number then return 400.
     *
     * @throws Exception the exception
     */
    @Test
    void addUserWhenInvalidInput_phoneNumber_thenReturns400() throws Exception {

        user.setPhoneNumber("112");
        MvcResult mvcResult = mockMvc.perform(post("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Phone number format is incorrect"));
    }

    /**
     * Add user when invalid user gender then return 400.
     *
     * @throws Exception the exception
     */
    @Test
    void addUserWhenInvalidInput_userGender_thenReturns400() throws Exception {

        user.setGender("MARTIAN");
        MvcResult mvcResult = mockMvc.perform(post("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Gender is incorrect"));
    }

    /**
     * Get user by user name when valid input then return 200.
     *
     * @throws Exception the exception
     */
    @Test
    void getUserByUserNameWhenValidInput_thenReturns200() throws Exception {
        when(userRepositoryMock.findByUserName("Pierre")).thenReturn(Optional.of(user));
        MvcResult mvcResult = mockMvc.perform(get("/api/users/{userName}", "Pierre")
                .contentType("application/json"))
                .andExpect(status().isOk()).andReturn();
        verify(userRepositoryMock, times(1)).findByUserName(ArgumentMatchers.anyString());

        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertThat(actualResponseBody)
                .isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(user));

    }

    /**
     * Get user by user name when invalid input if user name is null then return 400.
     *
     * @throws Exception the exception
     */
    @Test
    void getUserByUserNameWhenInvalidInput_UserNameNull_thenReturns400() throws Exception {

        mockMvc.perform(get("/api/users/{userName}", "")
                .contentType("application/json"))
                .andExpect(status().isMethodNotAllowed());
    }

}
