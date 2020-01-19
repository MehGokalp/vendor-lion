package com.vendorbear.web.api.action;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class FindControllerTest {

    private final String TEST_AUTH_USER = "admin";
    private final String TEST_AUTH_PASSWORD = "password";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSuccessFindResponse() throws Exception {
        this.mockMvc.perform(
                    get("/api/card/find/cf2616c3-5bb0-48fe-bf33-8a2320483115")
                        .with(httpBasic(this.TEST_AUTH_USER, this.TEST_AUTH_PASSWORD))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("\"reference\":\"cf2616c3-5bb0-48fe-bf33-8a2320483115\"")))
        ;
    }

    @Test
    public void testNotFoundResponse() throws Exception {
        this.mockMvc.perform(
                get("/api/card/find/not-found")
                        .with(httpBasic(this.TEST_AUTH_USER, this.TEST_AUTH_PASSWORD))
                    )
                .andDo(print())
                .andExpect(status().isNotFound())
        ;
    }

    @Test
    public void testNoAuthenticationResponse() throws Exception {
        this.mockMvc.perform(get("/api/card/find/not-found"))
            .andDo(print())
            .andExpect(status().isUnauthorized())
        ;
    }
}
