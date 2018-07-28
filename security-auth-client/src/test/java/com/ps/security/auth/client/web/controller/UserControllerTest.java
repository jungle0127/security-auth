package com.ps.security.auth.client.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	@Test
	public void testWhenQuerySuccess() throws Exception {
		String rsp = this.mockMvc.perform(MockMvcRequestBuilders.get("/user")
				.param("userName", "PS")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
		.andReturn().getResponse().getContentAsString();
		System.out.println(rsp);
	}
	@Test
	public void testMultiParamQuery() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/usr")
				.param("userName", "ps")
				.param("password", "jerry")
				.param("page", "3")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
	}
	
	@Test
	public void testGetUserInfo() throws Exception {
		String result = this.mockMvc.perform(MockMvcRequestBuilders.get("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("ps"))
		.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	@Test
	public void testGetUserInfoWhenInvalidParam() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/user/a").contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
}
