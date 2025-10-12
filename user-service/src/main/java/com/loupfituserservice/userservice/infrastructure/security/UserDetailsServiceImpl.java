package com.loupfituserservice.userservice.infrastructure.security;


import com.loupfituserservice.userservice.infrastructure.entity.Customer;
import com.loupfituserservice.userservice.infrastructure.entity.User;
import com.loupfituserservice.userservice.infrastructure.repository.CustomerRepository;
import com.loupfituserservice.userservice.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole().name())
                    .build();
        }

        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        return org.springframework.security.core.userdetails.User
                .withUsername(customer.getUsername())
                .password(customer.getPassword())
                .build();
    }
}
