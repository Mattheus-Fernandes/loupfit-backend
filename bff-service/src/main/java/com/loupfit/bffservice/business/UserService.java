package com.loupfit.bffservice.business;

import com.loupfit.bffservice.business.record.user.in.UserRequest;
import com.loupfit.bffservice.business.record.user.in.UserRoleRequest;
import com.loupfit.bffservice.business.record.user.in.UsernameRequest;
import com.loupfit.bffservice.business.record.user.out.UserResponse;
import com.loupfit.bffservice.infrastructure.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient userClient;

    public UserResponse addUser(UserRequest request) {
        return userClient.saveUser(request);
    }

    public List<UserResponse> filterAllUsers(String token) {
        return userClient.findUser(token);
    }

    public UserResponse filterByUsername(String token, String username) {
        return userClient.getUserByUsername(token, username);
    }

    public UserResponse removeUser(String token, Long id) {
        return  userClient.deleteUser(token, id);
    }

    public UserResponse editUser(String token, Long id, UserRequest request) {
        return userClient.updateUser(token, id, request);
    }

    public UserResponse editRoleUser(String token, Long id, UserRoleRequest request) {
        return userClient.updateRoleUser(token, id, request);
    }

    public UserResponse editUsername(String token, Long id, UsernameRequest request) {
        return userClient.updateUsernameUser(token, id, request);
    }

}
