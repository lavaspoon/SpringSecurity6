package org.example.springsecurity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomProviderService implements AuthenticationProvider {

    private final CustomUserDetailService customUserDetailService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final SCryptPasswordEncoder sCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        //UserDetailsService로 DB에서 사용자 세부정보 조회
        CustomUserDetails user = customUserDetailService.loadUserByUsername(username);

        switch (user.getUser().getAlgolithm()) {
            case BCRYPT : return checkPassword(user, password, bCryptPasswordEncoder);
            case SCRYPT : return checkPassword(user, password, sCryptPasswordEncoder);
        }

        throw new BadCredentialsException("Bad credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private Authentication checkPassword(CustomUserDetails user, String password, PasswordEncoder encoder) {
        if(encoder.matches(password, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword(),
                    user.getAuthorities()
            );
        } else {
            throw new BadCredentialsException("Bad credentials");
        }
    }
}
