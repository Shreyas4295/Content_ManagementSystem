package com.example.cms.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cms.dto.UserRequest;
import com.example.cms.dto.UserResponse;
import com.example.cms.exception.UserAlreadyExistByEmailException;
import com.example.cms.model.User;
import com.example.cms.repository.UserRepository;
import com.example.cms.service.UserService;
import com.example.cms.utility.ResponseStructure;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	
	private UserRepository repository;
	private ResponseStructure<UserResponse> resStructure;
	private PasswordEncoder passwordEncoder;

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(UserRequest userRequest) {
		if(repository.existsByEmail(userRequest.getEmail()))
			throw new UserAlreadyExistByEmailException("Failed to register user");
		
		User user = repository.save(mapToUserEntity(userRequest, new User()));
		
		return ResponseEntity.ok(resStructure.setBody(mapToUserResponse(user))
				                             .setMessage("User registered successfully")
				                             .setStatusCode(HttpStatus.OK.value()));
	}

	private UserResponse mapToUserResponse(User user) {
		return UserResponse.builder()
				           .userId(user.getUserId())
				           .userName(user.getUserName())
				           .email(user.getEmail())
				           .createdAt(user.getCreatedAt())
				           .lastModifiedAt(user.getLastModifiedAt())
				           .build();
	}

	private User mapToUserEntity(UserRequest userRequest, User user) {
		user.setUserName(userRequest.getUserName());
		user.setEmail(userRequest.getEmail());
		user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		return user;
	}

}
