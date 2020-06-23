package com.vendorlion.web.api.action;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CreateControllerTest {

    private final String TEST_AUTH_USER = "admin";
    private final String TEST_AUTH_PASSWORD = "password";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSuccessCreate() throws Exception {
        this.mockMvc.perform(
                    post("/api/card/create")
                        .with(httpBasic(this.TEST_AUTH_USER, this.TEST_AUTH_PASSWORD))
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .header("Accept", "application/json")
                        .content("currency=EUR&activationDate=2020-03-05&expireDate=2020-05-04&balance=5000")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.currency", is("EUR")))
                .andExpect(jsonPath("$.activationDate", is("2020-03-05")))
                .andExpect(jsonPath("$.expireDate", is("2020-05-04")))
                .andExpect(jsonPath("$.balance", is(5000)))
                .andExpect(jsonPath("$.cardNumber", matchesRegex("\\d{16}")))
        ;
    }

    @Test
    public void testNoAuthenticationResponse() throws Exception {
        this.mockMvc.perform(get("/api/card/create"))
            .andDo(print())
            .andExpect(status().isUnauthorized())
        ;
    }
}
