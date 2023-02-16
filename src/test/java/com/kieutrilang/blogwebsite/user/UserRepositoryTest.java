package com.kieutrilang.blogwebsite.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private final Pageable pageable = PageRequest.of(0,3, Sort.by(Sort.Direction.ASC,"id"));

    private final List<User> userList = Arrays.asList(
            User.builder().username("john smith").email("johnSmith@gmail.com").build(),
            User.builder().username("john alex").email("johnAlex@gmail.com").build(),
            User.builder().username("alex modern").email("alexJordan@gmail.com").build(),
            User.builder().username("kieu tri lang").email("kieutrilang@gmail.com").build(),
            User.builder().username("watch television").email("television@gmail.com").build(),
            User.builder().username("john king").email("johnking@gmail.com").build(),
            User.builder().username("black white").email("blackColor@gmail.com").build(),
            User.builder().username("white pick").email("pickme@gmail.com").build(),
            User.builder().username("pink grey").email("whiteSee@gmail.com").build(),
            User.builder().username("pink sea").email("johnAlex@gmail.com").build(),
            User.builder().username("kind gray").email("kindfully@gmail.com").build(),
            User.builder().username("beauty sea").email("graceSee@gmail.com").build(),
            User.builder().username("light thunder").email("thunderSee@gmail.com").build(),
            User.builder().username("gonna king").email("kingstayheer@gmail.com").build()
    );

    @Test
    void findAll_ReturnPaginatedListOfUsers() {
        // given
        userRepository.saveAll(userList);
        // when
        Page<User> users = userRepository.findAll(pageable);
        // then
        Integer pageExpected = userList.size() / pageable.getPageSize();
        Integer pageActual = users.getTotalPages();

        assertEquals(pageExpected,pageActual);
    }

    @Test
    void findByUsernameContaining_ReturnPaginatedListOfUsersContainingUsername() {
        // given

        // when

        // then

    }

    @Test
    void findByEmail_ReturnUserHasEmail() {
        // given

        // when

        // then
    }

    @Test
    void findByUsername_ReturnUserHasExactlyUsername() {
        // given

        // when

        // then
    }
}