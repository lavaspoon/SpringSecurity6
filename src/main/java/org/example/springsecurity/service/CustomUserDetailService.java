package org.example.springsecurity.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.springsecurity.domain.User;
import org.example.springsecurity.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Log4j2
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public CustomUserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        User user = userRepository.findById(userid).orElseThrow(() -> new UsernameNotFoundException("사용자 계정이 없습니다."));
        CustomUserDetails userDetails = modelMapper.map(user, CustomUserDetails.class);
        return userDetails;
    }
}
