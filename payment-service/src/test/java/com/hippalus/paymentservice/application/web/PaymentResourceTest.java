package com.hippalus.paymentservice.application.web;

import com.hippalus.paymentservice.PaymentServiceApplication;
import com.hippalus.paymentservice.application.request.PaymentRequest;
import com.hippalus.paymentservice.application.request.TransactionItemDTO;
import com.hippalus.paymentservice.application.response.PaymentResponse;
import com.hippalus.paymentservice.domain.service.IPaymentService;
import com.hippalus.sharedkernel.domain.DomainObject;
import com.hippalus.sharedkernel.utilities.DomainModelMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {PaymentServiceApplication.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
@AutoConfigureMockMvc
public class PaymentResourceTest {

    private static final String PAYMENT_URI = "/payment/pay";


    @Autowired
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private PaymentResource paymentResource;
    @Autowired
    private IPaymentService paymentService;


    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void contextLoads() {
        assertThat(paymentResource).isNotNull();
        assertThat(paymentService).isNotNull();
    }

    @Test
    void whenReceivedValidRequestThenShouldBeReturnSuccess() throws Exception {
        //given:
        final var paymentRequest = newPaymentRequest();
        final var requestPayload = DomainModelMapper.writeToJsonString(paymentRequest);

        //when:
        var mvcResult = mvc.perform(MockMvcRequestBuilders.post(PAYMENT_URI)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestPayload))
                .andReturn();

        //then:
        var status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(200);

        var content = mvcResult.getResponse().getContentAsString();
        final var response = DomainModelMapper.readValue(content, PaymentResponse.class);
        assertThat(response).isEqualTo(new PaymentResponse("SUCCESS"));
    }
    @Test
    void whenReceivedInvalidRequestThenShouldBeReturnFeiled() throws Exception {
        //given:
        final var paymentRequest = newInvalidPaymentRequest();
        final var requestPayload = DomainModelMapper.writeToJsonString(paymentRequest);

        //when:
        var mvcResult = mvc.perform(MockMvcRequestBuilders.post(PAYMENT_URI)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestPayload))
                .andReturn();

        //then:
        var status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(200);

        var content = mvcResult.getResponse().getContentAsString();
        final var response = DomainModelMapper.readValue(content, PaymentResponse.class);
        assertThat(response).isEqualTo(new PaymentResponse("FAILED"));
    }

    private PaymentRequest newInvalidPaymentRequest() {
        List<TransactionItemDTO> itemDTOS = newTransactionItemDTOS();

        return PaymentRequest.builder()
                .id("OrderId")
                .cardNumber("5105105105105101")
                .items(itemDTOS)
                .paymentMethod("CrEDiTcARD")
                .build();
    }

    private PaymentRequest newPaymentRequest() {
        List<TransactionItemDTO> itemDTOS = newTransactionItemDTOS();

        return PaymentRequest.builder()
                .id("OrderId")
                .cardNumber("5105105105105100")
                .items(itemDTOS)
                .paymentMethod("CrEDiTcARD")
                .build();
    }

    private List<TransactionItemDTO> newTransactionItemDTOS() {
        final TransactionItemDTO item1 = newTransactionItem1();
        final TransactionItemDTO item2 = newTransactionItem2();
        List<TransactionItemDTO> itemDTOS = new ArrayList<>();
        itemDTOS.add(item1);
        itemDTOS.add(item2);
        return itemDTOS;
    }

    private TransactionItemDTO newTransactionItem1() {
        return TransactionItemDTO.builder()
                    .id("TransactionItemId")
                    .itemDescription("Sac Kavurma")
                    .price(BigDecimal.valueOf(45.89))
                    .quantity(2)
                    .build();
    }

    private TransactionItemDTO newTransactionItem2() {
        return TransactionItemDTO.builder()
                    .id("TransactionItemId2")
                    .itemDescription("Sutlac")
                    .price(BigDecimal.valueOf(20))
                    .quantity(3)
                    .build();
    }

}