package com.ps.security.auth.client.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FileUploadControllerTest {
	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	@Test
	public void testFileUpload() throws  Exception {
		 this.mockMvc.perform(MockMvcRequestBuilders.fileUpload("/file")
				.file(new MockMultipartFile("file", "clientfilename.log","multipart/form-data","file context".getBytes("UTF-8"))))
				.andExpect(MockMvcResultMatchers.status().isOk());
//		
//		this.mockMvc.perform(MockMvcRequestBuilders.fileUpload("/file")
//				.file(new MockMultipartFile("file.txt", "test.log", "multipart/form-data", "hello upload".getBytes("UTF-8"))))
//				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	@Test
	public void testFileUploadWhenClientError() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/file"))
		.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
}
