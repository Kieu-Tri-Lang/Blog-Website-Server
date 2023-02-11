package com.kieutrilang.blogwebsite.user;

import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.kieutrilang.blogwebsite.file.File;
import com.kieutrilang.blogwebsite.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private File getUpdatePicture(File currentPicture, File updatedPicture) {
        if (currentPicture != null) {
            currentPicture.setData(updatedPicture.getData());
            return currentPicture;
        }
        return File.builder()
                .data(updatedPicture.getData())
                .name(UUID.randomUUID().toString())
                .type(updatedPicture.getType())
                .build();
    }

    private Collection<SimpleGrantedAuthority> getGrantedAuthorities(User user) {
        return Arrays.asList(new SimpleGrantedAuthority(user.getRole().toString()));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Not found user: " + email));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(),
                getGrantedAuthorities(user));
    }

    @Override
    public User createUser(User newUser) {

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        return userRepository.save(newUser);
    }

    @Override
    public Page<User> getAllUser(Pageable pageable) {

        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Not found user: " + email));

        return user;
    }

    @Override
    public User updateUser(User updatedUser) {

        User user = getUserByEmail(updatedUser.getEmail());

        return user;

    }

    @Override
    public void updateCoverPicture(String email, File updatedCoverPicture) {
        if (updatedCoverPicture != null) {
            User user = getUserByEmail(email);

            user.setCoverPicture(getUpdatePicture(user.getCoverPicture(), updatedCoverPicture));

            userRepository.save(user);
        }

    }

    @Override
    public void updateProfilePicture(String email, File updatedProfilePicture) {
        if (updatedProfilePicture != null) {
            User user = getUserByEmail(email);

            user.setProfilePicture(getUpdatePicture(user.getProfilePicture(), updatedProfilePicture));

            userRepository.save(user);
        }

    }

}
