package com.example.cms.dto;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {
	private int userId;
	private String userName;
	private String email;
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdAt;
	@LastModifiedDate
	private LocalDateTime lastModifiedAt;
	
}
