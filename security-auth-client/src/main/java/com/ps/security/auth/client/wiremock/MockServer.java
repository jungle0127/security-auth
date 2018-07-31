package com.ps.security.auth.client.wiremock;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import com.github.tomakehurst.wiremock.client.WireMock;

public class MockServer {

	public static void main(String[] args) throws IOException {
		WireMock.configureFor(8062);
		WireMock.removeAllMappings();
		
		ClassPathResource classPathResource = new ClassPathResource("mock/response/user.response.txt");
		
		String content = FileUtils.readFileToString(classPathResource.getFile(),"UTF-8");
		WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo("/user/1")).willReturn(WireMock.aResponse().withBody(content).withStatus(200)));
		
	}

}
