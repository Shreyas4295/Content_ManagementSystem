package com.example.cms.utility;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Component
public class ErrorStructure<T> {
	private int statusCode;
	private String message;
	private T rootCause;
	
	public ErrorStructure<T> setStatusCode(int statusCode) {
		this.statusCode = statusCode;
		return this;
	}
	public ErrorStructure<T> setMessage(String message) {
		this.message = message;
		return this;
	}
	public ErrorStructure<T> setRootCause(T rootCause) {
		this.rootCause = rootCause;
		return this;
	}
	
	
}
