package com.api.stockgalary.controller;

import java.util.NoSuchElementException;

import javax.servlet.ServletException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
final class ControllerHandlerException extends DefaultResponseErrorHandler {

	ControllerHandlerException() {}

	@ExceptionHandler(ServletException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public String requestNotFound() {
		return "[NOT FOUND . 404] -> SORRY BABY!!!";
	}

	@ExceptionHandler({ HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String invalidRequest() {
		return "[BAD REQUEST . 400] -> Invalid or incorrect resquisition!!!";
	}

	@ExceptionHandler({ EmptyResultDataAccessException.class, NoSuchElementException.class })
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String cantFoundEmployee() {
		return "[INTERNAL SERVER ERROR . 500] -> Couldn't find what you want!!!";
	}
}
