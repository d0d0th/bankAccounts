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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = BankAccountsApplication.class)
public class TransactionControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    void makeDeposit() throws Exception {
        String accountId = "100";
        AmountDTO amountDTO = new AmountDTO();
        amountDTO.setAmount(BigDecimal.valueOf(10));
        String bodyJson = mapper.writeValueAsString(amountDTO);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/accounts/" + accountId + "/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("type","DEPOSIT")
                        .content(bodyJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.account.id", Matchers.equalTo(100)))
                .andExpect(jsonPath("$.amount", Matchers.equalTo(10)));
    }

    @Test
    void makeWithdraw() throws Exception {
        String accountId = "100";
        AmountDTO amountDTO = new AmountDTO();
        amountDTO.setAmount(BigDecimal.valueOf(10));
        String bodyJson = mapper.writeValueAsString(amountDTO);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/accounts/" + accountId + "/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("type","WITHDRAW")
                        .content(bodyJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.account.id", Matchers.equalTo(100)))
                .andExpect(jsonPath("$.amount", Matchers.equalTo(-10)));
    }

    @Test
    void withdrawInsuficientBalance() throws Exception {
        String accountId = "100";
        AmountDTO amountDTO = new AmountDTO();
        amountDTO.setAmount(BigDecimal.valueOf(10000));
        String bodyJson = mapper.writeValueAsString(amountDTO);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/accounts/" + accountId + "/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("type","WITHDRAW")
                        .content(bodyJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void withdrawInvalidAccount() throws Exception {
        String accountId = "1123";
        AmountDTO amountDTO = new AmountDTO();
        amountDTO.setAmount(BigDecimal.valueOf(10));
        String bodyJson = mapper.writeValueAsString(amountDTO);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/accounts/" + accountId + "/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("type","WITHDRAW")
                        .content(bodyJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void getTransactions() throws Exception {
        String accountId = "100";
        String startDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT).toString();
        String endDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MAX).toString();


        mvc.perform(MockMvcRequestBuilders.get("/api/v1/accounts/" + accountId + "/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("startDateTime",startDateTime)
                        .param("endDateTime",endDateTime)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
