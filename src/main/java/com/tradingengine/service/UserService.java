package com.tradingengine.service;

import com.tradingengine.dto.request.UserRequest;
import com.tradingengine.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse registerUser(UserRequest request);

    UserResponse getUserById(Long id);

    List<UserResponse> getAllUsers();
}