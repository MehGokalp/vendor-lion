package com.vendorlion.web.api.action;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.matchesRegex;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RemoveControllerTest {

    private final String TEST_AUTH_USER = "admin";
    private final String TEST_AUTH_PASSWORD = "password";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSuccessCreate() throws Exception {
        this.mockMvc.perform(
                    delete("/api/card/remove/65259099-7604-45c1-b43d-99387b409842")
                        .with(httpBasic(this.TEST_AUTH_USER, this.TEST_AUTH_PASSWORD))
                )
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

    @Test
    public void testNoAuthenticationResponse() throws Exception {
        this.mockMvc.perform(get("/api/card/remove/ddf49b87-5f70-4cb6-919f-24ef84361005"))
            .andDo(print())
            .andExpect(status().isUnauthorized())
        ;
    }
}
