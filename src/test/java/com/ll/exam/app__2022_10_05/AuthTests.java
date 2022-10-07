package com.ll.exam.app__2022_10_05;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class AuthTests {

    @Autowired
    private MockMvc mvc;


    @Test
    @DisplayName("POST /member/login은 로그인 처리 URL이다.")
    void t1() throws Exception {

        //when
        ResultActions resultActions = mvc
                .perform(
                        post("/member/login")
                                .content("""
                                        {
                                        "username": "appuser1",
                                        "password": "app1234"
                                        }
                        
                                        """.stripIndent())
                                .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
                )
                .andDo(print());


        //then
        resultActions
                .andExpect(status().is2xxSuccessful());

    }


    @Test
    @DisplayName("POST /member/login으로 올바른 username과 password 데이터를 넘기면 JWT키를 발급해준다.")
    void v2() throws Exception {

        //when
        ResultActions resultActions = mvc
                .perform(
                        post("/member/login")
                                .content("""
                                        {
                                            "username": "user1",
                                            "password": "1234"
                                        }
                                        """.stripIndent())
                                .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
                )
                .andDo(print());

        //then
        resultActions.andExpect(status().is2xxSuccessful());

        MvcResult mvcResult = resultActions.andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        String authentication = response.getHeader("Authentication");

        assertThat(authentication).isNotEmpty();


    }


    @Test
    @DisplayName("POST /member/login 호출시 username 혹은 password 누락하면 400")
    void t3() throws Exception {

        //when
        ResultActions resultActions = mvc
                .perform(
                        post("/member/login")
                                .content("""
                                        {
                                            "username" : "",
                                            "password": "1234"
                                        }                                        
                                        """.stripIndent())
                                .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))

                )
                .andDo(print());


        //then
        resultActions
                .andExpect(status().is4xxClientError());

        resultActions = mvc
                .perform(
                        post("/member/login")
                                .content("""
                                        {
                                            "username": "user1",
                                            "password": ""
                                        }
                                                                               
                                        """.stripIndent())
                                .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
                )
                .andDo(print());

        //then
        resultActions
                .andExpect(status().is4xxClientError());

    }


    @Test
    @DisplayName("POST /member/login 호출시 올바르지 않은 username 혹은 password 입력시 400")
    void t4() throws Exception {

        //when
        ResultActions resultActions = mvc
                .perform(
                        post("/member/login")
                                .content("""
                                        {
                                            "username": "user3",
                                            "password": "1234"
                                        }
                                        """.stripIndent())
                                .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
                )
                .andDo(print());


        //then
        resultActions
                .andExpect(status().is4xxClientError());

        resultActions = mvc
                .perform(
                        post("/member/login")
                                .content("""
                                                                                
                                        {
                                            "username": "user1",
                                            "password": "12345"
                                        }
                                        """.stripIndent())
                                .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
                )
                .andDo(print());


        //then
        resultActions
                .andExpect(status().is4xxClientError());

    }



}
