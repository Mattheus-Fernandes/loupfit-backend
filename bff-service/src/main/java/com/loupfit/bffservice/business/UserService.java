package com.loupfit.bffservice.business;

import com.loupfit.bffservice.business.dto.out.UserDTO;
import com.loupfit.bffservice.business.dto.in.UserReqDTO;
import com.loupfit.bffservice.business.dto.in.UserRoleDTO;
import com.loupfit.bffservice.business.dto.in.UsernameReqDTO;
import com.loupfit.bffservice.infrastructure.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient userClient;

    public UserDTO addUser(UserReqDTO dto) {
        return userClient.saveUser(dto);
    }

    public List<UserDTO> filterAllUsers(String token) {
        return userClient.findUser(token);
    }

    public UserDTO filterByUsername(String token, String username) {
        return userClient.getUserByUsername(token, username);
    }

    public UserDTO removeUser(String token, Long id) {
        return  userClient.deleteUser(token, id);
    }

    public UserDTO editUser(String token, Long id, UserReqDTO dto) {
        return userClient.updateUser(token, id, dto);

    }

    public UserDTO editRoleUser(String token, Long id, UserRoleDTO dto) {
        return userClient.updateRoleUser(token, id, dto);
    }

    public UserDTO editUsername(String token, Long id, UsernameReqDTO dto) {
        return userClient.updateUsernameUser(token, id, dto);
    }

}
