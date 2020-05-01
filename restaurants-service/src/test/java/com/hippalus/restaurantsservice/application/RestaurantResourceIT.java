package com.hippalus.restaurantsservice.application;

import com.hippalus.restaurantsservice.RestaurantsServiceApplication;
import com.hippalus.restaurantsservice.application.request.CreateRestaurantRequest;
import com.hippalus.restaurantsservice.application.web.RestaurantResource;
import com.hippalus.restaurantsservice.application.response.MenuResponse;
import com.hippalus.restaurantsservice.application.response.ProductResponse;
import com.hippalus.restaurantsservice.application.response.RestaurantResponse;
import com.hippalus.restaurantsservice.domain.models.*;
import com.hippalus.restaurantsservice.domain.repository.RestaurantRepository;
import com.hippalus.restaurantsservice.domain.service.IRestaurantService;
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
@SpringBootTest(classes = {RestaurantsServiceApplication.class},
        webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
@AutoConfigureMockMvc
public class RestaurantResourceIT {

    private static final String GET_ALL_RESTAURANTS_URI = "/restaurants/getall";
    private static final String CREATE_RESTAURANT_URI = "/restaurants/create";

    @Autowired
    private RestaurantResource restaurantResource;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private IRestaurantService restaurantService;
    @Autowired
    private RestaurantRepository repository;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void contextLoads() {
        assertThat(restaurantResource).isNotNull();
        assertThat(restaurantService).isNotNull();
    }
    @DisplayName("When an restaurant creation request is received, then restaurant should be saved and then the restaurant details response should be given")
    @Test
    void createRestaurant() throws Exception {
        //given:
        var createRestaurantReq=newRestaurant(newMenus(newProductRe()));
        final var requestBody = DomainModelMapper.writeToJsonString(createRestaurantReq);

        //when:
        var mvcResult = mvc.perform(MockMvcRequestBuilders.post(CREATE_RESTAURANT_URI)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody))
                .andReturn();

        //then:
        var status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(201);

        var content = mvcResult.getResponse().getContentAsString();
        final var response = DomainModelMapper.readValue(content, RestaurantResponse.class);
        assertThat(response).isNotNull();
    }
    @Test
    void getAllRestaurants() throws Exception {
        //given:
        var products = newProduct();
        var menus = newMenu(products);
        var newRest= newRestaurants(menus);
        repository.save(newRest);
        var response= new RestaurantResponse("1","Dayinin Yeri",newMenus(newProductRe()));
        List<RestaurantResponse> expected=new ArrayList<>();
        expected.add(response);
        final var expectedContent = DomainModelMapper.writeToJsonString(expected);

        //when:
        var mvcResult = mvc.perform(MockMvcRequestBuilders.get(GET_ALL_RESTAURANTS_URI))
                .andReturn();

        //then:
        var status =mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(200);

        var content = mvcResult.getResponse().getContentAsString();
        assertThat(content).isNotEmpty();
        System.err.println(content);
        System.err.println(expectedContent);
    }



    private CreateRestaurantRequest newRestaurant(Set<MenuResponse> menus) {
        return new CreateRestaurantRequest("Dayinin Yeri",menus);
    }

    private Set<MenuResponse> newMenus(Set<ProductResponse> products) {
        Set<MenuResponse> menus=new HashSet<>();
        menus.add(new MenuResponse("1","Izgara Cesitleri",products));
        return menus;
    }

    private Set<ProductResponse> newProductRe() {
        Set<ProductResponse> products=new HashSet<>();
        products.add(new ProductResponse( "1","Kebap", BigDecimal.valueOf(30)));
        return products;
    }

    private Restaurant newRestaurants(Set<Menu> menus) {
        return new Restaurant(new RestaurantId("1"),"Dayinin Yeri",menus);
    }

    private Set<Menu> newMenu(Set<Product> products) {
        Set<Menu> menus=new HashSet<>();
        menus.add(new Menu("Izgara Cesitleri",products));
        return menus;
    }

    private Set<Product> newProduct() {
        Set<Product> products=new HashSet<>();
        products.add(new Product("Kebap", BigDecimal.valueOf(30)));
        return products;
    }


}
