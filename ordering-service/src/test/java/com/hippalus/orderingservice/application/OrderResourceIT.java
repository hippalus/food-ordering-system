package com.hippalus.orderingservice.application;

import com.hippalus.orderingservice.OrderingServiceApplication;
import com.hippalus.orderingservice.application.request.CreateOrderRequest;
import com.hippalus.orderingservice.application.response.OrderItemResponse;
import com.hippalus.orderingservice.application.response.OrderResponse;
import com.hippalus.orderingservice.application.web.OrderResource;
import com.hippalus.orderingservice.domain.service.IOrderService;
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
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {OrderingServiceApplication.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
@AutoConfigureMockMvc
public class OrderResourceIT {

    private static final String ORDER_URI = "/orders/create";

    @Autowired
    private OrderResource orderResource;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private IOrderService orderService;


    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void contextLoads() {
        assertThat(orderResource).isNotNull();
        assertThat(orderService).isNotNull();
    }

    @DisplayName("When an order creation request is received, then order should be saved and then the order details response should be given")
    @Test
    void createOrder() throws Exception {
        //given:
        Set<OrderItemResponse> items = new HashSet<>();
        items.add(new OrderItemResponse("lahmacun", 3, BigDecimal.TEN));
        items.add(new OrderItemResponse("ayran", 1, BigDecimal.valueOf(2.99)));
        var request = new CreateOrderRequest("Habip", items);
        final var requestBody = DomainModelMapper.writeToJsonString(request);

        //when:
        var mvcResult = mvc.perform(MockMvcRequestBuilders.post(ORDER_URI)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody))
                .andReturn();

        //then:
        var status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(201);

        var content = mvcResult.getResponse().getContentAsString();
        final var response = DomainModelMapper.readValue(content, OrderResponse.class);
        assertThat(response).isNotNull();
    }

    @DisplayName("when an invalid order create request received  then error message details should be return")
    @Test
    void createInvalidOrder() throws Exception {
        //given:
        Set<OrderItemResponse> items = new HashSet<>();
        items.add(new OrderItemResponse("lahmacun", 3, BigDecimal.TEN));
        items.add(new OrderItemResponse("ayran", 1, BigDecimal.valueOf(2.99)));
        var request = new CreateOrderRequest("", items);
        final var requestBody = DomainModelMapper.writeToJsonString(request);

        //when:
        var mvcResult = mvc.perform(MockMvcRequestBuilders.post(ORDER_URI)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody))
                .andReturn();

        //then:
        var status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(200);

        var content = mvcResult.getResponse().getContentAsString();
        assertThat(content).isNotNull();
        System.err.println(content);
    }

    @Test
    void createInvalidOrder2() throws Exception {
        //given:
        var request = new CreateOrderRequest();
        final var requestBody = DomainModelMapper.writeToJsonString(request);

        //when:
        var mvcResult = mvc.perform(MockMvcRequestBuilders.post(ORDER_URI)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody))
                .andReturn();

        //then:
        var status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(200);

        var content = mvcResult.getResponse().getContentAsString();
        assertThat(content).isNotNull();
        System.err.println(content);
    }


}