package com.loupfit.bffservice.infrastructure.client;

import com.loupfit.bffservice.business.record.LoginRequest;
import com.loupfit.bffservice.infrastructure.client.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", url = "${user.url}", configuration = FeignConfig.class)
public interface AuthClient {

    @PostMapping("/login")
    String doLogin(@RequestBody LoginRequest request);

}
