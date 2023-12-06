package com.example.bankaccounts.controller;

import com.example.bankaccounts.BankAccountsApplication;
import com.example.bankaccounts.dto.AmountDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = BankAccountsApplication.class)
public class AccountControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    void createAccount() throws Exception{
        String costumerId = "123456789";
        AmountDTO amountDTO = new AmountDTO();
        amountDTO.setAmount(BigDecimal.valueOf(100));
        String bodyJson = mapper.writeValueAsString(amountDTO);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/customers/"+costumerId+"/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.customer.id", Matchers.equalTo(123456789)))
                .andExpect(jsonPath("$.balance", Matchers.equalTo(100)));

    }

    @Test
    void createAccountInvalidCostumerId() throws Exception{
        String costumerId = "123";
        AmountDTO amountDTO = new AmountDTO();
        amountDTO.setAmount(BigDecimal.valueOf(100));
        String bodyJson = mapper.writeValueAsString(amountDTO);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/customers/"+costumerId+"/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void createAccountInvalidAmount() throws Exception{
        String costumerId = "123456789";
        AmountDTO amountDTO = new AmountDTO();
        amountDTO.setAmount(BigDecimal.ZERO);
        String bodyJson = mapper.writeValueAsString(amountDTO);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/customers/"+costumerId+"/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }


}

