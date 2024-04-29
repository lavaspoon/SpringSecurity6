package org.example.springsecurity.service;

import lombok.RequiredArgsConstructor;
import org.example.springsecurity.domain.User;
import org.example.springsecurity.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        User user = userRepository.findById(userid).orElseThrow(() -> new UsernameNotFoundException("사용자 계정이 없습니다."));
        return new CustomUserDetails(user);
    }
}
