package com.example.bankaccounts.controller;

import com.example.bankaccounts.BankAccountsApplication;
import com.example.bankaccounts.dto.CustomerDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = BankAccountsApplication.class)
public class CustomerControlerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    void createCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("User Test");
        customerDTO.setId(1l);
        String bodyJson = mapper.writeValueAsString(customerDTO);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", Matchers.equalTo("User Test")))
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)));

    }

    @Test
    void createCustomerWithoutName() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(2l);
        customerDTO.setName("");
        String bodyJson = mapper.writeValueAsString(customerDTO);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }

    @Test
    void createCustomerWithDpulicatedId() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(123456789l);
        customerDTO.setName("New User");
        String bodyJson = mapper.writeValueAsString(customerDTO);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }

}
