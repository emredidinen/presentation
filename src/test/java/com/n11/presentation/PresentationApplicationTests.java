package com.n11.presentation;

import com.n11.presentation.controller.PresentationController;
import com.n11.presentation.dto.Schedule;
import com.n11.presentation.enums.Success;
import com.n11.presentation.service.PresentationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = PresentationApplication.class)
@AutoConfigureMockMvc
public class PresentationApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PresentationController controller;

    @MockBean
    private PresentationService service;

    @Test
    public void contextLoads() throws Exception {

        assertThat(controller).isNotNull();

        this.mockMvc.perform(get("/defaultPresentation"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(Success.DEFAULT_DATA_ADDED.getType())));


    }

}
