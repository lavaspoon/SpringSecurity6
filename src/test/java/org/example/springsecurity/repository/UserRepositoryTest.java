package org.example.springsecurity.repository;

import org.example.springsecurity.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("findById() : 유저 정보를 조회한다.")
    public void 유저_정보_조회() throws Exception {
        //given
        User user = User.builder()
                .userid("lava")
                .password("1234")
                .usernm("lee")
                .build();

        userRepository.save(user);
        //when
        User findOne = userRepository.findByUserid("lava").orElseThrow();
        //then
        System.out.println("findOne = " + findOne);

    }
}