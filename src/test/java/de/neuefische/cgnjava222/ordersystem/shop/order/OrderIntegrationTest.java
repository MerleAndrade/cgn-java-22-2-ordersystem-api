package de.neuefische.cgnjava222.ordersystem.shop.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

public class OrderIntegrationTest {

    @Autowired
    MockMvc mockMvc;


    @Test
    void postOrders() throws Exception {
        //given
        mockMvc
                //when
                .perform(
                        post("/api/orders/4711")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        [1,3,4]
                                        """)
                )
                .andExpect(status().is(200))
                .andExpect(content().string(""));
        mockMvc
                //when
                .perform(
                        get("/api/orders/4711")
                )

                //then
                .andExpect(status().is(200))
                .andExpect(content().json("""
                        {"id":4711,"products":[{"id":1,"name":"Apfel"},{"id":3,"name":"Zitrone"},{"id":4,"name":"Mandarine"}]}
                        """));
    }

    @Test
    void getallOrders() throws Exception {
        //given
        mockMvc
                //when
                .perform(
                        get("/api/orders")
                )

                //then
                .andExpect(status().is(200))
                .andExpect(content().json("""
                     [{"id":4711,"products":[{"id":1,"name":"Apfel"},{"id":3,"name":"Zitrone"},{"id":4,"name":"Mandarine"}]}]
                        """));
    }

    }
