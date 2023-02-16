package com.kieutrilang.blogwebsite.user;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

    private User getUserByEmailFromRepo(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Not found user: " + email));
    }

    private UserResponse mapToDto(User user) {
        return UserResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .numberOfFollower(countList(user.getFollowers()))
                .numberOfFollowing(countList(user.getFollowing()))
                .numberOfPost(countList(user.getPosts()))
                .profilePictureLink("")
                .coverPictureLink("")
                .build();
    }

    private int countList(List<?> list) {
        return list == null ? 0 : list.size();

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
    public void createUser(UserRequest userRequest) {

        User user = User.builder()
                .id(userRequest.getId())
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .gender(userRequest.getGender()).build();

        mapToDto(userRepository.save(user));
    }

    @Override
    public Page<UserResponse> getAllUser(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);

        return new PageImpl<>(
                users.getContent().stream().map(this::mapToDto).collect(Collectors.toList()),
                users.getPageable(),
                users.getTotalElements());
    }

    @Override
    public Page<UserResponse> getUserByUsername(String username, Pageable pageable) {
        Page<User> users = userRepository.findByUsernameContaining(username,pageable);

        return new PageImpl<>(
                users.getContent().stream().map(this::mapToDto).collect(Collectors.toList()),
                users.getPageable(),
                users.getTotalElements());
    }

    @Override
    public UserResponse getPrivateInfoUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Not found user: "+username));
        return mapToDto(user);
    }

    @Override
    public UserResponse getUserByEmail(String email) {

        return mapToDto(getUserByEmailFromRepo(email));
    }

    @Override
    public UserResponse updateUser(UserRequest updatedUser) {

        User user = getUserByEmailFromRepo(updatedUser.getEmail());

        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        user.setGender(updatedUser.getGender());

        return mapToDto(userRepository.save(user));

    }

    @Override
    public void updateCoverPictureUser(String email, File updatedCoverPicture) {
        if (updatedCoverPicture != null) {
            User user = getUserByEmailFromRepo(email);

            user.setCoverPicture(getUpdatePicture(user.getCoverPicture(), updatedCoverPicture));

            userRepository.save(user);
        }

    }

    @Override
    public void updateProfilePictureUser(String email, File updatedProfilePicture) {

        if (updatedProfilePicture != null) {
            User user = getUserByEmailFromRepo(email);

            user.setProfilePicture(getUpdatePicture(user.getProfilePicture(), updatedProfilePicture));

            userRepository.save(user);
        }

    }

}
