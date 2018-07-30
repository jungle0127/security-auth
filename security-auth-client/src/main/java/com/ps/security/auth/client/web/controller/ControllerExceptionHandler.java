package com.ps.security.auth.client.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ps.security.auth.client.exception.UserNotExistException;

@ControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler(UserNotExistException.class)
	@ResponseBody
	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
	Map<String, Object> handleUserNotExistException(UserNotExistException ex){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("id", ex.getUserId());
		resultMap.put("message", ex.getMessage());
		return resultMap;
	}
}
