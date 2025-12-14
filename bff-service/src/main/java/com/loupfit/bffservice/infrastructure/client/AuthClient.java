package com.loupfit.bffservice.infrastructure.client;

import com.loupfit.bffservice.business.dto.in.LoginReqDTO;
import com.loupfit.bffservice.infrastructure.client.config.UserClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", url = "${user.url}", configuration = UserClientConfig.class)
public interface AuthClient {

    @PostMapping("/login")
    String doLogin(@RequestBody LoginReqDTO dto);

}
