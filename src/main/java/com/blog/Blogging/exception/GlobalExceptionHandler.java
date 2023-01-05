package com.blog.Blogging.exception;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.Blogging.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse>resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>>methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
		Map<String, String> errorResp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldValue = ((FieldError)error).getField();
			String message = (error).getDefaultMessage();
			errorResp.put(fieldValue, message);
		});
		
		return new ResponseEntity<Map<String,String>>(errorResp,HttpStatus.BAD_REQUEST);
	}

}
