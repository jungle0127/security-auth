package com.ps.security.auth.client.web.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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
		String rsp = this.mockMvc
				.perform(MockMvcRequestBuilders.get("/user").param("userName", "PS")
						.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3)).andReturn().getResponse()
				.getContentAsString();
		System.out.println(rsp);
	}

	@Test
	public void testMultiParamQuery() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/usr").param("userName", "ps").param("password", "jerry")
						.param("page", "3").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
	}

	@Test
	public void testGetUserInfo() throws Exception {
		String result = this.mockMvc
				.perform(MockMvcRequestBuilders.get("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("ps")).andReturn().getResponse()
				.getContentAsString();
		System.out.println(result);
	}

	@Test
	public void testGetUserInfoWhenInvalidParam() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/user/a").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}

	@Test
	public void testCreateUserWhenSuccess() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"userName\":\"ps\",\"password\":null}"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
	}

	@Test
	public void testUpdateUserWhenSuccess() throws Exception {
		Date date = new Date(
				LocalDateTime.now().plusYears(1L).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		this.mockMvc
				.perform(MockMvcRequestBuilders.put("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"id\":\"1\" ,\"userName\":\"ps\",\"password\":null,\"birthday\":"
								+ String.valueOf(date.getTime()) + "}"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
	}
	
	@Test
	public void testDeleteUserWhenSuccess() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
